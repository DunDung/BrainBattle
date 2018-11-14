package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GamePanel extends JPanel{ 	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("BackGround.png")).getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	

	private JTextField tf = new JTextField();
	private JButton enter = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerSend.png")));	
	private JButton ready = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("Ready.png")));
	private JLabel readyOk = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("ReadyOk.png")));
	private JLabel quiz= new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount3.png")));
	private JLabel goalLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Goal.png")));
	private JButton [] scoreImg = {new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("3.png"))),
								   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("5.png"))),
								   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("7.png"))),
								   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("10.png"))),
							   	   new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("15.png")))}; 
	private JLabel waitGoalScore = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("setGoal.png")));
	private int goalScore = 0;
	private int scoreImgIndexCount = 0;
	private boolean playOk =false;
	private boolean turnEnd = false;


	public GamePanel() {
		this.setLayout(null);
		
		
		add(ready);
		add(quiz);
		add(readyOk);
		add(waitGoalScore);
		
		quiz.setVisible(false);
		readyOk.setVisible(false);
		waitGoalScore.setVisible(false);
		
		ready.setBounds(410, 400, 250, 210);
		readyOk.setBounds(410, 400, 250, 210);
		quiz.setBounds(0, 0, 980, 885);
		waitGoalScore.setBounds(0, 0, 980, 885);
		
		tf.setFont(enter.getFont().deriveFont(16.0f));

		enter.setBorder(new LineBorder(Color.BLACK)); //��� �׵θ�
		tf.setBorder(new LineBorder(Color.BLACK));

		ready.setContentAreaFilled(false); //��ư ���뿵�� ����
		ready.setBorderPainted(false); //��ư �ܰ��� �����
		
		ready.addMouseListener(new MouseCursorEvent(ready));
			
		
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //��ư�� �̹����� �ٲ��ְ� �غ�Ϸ� ������ �˸�
				ready.setVisible(false);
				readyOk.setVisible(true);
				playOk = true;
			}
		});
	}

	public boolean getPlayOk() {
		return this.playOk;
	}
	public JLabel getReadyOk() {
		return this.readyOk;
	}
	public JLabel getQuiz() {
		return quiz;
	}
	public int getGoalScore() {
		return this.goalScore;
	}
	public void setGoalScore(int goalScore) {
		this.goalScore = goalScore;
	}
	public JLabel getWaitGoalScore() {
		return waitGoalScore;
	}
	public JButton getEnter() {
		return enter;
	}
	public JTextField getTf() {
		return tf;
	}
	public boolean getTurnEnd() {
		return turnEnd;
	}
	public void setTurnEnd(boolean turn) {
		turnEnd = turn;
	}
	public void setTfAndEnter() {
		add(tf);
		add(enter);
		tf.setBounds(0, 885, 878, 50);
		enter.setBounds(878, 885, 101, 50);
	}
	
	public void setScoreImg() {
		int y = 0;
		int [] score = {3, 5, 7, 10, 15};
		add(goalLabel);
		goalLabel.setBounds(0,y, 980 ,148);
		for(int i = 0; i<score.length; i++) {
			y+= 148;
			add(scoreImg[i]);
			scoreImg[i].addMouseListener(new MouseCursorEvent(scoreImg[i]));
			scoreImg[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					goalScore = score[scoreImgIndexCount++];
					goalLabel.setVisible(false);
					for(JButton b : scoreImg)
						b.setVisible(false);
				}
				
			});
			scoreImg[i].setBounds(0, y, 980, 148);
		}
	}
}

class MouseCursorEvent extends MouseAdapter {
	JButton b;
	
	public MouseCursorEvent(JButton b) {
		this.b = b;
	}
	@Override 
	public void mouseEntered(MouseEvent e) { // ���콺�� ��ư ���� �÷��� �� 
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));// �հ������ ���� ����
	}

	@Override 
	public void mouseExited(MouseEvent e) { // ���콺�� ��ư���� �ø��� �ʾ��� �� 
		b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ����Ʈ������ ���� 
	}
	
}
