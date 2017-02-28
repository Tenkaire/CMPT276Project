package com.g8.group8;

public class QuestionLibrary {
	
	private String questionLibrary[] = {
			"Question","Question2","Question3","Question4"
	};
	
	private String options [][] = {
			{"text1","text2","text3"},
			{"Q2text1","Q2text2","Q2text3"},
			{"Q3text1","Q3text2","Q3text3"},
			{"Q4text1","Q4text2","Q4text3"}
	};
	
	private String correctAnswer[] = {
			"text1","Q2text2","Q3text3","Q4text4"
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
