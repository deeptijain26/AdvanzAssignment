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
        
		/*
		// the logic for restructuring the list so that the question -> answer->subqns and so on....
		// nesting is sdone appropriately.
		
		//step 2
		// for quick searching we will convert the list of questions to Map
		Map<Integer, Question> qMap = new HashMap<Integer, Question>();
		Map<Integer, Answer> ansMap = new HashMap<Integer, Answer>();
		for (Question q : list) {
			qMap.put(q.getQuestionId(), q);
			if (q.getAnswers().size() == 1) {
				ansMap.put(q.getAnswers().get(0).getAnswerId(), q.getAnswers().get(0));
			}
		}
		
		// iterate the map of ans to subquestions
		for (AnsQuesReln ansSubQ : ansQMap) {
			Integer ansId = ansSubQ.getAnswerId();
			
			// retrieve the answer object in which the subquestions have to be associated
			Answer tmpAns = ansMap.get(ansId);
			if (tmpAns.getSubQuestions() == null) {
				tmpAns.setSubQuestions(new ArrayList<Question>());
			}
			tmpAns.getSubQuestions().add(qMap.get(ansSubQ.getQuestionId()));
			ansMap.put(ansId, tmpAns);
			// remove ONLY the question object from the map of qns fetched in the step 2
			qMap.remove(ansSubQ.getQuestionId());
		}
		
		//step 3: the qMap obtained above is a complete set of questions with parent child 
		// association set correctly between qns and answers and vice versa too.
		Collection<Question> tmpList = qMap.values();
		
        return tmpList;
        */
	}
    
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
    
    public Answer findAnswer(List<Question> qList, String parentAnsId) {    	
    	for (Question q: qList) {
    		if (q.getChildAnsObjId() != null && q.getChildAnsObjId().equalsIgnoreCase(parentAnsId)) {
    			return q.getAnswers().get(0);
    		}
    	}    	
    	return null;
	}
    
    @Transactional
    public List<Question> saveQuestions(List<Question> qnsList) {
    	List<Question> list = (List<Question>)qnRepository.save(qnsList);
    	addAnsToSubQuesMapping(list);
    	return list;
    }
}