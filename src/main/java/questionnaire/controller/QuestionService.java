package questionnaire.controller;
 
import java.util.List;
import java.util.Map;
import java.util.Optional;  
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import questionnaire.models.*;
import questionnaire.repository.AnsQuesMapRepository;
import questionnaire.repository.QuestionRepository;

@Service
@EnableTransactionManagement
public class QuestionService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    QuestionRepository qnRepository; 
	
	@Autowired
    AnsQuesMapRepository aqmRepository; 
	
	/**
	 * Fetches the complete set of questions and re-establishes the association
	 * between the single choice answers and their sub-questions.
	 * @return Nested questions->answers list
	 */
	public Collection<Question> retrieveQns() {
		// step 1 : fetch the list of all questions
		List<Question> list = (List<Question>) qnRepository.findAll();
		
		
		List<AnsQuesReln> ansQMap = (List<AnsQuesReln>) aqmRepository.findAll();
		
		Map<Integer, Question> qMap = new HashMap<Integer, Question>();
		for (Question q : list) {
			qMap.put(q.getQuestionId(), q);			
		}
		for (AnsQuesReln ansSubQ : ansQMap) {
			// remove ONLY the SUB question objects from the comprehensive list/map of qns fetched in the step 1
			// since JOIN takes care of prepopulating the sub questions
			qMap.remove(ansSubQ.getQuestionId());
		}
		
		//step 3: the qMap obtained above is a complete set of questions with parent child 
				// association set correctly between qns and answers and vice versa too.
		Collection<Question> tmpList = qMap.values();
		
        return tmpList;
	}
    
	/**
	 * Iterates through the list of questions saved to the database, determines the 
	 * subquestions which have been defined for single choice answers and stores the 
	 * mapping of answer->subquestion in the ANS_SUB_QUESTIONS table.
	 * @param questionsList List of questions in which only Question/Subquestion to 
	 * 			answer associated is stored.  
	 */
    public void addAnsToSubQuesMapping(List<Question> questionsList) {
    	Answer answer = null;
    	for (Question q: questionsList) {
    		if (q.getParentAnsObjId() != null &&  !q.getParentAnsObjId().trim().equals("")) {
    			answer = findAnswer(questionsList, q.getParentAnsObjId());
    			AnsQuesReln aqr = new AnsQuesReln();
    			aqr.setAnswerId(answer.getAnswerId());
    			aqr.setQuestionId(q.getQuestionId());
    			aqmRepository.save(aqr);    			
    		}
    	}    
    }
    
    /**
     * Iterates through the list of questions to find the parent answser for a 
     * particular subquestion given its parentAnsID(UI ID of the parent answer object)
     * @param qList
     * @param parentAnsId
     * @return
     */
    public Answer findAnswer(List<Question> qList, String parentAnsId) {    	
    	for (Question q: qList) {
    		if (q.getChildAnsObjId() != null && q.getChildAnsObjId().equalsIgnoreCase(parentAnsId)) {
    			return q.getAnswers().get(0);
    		}
    	}    	
    	return null;
	}
    
    /**
     * Saves questions, answers and the mapping of answer->subquestions in the 
     * corresponding DB tables
     * @param qnsList
     * @return
     */
    @Transactional
    public List<Question> saveQuestions(List<Question> qnsList) {
    	List<Question> list = (List<Question>)qnRepository.save(qnsList);
    	addAnsToSubQuesMapping(list);
    	return list;
    }
}