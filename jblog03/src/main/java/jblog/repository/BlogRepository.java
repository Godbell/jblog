package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
    private final SqlSession sqlSession;

    public BlogRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void save(BlogVo vo) {
        sqlSession.insert("blog.insert", vo);
    }

    public BlogVo findById(String id) {
        return sqlSession.selectOne("blog.findById", id);
    }
}
