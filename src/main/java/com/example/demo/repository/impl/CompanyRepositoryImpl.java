package com.example.demo.repository.impl;

import com.example.demo.domain.po.CompanyPO;
import com.example.demo.repository.CompanyRepository;
import javax.annotation.Resource;
import java.util.List;

public class CompanyRepositoryImpl {
    @Resource
    CompanyRepository companyRepository;

    Long save(CompanyPO companyPO){
        return companyRepository.save(companyPO);
    }

    CompanyPO find(Long companyId) {
        return companyRepository.find(companyId);
    }

    List<CompanyPO> list() {
        return companyRepository.list();
    }

    void deleteById(Long companyId) {
        companyRepository.deleteById(companyId);
    }

}
