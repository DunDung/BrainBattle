package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class SendThread extends Thread {

	private Socket socket;
	private ChattingPanel chat; //�߰�

	public SendThread(ChattingPanel chat) { //������ �߰�
		this.chat = chat;
	}

	@Override
	public void run() {
		super.run();
		try {

		//	BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); //Ű���� �Է� ���/�Է�
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream());  //setSocket���� �ʱ�ȭ�� ������ �ƿ�ǲ��Ʈ���� �����Ѵ�.
			
			//sendString = buf.readLine(); //�� �κп� �����ؼ� �ȵƾ���
		
				this.chat.tf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField t = (JTextField)e.getSource();
						String sendString =t.getText(); //�ý�Ʈ �ʵ忡 �ִ� ���ڿ��� sendString�� �����Ѵ�.
						sendWriter.println(sendString); //������ �ƿ�ǲ��Ʈ���� sendString�� ������.
						chat.ta.append("�� : " + sendString +"\n");  //JTextArea�� sendString�� �߰��Ѵ�.
						t.setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
						sendWriter.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.
						chat.setVisible(true);  //chat�г��� ȭ�鿡 �����ش�.
					}
				});
			

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {  //client�������� �ʱ�ȭ�ϴ� �뵵.
		this.socket = socket;
	}

}