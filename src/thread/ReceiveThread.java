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
			SetGoalThread setGoalThread = new SetGoalThread(mainFrame, socket);
			GameThread gameThread = new GameThread(mainFrame, socket);
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				String [] receiveArray = receiveString.split("/");

				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n");
					break;	
				
				case "ready" :
					if(mainFrame.getGame().getPlayOk()) {
						writer.println("go/");
						mainFrame.getGame().getReadyOk().setVisible(false);
						setGoalThread.start();
						break;
					}
					break;
					
				case "go" :
					mainFrame.getGame().getReadyOk().setVisible(false);
					mainFrame.getGame().getWaitGoalScore().setVisible(true);
					break;
					
				case "goal" :
					mainFrame.getGame().getWaitGoalScore().setVisible(false);
					mainFrame.getGame().setGoalScore(Integer.parseInt(receiveArray[1]));
					gameThread.start();
					break;
					
				case "answer":
					if(Question.getQuestionMap().get(receiveArray[1]).toString().equals(receiveArray[2])) {
						mainFrame.getScore().addYourScore(); //���ϱ����� �������� �ø���.
						mainFrame.getScore().repaint();
						writer.println("win/");
						break;
					}
					break;

				case "win":
					mainFrame.getScore().addMyScore();
					mainFrame.getScore().repaint();
					break;
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
