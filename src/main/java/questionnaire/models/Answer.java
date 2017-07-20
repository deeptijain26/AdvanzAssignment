package questionnaire.models;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "ANSWERS")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer answerId;

	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "QUESTION_ID")
	@JsonBackReference
	private Question parentQuestion; // parent question

	@Column(name = "TEXT")
	private String answerText;

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ANS_SUB_QUESTIONS", 
		joinColumns = { @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID")},
		inverseJoinColumns = {@JoinColumn(name = "QUESTION_ID") })		
	//@JsonManagedReference
	private List<Question> subQuestions; // list of child/subquestions could be
											// empty if there are no sub
											// questions defined for the answer

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Question getParentQuestion() {
		return parentQuestion;
	}

	public void setParentQuestion(Question parentQuestion) {
		this.parentQuestion = parentQuestion;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public void setSubQuestions(List<Question> subQuestionsList) {
		this.subQuestions = subQuestionsList;
	}

	public List<Question> getSubQuestions() {
		return this.subQuestions;
	}

	@Override
	public String toString() {
		return "Answer [answerId =" + answerId + ", parentQuestion=" + parentQuestion + ", answerText=" + answerText
				+ ", subQuestions=" ; //+ subQuestions + "]";
	}
}