package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;
	public static BufferedReader reader;
	public static PrintWriter writer;
	public static void main(String [] args) throws UnknownHostException {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü�� �����Ͽ� ����ڷκ��� ip�� �г����� �Է� �޴´�.

		try {
				Socket clientSocket = new Socket(ip,9876);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
				reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				writer = new PrintWriter(clientSocket.getOutputStream(),true);
				
				
//				SendThread se_thread = new SendThread(mainFrame);
//				se_thread.setSocket(clientSocket);
//				
//				ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
//				re_thread.setSocket(clientSocket);
//				
//				se_thread.start();
//				re_thread.start();
				
				
			
		}catch(IOException e){
			e.printStackTrace();
		}

		
	}
	public static String data(String str) throws IOException {
		writer.println(str);
		return reader.readLine();
	}


}
