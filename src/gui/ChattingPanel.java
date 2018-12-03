package gui;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class ChattingPanel extends JPanel{
	private	JTextArea ta = new JTextArea(){
		public void paintComponent(Graphics g) { //����̹����� �׷��ش�
			g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("ChatBackGround.png")).getImage(),0,0,null); //Graphics�� ���� g�� �̹����� �׷��ش�.
			setOpaque(false); // �������� �ʰ� ���������ν� �̹����� ���̰� �Ѵ�.
			super.paintComponent(g); //������Ʈ�� g�� �׸���.
		}
	}; //ä�õ� ���ڿ����� ����� Area
	private	JScrollPane ts = new JScrollPane(ta); //ä�ó����� ���� ��츦 ����� JScrollPane�� JTextArea������ ta�߰�
	private	JTextField tf = new JTextField(); //ä��â�� �Է��� ���� �Է��ϴ� Field
	private JButton enter = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("ChatSend.png"))); //enterŰ�� ������� ǥ��

	public ChattingPanel() {
		setLayout(null); //���̾ƿ� ����
		//�гο� �߰�
		add(ts);
		add(tf);
		add(enter);
		//���̾ƿ��� ���⿡ ��ġ�� ���� ������ �ش�.
		ts.setBounds(0,0, 450, 783);
		ts.setBorder(new LineBorder(Color.BLACK)); 
		tf.setBounds(0, 781,350, 50);
		enter.setBounds(349,780,101,50);
		//���۹�ư, ä��â, ä���Է�â ����ũ�� ����
		enter.setFont(enter.getFont().deriveFont(15.0f)); 
		tf.setFont(enter.getFont().deriveFont(16.0f));
		ta.setFont(enter.getFont().deriveFont(16.0f));
		//��� �׵θ�
		enter.setBorder(new LineBorder(Color.BLACK)); 
		tf.setBorder(new LineBorder(Color.BLACK));

	}
	public JTextArea getTa() { //ä��â getter
		return ta;
	}
	public JTextField getTf() { //�Է� �ʵ� getter
		return tf;
	}
	public JButton getEnter() { //���� ��ư getter
		return enter;
	}

}
