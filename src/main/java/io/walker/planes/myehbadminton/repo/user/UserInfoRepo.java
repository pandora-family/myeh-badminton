package io.walker.planes.myehbadminton.repo.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.walker.planes.myehbadminton.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Planeswalker23
 */
@Mapper
public interface UserInfoRepo extends BaseMapper<UserInfo> {
}
