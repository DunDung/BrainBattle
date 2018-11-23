package thread;

import java.io.IOException;
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
			
			mainFrame.getChat().taAdd("System :����Ǿ����ϴ�! ���� ���Ӽ����� �о��ּ���.\nSystem :�غ� �Ϸ�Ǹ� Ready��ư�� �����ּ���.\n");
			
			while(true) {
				Thread.sleep(100);
				if(mainFrame.getGame().getPlayOk()) {
					writer.println("ready/");
					mainFrame.getChat().taAdd("System :�غ�Ϸ�!\n");
					break;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
