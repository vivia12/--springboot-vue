package com.car.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author djh
 * @since 2023-12-14
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM t_user WHERE name = #{username}")
    User findUserByUsername(String username);

    @Delete("DELETE FROM t_user WHERE id = #{id}")
    void deleteUserById(Long id);

    @Update("UPDATE t_user SET name = #{name}, password = #{password}, email = #{email} WHERE id = #{id}")
    int updateUserById(@Param("id") Long id, @Param("name") String name, @Param("password") String password, @Param("email") String email);

}
