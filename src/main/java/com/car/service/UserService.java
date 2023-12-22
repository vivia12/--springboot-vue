package com.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author djh
 * @since 2023-12-14
 */
public interface UserService extends IService<User> {
   Page<User> getUsersWithPagination(int pageNum, int pageSize);

   User updateUser(Long id, User userDetails);
   User authenticate(String username, String password);

   void deleteById(long id);
}
