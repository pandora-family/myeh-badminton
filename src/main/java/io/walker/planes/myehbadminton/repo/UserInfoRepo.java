package io.walker.planes.myehbadminton.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.walker.planes.myehbadminton.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Planeswalker23
 */
@Mapper
public interface UserInfoRepo extends BaseMapper<UserInfo> {
}
