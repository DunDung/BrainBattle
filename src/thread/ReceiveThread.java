package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.ChattingPanel;
import gui.MainFrame;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //�߰�
	
	public ReceiveThread(MainFrame mainFrame) { //������ �߰�
		this.mainFrame = mainFrame;
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
					mainFrame.getChat().taAdd("���� ������ ������ϴ�.\n"); //������ �ȵǴµ� �������
					break;
				}
				else
					mainFrame.getChat().taAdd(receiveString+"\n"); //JTestArea�� �߰����ش�.
			}

			buf.close(); 
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) { //���� �ʱ�ȭ
		this.socket = socket;
	}

}
