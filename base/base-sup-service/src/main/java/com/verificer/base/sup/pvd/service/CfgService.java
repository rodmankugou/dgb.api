package com.verificer.base.sup.pvd.service;

public interface  CfgService {

    /**
     * 获取配置
     * @param code
     * @return
     */
    String getCfg(String code);

    /**
     * 获取加密的配置
     * @param code
     * @return 返回解密后的配置值
     */
    String getAesEncryptCfg(String code);
}
