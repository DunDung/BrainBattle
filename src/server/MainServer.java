package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;

public class MainServer {
	public static ReceiveThread re_thread; //���ڿ��� �޾��� ������
	public static SendThread se_thread; //���ڿ��� ���� ������
	public static MainFrame mainFrame; //GUI
	public static void main(String [] args) throws IOException {

		ServerSocket serverSocket = null; 
		Socket clientSocket  =null;
		try {
			serverSocket = new ServerSocket(8999); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.
			
			mainFrame = new MainFrame(); //���������� ����
			se_thread = new SendThread(mainFrame.getChat()); //SendThread�� ChattingPanel�� �Ķ���ͷ� �����Ѵ�.
			re_thread = new ReceiveThread(mainFrame.getChat());// ReceiveThread�� ChattingPanel�� �Ķ���ͷ� �����Ѵ�.

			re_thread.setSocket(clientSocket); //�������Ͽ� ������ Ŭ���̾�Ʈ �������� ��������� �ʱ�ȭ
			se_thread.setSocket(clientSocket);

			re_thread.start();
			se_thread.start();
			
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			serverSocket.close(); //���������� �ݴ´�.
		}
	}

}
