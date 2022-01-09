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

    @PostMapping("/addItem")
    public ApiResult addItem(@Valid @RequestBody PreparatoryItemDTO preparatoryItemDTO){
        CompanyPO companyPO = companyRepository.find(preparatoryItemDTO.getCompanyId());
        if(companyPO == null){
            return ApiResult.error(1201, "公司的信息不正确！");
        }
        if(preparatoryItemDTO.getName() == null || "".equals(preparatoryItemDTO.getName())){
            return ApiResult.error(1201, "项目名称不正确");
        }
        // 名字必须为中文或者数字，需要经过正则校验

        preparatoryItemDTO.setType("user"); // 设置为用户定义的模式

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
        // 名字必须为中文或者数字，需要经过正则校验

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
