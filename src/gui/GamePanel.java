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

import userState.UserState;

public class GamePanel extends JPanel{ 	
	public void paintComponent(Graphics g) { //��� �ֱ�
		g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("BackGround.png")).getImage(),0,0,null);//Graphics�� ���� g�� �̹����� �׷��ش�.
		setOpaque(false); //�������� �ʰ� �����ν� ����̹����� ���̰� �Ѵ�.
		super.paintComponent(g); //g�� ������Ʈ�� �׸���.
	}
	private JTextField tf = new JTextField(); // ����� �Է��� �ؽ�Ʈ �ʵ�
	private JButton enter = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("AnswerSend.png"))); //����� ������ ��ư
	private JButton ruleButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("RuleButton.png"))); //���Ӽ����� ������ ��ư
	private JLabel rule = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Rule.png"))); //���Ӽ����� ����� ��
	private JButton ready = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("Ready.png"))); //���� ��ư
	private JLabel readyOk = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("ReadyOk.png"))); //���� ��ư Ŭ���� ������ ��
	private JLabel quiz= new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("StartCount3.png"))); //��� ����ؼ� ����� ��. ó���� ��ŸƮī��Ʈ�� ���� 3���� �ʱ�ȭ
	private JLabel goalLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Goal.png"))); //��ǥ���� ������ ������ ��
	private JButton [] scoreImg = {new JButton("3", new ImageIcon(this.getClass().getClassLoader().getResource("3.png"))), //��ǥ���� ������ ������ 3�� ��ư
								   new JButton("5", new ImageIcon(this.getClass().getClassLoader().getResource("5.png"))), //��ǥ���� ������ ������ 5�� ��ư
								   new JButton("7",new ImageIcon(this.getClass().getClassLoader().getResource("7.png"))),  //��ǥ���� ������ ������ 7�� ��ư
								   new JButton("10",new ImageIcon(this.getClass().getClassLoader().getResource("10.png"))),//��ǥ���� ������ ������ 10�� ��ư
							   	   new JButton("15",new ImageIcon(this.getClass().getClassLoader().getResource("15.png")))}; //��ǥ���� ������ ������ 15�� ��ư
	private JLabel waitGoalScore = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("setGoal.png"))); //���÷��̾ ��ǥ������ ������ ���� ����϶�� ������ ����� ��
	private JLabel timer = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("T60.png"))); //Ÿ�̸Ӹ� ǥ���� �� ÷�� 60���� ����
	private JButton yes =  new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("Yes.png"))); //�������� �� �ٽ� �����ϰڳĴ� ������ �����ϴ� Yes��ư
	private JButton no =  new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("No.png")));//��������� �ٽ� �����ϰڳĴ� ������ �����ϴ� No��ư
	private int goalScore = 0; //��ư�� ���� ��ǥ������ ���� �� �ʱ�ȭ�� ����
//	private boolean playOk =false; //�غ�Ϸ����� �ƴ����� ��Ÿ�� �� ����
//	private boolean turnEnd = false; //�� ���Ḧ ��Ÿ�� ����
//	private boolean wrong = false; //���� �� ���� Ʋ������ ��Ÿ�� ����
//	private boolean otherCorrect = false; //������ �������� ��Ÿ�� ����
//	private boolean iAmCorrect  = false; //���� �������� ��Ÿ�� ����

	public GamePanel() { //������
		this.setLayout(null); //���̾ƿ� ����
		
		//�гο� �߰� 
		add(ruleButton); 
		add(ready);
		add(quiz);
		add(readyOk);
		add(waitGoalScore);
		add(rule);
		add(timer);
		
		//������ ��ġ �� ũ�� ����
		timer.setBounds(829, 2, 150, 150);
		rule.setBounds(414, 1, 565, 386);
		ruleButton.setBounds(760, 1, 219,87);
		ready.setBounds(410, 400, 250, 210);
		readyOk.setBounds(410, 400, 250, 210);
		quiz.setBounds(0, 0, 980, 885);
		waitGoalScore.setBounds(0, 0, 980, 885);
		
		//ó���� �������� �ʰ� ���߿� ��Ȳ�� �°� �����ش�.
		timer.setVisible(false);
		quiz.setVisible(false);
		readyOk.setVisible(false);
		waitGoalScore.setVisible(false);
		rule.setVisible(false);
		
		//����� �Է��ϴ� �ؽ�Ʈ�ʵ忡 ���Ե� ������ ũ�⸦ ����
		tf.setFont(enter.getFont().deriveFont(16.0f));

		//���ư�� ��Ʈ�� �ؽ�Ʈ�ʵ忡 ������ ��� �׵θ��� ����
		ruleButton.setBorder(new LineBorder(Color.BLACK));
		enter.setBorder(new LineBorder(Color.BLACK)); 
		tf.setBorder(new LineBorder(Color.BLACK));

		ready.setContentAreaFilled(false); //���� ��ư ���뿵�� ����
		ready.setBorderPainted(false); //���� ��ư �ܰ��� �����
		
		//���ư�� �����ư�� ���콺 Ŀ�� �̺�Ʈ �߰�
		ruleButton.addMouseListener(new MouseCursorEvent());
		ready.addMouseListener(new MouseCursorEvent());
			
		
		ready.addActionListener(new ActionListener(){ //�����ư�� �׼Ǹ����� �߰�
			@Override 
			public void actionPerformed(ActionEvent e) { //Ŭ����
				ready.setVisible(false); //�����ư�� �Ⱥ��̰��ϰ�
				readyOk.setVisible(true); //readyOK���� ���̰� �Ѵ�
				UserState.setPlayOk(true); //�غ�Ϸᰡ ������ ��Ÿ�� playOk������ true�� �ʱ�ȭ
			}
		});
	}
	
	//��������� getter/setter
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
	public JButton getRuleButton() {
		return ruleButton;
	}
//	public boolean getPlayOk() {
//		return this.playOk;
//	}
//	public boolean getTurnEnd() {
//		return turnEnd;
//	}
//	public void setTurnEnd(boolean turn) {
//		turnEnd = turn;
//	}
//	public void setWrong(boolean wrong) {
//		this.wrong = wrong;
//	}
//	public boolean getWrong() {
//		return wrong;
//	}
//	public void setOtherCorrect(boolean otherCorrect) {
//		this.otherCorrect = otherCorrect;
//	}
//	public boolean getOtherCorrect() {
//		return otherCorrect;
//	}
//	public void setIAmCorrect(boolean iAmCorrect) {
//		this.iAmCorrect = iAmCorrect;
//	}
//	public boolean getIAmCorrect() {
//		return iAmCorrect;
//	}
	public JLabel getTimer() {
		return timer;
	}
	public JButton getYes() {
		return yes;
	}
	public JButton getNo() {
		return no;
	}
	//���ӽ��� �� ������� ��ư�� ����� �Է��� �ؽ�Ʈ �ʵ带 ������ �޼ҵ�
	public void setTfAndEnter() {
		add(tf);
		add(enter);
		tf.setBounds(0, 885, 878, 50);
		enter.setBounds(878, 885, 101, 50);
	}
	//�������� �� ������ �ٽ� ������ �������� ���� ������ ����ϴ� yes��ư, no��ư�� �߰��ϰ� ��Ÿ�� �޼ҵ�
	public void setYesAndNo() {
		add(yes);
		add(no);
		yes.addMouseListener(new MouseCursorEvent()); //���콺Ŀ�� �̺�Ʈ �߰�
		no.addMouseListener(new MouseCursorEvent());  //���콺Ŀ�� �̺�Ʈ �߰�
		yes.setBounds(198, 611, 237, 74);
		no.setBounds(543, 611, 237, 74);
	}
	
	public void setScoreImg() { //��ǥ���� ���� �� ��Ÿ�� ������Ʈ���� �����ϴ� �޼ҵ�
		int y = 0; //y��ǥ���� 0���� �켱 �ʱ�ȭ
		add(goalLabel); //��ǥ���� �����̶� ���� �ȳ��� �� �߰�
		goalLabel.setBounds(0,y, 980 ,148);
		for(int i = 0; i<scoreImg.length; i++) {
			y+= 148; //y��ǥ�� 148�� �����ش�
			add(scoreImg[i]); //���� ��ư �߰�
			scoreImg[i].setBounds(0, y, 980, 148); //��ġ����
			scoreImg[i].addMouseListener(new MouseCursorEvent()); //���� ��ư�� ���콺 Ŀ�� �̺�Ʈ �߰�
			scoreImg[i].addActionListener(new ActionListener() { //���� ��ư�� �׼Ǹ����� �߰�
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton temp = (JButton) e.getSource(); //Ŭ���� ��ư���� temp�� �ʱ�ȭ
					goalScore = Integer.parseInt(temp.getText()); //Ŭ���� ��ư�� �̸� ������ text������ ��ǥ������ ��Ÿ�� goalScore�ʱ�ȭ
					//��ǥ���� ���� �� ����ߴ� ������Ʈ���� ���� �Ⱥ��̰� �Ѵ�.
					goalLabel.setVisible(false);
					for(JButton b : scoreImg)
						b.setVisible(false);
				}
			});
		}
	}
	
	//���콺 Ŀ�� �̺�Ʈ
	class MouseCursorEvent extends MouseAdapter {
		
		@Override 
		public void mouseEntered(MouseEvent e) { // ���콺�� ��ư ���� �÷��� �� 
			setCursor(new Cursor(Cursor.HAND_CURSOR));// Ŀ���� �հ������ ���� ����
			if(e.getSource().equals(ready)) //�����ư �� ��
				ready.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("MouseOnReady.png"))); //ready�� �̹����� �ٲ��ش�.
			if(e.getSource().equals(ruleButton)) //���Ӽ��� ��ư�� ��
				rule.setVisible(true); //���Ӽ����� ����� rule ���� �����ش�.
		}

		@Override 
		public void mouseExited(MouseEvent e) { // ���콺�� ��ư���� �ø��� �ʾ��� �� 
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� ����Ʈ������ ����
			if(e.getSource().equals(ready))  //�����ư �� ��
				ready.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Ready.png")));//�ٽ� ���� �̹����� �ٲ��ش�.
			if(e.getSource().equals(ruleButton))
				rule.setVisible(false); //rule���� ������� �Ѵ�.
		}
		
	}
}


