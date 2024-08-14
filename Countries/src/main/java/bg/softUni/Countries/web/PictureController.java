package bg.softUni.Countries.web;

import bg.softUni.Countries.entity.dto.UploadPictureDTO;
import bg.softUni.Countries.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping("/picture/upload-picture")
    public ModelAndView uploadPicture(UploadPictureDTO uploadPictureDTO) {
        pictureService.create(uploadPictureDTO);

        return new ModelAndView("redirect:/country/" + uploadPictureDTO.getCountryId());
    }
}
