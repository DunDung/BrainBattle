package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae

	public static void main(String [] args) throws IOException, InterruptedException {
		mainFrame = new MainFrame(); //mainFrmae ��ü ����
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü ����
		introFrame.getIpField().setText("IP�Է� ����������.");

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {

			serverSocket = new ServerSocket(9876); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.
			SendThread se_thread = new SendThread(mainFrame, clientSocket);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nick_thread = new NickNameThread(mainFrame, clientSocket);

			nick_thread.start();
			nick_thread.join();			
			se_thread.start();
			re_thread.start();

		}catch(IOException e){
			e.printStackTrace();
		}


	}


}
