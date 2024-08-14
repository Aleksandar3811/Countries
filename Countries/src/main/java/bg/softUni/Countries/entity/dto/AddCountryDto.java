package bg.softUni.Countries.entity.dto;

import bg.softUni.Countries.entity.CategoryType;
import bg.softUni.Countries.entity.Level;

public class AddCountryDto {
    private String name;
    private String description;

    private Level level;

    private String videoUrl;

    private CategoryType categoryType;

    public AddCountryDto() {
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


    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
