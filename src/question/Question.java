package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
	private static Map<String, String> questionMap = new HashMap<>(); //������ ���� ������ Map
	private static Map<String, String> questionHintMap = new HashMap<>(); //������ �� ������ ��Ʈ�� ������ Map
	private  List<String> questionList = new ArrayList<>(); //���� Image�� �̸��� ����� List

	public Question() { //��������鿡 ������ ���õ� ������ ����
		Question.questionMap.put("Q1.png", "11");
		Question.questionHintMap.put("Q1.png", "Q1Hint.png");
		questionList.add("Q1.png");
		Question.questionMap.put("Q2.png", "����");
		Question.questionHintMap.put("Q2.png", "Q2Hint.png");
		questionList.add("Q2.png");
		Question.questionMap.put("Q3.png", "87");
		Question.questionHintMap.put("Q3.png", "Q3Hint.png");
		questionList.add("Q3.png");
		Question.questionMap.put("Q4.png", "7");
		Question.questionHintMap.put("Q4.png", "Q4Hint.png");
		questionList.add("Q4.png");
		Question.questionMap.put("Q5.png", "�����");
		Question.questionHintMap.put("Q5.png", "Q5Hint.png");
		questionList.add("Q5.png");
		Question.questionMap.put("Q6.png", "220");
		Question.questionHintMap.put("Q6.png", "Q6Hint.png");
		questionList.add("Q6.png");
		Question.questionMap.put("Q7.png", "545+5=550");
		Question.questionHintMap.put("Q7.png", "Q7Hint.png");
		questionList.add("Q7.png");
		Question.questionMap.put("Q8.png", "9717");
		Question.questionHintMap.put("Q8.png", "Q8Hint.png");
		questionList.add("Q8.png");
		Question.questionMap.put("Q9.png", "34");
		Question.questionHintMap.put("Q9.png", "Q9Hint.png");
		questionList.add("Q9.png");
		Question.questionMap.put("Q10.png", "35");
		Question.questionHintMap.put("Q10.png", "Q10Hint.png");
		questionList.add("Q10.png");
		Question.questionMap.put("Q11.png", "42");
		Question.questionHintMap.put("Q11.png", "Q11Hint.png");
		questionList.add("Q11.png");
		Question.questionMap.put("Q12.png", "���°�");
		Question.questionHintMap.put("Q12.png", "Q12Hint.png");
		questionList.add("Q12.png");
		Question.questionMap.put("Q13.png", "ȸ����");
		Question.questionHintMap.put("Q13.png", "Q13Hint.png");
		questionList.add("Q13.png");
		Question.questionMap.put("Q14.png", "3");
		Question.questionHintMap.put("Q14.png", "Q14Hint.png");
		questionList.add("Q14.png");
		Question.questionMap.put("Q15.png", "21");
		Question.questionHintMap.put("Q15.png", "Q15Hint.png");
		questionList.add("Q15.png");
		Question.questionMap.put("Q16.png", "3");
		Question.questionHintMap.put("Q16.png", "Q16Hint.png");
		questionList.add("Q16.png");
		Question.questionMap.put("Q17.png", "���ν�Ÿ��");
		Question.questionHintMap.put("Q17.png", "Q17Hint.png");
		questionList.add("Q17.png");
		Question.questionMap.put("Q18.png", "�ﰭ����");
		Question.questionHintMap.put("Q18.png", "Q18Hint.png");
		questionList.add("Q18.png");
		Question.questionMap.put("Q19.png", "��纴");
		Question.questionHintMap.put("Q19.png", "Q19Hint.png");
		questionList.add("Q19.png");
		Question.questionMap.put("Q20.png", "ü�Թٶ�");
		Question.questionHintMap.put("Q20.png", "Q20Hint.png");
		questionList.add("Q20.png");
		Question.questionMap.put("Q21.png", "����");
		Question.questionHintMap.put("Q21.png", "Q21Hint.png");
		questionList.add("Q21.png");
		Question.questionMap.put("Q22.png", "��ũ��");
		Question.questionHintMap.put("Q22.png", "Q22Hint.png");
		questionList.add("Q22.png");
		Question.questionMap.put("Q23.png", "�ŵ�����");
		Question.questionHintMap.put("Q23.png", "Q23Hint.png");
		questionList.add("Q23.png");
		Question.questionMap.put("Q24.png", "������");
		Question.questionHintMap.put("Q24.png", "Q24Hint.png");
		questionList.add("Q24.png");
		Question.questionMap.put("Q25.png", "���õ��");
		Question.questionHintMap.put("Q25.png", "Q25Hint.png");
		questionList.add("Q25.png");
		Question.questionMap.put("Q26.png", "����");
		Question.questionHintMap.put("Q26.png", "Q26Hint.png");
		questionList.add("Q26.png");
		Question.questionMap.put("Q27.png", "0");
		Question.questionHintMap.put("Q27.png", "Q27Hint.png");
		questionList.add("Q27.png");
		Question.questionMap.put("Q28.png", "5");
		Question.questionHintMap.put("Q28.png", "Q28Hint.png");
		questionList.add("Q28.png");
		Question.questionMap.put("Q29.png", "6");
		Question.questionHintMap.put("Q29.png", "Q29Hint.png");
		questionList.add("Q29.png");
		Question.questionMap.put("Q30.png", "45");
		Question.questionHintMap.put("Q30.png", "Q30Hint.png");
		questionList.add("Q30.png");
		Question.questionMap.put("Q31.png", "156");
		Question.questionHintMap.put("Q31.png", "Q31Hint.png");
		questionList.add("Q31.png");	
		Question.questionMap.put("Q32.png", "�״�����");
		Question.questionHintMap.put("Q32.png", "Q32Hint.png");
		questionList.add("Q32.png");
		Question.questionMap.put("Q33.png", "S");
		Question.questionHintMap.put("Q33.png", "Q33Hint.png");
		questionList.add("Q33.png");
		Question.questionMap.put("Q34.png", ".");
		Question.questionHintMap.put("Q34.png", "Q34Hint.png");
		questionList.add("Q34.png");
		Question.questionMap.put("Q35.png", "26");
		Question.questionHintMap.put("Q35.png", "Q35Hint.png");
		questionList.add("Q35.png");
		Question.questionMap.put("Q36.png", "1");
		Question.questionHintMap.put("Q36.png", "Q36Hint.png");
		questionList.add("Q36.png");
		Question.questionMap.put("Q37.png", "9");
		Question.questionHintMap.put("Q37.png", "Q37Hint.png");
		questionList.add("Q37.png");
		Question.questionMap.put("Q38.png", "CEABD");
		Question.questionHintMap.put("Q38.png", "Q38Hint.png");
		questionList.add("Q38.png");
		Question.questionMap.put("Q39.png", "��������");
		Question.questionHintMap.put("Q39.png", "Q39Hint.png");
		questionList.add("Q39.png");
		Question.questionMap.put("Q40.png", "�Ժ�����");
		Question.questionHintMap.put("Q40.png", "Q40Hint.png");
		questionList.add("Q40.png");
		Question.questionMap.put("Q41.png", "������");
		Question.questionHintMap.put("Q41.png", "Q41Hint.png");
		questionList.add("Q41.png");
		Question.questionMap.put("Q42.png", "��������");
		Question.questionHintMap.put("Q42.png", "Q42Hint.png");
		questionList.add("Q42.png");
		Question.questionMap.put("Q43.png", "û����");
		Question.questionHintMap.put("Q43.png", "Q43Hint.png");
		questionList.add("Q43.png");
		Question.questionMap.put("Q44.png", "���ѻ꼺");
		Question.questionHintMap.put("Q44.png", "Q44Hint.png");
		questionList.add("Q44.png");
		Question.questionMap.put("Q45.png", "��");
		Question.questionHintMap.put("Q45.png", "Q45Hint.png");
		questionList.add("Q45.png");
		Question.questionMap.put("Q46.png", "�");
		Question.questionHintMap.put("Q46.png", "Q46Hint.png");
		questionList.add("Q46.png");
		Question.questionMap.put("Q47.png", "����");
		Question.questionHintMap.put("Q47.png", "Q47Hint.png");
		questionList.add("Q47.png");
		Question.questionMap.put("Q48.png", "������");
		Question.questionHintMap.put("Q48.png", "Q48Hint.png");
		questionList.add("Q48.png");
		Question.questionMap.put("Q49.png", "�ٺ�ž");
		Question.questionHintMap.put("Q49.png", "Q49Hint.png");
		questionList.add("Q49.png");
		Question.questionMap.put("Q50.png", "Ǫ�Ʊ׶�");
		Question.questionHintMap.put("Q50.png", "Q50Hint.png");
		questionList.add("Q50.png");
			
	}
	
	//��������� getter
	public List<String> getQuestionList() {
		return questionList;
	}
	public static Map<String, String> getQuestionMap() {
		return Question.questionMap;
	}
	public static Map<String, String> getQuestionHintMap() {
		return Question.questionHintMap;
	}

	//���濡�� ���� ����� �Ѱ� �ٶ� "/"�� ��ϵ��� �����ϱ� ����  toString�� ������ �Ͽ���.
	@Override
	public String toString() {
		StringBuffer BufferList = new StringBuffer(); //ThreadSafe�� StringBuffer�� ����Ͽ���.
		for(int i=0; i<questionList.size(); i++) {
			BufferList.append(questionList.get(i)); //���� �ϳ��� �߰� 
			if(questionList.size()-1 != i) //i�� �������� �ƴ϶��
				BufferList.append("/"); // "/"�� �߰��Ѵ�.
		}
		return BufferList.toString(); 
	}


}



