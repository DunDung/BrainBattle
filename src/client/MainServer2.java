package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import gui.IntroFrame;
import gui.MainFrame;
import thread.ReceiveThread;
import thread.SendThread;
import java.net.UnknownHostException;

public class MainServer2 {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;

	
	public static void main(String [] args) throws UnknownHostException {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //introFrame ��ü�� �����Ͽ� ����ڷκ��� ip�� �г����� �Է� �޴´�.
		
		InetAddress local = InetAddress.getLocalHost();
		
		try {
				Socket clientSocket =new Socket(local.getHostAddress(),9191);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
				
				while(true) {
					if(mainFrame.getNickName() != null) {
						DataOutputStream  dataOut = new DataOutputStream(clientSocket.getOutputStream());
						dataOut.writeUTF(mainFrame.getNickName());
						dataOut.flush();
						break;
					}
				}
				while(true) {
					if(mainFrame.getScore().getYourNickName().trim().length() <= 1) {
						DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
						String s =dataIn.readUTF();
						mainFrame.getScore().setYourNickName(s);
						break;
					}
				}
			//	introFrame.setSocket(clientSocket);
				
				SendThread se_thread = new SendThread(mainFrame);
				se_thread.setSocket(clientSocket);
				
				ReceiveThread re_thread = new ReceiveThread(mainFrame, se_thread);
				re_thread.setSocket(clientSocket);
				
				DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
				dataOut.flush();
				
			
			//	se_thread.start();
				re_thread.start();
				
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
