/**
 *
 */
package com.verificer.web.common.i18n;

import com.alibaba.dubbo.rpc.RpcContext;
import com.verificer.utils.ThreadLocalUtil;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Map;

/**
 * 动态过去local
 */
public class MessageSource extends ReloadableResourceBundleMessageSource {

    public final String getMessage(String code) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String requestLanguage = request.getHeader("language");//请求头获取language
        Map<String, String> attachments = RpcContext.getContext().getAttachments();
        String language = attachments.get("LOCAL_LANGUAGE");
        String requestLanguage = ThreadLocalUtil.get("LOCAL_LANGUAGE");
//        String requestLanguage = "en_US";
        String lang[] = null;
        Locale locale;

        if (null != requestLanguage && !requestLanguage.equals("")) {
//            ThreadLocalUtil.remove("LOCAL_LANGUAGE");
            lang = requestLanguage.split("_");
            if (null != lang && lang.length > 1) {
                locale = new Locale(lang[0], lang[1]);
                System.out.println("locale==" + locale.toString());
            } else {
                locale = LocaleContextHolder.getLocale();
            }
        } else {
            if (null != language && !language.equals("")) {
//                 locale = new Locale(lang[0],lang[1]);
                lang = language.split("_");
                if (lang.length > 1) {
                    locale = new Locale(lang[0],lang[1]);
                } else {
                    locale = LocaleContextHolder.getLocale();
                }
//                System.out.println("locale=99999=" + locale.toString());
            } else {
                locale = LocaleContextHolder.getLocale();
            }
        }
        String message = super.getMessage(code, null, locale);

        System.out.println("locale==" + locale.toString() + "---- " + message);

        return message;
    }

    public final String getMessageWithArgs(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return super.getMessage(code, args, locale);
    }
}
