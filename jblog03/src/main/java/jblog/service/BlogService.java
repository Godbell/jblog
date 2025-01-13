package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    public BlogService(BlogRepository blogRepository, CategoryRepository categoryRepository) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createBlog(String blogId) {
        BlogVo blogVo = createDefaultBlog(blogId);

        blogRepository.save(blogVo);

        createDefaultCategory(blogId);
    }

    private BlogVo createDefaultBlog(String blogId) {
        BlogVo blogVo = new BlogVo();
        blogVo.setBlogId(blogId);
        blogVo.setTitle(blogId + "의 블로그");
        blogVo.setProfile("안녕하세요, " + blogId + "의 블로그입니다.");

        return blogVo;
    }

    public void createCategory(String categoryName, String blogId) {
        CategoryVo vo = new CategoryVo();
        vo.setName(categoryName);
        vo.setBlogId(blogId);

        categoryRepository.save(vo);
    }

    private void createDefaultCategory(String blogId) {
        createCategory("미분류", blogId);
    }
}
