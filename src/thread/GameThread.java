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
	private Question question = new Question();
	PrintWriter writer;
	public GameThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream(),true);
		
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
			mainFrame.getGame().getQuiz().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Question.getQuestionList().get(0).toString())));
			
			mainFrame.getGame().getEnter().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { 
					writer.println("answer/"+Question.getQuestionList().get(0)+"/"+mainFrame.getGame().getTf().getText().toString()); //������ �ƿ�ǲ��Ʈ���� sendString�� ������.
					mainFrame.getGame().getTf().setText(""); //�ؽ�Ʈ �ʵ带 �ٽ� �ƹ��͵� ���� ���·� �����.
					writer.flush(); //���۸��Ǿ� ���� ��ϵ��� ���� �����͸� ��� ��Ʈ���� ��� ����Ѵ�.
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
}
