package jblog.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryVo createCategory(String categoryName, String blogId) {
        CategoryVo vo = new CategoryVo();
        vo.setName(categoryName);
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
}
