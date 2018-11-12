package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


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

		add(intro);
		intro.setBounds(0,0,810,630);
		//	revalidate();
		//	repaint();
		setVisible(true); //introFrame�� �����ش�.
		
	}
	public JTextField getIpField() { //ipField�� getter
		return intro.ipField;
	}

	class IntroPanel extends JPanel{
		public void paintComponent(Graphics g) {
			 URL IntroBackGroundImg = this.getClass().getClassLoader().getResource("IntroBackGround.png");

			g.drawImage(new ImageIcon(IntroBackGroundImg).getImage(),0,0,null);
			setOpaque(false);
			super.paintComponent(g);
		} 
		
		private JTextField ipField = new JTextField(); //ip�� �Է��� JTextField
		private JTextField nickNameField = new JTextField(); //�г����� �Է��� JTextField
		private URL okImg =this.getClass().getClassLoader().getResource("Ok.png");
		private JButton ok = new JButton(new ImageIcon(okImg));
		public IntroPanel() {
			this.setLayout(null);
			add(ipField);
			add(nickNameField);
			add(ok);

			ipField.setBounds(160,190, 450,70);
			ipField.setFont(new Font("Dialog", Font.BOLD, 34));
			ipField.setText(" ");

			nickNameField.setBounds(160,355,450,70);
			nickNameField.setFont(new Font("Dialog", Font.BOLD, 34));
			nickNameField.setText(" ");
			
			ok.setBounds(280, 500, 237, 74);

			ok.addActionListener(new ActionListener() { //�Է¿Ϸ� ��ư�� Ŭ���� �̺�Ʈ �߰� �� ����
				public void actionPerformed(ActionEvent e) {

					mainFrame.getScore().setMyNickName(nickNameField.getText().toString().trim()); //mainFrame�� nickName�ʵ带 IntroFrame�� nickNameField�� �ִ� �ؽ�Ʈ�� �ʱ�ȭ
					mainFrame.getScore().setNcikState();
					dispose(); //introFrameâ�� ����.
					mainFrame.viewTrue(); //�Ⱥ��̰� �صξ��� mainFrame�� ���̰� �Ѵ�
				}
			});
		}
		
	}
}



