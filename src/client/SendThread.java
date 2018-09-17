package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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


			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); //Ű���� �Է� ���/�Է�
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream()); 
			
			//sendString = buf.readLine(); //���������� ���ܵα�
		
				this.chat.tf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField t = (JTextField)e.getSource();
						String sendString =t.getText();
						sendWriter.println(sendString);
						chat.ta.append("�� : "+sendString ); 
						t.setText("");
						sendWriter.flush();
						chat.setVisible(true); 
					}
				});
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}


