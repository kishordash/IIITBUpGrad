package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Tag;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    /*public Comment getComment(Comment comment) {
        return commentRepository.findComment(comment);
    }*/

    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }

    public boolean deleteComment(Comment comment) {
        return commentRepository.deleteComment(comment);
    }

    /*public boolean deleteComments(Integer imageId) {
        return commentRepository.deleteComments(imageId);
    }*/
}
