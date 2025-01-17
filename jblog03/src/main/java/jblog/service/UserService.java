package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.dto.UserJoinRequestDto;
import jblog.exception.BadRequestException;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BlogService blogService;

    public UserService(UserRepository userRepository, BlogService blogService) {
        this.userRepository = userRepository;
        this.blogService = blogService;
    }

    public boolean isUserIdAvailable(String id) {
        return this.userRepository.isIdAvailable(id);
    }

    @Transactional
    public void createUser(UserJoinRequestDto dto) {
        UserVo userVo = new UserVo();
        userVo.setId(dto.getId());
        userVo.setName(dto.getName());
        userVo.setPassword(dto.getPassword());

        if (!userRepository.isIdAvailable(dto.getId())) {
            throw new BadRequestException();
        }

        userRepository.save(userVo);

        blogService.createBlog(dto.getId());
    }

    public UserVo getUser(String id, String password) {
        return this.userRepository.findByIdAndPassword(id, password);
    }
}
