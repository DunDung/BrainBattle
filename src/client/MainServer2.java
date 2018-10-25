package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.NickNameThread;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;
	public static String ip;

	public static void main(String [] args) throws UnknownHostException, InterruptedException {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü�� �����Ͽ� ����ڷκ��� ip�� �г����� �Է� �޴´�.

		try {
			//ip�ּ� �˾Ƴ��� �ڵ�� �ۼ��غ��� �ؿ�
			Socket clientSocket = new Socket("172.30.1.10",9876);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.

			SendThread se_thread = new SendThread(mainFrame, clientSocket);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, clientSocket);
			NickNameThread nick_thread = new NickNameThread(mainFrame, clientSocket);
			nick_thread.start();
			nick_thread.join();

			se_thread.start();
			re_thread.start();


		}catch(IOException e){
			e.printStackTrace();
		}


	}

}
