package mohannad.springframework.controllers;

import mohannad.springframework.model.Category;
import mohannad.springframework.model.UnitOfMeasure;
import mohannad.springframework.repositories.CategoryRepository;
import mohannad.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"" , "/" ,"/index","/index.html"})
    public String getIndexPage(){
        Optional<Category> categoryOptional =categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> unomOptional =unitOfMeasureRepository.findByDescription("Pinch");
        System.out.println("cat italian id : "+categoryOptional.get().getId());
        System.out.println("unom Pinch id : "+ unomOptional.get().getId());

        return "index";
    }
}
