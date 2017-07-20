package questionnaire.models;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@Entity
@Table(name = "QUESTIONS")
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
	private Integer questionId;
	
	@Column(name="Q_TYPE")
	private Integer qType;
	
	@Column(name="TEXT")
	private String questionText;

	@OneToMany(cascade = CascadeType.PERSIST)
			// didn't help mappedBy="parentQuestion", orphanRemoval=true)
    @JoinColumn(name = "QUESTION_ID")
	@JsonManagedReference
	private List<Answer> answers;
	 
	public Integer getQuestionId() {
	return questionId;
	}
	
	public void setQuestionId(Integer questionId) {
	this.questionId = questionId;
	}
	
	public Integer getQType() {
	return qType;
	}
	/*
	public void setQType(Integer qType) {
	this.qType = qType;
	}
	*/
	public void setQType(String qType) {
		try {
			this.qType = Integer.valueOf(qType);
		} catch (NumberFormatException ne) {
			System.out.println(ne.toString());
		}
	}
	
	public void setqType(String qType) {
		try {
			this.qType = Integer.valueOf(qType);
		} catch (NumberFormatException ne) {
			System.out.println(ne.toString());
		}
	}
	public String getQuestionText() {
		return questionText;
	}

	public Integer getqType() {
		return qType;
	}
/*
	public void setqType(Integer qType) {
		this.qType = qType;
	}
*/
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public void setAnswers(List<Answer> answersList) {
	 this.answers = answersList;
	}

	public List<Answer> getAnswers() {
	 return this.answers;
	}

	@Override
	public String toString() {
	return "Question [questionId=" + questionId + ", questionText=" + questionText
	+ ", qType=" + qType + ", answers=" + answers + "]";
	}
 
	//----------------------------------------------------------------
	//view fields : to set the answer to subquestions link
	@Transient
	private String parentAnsObjId;
	@Transient
	private String childAnsObjId;
	
	public String getParentAnsObjId() {
		return parentAnsObjId;
	}

	public void setParentAnsObjId(String parentAnsObjId) {
		this.parentAnsObjId = parentAnsObjId;
	}

	public String getChildAnsObjId() {
		return childAnsObjId;
	}

	public void setChildAnsObjId(String childAnsObjId) {
		this.childAnsObjId = childAnsObjId;
	}	
	//----------------------------------------------------------------
}