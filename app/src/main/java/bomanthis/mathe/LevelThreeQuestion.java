package bomanthis.mathe;
//Creats a level 3 question Simple Algebra

public class LevelThreeQuestion extends Questions{ 
	
	private int x;
	private int y;
	private int answer;
	private String question;
	
	//constructors
	
	public LevelThreeQuestion(){
		x = (int)(Math.random()*20);
		y = (int)(Math.random()*20);
		if((y - x) % 2 == 0){
			answer = (y - x)/2;
			question = ("2x + " + x + " = " + y);
		} else if(x % 2 == 0){
			answer = (y - x);
			question = ("x + " + x + " = " + y);
		} else if(y % 2 == 0){
			answer = (y - x);
			question = (x + " + x = " + y);
		} else {
			answer = -1 * (y-x);
			question = (x + " - x = " + y);
		}
	}
	
	//accessor methods
	
	public String getQuestion(){
		return (question + ". \n Find x.");
	}
	
	public String getRightAnswer(){
		return ("" + answer);
	}
	
	public String getWrongAnswer(){
		int n = (int)(Math.random()*30);
		while(n == Math.abs(answer))
			n = (int)(Math.random()*30);
		if(answer >= 0)
			return ("" + n);
		else
			return ("" + (n *(-1)));
	}
	
}
