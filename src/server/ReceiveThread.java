package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ReceiveThread extends Thread{
	private Socket socket;
	private ChattingPanel chat; //�߰�
	
	public ReceiveThread(ChattingPanel chat) { //������ �߰�
		this.chat = chat;
	}
	
	@Override
	public void run() {
		super.run();

		try {
			//Ŭ���̾�Ʈ ������ ��ǲ��Ʈ������ Ŭ���̾�Ʈ ������ ���� ������ �޴´�.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

			String receiveString; //Ŭ���̾�Ʈ�� ���� ���ڿ��� �޾��� ����

			while(true) {
				receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				if(receiveString == null)
				{
					chat.taAdd("���� ������ ������ϴ�.\n"); //������ �ȵǴµ� �������
					break;
				}
				else
					chat.taAdd("Ŭ���̾�Ʈ : "+receiveString+"\n"); //JTestArea�� �߰����ش�.
			}

			buf.close(); 
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
