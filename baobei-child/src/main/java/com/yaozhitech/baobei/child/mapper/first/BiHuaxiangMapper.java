package com.yaozhitech.baobei.child.mapper.first;

import com.yaozhitech.baobei.child.domain.BiHuaxiang;
import java.util.List;

public interface BiHuaxiangMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bi_huaxiang
     *
     * @mbg.generated Tue Nov 06 14:54:39 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bi_huaxiang
     *
     * @mbg.generated Tue Nov 06 14:54:39 CST 2018
     */
    int insert(BiHuaxiang record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bi_huaxiang
     *
     * @mbg.generated Tue Nov 06 14:54:39 CST 2018
     */
    BiHuaxiang selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bi_huaxiang
     *
     * @mbg.generated Tue Nov 06 14:54:39 CST 2018
     */
    List<BiHuaxiang> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bi_huaxiang
     *
     * @mbg.generated Tue Nov 06 14:54:39 CST 2018
     */
    int updateByPrimaryKey(BiHuaxiang record);
}