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
		mainFrame = new MainFrame(); //mainFrmae 객체 생성
		introFrame = new IntroFrame(mainFrame); //introFrame 객체 생성
		introFrame.getIpField().setText("IP입력 하지마세요.");

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
	//	Client.ip = InetAddress.getLocalHost().getHostAddress(); //클라이언트의 ip를 서버pc의 ip로 초기화
		try {

			serverSocket = new ServerSocket(9876); //서버소켓을 포트번호로 받아서 초기화 한다.
			clientSocket = serverSocket.accept();  //클라이언트소켓은 서버소켓에 접근한 소켓으로 초기화한다.
			SendThread se_thread = new SendThread(mainFrame, clientSocket);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nick_thread = new NickNameThread(mainFrame, clientSocket);
			ProgressGameThread game_thread = new ProgressGameThread(mainFrame, clientSocket);
			
			nick_thread.start();
			nick_thread.join();			
			se_thread.start();
			re_thread.start();
		
			game_thread.start();

		}catch(IOException e){
			e.printStackTrace();
		}


	}


}
