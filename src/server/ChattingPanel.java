package server;


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
	JTextArea ta = new JTextArea(); //ä�õ� ���ڿ����� ����� Area
	JScrollPane ts = new JScrollPane(ta); //ä�ó����� ���� ��츦 ����� JScrollPane
	JTextField tf = new JTextField(); //ä��â�� �Է��� ���� �Է��ϴ� Field

	public ChattingPanel() {
		setLayout(null);
		add(ts);
		add(tf);
		ts.setBounds(20,20, 1430, 830);
		tf.setBounds(20,850,1300,60);			
	}
	
	public void taAdd(String a) { //�߰�
		this.ta.append(a);
	}
}







