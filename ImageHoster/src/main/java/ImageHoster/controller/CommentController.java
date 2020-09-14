package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;

    //This Controller class method "newComment" maps to th/images/{imageId}/{title}e request URL "/image/{imageId}/{imageTitle}/comments"
    // for creating a new comment. After persisting the comment in the database, the controller logic redirects
    // to the showImage()’method in ‘ImageController’ displaying all the details of that particular image.

    @RequestMapping("/image/{imageId}/{imageTitle}/comments")
    public String newComment(@PathVariable(name = "imageId") Integer imageId,
                             @PathVariable(name = "imageTitle") String imageTitle,
                             @RequestParam(name = "comment") String commentText,
                             Model model, HttpSession session) {
        Comment comment = new Comment();

        User user = (User) session.getAttribute("loggeduser");
        comment.setUser(user);

        comment.setCreatedDate(LocalDate.now());
        comment.setText(commentText);

        Image image = imageService.getImage(imageId);
        comment.setImage(image);

        image.getComments().add(comment);
        commentService.createComment(comment);
        imageService.updateImage(image);
        return "redirect:/images/"+imageId+"/"+imageTitle;
    }
}
