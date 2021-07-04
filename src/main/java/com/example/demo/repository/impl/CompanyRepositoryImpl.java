package com.example.demo.repository.impl;

import com.example.demo.domain.po.CompanyPO;
import com.example.demo.repository.CompanyRepository;
import javax.annotation.Resource;
import java.util.List;

public class CompanyRepositoryImpl {
    @Resource
    CompanyRepository companyRepository;

    Integer save(CompanyPO companyPO){
        return companyRepository.save(companyPO);
    }

    CompanyPO find(Integer companyId) {
        return companyRepository.find(companyId);
    }

    List<CompanyPO> list() {
        return companyRepository.list();
    }

}
