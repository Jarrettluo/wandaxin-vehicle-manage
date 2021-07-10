package com.example.demo.service;

import com.example.utils.result.ApiResult;

public interface CompanyService {

    ApiResult list();

    ApiResult find(Long companyId);

}
