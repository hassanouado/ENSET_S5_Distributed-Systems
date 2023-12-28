package Hassan.Ouadouch.keynoteservice;

import Hassan.Ouadouch.keynoteservice.DAO.KeynoteRepository;
import Hassan.Ouadouch.keynoteservice.entities.Keynote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(KeynoteRepository keynoteRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration
    ){
        repositoryRestConfiguration.exposeIdsFor(Keynote.class);
        return args->{

            Stream.of("hassan", "abdo", "karima").forEach(name -> {

                keynoteRepository.save(new Keynote(1L,name,name+"@gmal.com"));

            });

            keynoteRepository.findAll().forEach(System.out::println);
        };
    }

}
