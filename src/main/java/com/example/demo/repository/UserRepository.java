package com.example.demo.repository;

import com.example.demo.domain.po.UserPO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM `user` where username = #{username}")
    UserPO findByUsername(String username);


    @Select("SELECT * FROM `user` where id = #{Id}")
    UserPO findUserById(String Id);

    @Select("SELECT * FROM `user` WHERE company_id = #{companyId}")
    List<UserPO> findUserByCompanyId(Integer companyId);

    @Insert("INSERT INTO `user` (username, password, type, company_id)" +
            "VALUES (#{username}, #{password}, #{type}, #{companyId)")
    Integer save(UserPO userPO);

    // 更新用户的密码和用户类型
    @Update("<script> UPDATE `user` <set>" +
            "<if test='password!=null'>password = #{password}, </if>" +
            "<if test='type!=null'> type = #{type}, </if>" +
            "</set>WHERE id = #{id}</script>")
    void update(UserPO userPO);

}
