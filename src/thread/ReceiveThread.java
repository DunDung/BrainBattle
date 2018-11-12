package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import gui.MainFrame;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //�߰�
	private static boolean start = false;
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
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				String [] receiveArray = receiveString.split("/");
				
				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n"); //JTestArea�� �߰����ش�.
//				case "ready" :
//					if(mainFrame.getGame().getPlayOk()) {
//						writer.println("go/test");
//						mainFrame.getGame().getReadyOk().setVisible(false);
//					}
//					else
//						readyThread.start();
//				case "go" :
//					mainFrame.getGame().getReadyOk().setVisible(false);
//						
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
