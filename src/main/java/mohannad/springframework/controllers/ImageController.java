package mohannad.springframework.controllers;

import mohannad.springframework.commands.RecipeCommand;
import mohannad.springframework.repositories.RecipeRepository;
import mohannad.springframework.services.ImageService;
import mohannad.springframework.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * created by mohannad
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    /**
     * get called when we chose image file
     * @param id
     * @param file
     * @return
     */
    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
        //save the image to the database
        imageService.saveImageFile(Long.valueOf(id), file);
        //redirect to the show recipe
        return "redirect:/recipe/" + id + "/show";
    }

    /**
     * get called when we want to display image in recipe show
     * call snippet of code is inside show recipe html
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        //get recipe from database
        //todo get only image
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        //convert image to byte array as it is Byte objects
        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }
            //display image to response
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            //IOUtils(apache tomcat) is going to copy is inputstream to response outputstream
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}