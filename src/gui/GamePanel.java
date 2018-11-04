package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{
	private JTextField tf = new JTextField();
	private JButton enter = new JButton("�������");
	private JButton ready;
	private JButton readyOk;
	private MainFrame mainFrame;

	public GamePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		ready = buttonSet(ready, "./image/Ready.png");

		add(tf);
		add(enter);
		add(ready);
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //��ư�� �̹����� �ٲ��ְ� �غ�Ϸ� ������ �˸�
				remove(ready);
				readyOk = buttonSet(readyOk, "./image/ReadyOk.png");
				add(readyOk);
				mainFrame.getChat().getTa().append("System :"+mainFrame.getScore().getMyNickName()+"�� �غ� �Ϸ�");
			}
		});

		tf.setBounds(350, 885, 200, 50);
		enter.setBounds(548, 885, 100, 50);
		ready.setBounds(410, 500, 200, 200);

		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
		enter.setFont(enter.getFont().deriveFont(15.0f));
		tf.setFont(enter.getFont().deriveFont(16.0f));
		
		//ready.setFont(new Font("Dialog", Font.BOLD, 40));
		//ready.setFont(ready.getFont().deriveFont(40.0f));

	}
	public static JButton buttonSet(JButton b, String url) { //��ư�� �������ִ� �޼ҵ�
		ImageIcon img = new ImageIcon((url));
		img = new ImageIcon(img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		b = new JButton(img);
		b.setContentAreaFilled(false); //��ư ���뿵�� ����
		b.setBorderPainted(false); //��ư �ܰ��� �����
		b.setBounds(410, 500, 200, 200);
		return b;
	}
}
