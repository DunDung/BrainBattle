package userState;

public class UserState {
	private static boolean playOk =false; //�غ�Ϸ����� �ƴ����� ��Ÿ�� �� ����
	private static boolean turnEnd = false; //�� ���Ḧ ��Ÿ�� ����
	private static boolean wrong = false; //���� �� ���� Ʋ������ ��Ÿ�� ����
	private static boolean otherCorrect = false; //������ �������� ��Ÿ�� ����
	private static boolean myCorrect  = false; //���� �������� ��Ÿ�� ����
	private static boolean nickState = false; //���� �г����� ������ �������� �ƴ��� ��Ÿ�� ����
	
	
	public static boolean isPlayOk() {
		return playOk;
	}
	public static void setPlayOk(boolean playOk) {
		UserState.playOk = playOk;
	}
	public static boolean isTurnEnd() {
		return turnEnd;
	}
	public static void setTurnEnd(boolean turnEnd) {
		UserState.turnEnd = turnEnd;
	}
	public static boolean isWrong() {
		return wrong;
	}
	public static void setWrong(boolean wrong) {
		UserState.wrong = wrong;
	}
	public static boolean isOtherCorrect() {
		return otherCorrect;
	}
	public static void setOtherCorrect(boolean otherCorrect) {
		UserState.otherCorrect = otherCorrect;
	}
	public static boolean isMyCorrect() {
		return myCorrect;
	}
	public static void setMyCorrect(boolean myCorrect) {
		UserState.myCorrect = myCorrect;
	}
	public static boolean isNickState() {
		return nickState;
	}
	public static void setNickState(boolean nickState) {
		UserState.nickState = nickState;
	}
}
