package questionnaire.models;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "ANS_SUB_QUESTIONS")
public class AnsQuesReln {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "QUESTION_ID")
	private Integer questionId;
	
	@Column(name = "ANSWER_ID")
	private Integer answerId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	@Override
	public String toString() {
		return "AnsQuesReln [Id =" + id + ", answerId=" + answerId + ", questionId=" + questionId + "]";
	}
}