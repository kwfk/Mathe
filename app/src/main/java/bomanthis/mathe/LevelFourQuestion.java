//Creats a level 4 question Algebra 1

public class LevelFourQuestion extends Questions{
	
	private int x;
	private int y;
	private String answer;
	private String question;
	
	//constructors
	
	public LevelFourQuestion(){
		x = (int)(Math.random()*20);
		y = (int)(Math.pow((double)(x / 2), 2.0));
		question = ("x^2 + " + x + "x + " + y);
		if(x % 2 == 0)
			answer = ("(x + " + (x / 2) + ")^2");
		else
			answer = ("(x + " + (x / 2) + ")(x + " + (x / 2 + 1) + ")");
	}
	
	//accessor methods
	
	public String getQuestion(){
		return (question + ". Simplify.");
	}
	
	public String getRightAnswer(){
		return answer;
	}
	
	public String getWrongAnswer(){
		int a = (int)(Math.random()*15);
		if(x % 2 == 0)
			return ("(x + " + a + ")^2");
		else
			return ("(x + " + a + ")(x + " + (a + 1) + ")");
	}
	
}