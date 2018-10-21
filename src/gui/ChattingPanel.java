package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChattingPanel extends JPanel{

	private	JTextArea ta = new JTextArea(); //ä�õ� ���ڿ����� ����� Area
	private	JScrollPane ts = new JScrollPane(ta); //ä�ó����� ���� ��츦 ����� JScrollPane�� JTextArea������ ta�߰�
	private	JTextField tf = new JTextField(); //ä��â�� �Է��� ���� �Է��ϴ� Field
	private JButton enter = new JButton("����"); //enterŰ�� ������� ǥ��

	public ChattingPanel() {
		setLayout(null); //���̾ƿ� ����
		//�гο� �߰�
		add(ts);
		add(tf);
		add(enter);
		//���̾ƿ��� ���⿡ ��ġ�� ���� ������ �ش�.
		ts.setBounds(0,0, 450, 793);
		tf.setBounds(0, 791,350,50);
		enter.setBounds(349,790,100,50);
		//���۹�ư, ä��â, ä���Է�â ����ũ�� ����
		enter.setFont(enter.getFont().deriveFont(15.0f)); 
		tf.setFont(enter.getFont().deriveFont(16.0f));
		ta.setFont(enter.getFont().deriveFont(16.0f));
		
		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
		ta.setBackground(Color.LIGHT_GRAY); //ä��â ���� ����
		
	
		
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

	public void taAdd(String a) { //ä��ȭ�鿡 ����ڰ� �Է��� �ؽ�Ʈ�� �߰��ϴ� �޼ҵ�
		this.ta.append(a);
	}

	
}




