package gui;


import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{

	private static JLabel score = new JLabel("Score");
	private static JLabel myNickName = new JLabel("");
	private static JLabel yourNickName = new JLabel("");
	private JLabel vs = new JLabel("VS");
	private JLabel myScore = new JLabel("");
	private JLabel yourScore = new JLabel("");
	private static int myScoreCount;
	private static int yourScoreCount;
	private Socket socket;
	

	public ScorePanel() {
		setLayout(null); //���̾ƿ� ����
		//�гο� �߰�
		add(score);
		add(myNickName);
		add(yourNickName);
		add(vs);
		add(myScore);
		add(yourScore);
		//���̾ƿ��� ���⿡ ��ġ�� ���� ������ �ش�.
		score.setBounds(185, 10, 100, 45);
		myNickName.setBounds(70, 60, 150,50);
		yourNickName.setBounds(320, 60, 150, 50);
		vs.setBounds(215, 60, 350, 50);
		myScore.setBounds(100, 120, 30, 50);
		yourScore.setBounds(350, 120, 30, 50);
		
		//�� �󺧵� ����ũ�� ����
		score.setFont(score.getFont().deriveFont(30.0f)); 
		myNickName.setFont(myNickName.getFont().deriveFont(23.0f));
		yourNickName.setFont(yourNickName.getFont().deriveFont(23.0f));
		vs.setFont(vs.getFont().deriveFont(19.0f));
		myScore.setFont(myScore.getFont().deriveFont(25.0f));
		yourScore.setFont(yourScore.getFont().deriveFont(25.0f));
		
		//�г� ��� �� ���� �� Score ���� �� ����
		setBackground(Color.WHITE);
		score.setForeground(Color.RED);

//		
//		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
//		ta.setBackground(Color.LIGHT_GRAY); //ä��â ���� ����
		
	}
	public void setMyNickName(String nickName) {
		this.myNickName.setText(nickName);
	}

	public void setYourNickName(String nickName) {
		this.yourNickName.setText(nickName);
	}
	
	public String getMyNickName() {
		return myNickName.getText().toString();
	}
	
	public String getYourNickName() {
		return yourNickName.getText().toString();
	}

	public int getMyScore() {
		return myScoreCount;
	}

	public void setMyScore(int score) {
		this.myScoreCount = score;
	}

	public int getYourScore() {
		return yourScoreCount;
	}

	public void setYourScore(int score) {
		this.yourScoreCount = score;
	}
	
	public void setSocket(Socket socket) {  //client�������� �ʱ�ȭ�ϴ� �뵵.
		this.socket = socket;
	}
	
}




