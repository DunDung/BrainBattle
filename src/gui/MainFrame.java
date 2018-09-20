package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainFrame extends JFrame{
	private Container c = this.getContentPane(); //�߰�
	private String ip;
	private String nickName;
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
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getIp() {
		return ip;
	}
		
	
}


