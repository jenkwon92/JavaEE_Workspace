package blood.model;

public class BloodAdvisor {

	public String getAdvice(String blood) {
		//�������� ���� �Ǵ� �޽��� �ۼ� �� ���
		String msg = null;
		
		if(blood.equals("A")){
			msg ="������, ������, å�Ӱ�, ���ɽ�����";
		}else if(blood.equals("B")){
			msg ="�����й�, â�Ǽ�, ������, ������, ������";
		}else if(blood.equals("O")){
			msg ="����, ������, ��õ��, �ڽŰ�, ���׺���";
		}else if(blood.equals("AB")){
			msg ="���ٸ�, ���뼺, ������, �����ο�";
		}else if(blood.equals("")){
			msg ="���, �������� ������ ���谡 �����";
		}
		return msg;
	}
}
