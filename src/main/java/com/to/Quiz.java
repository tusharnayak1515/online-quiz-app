package com.to;

public class Quiz {
	private int quiz_id;
	private String title;
	private Category category;
	
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Quiz [quiz_id=" + quiz_id + ", title=" + title + ", category=" + category + "]";
	}
}
