package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ChatSendThread;
import thread.GameThread;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;

public class Server {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae

	public static void main(String [] args) throws IOException, InterruptedException {
		mainFrame = new MainFrame(); //mainFrmae ��ü ����
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü ����
		introFrame.getIpField().setText("IP�Է� ����������.");

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
	//	Client.ip = InetAddress.getLocalHost().getHostAddress(); //Ŭ���̾�Ʈ�� ip�� ����pc�� ip�� �ʱ�ȭ
		try {

			serverSocket = new ServerSocket(9876); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nickThread = new NickNameThread(mainFrame, clientSocket);
			ReadyThread readyThread = new ReadyThread(mainFrame, clientSocket);
			GameThread gameThread = new GameThread(mainFrame, clientSocket);
			GoalScoreThread setScoreThread = new GoalScoreThread(mainFrame, clientSocket);

			nickThread.start();
			nickThread.join();			

			readyThread.start();
			readyThread.join();
			
			setScoreThread.start();
			setScoreThread.join();
			
			gameThread.start();
			chatThread.start();
			reThread.start();
		
			
		}catch(IOException e){
			e.printStackTrace();
		}


	}


}
