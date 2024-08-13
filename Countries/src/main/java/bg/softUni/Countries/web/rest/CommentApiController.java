package bg.softUni.Countries.web.rest;

import bg.softUni.Countries.entity.dto.CreateCommentApiDTO;
import bg.softUni.Countries.entity.dto.CreateCommentDTO;
import bg.softUni.Countries.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommentApiController {
    private final CommentService commentService;

    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comments")
    public CreateCommentApiDTO create(@RequestBody CreateCommentDTO createCommentApiDTO){
        return commentService.createApi(createCommentApiDTO);
    }
}
