package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import gui.MainFrame;
import question.Question;


public class ReceiveThread extends Thread{
	private Socket socket;
	private MainFrame mainFrame; //�߰�
	private static boolean start = false;
	public ReceiveThread(MainFrame mainFrame, Socket socket) { //������ �߰�
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();

		try {
			//Ŭ���̾�Ʈ ������ ��ǲ��Ʈ������ Ŭ���̾�Ʈ ������ ���� ������ �޴´�.
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				String [] receiveArray = receiveString.split("/");

				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n"); //JTestArea�� �߰����ش�.

				case "answer":
						System.out.println("answer����");
						if(Question.getQuestionMap().get(receiveArray[1]).toString().equals(receiveArray[2])) {
							mainFrame.getScore().addYourScore(); //���ϱ����� �������� �ø���.
							mainFrame.getScore().repaint();
							System.out.println("���ھ� �ʱ�ȭ");
							writer.println("lose/");
							System.out.println("lose����");

						}
					
				case "lose":
					System.out.println("lose����");
					mainFrame.getScore().addMyScore();
					mainFrame.getScore().repaint();

				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
