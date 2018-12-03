package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;

import gui.IntroFrame;
import gui.MainFrame;
import thread.PreSettingThread;
import thread.ReceiveThread;

public class Client {
	public static MainFrame mainFrame;
	public static IntroFrame introFrame;

	public static void main(String [] args) throws FileNotFoundException, URISyntaxException  {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //����ڷκ��� ip�� �г����� �Է¹ޱ� ���� Frame

		try {
//			while(mainFrame.getIp().length() == 0) {
//				Thread.sleep(100);
//			}
//			
			//InetAddress.getLocalHost().getHostAddress()
			//mainFrame.getIp()
			Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), 9999);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket); // �������� ���� ���� �޼����� ó���� ������
			PreSettingThread preSetThread = new PreSettingThread(mainFrame, clientSocket);//���� ������ �ϰ� ���α׷��� ���������� �̾ ������

			preSetThread.start();
			reThread.start();

		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println(e);
			mainFrame.getChat().getTa().append("System :������� ������ ���������ϴ�.\n");
		}
	}

}
