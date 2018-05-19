package com.tyrantqiao.service;

import com.tyrantqiao.entity.User;
import com.tyrantqiao.enums.ResultEnum;
import com.tyrantqiao.pojo.Result;
import org.springframework.stereotype.Service;

/**
 * @author tyrantqiao
 */
@Service("resultService")
public class ResultService {
	public Result<User> success(User user, String msg) {
		Result<User> result = new Result<User>();
		result.setData(user);
		result.setMessage(msg);
		result.setStatus(200);
		return result;
	}

	public Result<User> error(ResultEnum resultEnum) {
		Result<User> result = new Result<User>(resultEnum);
		result.setData(null);
		return result;
	}

	public Result<User> error(int status, String msg) {
		Result<User> result = new Result<User>();
		result.setStatus(status);
		result.setMessage(msg);
		result.setData(null);
		return result;
	}

	public Result<User> error(int status, String errorMessage, User user) {
		//TODO make the error can output user--pojo
		Result<User> result = new Result<User>();
		result.setData(user);
		result.setMessage(errorMessage);
		result.setStatus(status);
		return result;
	}
}
