package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;

import gui.MainFrame;
import question.Question;

public class GameThread extends Thread {
	private MainFrame mainFrame;
	private Socket socket;
	PrintWriter writer;
	private String[] questionArray;
	private int questionIndex = 0;

	public GameThread(MainFrame mainFrame, Socket socket, String [] questionArray)  {
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.questionArray = questionArray;
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true);
			mainFrame.getGame().getEnter().addActionListener(new AnswerSendEvent());
			mainFrame.getGame().getTf().addActionListener(new AnswerSendEvent());


			mainFrame.getGame().getQuiz().setVisible(true);
			Thread.sleep(3500);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount3.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount2.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount1.png")));
			Thread.sleep(1000);
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameStart.png")));
			Thread.sleep(1000);
			while(true) {
				if(mainFrame.getScore().getMyScore() == mainFrame.getGame().getGoalScore()) {
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameOver.png")));
					break;
				}
				if(mainFrame.getScore().getYourScore() == mainFrame.getGame().getGoalScore()) {
					mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("GameOver.png")));
					break;
				}
				mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(questionArray[questionIndex])));
				if(mainFrame.getGame().getTurnEnd()) {
					questionIndex++;
					mainFrame.getGame().setTurnEnd(false);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	class AnswerSendEvent implements ActionListener{ //enterŰ�� ���� ���� "����"��ư�� ������ ���� �̺�Ʈ Ŭ����
		@Override
		public void actionPerformed(ActionEvent e) { 
			String 	answer =mainFrame.getGame().getTf().getText().toString().trim(); //�ý�Ʈ �ʵ忡 �ִ� ���ڿ��� sendString�� �����Ѵ�.
			System.out.println("answer/" + questionArray[questionIndex] + "/" + answer);
			if(answer.length()!=0)
				writer.println("answer/" + questionArray[questionIndex] + "/" + answer); //������ �ƿ�ǲ��Ʈ���� sendString�� ������.
			mainFrame.getGame().getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
			writer.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.			}
		}
	}
}

