package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChattingPanel extends JPanel{
	Socket socket = null; //������ ������ SocketŬ���� ����
	public JTextArea ta = new JTextArea();
	public JScrollPane text = new JScrollPane(ta);
	public JTextField tf = new JTextField(); //ä��â�� �Է��� ���� �Է��ϴ� Field
	public JButton send = new JButton("�Է�"); //ä��â�� �Է��� ���� �����ϴ� ��ư

	public ChattingPanel() {
		setLayout(null);
		add(text);
		add(tf);
		add(send);
		text.setBounds(20,20, 1430, 830);
		tf.setBounds(20,850,1300,60);
		send.setBounds(1320,850,130,60);
		
	}
}






