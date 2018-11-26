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
	public static String ip; //����ڷκ��� �Է¹��� ip�� ������ ����

	public static void main(String [] args) throws FileNotFoundException, URISyntaxException  {
		mainFrame = new MainFrame(); //MainFrame ��ü�� �����Ѵ�. setVisible(false)�̱� ������ ������ �ʴ´�.
		introFrame = new IntroFrame(mainFrame); //����ڷκ��� ip�� �г����� �Է¹ޱ� ���� Frame

		try {
//			while(true) {
//				Thread.sleep(500);
//				if(mainFrame.getIp().length() != 0) { //����ڰ� ip�� �Է��ϸ�
//					ip = mainFrame.getIp(); //��������� ip�� �ʱ�ȭ�Ѵ�.
//					break; //while(true)Ż��
//				}
//			}
			Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(),9876);//����ڷκ��� �Է¹��� ip�� �������� ������ ��Ʈ�� �����Ѵ�.
			ReceiveThread reThread = new ReceiveThread(mainFrame, clientSocket);
			PreSettingThread preSetThread = new PreSettingThread(mainFrame, clientSocket);

			preSetThread.start();
			reThread.start();

		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e) {
			mainFrame.getChat().taAdd("System :������� ������ ���������ϴ�.\n");
		}
	}

}
