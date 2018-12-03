package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import question.Question;

public class GameThread extends Thread {
	private MainFrame mainFrame; //����������
	private Socket socket; //����� ����
	PrintWriter writer; //��� ReceiveThread�� ���� ����
	private String[] questionArray; //������� �迭
	private int questionIndex = 0; //������� �迭�� ���� �ε����� ��Ÿ�� ����
	private TimerThread timer; //Ÿ�̸� ����
	Question catchNull = new Question(); //��Ʈ�̹����� �ٲ� �� null����Ʈ ���� ����	

	public GameThread(MainFrame mainFrame, Socket socket, String [] questionArray)  {
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.questionArray = questionArray;
		this.timer = new TimerThread(mainFrame);
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true); //writer�� ������ outPutStream���� �ʱ�ȭ
			mainFrame.getGame().getEnter().addActionListener(new AnswerSendEvent()); //���� �г��� ����� ������ ��ư�� ���� ������ �߰�
			mainFrame.getGame().getTf().addActionListener(new AnswerSendEvent()); //�ؽ�Ʈ�ʵ忡�� ���͸� ġ�� �����ε� ������ �� �ְ� �׼� ������ �߰�
			mainFrame.getGame().getQuiz().setVisible(true); //�����г��� quiz���� �����ش�. StratCount3.png�� setIcon�Ǿ��ִ�. 3�� �����ش�
			Thread.sleep(1000); //1�� ����
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));//2�� �����ش�
			Thread.sleep(1000);//1�ʽ���
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png"))); //1�� �����ش�
			Thread.sleep(1000); //1�ʽ���
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png"))); //������ �����Ѵٴ� �� �˸��� �̹����� �ٲ۴�.
			Thread.sleep(1000); //1�ʽ���
			mainFrame.getGame().getTimer().setVisible(true); //�����г��� Ÿ�̸� ���� �����ְ�
			timer.start(); //Ÿ�̸� �����带 ���� ��Ų��.
			
			while(true) { //���� ����
				if(mainFrame.getGame().getOtherCorrect()|| mainFrame.getGame().getIAmCorrect()) { //������ ������ ��, ���� �� ���� ������ �� -> �� ����
					questionIndex++; //���� �ε��� +1
					timer.setHint(false); //���� �Ͽ� Hint�� �������� �� �����Ƿ� false�� ����
					if(mainFrame.getGame().getOtherCorrect()) { //���� ������ ������ ��
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("OtherCorrect.png"))); //������ ����ٴ� �̹����� �ٲ۴�
						mainFrame.getGame().setOtherCorrect(false); //ReceiveThread���� �ٲ� ���� �г��� OhterCorrect�� false�� �ٽ� �ʱ�ȭ
						BgmControlThread.playSoundEffect("OtherCorrect.mp3"); //Thread.sleep��� 1��¥�� ȿ������ �־���. ȿ������ ����Ǵµ��� �����ȴ�.
					}
					else { //���� ������ ��
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerOk.png"))); //����ٴ� �̹����� �ٲ� ��
						mainFrame.getGame().setIAmCorrect(false); //ReceiveThread���� �ٲ� ���� �г��� IAmCorrect�� false�� �ٽ� �ʱ�ȭ
						BgmControlThread.playSoundEffect("AnswerOk.mp3"); //ȿ���� ���
					}
				}
				
				if(mainFrame.getGame().getWrong()) { //���� �� ���� ������ �ƴ� ��
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerWrong.png"))); //Ʋ�ȴٴ� �̹����� �����ش�.
					BgmControlThread.playSoundEffect("Wrong.mp3"); //ȿ���� ���
					mainFrame.getGame().setWrong(false); //ReceiveThread���� �ٲ� ���� �г��� wrong�� false�� �ٽ� �ʱ�ȭ
				}

				if(mainFrame.getGame().getTurnEnd()) { //�ð� ���� , �� ����
					questionIndex++;//�����ε��� +1
					timer.setHint(false); //���� �Ͽ� Hint�� �������� �� �����Ƿ� false�� ����
					mainFrame.getGame().setTurnEnd(false); //ReceiveThread���� ���� ���� ���� �˸��� �����г��� turnEnd�� �ٽ� false�� �ʱ�ȭ
				}
				//���� �̱�ų�, ������ �̰��� ��
				if(mainFrame.getScore().getMyScore() == mainFrame.getGame().getGoalScore() || mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()) { 
					timer.killTimer(); //Ÿ�̸� �����带 ���δ�.
					mainFrame.getGame().setYesAndNo();//�����г��� setYesAndNo�޼ҵ� ����
					mainFrame.getGame().getYes().addActionListener(new YesOrNoEvent()); //Yes��ư�� �׼Ǹ����� �߰�
					mainFrame.getGame().getNo().addActionListener(new YesOrNoEvent()); //No��ư�� �׼Ǹ����� �߰�
					if(mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()){ //������ �̰��� ��
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouLose.png"))); //���ٴ� �̹����� �ٲ� ��
						BgmControlThread.playSoundEffect("Lose.mp3"); //ȿ���� ���
					}else { //���� �̰��� ��
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouWin.png"))); //�̰�ٴ� �̹����� �ٲ� ��
						BgmControlThread.playSoundEffect("Win.mp3"); //ȿ���� ���
					}
					break;
				}
				if(timer.getHint()) //timerThread���� 30�ʰ� ������ hint������ true�� �Ǹ�
					//�� ������ ������ ��Ʈ���� �����ִ� �̹����� �ٲ۴�.
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Question.getQuestionHintMap().get(questionArray[questionIndex]))));

				if(!timer.getHint()) //30�� ���� ��, hint ==false
					//�����Ϲ迭�� �ε����� ���� �̹����� �����ش�.
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(questionArray[questionIndex])));
			}
		} catch (IOException | InterruptedException | JavaLayerException | URISyntaxException e) {
			e.printStackTrace();
		} 
	}
	class AnswerSendEvent implements ActionListener{ //enterŰ�� ���� ���� "����"��ư�� ������ ���� �̺�Ʈ Ŭ����
		@Override
		public void actionPerformed(ActionEvent e) { 
			String 	answer =mainFrame.getGame().getTf().getText().toString().trim(); //�ý�Ʈ �ʵ忡 �ִ� ���ڿ��� String���� answer�� �����Ѵ�.
			if(answer.length()!=0) //������ ���� �ƴ� ��
				writer.println("answer/" + questionArray[questionIndex] + "/" + answer); //Ű����� �����̸�,������ ���� ������. 
			mainFrame.getGame().getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
			writer.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.			}
		}
	}
	class YesOrNoEvent implements ActionListener{ //��������� Yes��No��ư�� ������ �� �̺�Ʈ
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(mainFrame.getGame().getYes())){ //Yes��ư�� ������ ���
				mainFrame.newGame(); //������������ newGame�޼ҵ� �����Ͽ� game�г� �� ����
				mainFrame.getScore().scoreReset(); //������������ ���ھ��г��� scoreReset()�޼ҵ带 �����Ͽ� score�� ��ȭ
				PreSettingThread preSetThread = new PreSettingThread(mainFrame, socket); //�����غ�Thread�� �ٽ� ����
				preSetThread.start();
			}
			else //No��ư ������ ���
				System.exit(0); //���α׷� ���� 
		}
	}
}

