package com.verificer.base_user.mapper;

import com.verificer.base_user.entity.CustomerBankcard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerBankcardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_bankcard
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_bankcard
     *
     * @mbg.generated
     */
    int insert(CustomerBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_bankcard
     *
     * @mbg.generated
     */
    int insertSelective(CustomerBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_bankcard
     *
     * @mbg.generated
     */
    CustomerBankcard selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_bankcard
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CustomerBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_bankcard
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CustomerBankcard record);

    List<CustomerBankcard> selectByCustomerId(@Param("customerId") Long customerId);

    void delCustomerCardById(@Param("customerId") Long customerId,@Param("id") Long id);
}