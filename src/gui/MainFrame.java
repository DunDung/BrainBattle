package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainFrame extends JFrame{
	private Container c = this.getContentPane(); //�߰�
	private ChattingPanel chat = new ChattingPanel();
	private String nickName = null;
	public static ScorePanel score = new ScorePanel();

	public MainFrame() {
		setLocation(new Point(700,350)); //��������� â�� ��ġ�� ����ش�.
		setPreferredSize(new Dimension(1500, 1000));//�������� â��ũ�� ����
		setLayout(null);
		c.add(chat); //����
		c.add(score);
		chat.setBounds(1020,115,450,950);
		score.setBounds(1020, 10, 450, 100);
	
		pack();//������Ʈ ũ�⸸ŭ â�� ũ�⸦ ����ش�.
		setLocationRelativeTo(null); //(null)�� �������ν� ȭ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â�� ������� ���μ����� ����
		setVisible(false); //�������� �����ش�.
	}
	
	public ChattingPanel getChat() { //ChattingPanel getter
		return chat;
	}

	public String getNickName() { //nickName�ʵ� getter
		return nickName;
	}
	public void setNickName(String nickName) { //nickName�ʵ� setter
		this.nickName = nickName;
	}
	public ScorePanel getScore() {
		return score;
	}
	
	public void viewTrue() { //ȣ�� �� mainFrame�� ȭ�鿡 ��� �޼ҵ�
		setVisible(true);
	}
	
}


