package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.ui.Cursor;

public class GamePanel extends JPanel{
	private JTextField tf = new JTextField();
	private JButton enter = new JButton("�������");
	private JButton ready = new JButton();
	private MainFrame mainFrame;
	private JLabel question;
	private boolean playOk =false;

	public GamePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		ready = setButton(ready, "./image/Ready.png");
		question = labelSet(question, "./image/Q2.png");
		
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
		
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //��ư�� �̹����� �ٲ��ְ� �غ�Ϸ� ������ �˸�
				setButton(ready, "./image/ReadyOk.png");
				mainFrame.getChat().getTa().append("System :"+mainFrame.getScore().getMyNickName()+"�� �غ� �Ϸ�\n");
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
		l.setBounds(10, 10, 980, 860);
		return l;
	}
	public boolean getPlayOk() {
		return this.playOk;
	}
}
