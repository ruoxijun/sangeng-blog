package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Role;
import com.ruoxijun.service.RoleService;
import com.ruoxijun.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ruoxijun.constants.SystemConstants.ADMIN_KEY;

/**
 * @author ruoxijun
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2025-09-05 20:48:28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    /**
     * 根据用户id查询角色 key
     *
     * @param id 用户id
     * @return 角色 key 列表
     */
    @Override
    public List<String> getRoleKeysByUserId(Long id) {
        if (SystemConstants.ADMIN_ID == id) {
            return List.of(ADMIN_KEY);
        }
        return this.baseMapper.selectRoleKeysByUserId(id);
    }

}




