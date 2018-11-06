package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;


public class SendThread extends Thread {

	private Socket socket;
	private PrintWriter sendWriter;
	private MainFrame mainFrame;


	public SendThread(MainFrame mainFrame, Socket socket) { //������ �߰�
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();
		try {
			sendWriter = new PrintWriter(socket.getOutputStream());  //setSocket���� �ʱ�ȭ�� ������ �ƿ�ǲ��Ʈ���� �����Ѵ�.
			
			mainFrame.getChat().getTf().addActionListener(new SendEvent());//enterŰ�� ���� �� �̺�Ʈ
			mainFrame.getChat().getEnter().addActionListener(new SendEvent()); //���۹�ư�� ���� �� �̺�Ʈ
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


	class SendEvent implements ActionListener{ //enterŰ�� ���� ���� "����"��ư�� ������ ���� �̺�Ʈ Ŭ����
		@Override
		public void actionPerformed(ActionEvent e) { 
			
			String 	sendString =mainFrame.getChat().getTf().getText(); //�ý�Ʈ �ʵ忡 �ִ� ���ڿ��� sendString�� �����Ѵ�.
			sendWriter.println("chat/"+mainFrame.getScore().getMyNickName() +" :"+ sendString ); //������ �ƿ�ǲ��Ʈ���� sendString�� ������.
			mainFrame.getChat().getTa().append(mainFrame.getScore().getMyNickName() +" :"+ sendString +"\n");  //JTextArea�� sendString�� �߰��Ѵ�.
			mainFrame.getChat().getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
			sendWriter.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.
			mainFrame.getChat().setVisible(true);  //chat�г��� ȭ�鿡 �����ش�.
		}
	}
}