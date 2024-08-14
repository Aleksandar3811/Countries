package bg.softUni.Countries.entity.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadPictureDTO {
    private Long CountryId;
    private String title;
    private MultipartFile picture;

    public UploadPictureDTO() {}

    public Long getCountryId() {
        return CountryId;
    }

    public void setCountryId(Long countryId) {
        CountryId = countryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
