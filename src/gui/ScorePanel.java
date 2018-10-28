package gui;


import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{

	private  JLabel myNickName = new JLabel("");
	private  JLabel yourNickName = new JLabel("");
	private JLabel vs = new JLabel("VS");
	private  int myScoreCount = 0;
	private  int yourScoreCount = 0;
	private JLabel myScore = new JLabel(""+myScoreCount);
	private JLabel yourScore = new JLabel(""+yourScoreCount);
	private Socket socket;
	

	public ScorePanel() {
		setLayout(null); //���̾ƿ� ����
		//�гο� �߰�
		add(myNickName);
		add(yourNickName);
		add(vs);
		add(myScore);
		add(yourScore);
		//���̾ƿ��� ���⿡ ��ġ�� ���� ������ �ش�.
		myNickName.setBounds(15, 60, 130,30);
		yourNickName.setBounds(299, 60, 130, 30);
		myScore.setBounds(181, 15, 30, 50);
		vs.setBounds(210, 15, 350, 50);
		yourScore.setBounds(250, 15, 30, 50);
		
		//�� �󺧵� �۾� ũ�� �� �۾� ��, �� ���� ����
		myNickName.setFont(myNickName.getFont().deriveFont(20.0f));
		myNickName.setForeground(Color.BLACK); //�۾� ��
		myNickName.setBackground(Color.WHITE); //��� ��
		myNickName.setOpaque(true); //�� ���� ����
		myNickName.setHorizontalAlignment(JLabel.CENTER); //�г��� ��� ����
		yourNickName.setFont(yourNickName.getFont().deriveFont(20.0f));
		yourNickName.setForeground(Color.BLACK);
		yourNickName.setBackground(Color.WHITE);
		yourNickName.setOpaque(true);
		yourNickName.setHorizontalAlignment(JLabel.CENTER);
		
		vs.setFont(vs.getFont().deriveFont(19.0f));
		vs.setForeground(Color.WHITE);
		myScore.setFont(myScore.getFont().deriveFont(25.0f));
		myScore.setForeground(Color.BLUE);
		yourScore.setFont(yourScore.getFont().deriveFont(25.0f));
		yourScore.setForeground(Color.RED);
		
		//�г� ��� �� ���� �� Score ���� �� ����
		setBackground(Color.black);

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
		return myNickName.getText().toString().trim();
	}
	
	public String getYourNickName() {
		return yourNickName.getText().toString().trim();
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




