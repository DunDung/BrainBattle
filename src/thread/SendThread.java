package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;


public class SendThread extends Thread {

	private Socket socket;
	private PrintWriter sendWriter;
	private MainFrame mainFrame;


	public SendThread(MainFrame mainFrame) { //������ �߰�
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();
		try {
			sendWriter = new PrintWriter(socket.getOutputStream());  //setSocket���� �ʱ�ȭ�� ������ �ƿ�ǲ��Ʈ���� �����Ѵ�.
			while(true) {
				if(!mainFrame.getScore().getMyNickName().equals("")) {
					System.out.println(mainFrame.getScore().getMyNickName());
					sendWriter.println(mainFrame.getScore().getMyNickName());
					
					break;
				}
				else
					continue;
			}
			
			mainFrame.getChat().getTf().addActionListener(new SendEvent());//enterŰ�� ���� �� �̺�Ʈ
			mainFrame.getChat().getEnter().addActionListener(new SendEvent()); //���۹�ư�� ���� �� �̺�Ʈ
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {  //client�������� �ʱ�ȭ�ϴ� �뵵.
		this.socket = socket;
	}

	class SendEvent implements ActionListener{ //enterŰ�� ���� ���� "����"��ư�� ������ ���� �̺�Ʈ Ŭ����
		@Override
		public void actionPerformed(ActionEvent e) { 
			
			String 	sendString =mainFrame.getChat().getTf().getText(); //�ý�Ʈ �ʵ忡 �ִ� ���ڿ��� sendString�� �����Ѵ�.
			sendWriter.println(mainFrame.getScore().getMyNickName() +" :"+ sendString ); //������ �ƿ�ǲ��Ʈ���� sendString�� ������.
			mainFrame.getChat().getTa().append(mainFrame.getScore().getMyNickName() +" :"+ sendString +"\n");  //JTextArea�� sendString�� �߰��Ѵ�.
			mainFrame.getChat().getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
			sendWriter.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.
			mainFrame.getChat().setVisible(true);  //chat�г��� ȭ�鿡 �����ش�.
		}
	}
}