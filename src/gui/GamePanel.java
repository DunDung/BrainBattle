package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{
	private JTextField tf = new JTextField();
	private JButton enter = new JButton("답안전송");
	private JButton ready;
	private JButton readyOk;
	private MainFrame mainFrame;
	private JLabel question;
	private boolean playOk =false;

	public GamePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		ready = buttonSet(ready, "./image/Ready.png");
		question = labelSet(question, "./image/Q2.png");
		
		add(tf);
		add(enter);
		add(ready);
		//add(question);
		
		ready.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //버튼의 이미지를 바꿔주고 준비완료 됐음을 알림
				remove(ready);
				readyOk = buttonSet(readyOk, "./image/ReadyOk.png");
				add(readyOk);
				mainFrame.getChat().getTa().append("System :"+mainFrame.getScore().getMyNickName()+"님 준비 완료\n");
				playOk = true;
			}
		});

		tf.setBounds(350, 885, 200, 50);
		enter.setBounds(548, 885, 100, 50);
		
		enter.setBackground(Color.YELLOW); //전송 버튼 배경색 변경
		enter.setFont(enter.getFont().deriveFont(15.0f));
		tf.setFont(enter.getFont().deriveFont(16.0f));
		
		//ready.setFont(new Font("Dialog", Font.BOLD, 40));
		//ready.setFont(ready.getFont().deriveFont(40.0f));

	}
	public static JButton buttonSet(JButton b, String url) { //버튼을 셋팅해주는 메소드
		ImageIcon img = new ImageIcon((url));
		img = new ImageIcon(img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		b = new JButton(img);
		b.setContentAreaFilled(false); //버튼 내용영역 비우기
		b.setBorderPainted(false); //버튼 외곽선 지우기
		b.setBounds(410, 500, 200, 200);
		return b;
	}
	public static JLabel labelSet(JLabel l, String url) { //버튼을 셋팅해주는 메소드
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
