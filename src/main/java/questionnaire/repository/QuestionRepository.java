package questionnaire.repository;

import questionnaire.models.Question;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
