package com.k1.pack.statesFree;


public class Question{
	int questionNumber;
	String answer;
	int answerID;
	State[] options;
	String userInput;
	int usersPoint;
	
	public Question(int QN,String Ans,int ansID,State[] opt) {
		questionNumber = QN;
		answer = Ans;
		answerID = ansID;
		options = opt;
		userInput = "";
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public void setOptions(State[] options) {
		this.options = options;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	public void setUsersPoint(int usersPoint) {
		this.usersPoint = usersPoint;
	}
	public String getUserInput() {
		return userInput;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public State[] getOptions() {
		return options;
	}
	public int getAnswerID() {
		return answerID;
	}
	public String getAnswer() {
		return answer;
	}
	public int getUsersPoint() {
		return usersPoint;
	}
	public String toString() {
		return questionNumber+" "+answer;
	}
	
}
