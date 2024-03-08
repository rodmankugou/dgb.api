
package com.verificer.biz.biz.config;

import com.verificer.base_user.config.LimitedConfig;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 35336 on 2020/12/26.
 */
@Component
@PropertySource(value = {"classpath:properties/config.properties"})
@ConfigurationProperties(prefix = "config")
public class BizConfig {
    private boolean isDebug;

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }
}
