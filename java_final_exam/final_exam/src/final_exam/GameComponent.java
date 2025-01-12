package final_exam;

// 게임에 필요한 규칙들 method로 모아서 정의
public interface GameComponent{
	public int distance(); // 도둑과 경찰의 거리 계산
	public void movePoliceRule(); // 경찰 움직임
	public void getThief(); // 도둑 잡았을 때
	public void arriveDetination(); // 도둑이 탈출 했을때
	public void initPosition(boolean init); // 도둑, 경찰 위치 초기화
	public void initTimeUI(); // 시간 측정 UI
	public void setTimeThread(); // 시간 측정 스레드
}
