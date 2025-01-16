package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.dto.CategoryDeleteDto;
import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
    private final SqlSession sqlSession;

    public CategoryRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public CategoryVo save(CategoryVo vo) {
        sqlSession.insert("category.insert", vo);
        return vo;
    }

    public List<CategoryVo> findAllByBlogId(String blogId) {
        return sqlSession.selectList("category.findAllByBlogId", blogId);
    }

    public void deleteCategory(CategoryDeleteDto dto) {
        sqlSession.delete("category.deleteCategory", dto);
    }
}
