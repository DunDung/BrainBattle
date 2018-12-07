package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import userState.UserState;

public class IntroFrame extends JFrame{ 	

	private MainFrame mainFrame; //���������� ����
	private IntroPanel intro = new IntroPanel();


	public IntroFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame; //MainFrame���� mainFrame �ʱ�ȭ
		setTitle("BrainBattle"); //Ÿ��Ʋ ����
		setLocation(new Point(700,350)); //��������� â�� ��ġ�� ����ش�.
		setPreferredSize(new Dimension(820, 645));//�������� â��ũ�� ����
		pack();//������Ʈ ũ�⸸ŭ â�� ũ�⸦ ����ش�.
		setLocationRelativeTo(null); //(null)�� �������ν� ȭ���߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â�� ������� ���μ����� ����

		setLayout(null); //���̾ƿ� ����

		add(intro); //intro�г� �߰�
		intro.setBounds(0,0,810,630); //��ġ����
		setVisible(true); //introFrame�� �����ش�.
	}

	public JTextField getIpField() { //ipField�� getter
		return intro.ipField;
	}

	class IntroPanel extends JPanel{
		public void paintComponent(Graphics g) { //����̹��� ����
			g.drawImage(new ImageIcon(this.getClass().getClassLoader().getResource("IntroBackGround.png")).getImage(),0,0,null);
			setOpaque(false);
			super.paintComponent(g);
		} 

		private JTextField ipField = new JTextField(); //ip�� �Է��� JTextField
		private JTextField nickNameField = new JTextField(); //�г����� �Է��� JTextField
		private JButton ok = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("Ok.png")));; //�Է¿Ϸ�� ������ ��ư

		public IntroPanel() {
			this.setLayout(null); //���̾ƿ� ����

			//�� ��������� �߰�
			add(ipField);
			add(nickNameField);
			add(ok);

			//��ġ����, ��Ʈũ��, ���Ե� �۾�ü ����
			ipField.setBounds(160,190, 450,70);
			ipField.setFont(new Font("Dialog", Font.BOLD, 34));
			ipField.setText(" "); // �۾��� �̻ڰ� ���̱� ���� ����

			nickNameField.setBounds(160,355,450,70);
			nickNameField.setFont(new Font("Dialog", Font.BOLD, 34));
			nickNameField.setText(" ");

			ok.setBounds(282, 500, 237, 74);

			ok.addActionListener(new ActionListener() { //ok ��ư�� Ŭ���� �̺�Ʈ �߰� �� ����
				public void actionPerformed(ActionEvent e) {
					mainFrame.setIp(ipField.getText().toString().trim()); //ipField���� ip�� ������ ������������ ip�ʵ忡 �����Ѵ�.
					mainFrame.getScore().setMyNickName(nickNameField.getText().toString().trim()); //mainFrame�� nickName�ʵ带 IntroFrame�� nickNameField�� �ִ� �ؽ�Ʈ�� �ʱ�ȭ
					UserState.setNickState(true); //�г��ӻ��¸� true�� �ʱ�ȭ
					dispose(); //introFrameâ�� ����.
					mainFrame.viewTrue(); //�Ⱥ��̰� �صξ��� mainFrame�� ���̰� �Ѵ�
				}
			});
			ok.addMouseListener(new MouseAdapter() { //���콺 Ŀ�� �̺�Ʈ �߰�
				@Override 
				public void mouseEntered(MouseEvent e) { // ���콺�� ��ư ���� �÷��� �� 
					ok.setCursor(new Cursor(Cursor.HAND_CURSOR));// �հ������ ���� ����
				}

				@Override 
				public void mouseExited(MouseEvent e) { // ���콺�� ��ư���� �ø��� �ʾ��� �� 
					ok.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ����Ʈ������ ���� 
				}
			});
		}
	}
}
