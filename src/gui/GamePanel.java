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
	private JButton ready = new JButton();
	private JLabel readyOk = new JLabel();
	private JLabel question;
	private boolean playOk =false;
	private JLabel start ;


	public GamePanel() {
		this.setLayout(null);
		
		question = labelSet(question, "./image/Q2.png");
		start = labelSet(start,"./image/Q2.png");
		ready.setIcon(imageMake(200, 200, "./image/Ready.png"));
		
		add(tf);
		add(enter);
		add(ready);
		
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

		//add(question);
		//add(start);
	
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //��ư�� �̹����� �ٲ��ְ� �غ�Ϸ� ������ �˸�
				remove(ready);
				readyOk.setBounds(410, 400, 200, 200);
				add(readyOk);
	
				readyOk.setIcon(imageMake(200,200, "./image/ReadyOk.png"));
				playOk = true;
			}
		});

		tf.setBounds(350, 885, 200, 50);
		enter.setBounds(548, 885, 100, 50);
		ready.setBounds(410, 400, 200, 200);
		ready.setContentAreaFilled(false); //��ư ���뿵�� ����
		ready.setBorderPainted(false); //��ư �ܰ��� �����
		
		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
		enter.setFont(enter.getFont().deriveFont(15.0f));
		tf.setFont(enter.getFont().deriveFont(16.0f));


	}
	
	public static JLabel labelSet(JLabel l, String url) { //��ư�� �������ִ� �޼ҵ�
		ImageIcon img = new ImageIcon((url));
		img = new ImageIcon(img.getImage().getScaledInstance(980, 860, Image.SCALE_SMOOTH));
		l = new JLabel(img);
//		l.setBounds(10, 10, 980, 860);
		l.setBounds(10, 350, 980, 200);
		return l;
	}
	public static ImageIcon imageMake(int w, int h, String url) {
		ImageIcon img = new ImageIcon((url));
		img = new ImageIcon(img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		return img;
	}
	public boolean getPlayOk() {
		return this.playOk;
	}
	public JLabel getReadyOk() {
		return this.readyOk;
	}
}
