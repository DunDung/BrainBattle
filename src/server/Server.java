package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import client.Client;
import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ProgressGameThread;
import thread.ReceiveThread;
import thread.SendThread;

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
			SendThread seThread = new SendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nickThread = new NickNameThread(mainFrame, clientSocket);
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
