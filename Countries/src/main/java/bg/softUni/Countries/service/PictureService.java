package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.Picture;
import bg.softUni.Countries.entity.dto.UploadPictureDTO;
import bg.softUni.Countries.repository.PictureRepository;
import bg.softUni.Countries.service.helper.CountryHelperService;
import bg.softUni.Countries.service.helper.UserHelperService;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final CountryHelperService countryHelperService;
    private final UserHelperService userHelperService;

    public PictureService(PictureRepository pictureRepository, CloudinaryService cloudinaryService, CountryHelperService countryHelperService, UserHelperService userHelperService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.countryHelperService = countryHelperService;
        this.userHelperService = userHelperService;
    }

    public void create(UploadPictureDTO uploadPictureDTO) {
        String path = cloudinaryService.upload(uploadPictureDTO.getPicture(), "");

        Picture picture = new Picture();
        picture.setUrl(path);
        picture.setTitle(uploadPictureDTO.getTitle());
        picture.setRoute(countryHelperService.getByIdOrThrow(uploadPictureDTO.getCountryId()));
        picture.setAuthor(userHelperService.getUser());

        pictureRepository.save(picture);
    }
}
