package com.example.demo.repository;

import com.example.demo.domain.po.PreparatoryItemPO;
import com.example.demo.domain.po.VehicleDescriptionPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PrepItemRepository {


    /**
     * 增加自定义的整备项目
     * @param preparatoryItemPO 自定义整备项目
     * @return 返回车辆的保存结果
     */
    @Insert("<script> INSERT INTO `preparatory_item` (name, company_id, type) values " +
            "(#{name}, #{companyId}, #{type})" +
            " </script>")
    Integer save(@Param(value = "preparatoryItemPO") PreparatoryItemPO preparatoryItemPO);

    /**
     * 删除某个自定义的整备项目
     * @param itemId 整备项目的主键ID
     */
    @Delete("DELETE FROM `preparatory_item` WHERE id = #{itemId} and type = 'user'")
    void remove(Long itemId);

    /**
     * 更新自定义的整备项目
     * @param preparatoryItemPO  自定义整备项目的对象
     */
    @Update("<script> UPDATE `preparatory_item` <set>" +
            "name = #{name} " +
            "</set>WHERE id = #{id} and company_id = #{companyId}</script>")
    void update(PreparatoryItemPO preparatoryItemPO);

    /**
     * 查询默认的类目
     * @return 返回全部默认选项
     */
    @Select("SELECT * FROM `preparatory_item` WHERE type = 'default'")
    List<PreparatoryItemPO> findDefaultItemList();

    /**
     * 根据公司的主键ID查询用户自定义的整备项目
     * @param companyId  公司的主键ID
     * @return 返回自定义的整备项目
     */
    @Select("SELECT * FROM `preparatory_item` WHERE company_id = #{companyId}")
    List<PreparatoryItemPO> findUserItemList(Long companyId);

}