package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import bgm.BgmControlThread;
import bgm.IntroBgm;
import gui.IntroFrame;
import gui.MainFrame;
import thread.ChatSendThread;
import thread.NickNameThread;
import thread.ReadyThread;
import thread.ReceiveThread;

public class Server {

	public static MainFrame mainFrame; 
	public static IntroFrame introFrame;
	private static IntroBgm introBgm = new IntroBgm();
	
	public static void main(String [] args) throws FileNotFoundException, URISyntaxException  {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //����ڷκ��� ip�� �г����� �Է¹ޱ� ���� Frame
		introBgm.start();
		
		introFrame.getIpField().setText(" ������ ip�� �ʿ� �����ϴ�!"); 
		

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {

			serverSocket = new ServerSocket(9876); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.
			introBgm.stop();
			ChatSendThread chatThread = new ChatSendThread(mainFrame, clientSocket);
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread  nickThread = new NickNameThread(mainFrame, clientSocket);
			ReadyThread readyThread = new ReadyThread(mainFrame, clientSocket);

			nickThread.start();
			nickThread.join();			

			readyThread.start();
			
			chatThread.start();
			reThread.start();

		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e) {
			mainFrame.getChat().taAdd("System :������� ������ ���������ϴ�.\n");
		}


	}


}
