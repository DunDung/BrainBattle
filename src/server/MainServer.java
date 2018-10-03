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
		//	introFrame.getIpFiedl().setText(" �г��Ӹ� �Է��ϼ���."); //������ǻ���� ��쿣 Ip�� �Է��� �ʿ䰡 �����Ƿ� introFrame�� ipField�� ���� ����

		try {
			serverSocket = new ServerSocket(9191); //���������� ��Ʈ��ȣ�� �޾Ƽ� �ʱ�ȭ �Ѵ�.
			clientSocket = serverSocket.accept();  //Ŭ���̾�Ʈ������ �������Ͽ� ������ �������� �ʱ�ȭ�Ѵ�.

			//introFrame.setSocket(clientSocket);
			while(true) {
				if(mainFrame.getScore().getYourNickName().trim().length() <= 1) {
					DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
					String s =dataIn.readUTF();
					mainFrame.getScore().setYourNickName(s);
					break;
				}
			}
			while(true) {
				if(mainFrame.getNickName() != null) {
					DataOutputStream  dataOut = new DataOutputStream(clientSocket.getOutputStream());
					dataOut.writeUTF(mainFrame.getNickName());
					dataOut.flush();
					break;
				}
			}

			SendThread se_thread = new SendThread(mainFrame);
			ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
			re_thread.setSocket(clientSocket);

			se_thread.setSocket(clientSocket);

			se_thread.start();
			re_thread.start();


		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
