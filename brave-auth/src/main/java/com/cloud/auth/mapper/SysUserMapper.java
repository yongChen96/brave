package com.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.auth.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SysUserMapper
 * @Description: 用户信息Mapper
 * @Author: yongchen
 * @Date: 2021/5/24 15:42
 **/
@Repository
public interface SysUserMapper extends BaseMapper {
    /**
     * @Author: yongchen
     * @Description: 根据用户名查询用户信息
     * @Date: 15:48 2021/5/26
     * @Param: [userName]
     * @return: com.cloud.auth.entity.SysUser
     **/
    SysUser selectUserByPhone(@Param("phone") String phone);
}
