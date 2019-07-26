package mohannad.springframework;

import mohannad.springframework.model.Category;
import mohannad.springframework.repositories.CategoryRepository;
import mohannad.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Spring5RecipeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5RecipeAppApplication.class, args);
    }

}
