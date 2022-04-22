package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.domain.po.UserPO;
import com.example.demo.domain.vo.TryoutCompanyVO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.service.OperationLogService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    CompanyService companyService;

    @Resource
    UserRepository userRepository;

    @UserLoginToken
    @GetMapping("/list/")
    public ApiResult list() {
        return companyService.list();
    }

    @UserLoginToken
    @GetMapping("/")
    public ApiResult find(@RequestParam Long companyId) {
        return companyService.find(companyId);
    }

    @UserLoginToken
    @PostMapping("/")
    public ApiResult save(@RequestBody TryoutCompanyVO tryoutCompanyVO) throws IllegalAccessException {
        // 判断Username不能重复
        UserPO userPO = userRepository.findByUsername(tryoutCompanyVO.getUsername());
        if(userPO != null){
            return ApiResult.error(1201,"该账号已经存在，请更换！");
        }
        return companyService.save(tryoutCompanyVO);
    }

}
