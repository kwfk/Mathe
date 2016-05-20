package bomanthis.mathe;
//Creats a level 5 question. Trig

public class LevelFiveQuestion extends Questions{
	
	private String pi = ("" + (char)65);
	private String root = ("" + (char)66);
	private String[] x = new String[]{"0", "" + pi + "/6", "" + pi + "/4", "" + pi + "/3", "" + pi + "/2"};
	private String[][] y = new String[][] {
											{"0", "1/2", "" + root + "2/2", "" + root + "3/2", "1"},
											{"1", "" + root + "3/2", "" + root + "2/2", "1/2", "0"},
											{"0", "1/" + "" + root + "3", "1", "" + root + "3", "undefined"},
										  };
	private String answer;
	private String question;
	private int choose1;
	private int choose2;
	int a = 0;
	
	//constructors
	
	public LevelFiveQuestion(){
		choose1 = (int)(Math.random()*5);
		choose2 = (int)(Math.random()*3);
		if(choose2 == 0){
			question = ("sin " + x[choose1] + " = ");
			answer = y[choose2][choose1];
		} else if(choose2 == 1){
			question = ("cos " + x[choose1] + " = ");
			answer = y[choose2][choose1];
		} else {
			question = ("tan " + x[choose1] + " = ");
			answer = y[choose2][choose1];
		}
	}
	
	//accessor methods
	
	public String getQuestion(){
		return question;
	}
	
	public String getRightAnswer(){
		return answer;
	}
	
	public String getWrongAnswer(){
		while(a == choose1){
			a++;
			return y[choose2][a];
		}
		int temp = a;
		a++;
		return y[choose2][temp];
	}
	
}
