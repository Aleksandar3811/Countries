package bg.softUni.Countries.entity.dto;

import bg.softUni.Countries.entity.Level;

import java.util.List;

public class CountryDetailsDTO {
    private long id;
    private String name;
    private String description;
    private Level level;
    private String videoUrl;
    private String authorName;
    private List<String> imageUrls;
    private List<CountryDetailsCommentDTO> comments;

    public CountryDetailsDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<CountryDetailsCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CountryDetailsCommentDTO> comments) {
        this.comments = comments;
    }
}
