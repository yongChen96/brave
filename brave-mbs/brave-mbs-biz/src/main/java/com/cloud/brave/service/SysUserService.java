package com.cloud.brave.service;

import com.cloud.brave.dto.UserDTO;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-03
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 14:53 2021/6/4
     * @Param: [username]
     * @return: com.cloud.brave.dto.UserInfoDTO
     **/
    UserInfoDTO getUserInfo(String username);

    /**
     * @Author: yongchen
     * @Description: 添加新用户信息
     * @Date: 11:01 2021/6/7
     * @Param: [userDTO]
     * @return: java.lang.Boolean
     **/
    Boolean saveNewUser(UserDTO userDTO);
}
