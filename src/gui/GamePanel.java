package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{ 	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("./image/BackGround.png").getImage(),0,0,null);
		setOpaque(false);
		super.paintComponent(g);
	}
	private JTextField tf = new JTextField();
	private JButton enter = new JButton("�������");
	private JButton ready = new JButton(new ImageIcon("./image/Ready.png"));
	private JLabel readyOk = new JLabel(new ImageIcon("./image/ReadyOk.png"));
	private boolean playOk =false;
	private JLabel quiz= new JLabel(new ImageIcon("./image/Rule.png"));


	public GamePanel() {
		this.setLayout(null);
		
		add(tf);
		add(enter);
		add(ready);
		add(quiz);
		add(readyOk);
		
		
		quiz.setVisible(false);
		readyOk.setVisible(false);
		
		tf.setBounds(350, 885, 200, 50);
		enter.setBounds(548, 885, 100, 50);
		ready.setBounds(410, 400, 200, 200);
		quiz.setBounds(5, 5, 968, 875);
		readyOk.setBounds(410, 400, 200, 200);
		
		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
		enter.setFont(enter.getFont().deriveFont(15.0f));
		tf.setFont(enter.getFont().deriveFont(16.0f));

		ready.setContentAreaFilled(false); //��ư ���뿵�� ����
		ready.setBorderPainted(false); //��ư �ܰ��� �����
		
		
		ready.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseEntered(MouseEvent e) { // ���콺�� X��ư ���� �÷��� �� 
				ready.setCursor(new Cursor(Cursor.HAND_CURSOR));// �հ������ 
			}

			@Override 
			public void mouseExited(MouseEvent e) { // ���콺�� X��ư���� �ø��� �ʾ��� �� 
				ready.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ����Ʈ�� 
			}
		}); 
		
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
}
