package com.example.demo.repository.impl;


import com.example.demo.domain.po.PreparatoryItemPO;
import com.example.demo.repository.PrepItemRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PrepItemRepositoryImpl {

    @Resource
    PrepItemRepository prepItemRepository;

    public Integer save(@Param(value = "preparatoryItemPO") PreparatoryItemPO preparatoryItemPO){
        return prepItemRepository.save(preparatoryItemPO);
    }

    public void remove(Long itemId){
        prepItemRepository.remove(itemId);
    }

    public void update(PreparatoryItemPO preparatoryItemPO){
        prepItemRepository.update(preparatoryItemPO);
    }

    public List<PreparatoryItemPO> findDefaultItemList(){
        return prepItemRepository.findDefaultItemList();
    }

    public List<PreparatoryItemPO> findUserItemList(Long companyId){
        return prepItemRepository.findUserItemList(companyId);
    }

    public Integer countNum(String type, Long companyId, String name) {
        return prepItemRepository.countItem(type, companyId, name);
    }

}
