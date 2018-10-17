package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class IntroFrame extends JFrame{ 
	private MainFrame mainFrame; //���������� ����
	private Container c = this.getContentPane(); //IntroFrame�� ����Ʈ���� ���� �����̳�
	private JTextField ipField = new JTextField(); //ip�� �Է��� JTextField
	private JTextField nickNameField = new JTextField(); //�г����� �Է��� JTextField
	private JLabel ip = new JLabel("IP :"); //ip�� �Է��� �ʵ��� ���� �˷��� JLabel
	private JLabel nickName = new JLabel("�г���  :"); //�г����� �Է��� �ʵ��� ���� �˷��� JLabel
	private JButton inputOk = new JButton("�Է¿Ϸ�"); //�г��Ӱ� ip�� �Է� �Ŀ� ���� ��ư 
	private JButton ipDoNotKnow = new JButton("IP�� �𸥴ٸ�"); //ip�� �ƴ¹��� �˷��� ��ư

	private Socket socket;

	public IntroFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame; //MainFrame���� mainFrame �ʱ�ȭ

		setTitle("BrainBattle"); //Ÿ��Ʋ ����
		setLocation(new Point(700,350)); //��������� â�� ��ġ�� ����ش�.
		setPreferredSize(new Dimension(600, 400));//�������� â��ũ�� ����
		pack();//������Ʈ ũ�⸸ŭ â�� ũ�⸦ ����ش�.
		setLocationRelativeTo(null); //(null)�� �������ν� ȭ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â�� ������� ���μ����� ����

		setLayout(null); //���̾ƿ� ����
		c.add(ip); //IP : �߰�
		ip.setFont(ip.getFont().deriveFont(23.0f)); //�۾�ũ�� ����
		ip.setBounds(50,60,50,50); //��ġ����

		c.add(ipField); //ip�� �Է��� �ʵ� �߰�
		ipField.setFont(ipField.getFont().deriveFont(30.0f)); 
		ipField.setBounds(110,50, 430, 80); //�ʵ� ��ġ����

		c.add(nickName); //�г��� : �߰�
		nickName.setFont(nickName.getFont().deriveFont(18.0f)); //�۾�ũ�� ����
		nickName.setBounds(20,150,100,100); //��ġ����

		c.add(nickNameField); //�г����� �Է��� �ʵ� �߰�
		nickNameField.setFont(nickNameField.getFont().deriveFont(30.0f));
		nickNameField.setBounds(110,160, 430, 80); //��ġ����

		c.add(inputOk); //�Է¿Ϸ� ��ư �߰�
		inputOk.setFont(nickName.getFont().deriveFont(16.0f)); 
		inputOk.setBounds(240, 260, 150, 70);

		c.add(ipDoNotKnow); //ip�� �𸥴ٸ� ��ư �߰�
		ipDoNotKnow.setFont(ipDoNotKnow.getFont().deriveFont(12.0f)); 
		ipDoNotKnow.setBounds(419, 10, 120, 30);

		ipDoNotKnow.addActionListener(new ActionListener() { //ip�� �𸥴ٸ� ��ư�� Ŭ���� �̺�Ʈ �߰� �� ����
			public void actionPerformed(ActionEvent e) {
				c.remove(ipDoNotKnow); //ip�� �𸥴ٸ� ��ư�� �����
				JLabel ipManner= new JLabel("��� ������Ʈ���� ipconfig �˻� �� IPv4 �ּҸ� ����");
				c.add(ipManner); //ip�� ��� ���� ������ ���� �߰�
				ipManner.setBounds(210,10,330,30); //��ġ����
				ipManner.setFont(ipManner.getFont().deriveFont(13.0f)); //�۾�ũ�� ����
				//				revalidate();
				//				repaint();
			}
		});

		inputOk.addActionListener(new ActionListener() { //�Է¿Ϸ� ��ư�� Ŭ���� �̺�Ʈ �߰� �� ����
			public void actionPerformed(ActionEvent e) {
				mainFrame.setNickName(nickNameField.getText().toString()); //mainFrame�� nickName�ʵ带 IntroFrame�� nickNameField�� �ִ� �ؽ�Ʈ�� �ʱ�ȭ
				dispose(); //introFrameâ�� ����.
				String s =mainFrame.getNickName();
			//	mainFrame.getScore().setMyNickName(s);
				mainFrame.viewTrue(); //�Ⱥ��̰� �صξ��� mainFrame�� ���̰� �Ѵ�
				
				}
		});

		setVisible(true); //introFrame�� �����ش�.
	}

	public JTextField getIpFiedl() { //ipField�� getter
		return ipField;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}


}

