package jblog.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jblog.dto.CategoryCreateDto;
import jblog.dto.CategoryDeleteDto;
import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryVo createCategory(CategoryCreateDto dto) {
        return createCategory(dto.getName(), dto.getDescription(), dto.getBlogId());
    }

    public CategoryVo createCategory(String name, String description, String blogId) {
        CategoryVo vo = new CategoryVo();
        vo.setName(name);
        vo.setDescription(description);
        vo.setBlogId(blogId);

        return categoryRepository.save(vo);
    }

    public String getCategoryListJsonString(String blogId) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        List<CategoryVo> list = categoryRepository.findAllByBlogId(blogId);
        objectMapper.writeValue(byteArrayOutputStream, list);

        return byteArrayOutputStream.toString();
    }

    public void deleteCategory(Long categoryId, String blogId) {
        CategoryDeleteDto dto = new CategoryDeleteDto();
        dto.setId(categoryId);
        dto.setBlogId(blogId);

        categoryRepository.deleteCategory(dto);
    }
}
