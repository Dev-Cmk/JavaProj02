package project2.ver03;

public class MenuSelectException extends Exception{
	public MenuSelectException() {
		super("입력값 범위 초과");
	}
}