package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.config.UsrSrvConfig;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.mapper.CustomerAttachMapper;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.base_user.service.EmailCustomerActivationService;
import com.verificer.base_user.service.CustomerExtendService;
import com.verificer.base_user.service.CustomerService;
import com.verificer.beans.AdminCustomerVo;
import com.verificer.beans.CustomerVo;
import com.verificer.beans.customer.AdmWebUserVo;
import com.verificer.beans.customer.req.CustomerPageVo;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.StaffVo;
import com.verificer.utils.check.CheckUtil;
import com.verificer.utils.web.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/3/1.
 */
@Service("customerExtendService")
@Transactional(rollbackFor = Exception.class)
public class CustomerExtendServiceImpl implements CustomerExtendService {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerAttachMapper customerAttachMapper;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmailCustomerActivationService emailCustomerActivationService;

    @Autowired
    UsrSrvConfig usrSrvConfig;


    @Override
    public CustomerVo addCustomer(String username, String nickname, String password, Long roleId, String roleName, String crmAccount) {
        if(SStringUtils.isEmpty(username)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(nickname)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(password) || password.length() < 6 || password.length() > 20){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(roleId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(roleName) ){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }



        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setNickName(nickname);
        customer.setPassword(PasswordUtil.loginPassword(password));


        customerMapper.insertSelective(customer);

        return customerService.getCustomerVo(customer.getId());
    }

    @Override
    public void updateCustomer(Long id,String nickname, Long roleId, String roleName,String crmAccount) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(roleId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(roleName) ){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(nickname)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }



        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        customer.setNickName(nickname);
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public void resetPassword(Long id, String password) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(password) || password.length() < 6 || password.length() > 20){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        customer.setPassword(PasswordUtil.loginPassword(password));
        customerMapper.updateByPrimaryKey(customer);
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void updatePassword(Long id,String oldPassword,String newPassword) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(oldPassword)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(newPassword) || newPassword.length() < 6 || newPassword.length() > 20){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        if(!PasswordUtil.loginPassword(oldPassword).equals(customer.getPassword())){
            throw new BaseException(ErrCode.LOGIN_PASSWORD_ERROR);
        }
        customer.setPassword(PasswordUtil.loginPassword(newPassword));
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void onUpdateRole(Long roleId, String roleName) {
        List<Customer> customerList = customerMapper.selectByRoleId(roleId);
        for(Customer customer : customerList){
            Customer tmp = new Customer();
            tmp.setId(customer.getId());
            customerMapper.updateByPrimaryKeySelective(tmp);
        }
    }

    @Override
    public void onDeleteRole(Long roleId) {
        List<Customer> customerList = customerMapper.selectByRoleId(roleId);
        for(Customer customer : customerList){
            Customer tmp = new Customer();
            tmp.setId(customer.getId());
            customerMapper.updateByPrimaryKeySelective(tmp);
        }
    }

    @Override
    public List<StaffVo> getStaffList(String nickname, Integer limit) {
        List<Customer> customerList = customerMapper.selectByNickname(nickname,limit);
        List<StaffVo> voList = new LinkedList<>();
        for(Customer customer : customerList){
            StaffVo vo = new StaffVo();
            SBeanUtils.copyProperties2(customer,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminCustomerVo detailCustomer(Long id) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null)
            return null;

        AdminCustomerVo vo = new AdminCustomerVo();
        SBeanUtils.copyProperties2(customer,vo);
        return vo;
    }



    @Override
    public CustomerVo registerCustomer(String actiUrl,String ip,Integer userType, String username, String password,String language) {

        if(userType == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        if(SStringUtils.isEmpty(username)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(password)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(customerService.checkMailExist(username)){
            throw new BaseException(ErrCode.REG_MAIL_IS_EXISTING);
        }

        if(!CheckUtil.checkLoginPassword(password)){
            throw new BaseException(ErrCode.LOGIN_PASSWORD_NOT_MATCH);
        }

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(PasswordUtil.loginPassword(password));
        customer.setIsActivation(false);
        customer.setEmail(username);
        customer.setRegTime(System.currentTimeMillis());

        customerMapper.insertSelective(customer);


        if(!usrSrvConfig.isDebug()){
            emailCustomerActivationService.sendActivationCode(customer.getId(),actiUrl,language);

        }else {
            Customer temp = new Customer();
            temp.setId(customer.getId());
            temp.setIsActivation(true);
            customerMapper.updateByPrimaryKeySelective(temp);
        }

        return customerService.getCustomerVo(customer.getId());
    }

    @Override
    public List<AdmWebUserVo> pageWebUser(CustomerPageVo queryVo, String language) {
        List<Customer> customerList = customerMapper.extendPage(queryVo);
        List<AdmWebUserVo> voList = new LinkedList<>();
        for(Customer customer : customerList){
            AdmWebUserVo vo = new AdmWebUserVo();
            vo.setHasActived(customer.getIsActivation());
            SBeanUtils.copyProperties2(customer,vo);


            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int countUser(CustomerPageVo queryVo) {
        return customerMapper.extendCount(queryVo);
    }


}
