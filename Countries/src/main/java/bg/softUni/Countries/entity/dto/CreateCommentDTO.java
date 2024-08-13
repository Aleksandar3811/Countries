package bg.softUni.Countries.entity.dto;

public class CreateCommentDTO {
    private Long countryId;
    private String message;

    public CreateCommentDTO() {
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
