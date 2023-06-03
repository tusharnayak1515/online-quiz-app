package com.to;

public class Score {
	private int score_id;
	private int score;
	private Quiz quiz;
	private User user;
	public int getScore_id() {
		return score_id;
	}
	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Score [score_id=" + score_id + ", score=" + score + ", quiz=" + quiz + ", user=" + user + "]";
	}
}
