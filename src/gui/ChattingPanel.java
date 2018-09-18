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
		ts.setBounds(20,20, 1440, 830);
		tf.setBounds(20,850,1340,60);
		enter.setBounds(1360,850,100,60);
		
		enter.setFont(enter.getFont().deriveFont(16.0f)); //���� ��ư �۾�ũ�� ����
		
		enter.setBackground(Color.YELLOW); //���� ��ư ���� ����
		ta.setBackground(Color.LIGHT_GRAY); //ä��â ���� ����
		
	
		
	}
	public JTextArea getTa() { //ä��â getter
		return ta;
	}
	public JTextField getTf() { //�Է� �ʵ� getter
		return tf;
	}

	public void taAdd(String a) { //�߰�
		this.ta.append(a);
	}

	
}




