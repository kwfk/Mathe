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
		if(y % 2 == 0){
			return("" + (int)(Math.random()*20)*(int)(Math.random()*20));
		} else {
			return("" + (x / y) + " R: " + ((int)(Math.random()*y)));
		}
	}
	
}