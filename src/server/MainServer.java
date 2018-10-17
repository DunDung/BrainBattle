package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae

	public static void main(String [] args) throws IOException {

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		mainFrame = new MainFrame(); //mainFrmae ��ü ����
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü ����

		try {
			serverSocket = new ServerSocket(9191); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.


			SendThread se_thread = new SendThread(mainFrame);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
			re_thread.setSocket(clientSocket);

			se_thread.setSocket(clientSocket);
			MainFrame.score.setMyNickName("����");
			se_thread.start();
			re_thread.start();


		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
