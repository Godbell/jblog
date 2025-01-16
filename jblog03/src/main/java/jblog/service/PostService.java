package jblog.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jblog.dto.PostCreateDto;
import jblog.dto.PostListElementDto;
import jblog.dto.PostQueryDto;
import jblog.dto.PostResponseDto;
import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostVo createPost(PostCreateDto dto) {
        return createPost(
            dto.getTitle(),
            dto.getContent(),
            dto.getCategoryId()
        );
    }

    public PostVo createPost(String title, String content, Long categoryId) {
        PostVo postVo = new PostVo();
        postVo.setTitle(title);
        postVo.setContent(content);
        postVo.setCategoryId(categoryId);

        return postRepository.save(postVo);
    }

    public PostResponseDto getPost(Long postId, String blogId, Long categoryId) {
        PostQueryDto queryDto = new PostQueryDto();
        queryDto.setPostId(postId);
        queryDto.setBlogId(blogId);
        queryDto.setCategoryId(categoryId);

        return postRepository.findByIdOrDefault(queryDto);
    }

    public String getPostListJsonString(String blogId, Long categoryId) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        List<PostListElementDto> list = postRepository.findByBlogIdAndCategoryId(blogId, categoryId);
        objectMapper.writeValue(byteArrayOutputStream, list);

        return byteArrayOutputStream.toString();
    }

    public void unsetCategories(Long categoryId) {
        postRepository.unsetCategoryByCategoryId(categoryId);
    }
}
