package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ProgressGameThread;
import thread.ReceiveThread;
import thread.SendThread;

public class Client {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;

	public static void main(String [] args) throws UnknownHostException, InterruptedException {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü�� �����Ͽ� ����ڷκ��� ip�� �г����� �Է� �޴´�.

		try {
			Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(),9876);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
			SendThread seThread = new SendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread nickThread = new NickNameThread(mainFrame, clientSocket);
			ProgressGameThread gameThread = new ProgressGameThread(mainFrame, clientSocket);
			
			nickThread.start();
			nickThread.join();

			gameThread.start();
			gameThread.join();
			
			seThread.start();
			reThread.start();
			

		}catch(IOException e){
			e.printStackTrace();
		}


	}

}
