package com.example.demo.service.impl;

import com.example.demo.domain.dto.PreparatoryItemDTO;
import com.example.demo.domain.po.PreparatoryItemPO;
import com.example.demo.repository.impl.PrepItemRepositoryImpl;
import com.example.demo.repository.impl.PreparednessRepositoryImpl;
import com.example.demo.service.PrepItemService;
import com.example.utils.result.ApiResult;
import com.example.utils.result.bean.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class PrepItemServiceImpl implements PrepItemService {

    @Resource
    PrepItemRepositoryImpl prepItemRepositoryImpl;

    @Override
    public ApiResult addItem(PreparatoryItemDTO preparatoryItemDTO) {
        PreparatoryItemPO preparatoryItemPO = BeanUtil.mapperBean(preparatoryItemDTO, PreparatoryItemPO.class);
        prepItemRepositoryImpl.save(preparatoryItemPO);
        return ApiResult.success("保存成功！");
    }

    @Override
    public ApiResult removeItem(Long id) {
        prepItemRepositoryImpl.remove(id);
        return ApiResult.success("删除成功！");
    }

    @Override
    public ApiResult updateItem(PreparatoryItemDTO preparatoryItemDTO) {
        PreparatoryItemPO preparatoryItemPO = BeanUtil.mapperBean(preparatoryItemDTO, PreparatoryItemPO.class);
        prepItemRepositoryImpl.update(preparatoryItemPO);
        return ApiResult.success("更新成功！");
    }

    @Override
    public ApiResult list(Long companyId) {
        List<PreparatoryItemPO> defaultPreparatoryItemPOS = prepItemRepositoryImpl.findDefaultItemList();
        List<PreparatoryItemPO> userPreparatoryItemPOS = prepItemRepositoryImpl.findUserItemList(companyId);
        defaultPreparatoryItemPOS.addAll(userPreparatoryItemPOS);
        List<PreparatoryItemDTO> preparatoryItemDTOS = BeanUtil.mapperList(defaultPreparatoryItemPOS, PreparatoryItemDTO.class);
        return ApiResult.success(preparatoryItemDTOS);
    }
}
