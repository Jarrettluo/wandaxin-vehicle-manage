package com.example.demo.service.impl;


import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.po.CompanyPO;
import com.example.demo.domain.po.UserPO;
import com.example.demo.domain.vo.LoginPageVO;
import com.example.demo.domain.vo.UserInfoVO;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.impl.UserRepositoryImpl;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/11 15:42
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    CompanyRepository companyRepository;

    @Autowired
    TokenService tokenService;

    /**
     * @param userDTO
     * @return
     */
    @Override
    public ApiResult findByUsername(UserDTO userDTO) {
        UserPO userPO = userRepository.findByUsername(userDTO.getUsername());
        UserDTO userForBase = BeanUtil.mapperBean(userPO, UserDTO.class);
        if(userForBase==null){
            return ApiResult.error(1201,"登录失败,用户不存在");
        }else {
            if (!userForBase.getPassword().equals(userDTO.getPassword())){
                return ApiResult.error(1202,"登录失败,密码错误");
            }else {
                CompanyPO companyPO = companyRepository.find(userPO.getCompanyId());
                if(ObjectUtils.isEmpty(companyPO)) return ApiResult.error(1203,"为找到所属公司信息");
                String token = tokenService.getToken(userForBase);
                LoginPageVO loginPageVO = new LoginPageVO(); // 新建一个视图函数
                loginPageVO.setToken(token);
                loginPageVO.setUserId(userForBase.getId());
                loginPageVO.setUserName(userForBase.getUsername());
                loginPageVO.setCompanyName(companyPO.getCompanyName());
                loginPageVO.setCompanyAbbreviation(companyPO.getAbbreviation());
                loginPageVO.setExpirationTime(companyPO.getExpirationTime());
                loginPageVO.setCompanyId(companyPO.getId());
                return ApiResult.success(loginPageVO);
            }
        }
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public UserDTO findUserById(String userId) {
        UserPO userPO = userRepository.findUserById(userId);
        UserDTO userDTO = BeanUtil.mapperBean(userPO, UserDTO.class);
        return userDTO;
    }

    @Override
    public ApiResult list(Long companyId) {
        if(companyId == null) return ApiResult.error(1201, "参数不足");
        List<UserPO> userPOList = userRepository.findUserByCompanyId(companyId);
        List<UserInfoVO> userInfoVOList = BeanUtil.mapperList(userPOList, UserInfoVO.class);
        return ApiResult.success(userInfoVOList);
    }

    @Override
    public ApiResult changePwd(Map<String, String> pwdMap) {
        if(pwdMap.get("userId") == null || pwdMap.get("oldPwd") == null || pwdMap.get("newPwd") == null){
            return ApiResult.error(1201, "参数不足");
        }
        UserPO userPO = userRepository.findUserById(pwdMap.get("userId"));
        if(userPO.getPassword() == null || userPO.getPassword() != pwdMap.get("oldPwd")) {
            return ApiResult.error(1202, "旧密码错误");
        }
        if(pwdMap.get("newPwd").length() > 20 && pwdMap.get("newPwd").length() < 6) {
            return ApiResult.error(1203, "新密码长度不足");
        }
        userPO.setPassword(pwdMap.get("newPwd"));
        userRepository.update(userPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult changeType(Map<String, String> typeMap) {
        String adminId = typeMap.get("adminId");
        String userId = typeMap.get("userId");
        if( adminId == null || userId == null || adminId == userId){
            return ApiResult.error(1201, "参数不足");
        }
        UserPO adminPO = userRepository.findUserById(adminId);
        if(adminPO.getType().equals("admin") == false || adminPO.getId().equals("")) {
            return ApiResult.error(1202, "管理员信息错误");
        }
        UserPO userPO = userRepository.findUserById(userId);
        userPO.setType(userPO.getType().equals("admin") ? "user":"admin");
        System.out.println(userPO);
        userRepository.update(userPO);
        return ApiResult.success();
    }

    @Override
    public ApiResult deleteUser(String userId) {
        if(userId == null) return ApiResult.error(1201, "参数不足");
        userRepository.deleteUser(userId);
        return ApiResult.success();

    }
}
