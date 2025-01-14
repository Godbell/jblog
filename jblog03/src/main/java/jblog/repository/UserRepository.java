package jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
    private final SqlSession sqlSession;

    public UserRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void save(UserVo vo) {
        sqlSession.insert("user.insert", vo);
    }

    public boolean isIdAvailable(String id) {
        return (int)sqlSession.selectOne("user.isIdAvailable", id) == 0;
    }

    public UserVo findByIdAndPassword(String id, String password) {
        return sqlSession.selectOne(
            "user.findByIdAndPassword",
            Map.of(
                "id", id,
                "password", password
            )
        );
    }
}
