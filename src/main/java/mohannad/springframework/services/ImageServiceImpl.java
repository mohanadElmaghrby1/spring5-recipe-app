package mohannad.springframework.services;

import mohannad.springframework.model.Recipe;
import mohannad.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * created by mohannad
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeService) {
        this.recipeRepository = recipeService;
    }


    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile imageFile) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[imageFile.getBytes().length];

            int i = 0;

            for (byte b : imageFile.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
//            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }
}
