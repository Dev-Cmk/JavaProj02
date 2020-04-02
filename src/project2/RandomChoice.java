package project2;

import java.util.Random;

public class RandomChoice {

	static final int RANDOM_COUNT = 10000000;//난수생성횟수
	static final int STUDENT_COUNT = 28;//학생수
	
	public static void main(String[] args) {		
		
		//61기
		/* 발표한 사람은 여기로...ㅋ
		
		*/
		String[] kosmo = {
				"정윤지","김태욱","이정연","이정수","장채훈","유재영",
				"이태랑","전소영","송지웅","정덕래","김홍대","정상혁",
				"김희철","이은석","조민규","안진석","박예진","노희종",
				"성기훈","옥광휘","이동건","이창호","이준승","김서영",
				"박진슬","신지희","박성일","진병찬"
		};
		//난수를 누적해서 저장할 배열
		int[] choice = new int[STUDENT_COUNT];
		
		//난수생성을 위한 객체생성 및 씨드설정
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
				
		//일정횟수 반복하면서 난수생성
		for (int i=0 ; i<RANDOM_COUNT ; i++) {			
			int rndNumber = random.nextInt(RANDOM_COUNT);
			//학생수 만큼의 난수생성
			int stuNumber = rndNumber % kosmo.length;
			//System.out.println("comNumber="+stuNumber);
			//생성된 난수를 배열의 인덱스로 사용하여 +1증가
			choice[stuNumber]++;
		}
		
		for (int i=0 ; i<kosmo.length ; i++) {			
			System.out.printf("%s[%d] = %d\n", kosmo[i], i, choice[i]);
		}
		
		int max=0;
		int maxIndex=-1;
		//배열에서 가장 큰값 찾기
		for (int i=0 ; i<kosmo.length ; i++) {			
			if(max<choice[i]) {
				max = choice[i];	
				maxIndex = i;
			}
		}
		
		System.out.println();
		System.out.println("선택된사람 : "+ kosmo[maxIndex]);
	}
}