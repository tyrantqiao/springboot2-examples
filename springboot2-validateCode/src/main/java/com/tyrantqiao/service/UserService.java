package com.tyrantqiao.service;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.mapper.UserMapper;
import com.tyrantqiao.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Service
public class UserService {
	private UserMapper userMapper;
	private ResultService resultService;

	@Autowired
	public UserService(UserMapper userMapper, ResultService resultService) {
		this.userMapper = userMapper;
		this.resultService = resultService;
	}

	public User getById(Long id) {
		return userMapper.getById(id);
	}

	public Result<User> save(User user) {
		User findUser = userMapper.getById(user.getId());
		if (findUser != null) {
			return resultService.error(500, "user exists", findUser);
		}
		userMapper.insert(findUser);
		return resultService.success(findUser, "save user successfully");
	}
}
