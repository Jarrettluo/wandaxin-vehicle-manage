package com.example.demo.service.impl;

import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.po.CompanyPO;
import com.example.demo.domain.vo.CompanyVO;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    CompanyRepository companyRepository;

    @Override
    public ApiResult find(Long companyId) {
        if(companyId == null) return ApiResult.error(1201, "参数不足");
        CompanyPO companyPO = companyRepository.find(companyId);
        CompanyVO companyVO = BeanUtil.mapperBean(companyPO, CompanyVO.class);
        return ApiResult.success(companyVO);
    }
}
