package jblog.service;

import org.springframework.stereotype.Service;

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
}
