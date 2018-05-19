package com.tyrantqiao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tyrantqiao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
	@Select("SELECT * FROM user where id=#{id}")
	User getById(Long id);
}
