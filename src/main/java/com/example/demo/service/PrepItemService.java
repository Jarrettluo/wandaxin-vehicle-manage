package com.example.demo.service;

import com.example.demo.domain.po.PreparatoryItemPO;
import java.util.List;

public interface PrepItemService {

    Integer addItem(PreparatoryItemPO preparatoryItemPO);
    void removeItem(Long id);
    Integer updateItem(PreparatoryItemPO preparatoryItemPO);
    List<PreparatoryItemPO> list(Long companyId);
}
