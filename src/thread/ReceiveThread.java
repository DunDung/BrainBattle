package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import gui.MainFrame;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //�߰�

	public ReceiveThread(MainFrame mainFrame, Socket socket) { //������ �߰�
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();

		try {
			//Ŭ���̾�Ʈ ������ ��ǲ��Ʈ������ Ŭ���̾�Ʈ ������ ���� ������ �޴´�.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			//			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			String receiveString; //Ŭ���̾�Ʈ�� ���� ���ڿ��� �޾��� ����


			while(true) {

				receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				mainFrame.getChat().taAdd(receiveString+"\n"); //JTestArea�� �߰����ش�.
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
