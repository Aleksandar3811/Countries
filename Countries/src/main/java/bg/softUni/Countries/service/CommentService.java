package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.Comment;
import bg.softUni.Countries.entity.dto.CreateCommentApiDTO;
import bg.softUni.Countries.entity.dto.CreateCommentDTO;
import bg.softUni.Countries.repository.CommentRepository;
import bg.softUni.Countries.service.helper.CountryHelperService;
import bg.softUni.Countries.service.helper.UserHelperService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final CountryHelperService routeHelperService;
    private final UserHelperService userHelperService;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper, CountryHelperService routeHelperService, UserHelperService userHelperService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.routeHelperService = routeHelperService;
        this.userHelperService = userHelperService;
    }

    public void create(CreateCommentDTO createCommentDTO) {
        createInternal(createCommentDTO);
    }

    public CreateCommentApiDTO createApi(CreateCommentDTO createCommentDTO) {
        return modelMapper.map(createInternal(createCommentDTO), CreateCommentApiDTO.class);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    private Comment createInternal(CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment();

        comment.setContent(createCommentDTO.getMessage());
        comment.setRoute(routeHelperService.getByIdOrThrow(createCommentDTO.getCountryId()));
        comment.setAuthor(userHelperService.getUser());
        comment.setCreated(Instant.now());

        return commentRepository.save(comment);
    }
}
