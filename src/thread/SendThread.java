package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;
import gui.ChattingPanel;


public class SendThread extends Thread {

	private Socket socket;
	private ChattingPanel chat; //�߰�
	private PrintWriter sendWriter;

	public SendThread(ChattingPanel chat) { //������ �߰�
		this.chat = chat;
	}

	@Override
	public void run() {
		super.run();
		try {

			sendWriter = new PrintWriter(socket.getOutputStream());  //setSocket���� �ʱ�ȭ�� ������ �ƿ�ǲ��Ʈ���� �����Ѵ�.
			
			this.chat.getTf().addActionListener(new SendEvent());//enterŰ�� ���� �� �̺�Ʈ
			this.chat.getEnter().addActionListener(new SendEvent()); //���۹�ư�� ���� �� �̺�Ʈ

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
			String sendString =chat.getTf().getText(); //�ý�Ʈ �ʵ忡 �ִ� ���ڿ��� sendString�� �����Ѵ�.
			sendWriter.println(sendString); //������ �ƿ�ǲ��Ʈ���� sendString�� ������.
			chat.getTa().append("�� : " + sendString +"\n");  //JTextArea�� sendString�� �߰��Ѵ�.
			chat.getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
			sendWriter.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.
			chat.setVisible(true);  //chat�г��� ȭ�鿡 �����ش�.
		}
	}
}