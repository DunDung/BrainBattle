package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MainFrame;

public class GoalScoreThread extends Thread {
	private Socket socket;
	private MainFrame mainFrame;
	public GoalScoreThread(MainFrame mainFrame, Socket socket) {
		this.mainFrame = mainFrame;
		this.socket = socket;
	}
	@Override
	public void run() {
		try {

			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			mainFrame.getGame().getWaitGoalScore().setVisible(true);
			while(true) {
				String receiveString = buf.readLine(); //Ŭ���̾�Ʈ�� ���� ���ڿ���  �о receiveSring�� �����Ѵ�.
				String [] receiveArray = receiveString.split("/");

				if(receiveArray[0].equals("goal")) {
					mainFrame.getGame().setGoalScore(Integer.parseInt(receiveArray[1]));
					mainFrame.getGame().getWaitGoalScore().setVisible(false);
					break;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}