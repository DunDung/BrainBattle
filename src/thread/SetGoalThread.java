package thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;

import gui.MainFrame;
import question.Question;

public class SetGoalThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame;
	
	public SetGoalThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() { //������ ���÷��̾ ����Ǵ� ������
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true); //����� ������  outPutStream���� �ʱ�ȭ
			mainFrame.getGame().setScoreImg(); //�����г��� ��ǥ���� ���� �̹����� ���� ��ư���� �����ִ� �޼ҵ�
			while(true) { //��ǥ���� ��ư�� ������ ��ǥ������ �����ɶ� ���� ����
				Thread.sleep(50);
				if(mainFrame.getGame().getGoalScore() != 0) { //��ǥ������ �ʱ�ȭ  �Ǿ� 0�� �ƴϸ�
					writer.println("goal/"+mainFrame.getGame().getGoalScore()); //������ ��ǥ������ ���濡�� ������.
					break;
				}
			}
			Question question = new Question(); //��ü������ ���� ������ �����.
			Collections.shuffle(question.getQuestionList()); //�������� ���´�.
			GameThread gameThread = new GameThread(mainFrame, socket,question.toString().split("/")); //���� �������� ������ �迭�� ���Ӿ����� ��ü������ �Ķ���ͷ� ������ �����Ѵ�. 
			gameThread.start(); //���ӽ���
			writer.println("questionList/"+question); //���� �������� ReceiveThread�� ������. Receive�����忡�� �����Ͽ� �ʱ�ȭ�Ѵ�.
			
		} catch (IOException|InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
