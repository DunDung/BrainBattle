package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import server.MainServer;


public class MainFrame extends JFrame{
	JButton test;
	public MainFrame() {
		super("test");
		setLocation(new Point(700,350)); //��������� â�� ��ġ�� ����ش�.
		setPreferredSize(new Dimension(1000, 700));//�������� â��ũ�� ����
		setLayout(new GridLayout(0,2,30,30));
		pack();//������Ʈ ũ�⸸ŭ â�� ũ�⸦ ����ش�.
		setLocationRelativeTo(null); //(null)�� �������ν� ȭ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â�� ������� ���μ����� ����
		add(new JTextField());
		test = new JButton("�Է�");
		test.addActionListener(new ButtonEvent());
		add(test);
		setVisible(true); //�������� �����ش�.
		

	}

}
class ButtonEvent implements ActionListener{ 
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		MainServer.re_thread.start();
		MainServer.se_thread.start();
s
	}
}
