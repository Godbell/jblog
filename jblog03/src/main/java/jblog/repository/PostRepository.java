package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.dto.PostQueryDto;
import jblog.dto.PostResponseDto;
import jblog.vo.PostVo;

@Repository
public class PostRepository {
    private final SqlSession sqlSession;

    public PostRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void save(PostVo vo) {
        this.sqlSession.insert("post.insert", vo);
    }

    public PostResponseDto findByIdOrDefault(PostQueryDto dto) {
        return this.sqlSession.selectOne(
            "post.findByIdOrDefault",
            dto
        );
    }
}
