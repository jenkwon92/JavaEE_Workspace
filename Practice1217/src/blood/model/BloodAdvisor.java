package blood.model;

public class BloodAdvisor {

	public String getAdvice(String blood) {
		//혈액형에 대한 판단 메시지 작성 및 출력
		String msg = null;
		
		if(blood.equals("A")){
			msg ="성실함, 세심함, 책임감, 조심스러움";
		}else if(blood.equals("B")){
			msg ="자유분방, 창의성, 추진력, 직설적, 오지랖";
		}else if(blood.equals("O")){
			msg ="열정, 적응력, 낙천성, 자신감, 개그본능";
		}else if(blood.equals("AB")){
			msg ="남다름, 융통성, 무심함, 여유로운";
		}else if(blood.equals("")){
			msg ="사실, 혈액형과 성격은 관계가 없어요";
		}
		return msg;
	}
}
