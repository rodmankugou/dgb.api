package com.verificer.biz.biz.service.core.user;

import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.integral.AppIntegralLogVo;
import com.verificer.biz.beans.vo.integral.IntegralListVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public interface IntegralService  {

    BigDecimal integralBalance(IdVo idVo);

    List<AppIntegralLogVo> integralList(IntegralListVo reqVo);
}
