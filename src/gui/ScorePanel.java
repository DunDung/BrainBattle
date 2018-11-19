package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{ 	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("ScoreBackGround.png")).getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	private JLabel myNickName = new JLabel("");
	private JLabel yourNickName = new JLabel("");
	private int myScoreCount = 0;
	private int yourScoreCount = 0;
	private JLabel myScore = new JLabel(""+myScoreCount);
	private JLabel yourScore = new JLabel(""+yourScoreCount);
	private boolean nickState = false;


	public ScorePanel() {
		setLayout(null); //���̾ƿ� ����
		//�гο� �߰�
		add(myNickName);
		add(yourNickName);
		add(myScore);
		add(yourScore);
		//���̾ƿ��� ���⿡ ��ġ�� ���� ������ �ش�.
		myNickName.setBounds(98, 8, 92,22);
		yourNickName.setBounds(262, 8, 92, 22);
		myScore.setBounds(99, 32, 91, 60);
		yourScore.setBounds(262, 32, 91, 60);

		//�� �󺧵� �۾� ũ�� �� �۾� ��, �� ���� ����
		myNickName.setFont(new Font("Dialog", Font.BOLD, 19));
		myNickName.setForeground(Color.BLACK); //�۾� ��
		myNickName.setHorizontalAlignment(JLabel.CENTER); //�г��� ��� ����

		yourNickName.setFont(new Font("Dialog", Font.BOLD, 19));
		yourNickName.setForeground(Color.BLACK);
		yourNickName.setHorizontalAlignment(JLabel.CENTER);

		myScore.setFont(new Font("Dialog", Font.BOLD, 50));
		myScore.setForeground(Color.BLUE);
		myScore.setHorizontalAlignment(JLabel.CENTER); 

		yourScore.setFont(new Font("Dialog", Font.BOLD, 50));
		yourScore.setForeground(Color.RED);
		yourScore.setHorizontalAlignment(JLabel.CENTER); 

		
		

	}
	public void setMyNickName(String nickName) {
		this.myNickName.setText(nickName);
	}

	public void setYourNickName(String nickName) {
		this.yourNickName.setText(nickName);

	}

	public String getMyNickName() {
		return this.myNickName.getText().toString().trim();
	}

	public String getYourNickName() {
		return this.yourNickName.getText().toString().trim();
	}

	public int getMyScore() {
		return myScoreCount;
	}

	public void addMyScore() {
		this.myScore.setText(""+ (++myScoreCount));;
	}

	public int getYourScore() {
		return yourScoreCount;
	}

	public void addYourScore() {
		this.yourScore.setText(""+ (++yourScoreCount));;
	}

	public void setNcikState() {  
		this.nickState = true;
	}
	public boolean getNickState() {
		return nickState;
	}
	
	public void scoreReset() {
		this.myScoreCount = 0;
		this.myScore.setText(""+ myScoreCount);
		this.yourScoreCount = 0;
		this.yourScore.setText(""+yourScoreCount);
		this.repaint();
	}

}




