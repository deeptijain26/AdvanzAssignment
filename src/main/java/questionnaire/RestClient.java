package questionnaire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import questionnaire.controller.QuestionnaireController;
import questionnaire.models.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class RestClient {

	private static final Logger log = LoggerFactory.getLogger(QuestionnaireController.class);
    public void getAllQuestions() {
    	log.info("INside rest client");
		HttpHeaders headers = new HttpHeaders();	
		String url = "http://localhost:8080/questionnaire";
		RestTemplate restTemplate = new RestTemplate();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		log.info("1....");
		ResponseEntity<String> responseEntity = 
			restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		log.info(responseEntity.getBody());
    }
	
	
    public void addQuestions() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/questionnaire";
		
		Question qn = new Question();
		qn.setQuestionText("How would you rate pappu's dance?");
		//qn.setQType(1);
		qn.setqType(""+1);
		log.info("addQ....1");
		List<Answer> ansList = new ArrayList<Answer>();
		Answer answer = new Answer();
		answer.setAnswerText("Pappu cant dance saala");
		answer.setParentQuestion(qn);
		ansList.add(answer);
		log.info("addQ....2");
		
		qn.setAnswers(ansList);
		
		log.info("addQ....3");
		List<Question> listQns = new LinkedList<Question>();
		listQns.add(qn);
		HttpEntity<List<Question>> requestEntity = new HttpEntity<List<Question>>(listQns, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(
	            url, HttpMethod.POST, requestEntity,String.class);
		log.info("HHTP Status code " + responseEntity.getStatusCode());
		log.info(responseEntity.getBody());		
    }
/*
    public static void main(String args[]) {
    	RestClient util = new RestClient();
    	log.info("Get post api testing");
    	util.addQuestions();
    	
    	log.info("Get rest api testing");
    	util.getAllQuestions();
    }
    */
} 