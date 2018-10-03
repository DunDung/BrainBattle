package thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MainFrame;
import gui.ScorePanel;


public class ReceiveTest extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //�߰�
	private ScorePanel score;
	private DataInputStream dataIn;

	public ReceiveTest(MainFrame mainFrame) { //������ �߰�
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();

		try {
			//Ŭ���̾�Ʈ ������ ��ǲ��Ʈ������ Ŭ���̾�Ʈ ������ ���� ������ �޴´�.
			
			String s; //Ŭ���̾�Ʈ�� ���� ���ڿ��� �޾��� ����
			
			
			while(true) {
				if(mainFrame.getScore().getYourNickName().equals("") == true && mainFrame.getNickName()!= null) {
				dataIn = new DataInputStream(socket.getInputStream());
				s = dataIn.readUTF();
				mainFrame.getScore().setYourNickName(s);
				break;
				}

			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) { //���� �ʱ�ȭ
		this.socket = socket;
	}

}
