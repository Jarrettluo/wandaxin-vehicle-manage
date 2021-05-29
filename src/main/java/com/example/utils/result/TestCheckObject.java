package com.example.utils.result;

import com.example.demo.domain.dto.PartnerDTO;

public class TestCheckObject {

    public static void main(String[] args) throws IllegalAccessException {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setName("dsf");
        partnerDTO.setPrice(1200);
        long b = 100000;
        partnerDTO.setVehicleId(b);
        System.out.println(partnerDTO.toString());
        boolean a = CheckObject.checkObjFieldIsNull(partnerDTO);

        System.out.println(a);
    }
}
