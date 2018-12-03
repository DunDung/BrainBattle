package thread;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BgmControlThread extends Thread{
	private Player intro; //��Ʈ�� ������ Ʋ���� ���� , �ܺο��� �������� ��������� �����Ͽ���.


	public BgmControlThread() throws FileNotFoundException, JavaLayerException, URISyntaxException {
		InputStream musicPath = this.getClass().getClassLoader().getResourceAsStream("Intro.mp3"); //URL�� �ϸ� ������ ���⿡ InputStream�� ���� ó���� �־���
		intro = new Player(musicPath); //intro�ʱ�ȭ
	}
	@Override
	public void run() {
		try {
			while(true) { //���α׷��� ������ ������ ����Ͽ� ������ �ݺ� ����Ѵ�.
				InputStream musicPath = this.getClass().getClassLoader().getResourceAsStream("GameProgress.mp3");
				Player gameProgress = new Player(musicPath);
				gameProgress.play();
			}
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}
	public void closeIntro() { //��Ʈ�� ������ ���ش�.
		intro.close();
	}
	//���� ȿ������ �ʿ��� ������ ȿ������ Ʋ���� �޼ҵ�
	public static void playSoundEffect(String musicName) throws InterruptedException, JavaLayerException, FileNotFoundException, URISyntaxException {
		InputStream musicPath = BgmControlThread.class.getClassLoader().getResourceAsStream(musicName);
		Player bgm = new Player(musicPath);
		bgm.play();
	}
}