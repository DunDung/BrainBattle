package thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;


public class SendTest extends Thread {

	private Socket socket;
	private DataOutputStream dataOut;
	private MainFrame mainFrame;


	public SendTest(MainFrame mainFrame) { //������ �߰�
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		super.run();
		try {
			while(true) {
				if(mainFrame.getScore().getYourNickName().equals("") == true && mainFrame.getNickName()!= null) {
					dataOut =  new DataOutputStream(socket.getOutputStream());  //setSocket���� �ʱ�ȭ�� ������ �ƿ�ǲ��Ʈ���� �����Ѵ�.
					dataOut.writeUTF(mainFrame.getNickName());
					break;
				}
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setSocket(Socket socket) {  //client�������� �ʱ�ȭ�ϴ� �뵵.
		this.socket = socket;
	}
}


