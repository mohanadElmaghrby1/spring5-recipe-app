package mohannad.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * created by mohannad
 */
public interface ImageService {
    void saveImageFile(Long recipeId , MultipartFile imageFile);
}
