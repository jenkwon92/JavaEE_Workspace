/*요리사를 정의한다 */
package food;

public class Cook {
	//상위 자료형으로 has a 관계를 명시했을 때 얻는 장점?
	//하위 자료형이 삭제되거나, 변화가 생기더라도 현재클래스와 has a 관계에 있는 클래스간
	//의존성을 약화시켰기 때문에 유지보수성이 좋아진자.
	private Pan pan; //정확한 자료형으로 has a 관계를 표시하지 말자 (상위클래스)
	
	public void setPan(Pan pan) {
		//pan = new FriPan(); //new가 있는한 영원히 유지보수 문제는 해결될 수 없다 
		//해결책? new를 제거하자!
		this.pan = pan;
	}
	public void makeFood() {
		pan.boil();
	}
}
