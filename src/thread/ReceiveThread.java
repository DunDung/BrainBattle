package thread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import question.Question;


public class ReceiveThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame; //�߰�
	private static boolean start = false;
	BgmControlThread bgm =null;
	public ReceiveThread(MainFrame mainFrame, Socket socket) throws FileNotFoundException, JavaLayerException, URISyntaxException{ //������ �߰�
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.bgm = new BgmControlThread();
	}

	@Override
	public void run() {
		super.run();

		try {
			//Ŭ���̾�Ʈ ������ ��ǲ��Ʈ������ Ŭ���̾�Ʈ ������ ���� ������ �޴´�.
			bgm.start();
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			Question catchNull = new Question(); //������ ���� �� null����Ʈ ���� ����
			
			while(true) {
				String receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveString�� �����Ѵ�.
				String [] receiveArray = receiveString.split("/"); // "/"�� �������� receiveString�� ����� �迭�� ����.

				switch(receiveArray[0]) { //������ ���ڿ� �� �Ǿ� ���ڿ��� �������� ������ ���ϴ��� �Ǵ��Ѵ�.
				
				case "chat": //ä��
					mainFrame.getChat().getTa().append(receiveArray[1]+"\n"); //�� ä��â�� �޾ƿ� �޽����� �߰��Ѵ�.
					break;	
					
				case "nickName" : //�г���
					mainFrame.getScore().setYourNickName(receiveArray[1]); //�޾ƿ� �г������� ��� �г��� �ʱ�ȭ
					break;
					
				case "ready" : //�غ�Ϸ� 
					if(mainFrame.getGame().getPlayOk()) { //���� �غ�Ϸ� �Ǿ��� ���
						writer.println("start/"); //��뿡�� start�� ������
						mainFrame.getGame().getReadyOk().setVisible(false); //�غ�Ϸ� ��ư�� ������� �Ѵ�.
						mainFrame.getGame().setTfAndEnter(); //��������� �ؽ�Ʈ�ʵ�� ��ư�� �߰�
						mainFrame.getGame().getRuleButton().setVisible(false); //���Ӽ��� ��ư�� ������� �Ѵ�.
						mainFrame.getChat().getTa().append("System :������ �����մϴ�.\n"); 
						bgm.closeIntro(); //������ ���۵Ǳ� ������ Intro������ ���ش�.
						
						SetGoalThread setGoalThread = new SetGoalThread(mainFrame, socket); //�Ѹ��� �÷��̾ ��ǥ������ �����Ѵ�.
						setGoalThread.start(); 
						break;
					}
					break;
					
				case "start" : //����
					mainFrame.getGame().getReadyOk().setVisible(false);           
					mainFrame.getGame().setTfAndEnter();						
					mainFrame.getGame().getRuleButton().setVisible(false);			
					mainFrame.getChat().getTa().append("System :������ �����մϴ�.\n");   
					bgm.closeIntro();                                               //���۽�ȣ�� ���� ���浵 �Ȱ��� ȯ���� ������ش�.                                              
					
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
