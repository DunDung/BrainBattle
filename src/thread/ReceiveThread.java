package thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MainFrame;
import gui.ScorePanel;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //�߰�
	private ScorePanel score;

	public ReceiveThread(MainFrame mainFrame) { //������ �߰�
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();

		try {
			//Ŭ���̾�Ʈ ������ ��ǲ��Ʈ������ Ŭ���̾�Ʈ ������ ���� ������ �޴´�.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			String receiveString; //Ŭ���̾�Ʈ�� ���� ���ڿ��� �޾��� ����
			while(true) {
				if(mainFrame.getNickName() != null) {
					String s = dataInput.readUTF();
					 mainFrame.getScore().setYourNickName(s);
					 break;
				}
			}
			
			while(true) {

				receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				mainFrame.getChat().taAdd(receiveString+"\n"); //JTestArea�� �߰����ش�.

			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) { //���� �ʱ�ȭ
		this.socket = socket;
	}

}
