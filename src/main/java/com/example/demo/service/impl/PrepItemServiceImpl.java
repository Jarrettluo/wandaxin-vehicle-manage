package com.example.demo.service.impl;

import com.example.demo.domain.po.PreparatoryItemPO;
import com.example.demo.service.PrepItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrepItemServiceImpl implements PrepItemService {

    @Override
    public Integer addItem(PreparatoryItemPO preparatoryItemPO) {
        return null;
    }

    @Override
    public void removeItem(Long id) {

    }

    @Override
    public Integer updateItem(PreparatoryItemPO preparatoryItemPO) {
        return null;
    }

    @Override
    public List<PreparatoryItemPO> list(Long companyId) {
        return null;
    }
}
