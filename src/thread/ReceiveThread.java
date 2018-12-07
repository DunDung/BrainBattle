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
			bgm.start(); //bgm�����带 ���������ν� ��Ʈ�������� ư��.
			
			//����� ������ inputStream�� outputStream���� �ְ�ޱ����� ���� �ʱ�ȭ
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
					//���۽�ȣ�� ���� ���浵 �Ȱ��� ȯ���� ������ش�.
					mainFrame.getGame().getReadyOk().setVisible(false);       
					mainFrame.getGame().setTfAndEnter(); 						
					mainFrame.getGame().getRuleButton().setVisible(false);
					mainFrame.getChat().getTa().append("System :������ �����մϴ�.\n");   
					bgm.closeIntro();                                                                                         
					
					//���� �÷��̾ ��ǥ������ ������ ���� ��ٷ� �޶�� �̹����� ǥ���Ѵ�.
					mainFrame.getGame().getWaitGoalScore().setVisible(true);
					break;
					
				case "goal" ://��ǥ����
					mainFrame.getGame().getWaitGoalScore().setVisible(false); //��ǥ���� �������� ��ٷ��޶�� �̹����� �Ⱥ��̰� �Ѵ�.
					mainFrame.getGame().setGoalScore(Integer.parseInt(receiveArray[1])); //�޾ƿ� ��ǥ������ Integer�� �ٲ��� �����гο� �ʱ�ȭ�Ѵ�.
					break;
					
				case "questionList" ://�������, ���� ������ �����ֱ� ���� ���ʿ����� ���� ������ ����� �޾ƾ��Ѵ�.
					String [] questionArray = new String[receiveArray.length-1];//��������� �޾ƿ��� ���� String�� �迭
					for(int i=1; i<receiveArray.length; i++) { //receiveArray[0]�� questionList��� Ű����� ������ i=1�̴�.
						questionArray[i-1] = receiveArray[i]; // questionArray[0]���� ���� ����������� ä���.
					}
					GameThread gameThread = new GameThread(mainFrame, socket, questionArray); //�޾ƿ� ����������� ���Ӿ����带 �ʱ�ȭ�� ����
					gameThread.start();
					break;
					
				case "answer": //���� ���� , �� ������ ������ ������ �����̴�, ������ �´��� QuestionŬ������ QuestionMap�� ������ȣ�� key�� value�� ���Ѵ�. 
						receiveArray[2] = receiveArray[2].toUpperCase(); //�빮�ڷ� �ٲ㼭 �ٽ� ����
						System.out.println("������ȣ "+receiveArray[1]+ " ��  "+receiveArray[2]);
					if(Question.getQuestionMap().get(receiveArray[1]).equals(receiveArray[2])) { //�¾��� ��
						mainFrame.getGame().setOtherCorrect(true); //������ �¾����� �˸���.
						mainFrame.getScore().addYourScore(); //�������� �ø���.
						mainFrame.getScore().repaint(); //���ھ �ٽñ׸���.
						TimerThread.setTimerStop(true); //Ÿ�̸Ӹ� ���� �� ���� ������ �� �ְ� �Ѵ�.
						writer.println("answerOk/"); //���濡�� ������ �´ٰ� ������.
						break;
					}
					else { //Ʋ���� ��
						writer.println("answerWrong/"); // ���濡�� ������ �ƴ϶�� ������.
						break;
					}

				case "answerOk": //������ ���� �¾��� ��
					mainFrame.getGame().setIAmCorrect(true); //�����¾����� �˸���.
					TimerThread.setTimerStop(true); //Ÿ�̸Ӹ� ���� �� ���� ������ �� �ְ� �Ѵ�.
					mainFrame.getScore().addMyScore(); //���� ������ �ø���.
					mainFrame.getScore().repaint(); //���ھ �ٽ� �׸���.
					break;
				
				case "answerWrong" : //���� Ʋ���� ��
					mainFrame.getGame().setWrong(true); //���� Ʋ������ �˸���.
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
