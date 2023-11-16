/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3.btltracnghiemtienganh;

/**
 *
 * @author nmau4
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestResult {
	private double score;
	private Date date;
	private List<Integer> answeredQuestions = new ArrayList<>();// sẽ lưu id cau hoi da lam của mỗi lần làm 
	
	
	// constructor
	public TestResult() {}
	public TestResult(double score, Date date) {
		this.score = score;
		this.date = date;
		this.answeredQuestions = new ArrayList<>();
	}

	//Lưu lại các câu hỏi
	public void addAnsweredQuestion(Integer questionId) {
		answeredQuestions.add(questionId);
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Integer> getAnsweredQuestions() {
		return answeredQuestions;
	}

}
