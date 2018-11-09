package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.MainFrame;

public class ReadyThread extends Thread{
	private MainFrame mainFrame;
	private Socket socket;
	
	public ReadyThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			mainFrame.getChat().taAdd("System :�غ� �Ϸ�Ǹ� Ready��ư�� �����ּ���.\n");
			
			while(true) {
				Thread.sleep(100);
				if(mainFrame.getGame().getPlayOk()) {
					writer.println("ready");
					break;
				}
				else
					continue;
			}
			String ready = buf.readLine();
			if(ready.equals("ready")) {
				mainFrame.getChat().taAdd("System :ä�ñ�� Ȱ��ȭ\n");
				mainFrame.getGame().getReadyOk().setVisible(false);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
