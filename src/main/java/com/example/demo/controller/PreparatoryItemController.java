package com.example.demo.controller;

import com.example.demo.domain.dto.PreparatoryItemDTO;
import com.example.demo.domain.po.CompanyPO;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.PrepItemService;
import com.example.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/preparatoryItem")
public class PreparatoryItemController {

    @Resource
    CompanyRepository companyRepository;

    @Resource
    PrepItemService prepItemService;

    /**
     * 校验中文数字英文
     * @param str 整备项目名字
     * @return boolean
     */
    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }

    @PostMapping("/addItem")
    public ApiResult addItem(@Valid @RequestBody PreparatoryItemDTO preparatoryItemDTO){
        CompanyPO companyPO = companyRepository.find(preparatoryItemDTO.getCompanyId());
        if(companyPO == null){
            return ApiResult.error(1201, "公司的信息不正确！");
        }
        String itemName = preparatoryItemDTO.getName();
        if( itemName == null || "".equals(itemName)){
            return ApiResult.error(1201, "项目名称不正确");
        }
        // 名字必须为中文或者数字，需要经过正则校验
        if(!isLetterDigitOrChinese(itemName)){
            return ApiResult.error(1202, "项目名称不符合规范！");
        }
        // 名字需要进行查重

        // 设置为用户定义的模式
        preparatoryItemDTO.setType("user");
        return prepItemService.addItem(preparatoryItemDTO);
    }

    @DeleteMapping("/removeItem")
    public ApiResult removeItem(@Valid @RequestParam long itemId){
        // 首先校验该id所处的信息是否存在

        return prepItemService.removeItem(itemId);
    }

    @PutMapping("/updateItem")
    public ApiResult updateItem(@Valid @RequestBody PreparatoryItemDTO preparatoryItemDTO){
        CompanyPO companyPO = companyRepository.find(preparatoryItemDTO.getCompanyId());
        if(companyPO == null){
            return ApiResult.error(1201, "公司的信息不正确！");
        }
        String itemName = preparatoryItemDTO.getName();
        if( itemName == null || "".equals(itemName)){
            return ApiResult.error(1201, "项目名称不正确");
        }
        // 名字必须为中文或者数字，需要经过正则校验
        if(!isLetterDigitOrChinese(itemName)){
            return ApiResult.error(1202, "项目名称不符合规范！");
        }
        // 名字需要进行查重

        // 设置为用户定义的模式
        preparatoryItemDTO.setType("user");
        return prepItemService.updateItem(preparatoryItemDTO);
    }

    @GetMapping("/list")
    public ApiResult list(@RequestParam Long companyId) {
        CompanyPO companyPO = companyRepository.find(companyId);
        if(companyPO == null){
            return ApiResult.error(1201, "公司的信息不正确！");
        }
        return prepItemService.list(companyId);
    }




}