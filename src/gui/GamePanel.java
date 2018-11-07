package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
	private JTextField tf = new JTextField();
	private JButton enter = new JButton("�������");
	private JButton ready = new JButton();
	private JLabel readyOk = new JLabel();
	private MainFrame mainFrame;
	private JLabel question;
	private boolean playOk =false;
	private JLabel start ;

	public GamePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		ready = setButton(ready, "./image/Ready.png");
		question = labelSet(question, "./image/Q2.png");
		start = labelSet(start,"./image/Q2.png");

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
		add(start);
		start.setVisible(false);
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //��ư�� �̹����� �ٲ��ְ� �غ�Ϸ� ������ �˸�
				remove(ready);
				ImageIcon img = new ImageIcon(("./image/ReadyOk.png"));
				img = new ImageIcon(img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
				readyOk = new JLabel(img);
				add(readyOk);
				readyOk.setBounds(410, 500, 200, 200);
				playOk = true;
			}
		});

		tf.setBounds(350, 885, 200, 50);
		enter.setBounds(548, 885, 100, 50);

		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
		enter.setFont(enter.getFont().deriveFont(15.0f));
		tf.setFont(enter.getFont().deriveFont(16.0f));

		//ready.setFont(new Font("Dialog", Font.BOLD, 40));
		//ready.setFont(ready.getFont().deriveFont(40.0f));

	}
	public static JButton setButton(JButton b, String url) { //��ư�� �������ִ� �޼ҵ�
		ImageIcon img = new ImageIcon((url));
		img = new ImageIcon(img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		b.setIcon(img);
		b.setContentAreaFilled(false); //��ư ���뿵�� ����
		b.setBorderPainted(false); //��ư �ܰ��� �����
		b.setBounds(410, 500, 200, 200);
		return b;
	}
	public static JLabel labelSet(JLabel l, String url) { //��ư�� �������ִ� �޼ҵ�
		ImageIcon img = new ImageIcon((url));
		img = new ImageIcon(img.getImage().getScaledInstance(980, 860, Image.SCALE_SMOOTH));
		l = new JLabel(img);
//		l.setBounds(10, 10, 980, 860);
		l.setBounds(10, 350, 980, 200);
		return l;
	}
	public boolean getPlayOk() {
		return this.playOk;
	}
	public void startGame() {
		this.readyOk.setVisible(false);
		this.start.setVisible(true);
	}
}
