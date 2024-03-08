package com.verificer.base.sup.pvd.service.impl;

import com.amazonaws.partitions.PartitionRegionImpl;
import com.verificer.GlobalConfig;
import com.verificer.base.sup.pvd.entity.Cfg;
import com.verificer.base.sup.pvd.mapper.CfgMapper;
import com.verificer.base.sup.pvd.service.CfgService;
import com.verificer.utils.AESUtils;
import com.verificer.utils.ThreadUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Transactional
public class CfgServiceImpl implements CfgService {
    private static final Logger logger = LoggerFactory.getLogger(CfgServiceImpl.class);

    private Map<String,String> map = new HashMap<>();
    private final ReentrantReadWriteLock L = new ReentrantReadWriteLock();
    private final Lock WLock = L.writeLock();
    private final Lock RLock = L.readLock();

    @Autowired
    CfgMapper mapper;

    @PostConstruct
    protected void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        refresh();
                    } catch (Exception e) {
                        logger.error("刷新配置失败："+e.getMessage(),e);
                    }finally {
                        ThreadUtils.sleep(3*1000);
                    }
                }
            }
        }).start();
    }

    private void refresh(){
        List<Cfg> cfgList = mapper.selectAll();
        Map<String,String> map = new HashMap<>();
        for(Cfg cfg : cfgList){
            map.put(cfg.getCode(),cfg.getVal());
        }

        WLock.lock();
        try {
            this.map = map;
        } finally {
            WLock.unlock();
        }
    }

    @Override
    public String getCfg(String code) {
        RLock.lock();
        try {
            if(!map.containsKey(code))
                throw new RuntimeException("Cfg [code="+code+"] not exist");
            return map.get(code);
        } finally {
            RLock.unlock();
        }

    }

    @Override
    public String getAesEncryptCfg(String code) {
        String val = getCfg(code);
        try {
            return AESUtils.decrypt( GlobalConfig.AES_SEED,val);
        } catch (Exception e) {
            throw new RuntimeException("解密失败："+e.getMessage(),e);
        }
    }
}
