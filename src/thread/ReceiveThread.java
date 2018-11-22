package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;

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
			Question catchNull = new Question(); //������ ���� �� null����Ʈ ���� ����
			while(true) {
				String receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				String [] receiveArray = receiveString.split("/");

				switch(receiveArray[0]) {
				case "chat":
					mainFrame.getChat().taAdd(receiveArray[1]+"\n");
					break;	
				
				case "ready" :
					if(mainFrame.getGame().getPlayOk()) {
						writer.println("start/");
						mainFrame.getGame().getReadyOk().setVisible(false);
						mainFrame.getGame().setTfAndEnter();
						mainFrame.getGame().getRuleButton().setVisible(false);
						mainFrame.getChat().taAdd("System :������ �����մϴ�.\n");
						SetGoalThread setGoalThread = new SetGoalThread(mainFrame, socket);
						setGoalThread.start();
						break;
					}
					break;
					
				case "start" :
					mainFrame.getGame().getReadyOk().setVisible(false);
					mainFrame.getGame().setTfAndEnter();
					mainFrame.getGame().getRuleButton().setVisible(false);
					mainFrame.getChat().taAdd("System :������ �����մϴ�.\n");
					mainFrame.getGame().getWaitGoalScore().setVisible(true);
					break;
					
				case "goal" :
					mainFrame.getGame().getWaitGoalScore().setVisible(false);
					mainFrame.getGame().setGoalScore(Integer.parseInt(receiveArray[1]));
					break;
					
				case "questionList" :
					String [] questionArray = new String[receiveArray.length-1];
					for(int i=1; i<receiveArray.length; i++) {
						questionArray[i-1] = receiveArray[i];
					}
					GameThread gameThread = new GameThread(mainFrame, socket, questionArray);
					gameThread.start();
					break;
					
				case "answer":
					if(Question.getQuestionMap().get(receiveArray[1]).equals(receiveArray[2])) {
						mainFrame.getGame().setOtherCorrect(true);
						mainFrame.getScore().addYourScore(); //���ϱ����� �������� �ø���.
						mainFrame.getScore().repaint();
						TimerThread.setTimerStop(true);
						writer.println("answerOk/");
						break;
					}
					else {
						writer.println("answerWrong/");
						break;
					}

				case "answerOk":
					mainFrame.getGame().setIAmCorrect(true);
					TimerThread.setTimerStop(true);
					mainFrame.getScore().addMyScore();
					mainFrame.getScore().repaint();
					break;
				
				case "answerWrong" :
					mainFrame.getGame().setWrong(true);

					break;
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
