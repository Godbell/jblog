package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
}
