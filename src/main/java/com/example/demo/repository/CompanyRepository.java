package com.example.demo.repository;

import com.example.demo.domain.po.CompanyPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface CompanyRepository {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("<script>INSERT INTO `company` (company_name, abbreviation, valid_account, " +
            "expiration_time) VALUES(#{companyName}, #{abbreviation}, #{validAccount}, #{expirationTime})</script>")
    Long save(CompanyPO companyPO);

    @Select("SELECT * FROM `company` where id = #{companyId}")
    CompanyPO find(Long companyId);

    @Select("SELECT * FROM `company` ")
    List<CompanyPO> list();

    @Delete("DELETE FROM `company` where id = #{companyId}")
    void deleteById(Long companyId);
}
