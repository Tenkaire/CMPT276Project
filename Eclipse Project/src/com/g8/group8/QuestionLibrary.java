package com.g8.group8;

public class QuestionLibrary {
	
	private String questionLibrary[] = {
			"Question","Question2","Question3","Question4"
	};
	
	private String options [][] = {
			{"module1","module2","module3"},
			{"Q2module1","Q2module2","Q2module3"},
			{"Q3module1","Q3module2","Q3module3"},
			{"Q4module1","Q4module2","Q4module3"}
	};
	
	private String correctAnswer[] = {
			"module1","Q2module2","Q3module3","Q4module3"
	};
	
	public String getQuestion (int num){
		return questionLibrary[num];
	}
	
	public String getOption1 (int num){
		return options[num][0];
	}
	
	public String getOption2 (int num){
		return options[num][1];
	}
	
	public String getOption3 (int num){
		return options[num][2];
	}
	
	public String getCorrect (int num){
		return correctAnswer[num];
	}

}
