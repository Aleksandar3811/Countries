package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.dto.CreateCommentDTO;
import bg.softUni.Countries.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("comments/create")
    public ModelAndView create(CreateCommentDTO createCommentDTO) {
        commentService.create(createCommentDTO);

        return new ModelAndView("redirect:/country/" + createCommentDTO.getCountryId());
    }

    @PostMapping("comments/delete/{countryId}/{id}")
    public ModelAndView delete(@PathVariable Long countryId, @PathVariable Long id) {
        commentService.delete(id);

        return new ModelAndView("redirect:/country/" + countryId);
    }
}
