package thread;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

import gui.MainFrame;
import javazoom.jl.decoder.JavaLayerException;
import userState.UserState;

public class TimerThread extends Thread{
	private MainFrame mainFrame;
	private String [] timer = {"T60.png", "T59.png", "T58.png", "T57.png", "T56.png","T55.png", "T54.png",
			"T53.png", "T52.png", "T51.png", "T50.png", "T49.png", "T48.png", "T47.png",
			"T46.png", "T45.png", "T44.png", "T43.png", "T42.png", "T41.png", "T40.png", 
			"T39.png", "T38.png", "T37.png", "T36.png", "T35.png", "T34.png", "T33.png",
			"T32.png", "T31.png", "T30.png", "T29.png", "T28.png", "T27.png", "T26.png",
			"T25.png", "T24.png", "T23.png", "T22.png", "T21.png", "T20.png", "T19.png",
			"T18.png", "T17.png", "T16.png", "T15.png", "T14.png", "T13.png", "T12.png",
			"T11.png", "T10.png", "T9.png", "T8.png", "T7.png", "T6.png", "T5.png",
			"T4.png", "T3.png", "T2.png", "T1.png", "T0.png"}; //�ð��� ������ �̹��� �̸� �迭
	private static boolean timerStop = false; //Ÿ�̸Ӹ� ������ �˷��ִ� ����
	private boolean timerEnd = false; //Ÿ�̸Ӿ����带 ������ ���� ������ ����
	private boolean hint = false; //30�ʰ� ���� ��Ʈ�� �������� �˷��� ����

	public TimerThread(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		try {
			while(!timerEnd) { //�ٸ������尡 ������ ����Ǿ� Ÿ�̸Ӹ� ������ ������ ��� �ݺ� ����
				for(int i=0; i<timer.length; i++) { //60��
					if(timerStop) //������ ������ ���߾� timerStop�� true�� �ʱ�ȭ ���� ��
						break; //for�� ����������.
					if(i == 31) { //30�ʰ� ������ ��
						hint = true; //hint������ �ʱ�ȭ�ؼ� ���Ӿ����忡�� ��Ʈ �̹����� �����ش�.
					}
					if(i<51&&i>0) //10�� ������ �� �������� 1�ʾ� ���Ƿ� 10�ʰ� ���������� 1�ʾ� sleep�Ѵ�
						Thread.sleep(1000);
					
					if(i >= 51) { //10�ʰ� ������ �� ȿ������ Ų��
						try {
							soundOn();
						} catch (FileNotFoundException | JavaLayerException | URISyntaxException e) {
							e.printStackTrace();
						}
					}
					mainFrame.getGame().getTimer().setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(timer[i]))); //�̹����� ����ؼ� �ٲ��ָ� �ð��� ǥ���Ѵ�.
				}
				UserState.setTurnEnd(true); //���Ӿ����忡�� �̹� ���� �����ٴ� �� �� �� �ֵ��� turnEnd�� true�� �ʱ�ȭ.
				setTimerStop(false); //timerStop���� for���� ������ ��찡 �����Ƿ� for���� ���� �ڿ� �׻� false�� �ٲ��ش�.
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	//����������� getter, setter
	public static void setTimerStop(boolean stop) {
		TimerThread.timerStop = stop;
	}
	public boolean isHint() {
		return hint;
	}
	public void setHint(boolean hint) {
		this.hint = hint;
	}
	public void killTimer() { //timerThread�� �ܺο��� ���̱� ���� �޼ҵ�
		this.timerEnd = true;
		setTimerStop(true);
	}
	public void soundOn() throws FileNotFoundException, InterruptedException, JavaLayerException, URISyntaxException { //ȿ���� �޼ҵ�
		BgmControlThread.playSoundEffect("10second2.mp3");
	}
}

