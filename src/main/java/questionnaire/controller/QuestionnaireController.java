package questionnaire.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import questionnaire.models.Question;
import questionnaire.repository.QuestionRepository;
 
@RestController
//@EnableAutoConfiguration
public class QuestionnaireController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
 
	@Autowired
    QuestionRepository questionRepository; 
	
	@Autowired
	QuestionService qnSvc;
	
	@RequestMapping("/")
    public String index(){
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");
        return "index";
    }

    @RequestMapping(value = "/questions",method = RequestMethod.GET)
    public Collection<Question> retrieveQuestionnaire(){
    	log.info("Before retrieving Questionnaire. Inside retrieveQuestionnaire()");
    	Collection<Question> list = qnSvc.retrieveQns();
    	log.info("After retrieving Questionnaire. Inside retrieveQuestionnaire()");
    	return list;
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public List<Question> saveQuestionnaire(@RequestBody List<Question> questionsList) {
    	log.info("INside Controller .... Before calling save on repository");
    	log.info("" + questionsList.size());
    	List<Question> list = (List<Question>)qnSvc.saveQuestions(questionsList);
		// TODO : Check to see if the call to the repository.findAll() can be avoided
    	log.info("INside Controller .... Save executed");
        return list;
        
    }
}
