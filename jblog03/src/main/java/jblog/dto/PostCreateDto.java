package jblog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class PostCreateDto {
    @NotEmpty
    @Size(min = 1, max = 200, message = "200자 이내의 제목을 반드시 작성해 주세요.")
    private String title;

    @NotEmpty
    @Size(min = 1, max = 65535, message = "65,535자 이내의 내용을 반드시 작성해 주세요.")
    private String content;

    @NotNull
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
