package com.example.demo.service.impl;

import com.example.demo.domain.po.CompanyPO;
import com.example.demo.domain.po.UserPO;
import com.example.demo.domain.vo.CompanyVO;
import com.example.demo.domain.vo.TryoutCompanyVO;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CompanyService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.CheckObject;
import com.example.utils.result.bean.BeanUtil;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    CompanyRepository companyRepository;
    @Resource
    UserRepository userRepository;

    @Override
    public ApiResult list() {
        List<CompanyPO> companyPOList = companyRepository.list();
        List<CompanyVO> companyVOList = BeanUtil.mapperList(companyPOList, CompanyVO.class);
        return ApiResult.success(companyVOList);
    }

    @Override
    public ApiResult find(Long companyId) {
        if(companyId.equals("")) return ApiResult.error(1201, "参数不足");
        CompanyPO companyPO = companyRepository.find(companyId);
        CompanyVO companyVO = BeanUtil.mapperBean(companyPO, CompanyVO.class);
        return ApiResult.success(companyVO);
    }

    @Override
    public ApiResult save(TryoutCompanyVO tryoutCompanyVO) throws IllegalAccessException {
        if(CheckObject.checkObjFieldIsNull(tryoutCompanyVO)){
            return ApiResult.error(1201,"参数错误");
        }
        CompanyPO companyPO = BeanUtil.mapperBean(tryoutCompanyVO, CompanyPO.class);
        UserPO userPO = BeanUtil.mapperBean(tryoutCompanyVO, UserPO.class);
        try {
            companyRepository.save(companyPO);
        }catch (Exception e) {
            return ApiResult.error(1202, "写入数据库失败，请重试！");
        }
        Long companyId = companyPO.getId();
        if(companyId.equals(null)) return ApiResult.error(1202, "公司保存失败,请重试");
        userPO.setCompanyId(companyId); // 赋值
        userPO.setType("admin"); // 默认赋值为admin
        String regex = "^1(3|4|5|7|8)\\d{9}$";  // 写入数据库的数据必须经过正则判断，手机号码，用户密码
        String pwdReg = "^[\\dA-Za-z_]{6,14}$"; // 密码正则限制
        if(!userPO.getUsername().matches(regex) || !userPO.getPassword().matches(pwdReg)){
            companyRepository.deleteById(companyId); // 写入失败，必须将类型给
            return ApiResult.error(1203, "用户写入失败，请重试！");
        }
        try{
            userRepository.save(userPO);
        }catch (Exception err) {
            companyRepository.deleteById(companyId);
            return ApiResult.error(1203, "用户写入失败，请重试！");
        }
        return ApiResult.success("添加成功");
    }
}
