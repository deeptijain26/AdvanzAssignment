package questionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Import;

 
@SpringBootApplication
//@Import(RepositoryRestMvcAutoConfiguration.class)
public class Application {
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

