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
	private MainFrame mainFrame;
	private Socket socket;
	PrintWriter writer;
	private String[] questionArray;
	private int questionIndex = 0;
	private TimerThread timer;
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
			writer = new PrintWriter(socket.getOutputStream(),true);
			mainFrame.getGame().getEnter().addActionListener(new AnswerSendEvent());
			mainFrame.getGame().getTf().addActionListener(new AnswerSendEvent());
			mainFrame.getGame().getQuiz().setVisible(true);
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getTimer().setVisible(true);
			timer.start();
			while(true) {

				if(mainFrame.getGame().getOtherCorrect()|| mainFrame.getGame().getIAmCorrect()) { //������ ������ ��, ���� �� ���� ������ �� -> �� ����
					questionIndex++;
					timer.setHint(false);
					if(mainFrame.getGame().getOtherCorrect()) {
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("OtherCorrect.png")));
						mainFrame.getGame().setOtherCorrect(false);
						BgmControlThread.playSoundEffect("OtherCorrect.mp3");
					}
					else {
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerOk.png")));
						mainFrame.getGame().setIAmCorrect(false);
						BgmControlThread.playSoundEffect("AnswerOk.mp3");
					}
				}
				
				if(mainFrame.getGame().getWrong()) { //���� �� ���� ������ �ƴ� ��
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerWrong.png")));
					BgmControlThread.playSoundEffect("Wrong.mp3");
					mainFrame.getGame().setWrong(false);
				}

				if(mainFrame.getGame().getTurnEnd()) { //�ð� ���� , �� ����
					questionIndex++;
					timer.setHint(false);
					mainFrame.getGame().setTurnEnd(false);
				}
				//���� �̱�ų�, ������ �̰��� ��
				if(mainFrame.getScore().getMyScore() == mainFrame.getGame().getGoalScore() || mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()) { 
					timer.killTimer();
					mainFrame.getGame().setYesAndNo();
					mainFrame.getGame().getYes().addActionListener(new YesOrNoEvent());
					mainFrame.getGame().getNo().addActionListener(new YesOrNoEvent());
					if(mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()){
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouLose.png")));
						BgmControlThread.playSoundEffect("Lose.mp3");
					}else {
						mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("YouWin.png")));
						BgmControlThread.playSoundEffect("Win.mp3");
					}
					break;
				}
				if(timer.getHint()) 
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Question.getQuestionHintMap().get(questionArray[questionIndex]))));

				if(!timer.getHint())
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
			if(answer.length()!=0)
				writer.println("answer/" + questionArray[questionIndex] + "/" + answer); 
			mainFrame.getGame().getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
			writer.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.			}
		}
	}
	class YesOrNoEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(mainFrame.getGame().getYes())){
				mainFrame.newGame();
				mainFrame.getScore().scoreReset();
				PreSettingThread preSetThread = new PreSettingThread(mainFrame, socket);
				preSetThread.start();
			}
			else
				System.exit(0);
		}
	}
}

