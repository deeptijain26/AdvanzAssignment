package questionnaire.repository;

import questionnaire.models.AnsQuesReln;
import questionnaire.models.Question;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AnsQuesMapRepository extends CrudRepository<AnsQuesReln, Integer> {

}
