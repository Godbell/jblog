package jblog.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jblog.component.FileManager;
import jblog.exception.BadRequestException;
import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final PostService postService;
    private final CategoryService categoryService;
    private final FileManager fileManager;

    public BlogService(
        BlogRepository blogRepository,
        PostService postService,
        CategoryService categoryService,
        FileManager fileManager) {
        this.blogRepository = blogRepository;
        this.postService = postService;
        this.categoryService = categoryService;
        this.fileManager = fileManager;
    }

    public void createBlog(String blogId) {
        BlogVo blogVo = new BlogVo();
        blogVo.setBlogId(blogId);
        blogVo.setTitle(blogId + "의 블로그");
        blogVo.setProfile("~assets/images/default_blog_img.jpg");
        blogRepository.save(blogVo);

        CategoryVo categoryVo =
            categoryService.createCategory("미분류", "", blogId);

        postService.createPost(
            "새 포스트를 작성해 보세요!",
            """
                |블로그를 통해 자신의 개성을 드러내 봐요,
                |지식과 일상을 공유하고 다른 사람들의 이야기도 살펴 보면서 다양한 인사이트를 얻어 가시기 바랍니다.
                |첫 포스트를 작성하여 블로그의 시작을 알려 보아요! :D
                """.trim(),
            categoryVo.getId()
        );
    }

    public BlogVo getBlog(String id) {
        return blogRepository.findById(id);
    }

    public void updateBlogInfo(
        String blogId,
        String title,
        MultipartFile file,
        String uploadDir
    ) throws IOException {
        BlogVo vo = new BlogVo();
        vo.setBlogId(blogId);
        vo.setTitle(title);

        if (file != null && !file.isEmpty()) {
            String filename = fileManager.saveFile(uploadDir, file, null);

            if (filename == null) {
                throw new BadRequestException();
            }

            String filePath = "~assets/uploads/" + filename;
            vo.setProfile(filePath);
        }

        blogRepository.update(vo);
    }
}
