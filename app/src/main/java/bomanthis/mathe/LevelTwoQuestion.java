package bomanthis.mathe;
//Creats a level 2 question Multiplication/Division

public class LevelTwoQuestion extends Questions{ 
	
	private int x;
	private int y;
	private String answer;
	private String n;
	private int rem;
	
	//constructors
	
	public LevelTwoQuestion(){
		x = (int)(Math.random()*20);
		y = (int)(Math.random()*20);
		if(y % 2 == 0){
			n = " * ";
			answer = ("" + (x * y));
		} else {
			n = " / ";
			rem = x % y;
			answer = ("" + (x / y) + " R: " + rem);
		}
	}
	
	//accessor methods
	
	public String getQuestion(){
		return (x + n + y + " = ");
	}
	
	public String getRightAnswer(){
		return answer;
	}
	
	public String getWrongAnswer(){
		int n = (int)(Math.random()*20);
		int d = (int)(Math.random()*20);
		int r = (int)(Math.random()*y);
		while((n == x) && (d == y) || (r == rem)){
			n = (int)(Math.random()*20);
			d = (int)(Math.random()*20);
			r = (int)(Math.random()*y);
		}
		if(y % 2 == 0){
			return("" + (n * d));
		} else {
			return("" + (x / y) + " R: " + r);
		}
	}
	
}
