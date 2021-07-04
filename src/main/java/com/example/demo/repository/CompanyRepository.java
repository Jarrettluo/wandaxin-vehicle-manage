package com.example.demo.repository;

import com.example.demo.domain.po.CompanyPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface CompanyRepository {

    @Insert("Insert INTO `company` (company_name, abbreviation, valid_account, expiration_time)" +
            "VALUES （#{companyName}, #{abbreviation}, #{validAccount}, #{expiration}")
    Integer save(CompanyPO companyPO);

    @Select("SELECT * FROM `company` where id = #{companyId}")
    CompanyPO find(Integer companyId);

    @Select("SELECT * FROM `company` ")
    List<CompanyPO> list();
}
