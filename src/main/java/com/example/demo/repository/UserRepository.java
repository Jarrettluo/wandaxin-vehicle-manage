package com.example.demo.repository;

import com.example.demo.domain.po.UserPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM `user` where username = #{username}")
    UserPO findByUsername(String username);


    @Select("SELECT * FROM `user` where id = #{userId}")
    UserPO findUserById(String userId);

    @Select("SELECT * FROM `user` WHERE company_id = #{companyId}")
    List<UserPO> findUserByCompanyId(Long companyId);


    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("<script>INSERT INTO `user` (username, password, type, company_id) " +
            "VALUES(#{username}, #{password}, #{type}, #{companyId})</script>")
    Integer save(UserPO userPO);

    /**
     * 更新用户的密码和用户类型
     * @param userPO 用户POJO
     */
    @Update("<script> UPDATE `user` <set>" +
            "<if test='password!=null'>password = #{password}, </if>" +
            "<if test='type!=null'> type = #{type}, </if>" +
            "</set>WHERE id = #{id}</script>")
    void update(UserPO userPO);

    @Delete("DELETE FROM `user` WHERE id = #{id}")
    void deleteUser(String userId);

}
