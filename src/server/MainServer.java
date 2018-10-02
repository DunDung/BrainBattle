package server;

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
		introFrame.getIpFiedl().setText(" �г��Ӹ� �Է��ϼ���."); //������ǻ���� ��쿣 Ip�� �Է��� �ʿ䰡 �����Ƿ� introFrame�� ipField�� ���� ����

		try {
			serverSocket = new ServerSocket(8999); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.
			
			introFrame.setSocket(clientSocket);
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
