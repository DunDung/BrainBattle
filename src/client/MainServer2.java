package client;

import java.io.IOException;
import java.net.Socket;

import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static void main(String [] args) {
		try {
			String s = "192.168.43.190"; //������� ip�� ���� ����
			Socket clientSocket =new Socket(s,8999); 
			mainFrame  = new MainFrame();
			ReceiveThread re_thread = new ReceiveThread(mainFrame.getChat());
			re_thread.setSocket(clientSocket);
			
			SendThread se_thread = new SendThread(mainFrame.getChat());
			se_thread.setSocket(clientSocket);
			re_thread.start();
			se_thread.start();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
