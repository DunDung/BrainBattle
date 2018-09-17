package server;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainFrame extends JFrame{
	Container c = this.getContentPane(); //�߰�
	private ChattingPanel chat = new ChattingPanel();
	public MainFrame() {
		setLocation(new Point(700,350)); //��������� â�� ��ġ�� ����ش�.
		setPreferredSize(new Dimension(1500, 1000));//�������� â��ũ�� ����
		c.add(chat); //����
		pack();//������Ʈ ũ�⸸ŭ â�� ũ�⸦ ����ش�.
		setLocationRelativeTo(null); //(null)�� �������ν� ȭ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â�� ������� ���μ����� ����
		setVisible(true); //�������� �����ش�.
	
	}
	public ChattingPanel getChat() {
		return chat;
	}
}


