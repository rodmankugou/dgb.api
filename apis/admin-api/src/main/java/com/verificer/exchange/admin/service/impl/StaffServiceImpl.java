package com.verificer.exchange.admin.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.auth.service.BaseAuthService;
import com.verificer.beans.RoleVo;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.admin.AdminApiConstants;
import com.verificer.exchange.admin.entity.Staff;
import com.verificer.exchange.admin.mapper.StaffMapper;
import com.verificer.exchange.admin.service.StaffService;
import com.verificer.exchange.admin.vo.StaffVo;
import com.verificer.message.utils.ActiveCodeUtil;
import com.verificer.utils.ImageCodeUtils;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.CheckUtil;
import com.verificer.utils.googalauth.GoogleAuthenticator;
import com.verificer.utils.web.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by 35336 on 2021/1/22.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;

    @Autowired
    ActiveCodeUtil activeCodeUtil;

    @Autowired
    RedisUtil redisUtil;

//    @Autowired
//    BaseAuthService baseAuthService;

    @Value("#{configProperties['IS_DEBUG']}")
    private boolean isDebug;

    @Override
    public Staff login(String username, String password, String imageId, String imageCode, String googleCode, String mobileCode) {
        if(SStringUtils.isEmpty(username)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(password)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!isDebug && SStringUtils.isEmpty(imageId)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!isDebug && SStringUtils.isEmpty(imageCode)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
//        if(SStringUtils.isEmpty(googleCode) && SStringUtils.isEmpty(mobileCode)){
//            throw new BaseException(ErrCode.PARAMS_ERR);
//        }

        if(!isDebug && !ImageCodeUtils.checkCode(redisUtil,imageId,imageCode)){
            throw new BaseException(ErrCode.IMAGE_CODE_CHECK_FAILED);
        }


        Staff staff = staffMapper.selectByUsername(username);
        if(staff == null){
            throw new BaseException(ErrCode.USERNAME_NOT_EXIST);
        }

        //校验谷歌验证码
        if(!isDebug && !SStringUtils.isEmpty(googleCode)){
            Long gCode = null;
            try {
                gCode = Long.parseLong(googleCode);
            } catch (NumberFormatException e) {
                throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
            }

            if (!staff.getGoogleSecretAuth()) {
                throw new BaseException(ErrCode.GOOGLE_AUTH_NOTEXIST);
            }
            long t = System.currentTimeMillis();
            GoogleAuthenticator ga = new GoogleAuthenticator();
            ga.setWindowSize(5);
            if(!ga.check_code(staff.getGoogleSecret(), gCode, t)){
                throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
            }
        }

        //校验手机验证码
        if(!isDebug && !SStringUtils.isEmpty(mobileCode)){
            if(!activeCodeUtil.checkCode(AdminApiConstants.SMS_CODE_SCENE_LOGIN,staff.getMobile(),mobileCode)){
                throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
            }
        }

        //判断是否可用
        if(staff.getEnable() == 0)
            throw new BaseException(ErrCode.STAFF_NOT_ENABLE);

        //校验密码
        if(!PasswordUtil.loginPassword(password).equals(staff.getPassword())){
            throw new BaseException(ErrCode.LOGIN_PASSWORD_ERROR);
        }

        return staff;
    }


    private StaffVo toVo(Staff staff){
        if(staff == null){
            return null;
        }
        StaffVo vo = new StaffVo();
        SBeanUtils.copyProperties2(staff,vo);
//        RoleVo roleVo = baseAuthService.getUserRole(staff.getId());
//        if(roleVo != null){
//            vo.setRoleName(roleVo.getName());
//            vo.setRoleId(roleVo.getId());
//        }
        return vo;
    }

    private List<StaffVo> toVoList(List<Staff> staffList){
        List<StaffVo> voList = new LinkedList<>();
        for(Staff staff : staffList){
            voList.add(toVo(staff));
        }
        return voList;
    }

    @Override
    public StaffVo detail(Long id) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Staff staff = staffMapper.selectByPrimaryKey(id);
        return toVo(staff);
    }

    @Override
    public List<StaffVo> page(Integer from, Integer pageSize) {
        List<Staff> staffList = staffMapper.selectPage(from,pageSize);
        return toVoList(staffList);
    }

    @Override
    public int count() {
        return staffMapper.countPage();
    }

    @Override
    public void add(Long creatorId, String username, String realName, String mobile, String password, Long roleId, Integer enable) {
        if(creatorId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(username)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(realName)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(password)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!CheckUtil.checkLoginPassword(password)){
            throw new BaseException(ErrCode.LOGIN_PASSWORD_NOT_MATCH);
        }

        if(staffMapper.selectByUsername(username) != null)
            throw new BaseException(ErrCode.STAFF_USER_NAME_HAS_EXIST);


        Staff staff = new Staff();
        staff.setCreatorId(creatorId);
        staff.setUsername(username);
        staff.setRealName(realName);
        staff.setPassword(PasswordUtil.loginPassword(password));
        staff.setMobile(mobile);
        staff.setEnable(enable);
        staff.setCreateTime(System.currentTimeMillis());
        staff.setLockStatus(0);

        staffMapper.insertSelective(staff);
//        if(roleId != null){
//            baseAuthService.setUserRole(staff.getId(),roleId);
//        }
    }

    @Override
    public void update(Long id, String realName, Long roleId, Integer enable) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Staff staff = new Staff();
        staff.setId(id);
        staff.setRealName(realName);
        staff.setEnable(enable);
        staffMapper.updateByPrimaryKeySelective(staff);
//        baseAuthService.setUserRole(id,roleId);
    }

    @Override
    public void updateEnableStatus(Long id, Integer enable) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Staff staff = new Staff();
        staff.setId(id);
        staff.setEnable(enable);
        staffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public void updateLockStatus(Long id, int lockStatus) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Staff staff = new Staff();
        staff.setId(id);
        staff.setLockStatus(lockStatus);
        staffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public Staff getById(Long id) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        return staffMapper.selectByPrimaryKey(id);
    }

    @Override
    public String getRealName(Long id) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Staff staff = staffMapper.selectByPrimaryKey(id);
        if(staff == null){
            throw new BaseException(ErrCode.STAFF_NOT_EXIST);
        }
        return staff.getRealName();
    }

    @Override
    public void resetPassword(Long id, String defaultResetPassword) {
        Staff staff = new Staff();
        staff.setId(id);
        staff.setPassword(PasswordUtil.loginPassword(defaultResetPassword));
        staffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public void updatePassword(Long staffId, String originPassword, String password) {
        if(staffId == null )
            throw new BaseException(ErrCode.PARAMS_ERR);

        if(SStringUtils.isEmpty(originPassword))
            throw new BaseException(ErrCode.PARAMS_ERR);

        if(SStringUtils.isEmpty(password))
            throw new BaseException(ErrCode.PARAMS_ERR);

        Staff staff = staffMapper.selectByPrimaryKey(staffId);
        if(staff == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(originPassword.equals(password)){
            throw new BaseException(ErrCode.STAFF_NEW_PWD_EQUALS_OLD_PWD);
        }

        if(!PasswordUtil.loginPassword(originPassword).equals(staff.getPassword())){
            throw new BaseException(ErrCode.LOGIN_PASSWORD_ERROR);
        }


        staff.setPassword(PasswordUtil.loginPassword(password));
        staffMapper.updateByPrimaryKey(staff);

    }

    @Override
    public Staff getByUserName(String username) {
        if(SStringUtils.isEmpty(username))
            throw new BaseException(ErrCode.PARAMS_ERR);
        return staffMapper.selectByUsername(username);
    }
}
