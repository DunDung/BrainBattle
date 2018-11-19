package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;


public class MainFrame extends JFrame{
	private Container c = this.getContentPane(); //�߰�
	private ChattingPanel chat = new ChattingPanel();
	private ScorePanel score = new ScorePanel();
	private GamePanel game = new GamePanel();
	private String ip = new String("");

	public MainFrame() {
		setTitle("BrainBattle");
		setLocation(new Point(700,350)); //��������� â�� ��ġ�� ����ش�.
		setPreferredSize(new Dimension(1500, 1000));//�������� â��ũ�� ����
		setResizable(false); //â ũ�� ����
		setLayout(null); //���̾ƿ� ����
		
		c.add(chat); //����
		c.add(score);
		c.add(game);
		
		
		chat.setBounds(1020,120,450,950);
		score.setBounds(1020, 15, 450, 100);
		game.setBounds(20, 15 , 1000, 935);
		pack();//������Ʈ ũ�⸸ŭ â�� ũ�⸦ ����ش�.
		setLocationRelativeTo(null); //(null)�� �������ν� ȭ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â�� ������� ���μ����� ����
		setVisible(false); //�������� �����ش�.
	}
	
	public ChattingPanel getChat() { //ChattingPanel getter
		return chat;
	}

	public ScorePanel getScore() {
		return score;
	}
	
	public GamePanel getGame() {
		return game;
	}
	public void newGame() {
		remove(game);
		game = new GamePanel();
		add(game);
		game.setBounds(20, 15 , 1000, 935);
		this.repaint();
	}
	
	public void viewTrue() { //ȣ�� �� mainFrame�� ȭ�鿡 ��� �޼ҵ�
		setVisible(true);
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	
}


