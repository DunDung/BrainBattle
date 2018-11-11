package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;
import thread.ChatSendThread;
import thread.GameThread;

public class Client {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;

	public static void main(String [] args) throws UnknownHostException, InterruptedException {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü�� �����Ͽ� ����ڷκ��� ip�� �г����� �Է� �޴´�.

		try {
			Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(),9876);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread nickThread = new NickNameThread(mainFrame, clientSocket);
			ReadyThread readyThread = new ReadyThread(mainFrame, clientSocket);
			GameThread gameThread = new GameThread(mainFrame, clientSocket);
			GoalScoreThread setScoreThread = new GoalScoreThread(mainFrame, clientSocket);
			
			nickThread.start();
			nickThread.join();
			
			readyThread.start();
			readyThread.join();
			
			setScoreThread.start();
			setScoreThread.join();
			
			chatThread.start();
			reThread.start();
			
			gameThread.start();
			
			

		}catch(IOException e){
			e.printStackTrace();
		}


	}

}
