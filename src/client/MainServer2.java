package client;

import java.io.IOException;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer2 {
	public static void main(String [] args) {
		MainFrame mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		IntroFrame intro = new IntroFrame(mainFrame); //introFrame ��ü�� �����Ͽ� ����ڷκ��� ip�� �г����� �Է� �޴´�.
		try {
				Socket clientSocket =new Socket(mainFrame.getIp(),8999);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
				ReceiveThread re_thread = new ReceiveThread(mainFrame);
				re_thread.setSocket(clientSocket);
				SendThread se_thread = new SendThread(mainFrame);
				se_thread.setSocket(clientSocket);
				re_thread.start();
				se_thread.start();
			
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
