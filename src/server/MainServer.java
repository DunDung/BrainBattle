package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import client.MainServer2;
import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {

	public static MainFrame mainFrame; //MainFrmae 
	public static IntroFrame introFrame; //introFrmae
	public static BufferedReader reader;
	public static PrintWriter writer;

	public static void main(String [] args) throws IOException {
		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {
			serverSocket = new ServerSocket(9876); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new PrintWriter(clientSocket.getOutputStream(),true);
//
//			SendThread se_thread = new SendThread(mainFrame);
//			ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
//			re_thread.setSocket(clientSocket);
//
//			se_thread.setSocket(clientSocket);
//			se_thread.start();
//			re_thread.start();

		}catch(IOException e){
			e.printStackTrace();
		}
		mainFrame = new MainFrame(); //mainFrmae ��ü ����
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü ����
		introFrame.getIpField().setText("IP�Է� ����������.");

		
	}
	
	public static String data(String str) throws IOException {
		writer.println(str);
		return reader.readLine();
	}
}
