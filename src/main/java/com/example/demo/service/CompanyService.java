package com.example.demo.service;

import com.example.demo.domain.vo.TryoutCompanyVO;
import com.example.utils.result.ApiResult;

public interface CompanyService {

    ApiResult list();

    ApiResult find(Long companyId);

    ApiResult save(TryoutCompanyVO tryoutCompanyVO) throws IllegalAccessException;

}
