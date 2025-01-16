package jblog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
public class CategoryCreateDto {
    @NotEmpty
    @Pattern(regexp = "^[ -~]$", message = "잘못된 이름입니다.")
    private String name;

    @Pattern(regexp = "^[ -~]$", message = "잘못된 설명입니다.")
    private String description;

    private String blogId;

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

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
}
