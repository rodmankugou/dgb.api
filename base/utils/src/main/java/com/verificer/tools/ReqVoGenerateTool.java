package com.verificer.tools;

import com.verificer.tools.packagescan.ClassUtils;
import com.verificer.utils.SStringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扫描包生成requestVo
 * Created by 35336 on 2020/12/30.
 */
public class ReqVoGenerateTool {

    public static List<Method> getControllerMethod(Class clazz){
        List<Method> rst = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            if(m.getAnnotation(RequestMapping.class) != null){
                rst.add(m);
            }
        }
        return rst;
    }

    public static List<Parameter> getParamClasss(Method method,Class... ignoreClass){
        Parameter[] parameters = method.getParameters();
        List<Parameter> rst = new ArrayList<>();
        for(Parameter parameter : parameters){
            for(Class ignore : ignoreClass){
                if(parameter.getType().equals(ignore)){
                    continue;
                }
                rst.add(parameter);
            }
        }
        return rst;
    }

    private static boolean isJsonReq(Method method){
        Parameter[] params = method.getParameters();
        for(Parameter p : params){
            if(p.getAnnotation(RequestBody.class) != null){
                return true;
            }
        }
        return false;
    }



    public static ApiImplicitParam getApiAnnotation(Method method, Parameter parameter){
        ApiImplicitParams ano = method.getAnnotation(ApiImplicitParams.class);
        if(ano == null){
            return null;
        }
        for(ApiImplicitParam  ap : ano.value()){
            if(parameter.getName().equals(ap.name())){
                return ap;
            }
        }

        return null;

    }

    public static String genrateReqVo(String packageName,String className,Method method,String classTemplate){
        String apiTmmplate = "  @ApiModelProperty(value = \"${value}\",required = ${required}), type=${dataType}\n";
        String fieldTemplate = "    private ${type} ${name};\n\n";
        if(isJsonReq(method)){
            return null;
        }


        StringBuffer content = new StringBuffer();
        List<Parameter> params = getParamClasss(method, HttpServletRequest.class);
        for(Parameter p : params){
            //生成api描述
            ApiImplicitParam apiParam = getApiAnnotation(method,p);
            Map<String,String> pMap = new HashMap<>();
            if(apiParam != null){
                if(!SStringUtils.isEmpty(apiParam.name())){
                    pMap.put("value",apiParam.value());
                    pMap.put("required",new Boolean(apiParam.required()).toString());
                    pMap.put("dataType",p.getType().getSimpleName().toLowerCase());
                }
            }else {
                ApiParam ap = p.getAnnotation(ApiParam.class);
                if(ap != null){
                    pMap.put("value",ap.value());
                    pMap.put("required",new Boolean(ap.required()).toString());
                    pMap.put("dataType",p.getType().getSimpleName().toLowerCase());
                }
            }
            if(pMap.size() > 0){
                content.append(SStringUtils.resolvePlaceholders(apiTmmplate,pMap));
            }

            //生成字段描述
            String typeName = p.getType().getSimpleName();
            Map<String,String> map = new HashMap<>();
            map.put("type",typeName);
            map.put("name",p.getName());
            content.append(SStringUtils.resolvePlaceholders(fieldTemplate,map));
        }

        Map<String,String> map = new HashMap<>();
        map.put("packageName",packageName);
        map.put("className",className);
        map.put("fields",content.toString());
        String clasContent = SStringUtils.resolvePlaceholders(classTemplate,map);
        return clasContent;
    }

    public static void generateVoClassFile(String outputDir,String packageName,String targetPackageName,String classTemplateFileName) throws IOException {
        File clasTemplateFile = new File(classTemplateFileName);
        String classTemplate = FileUtils.readFileToString(clasTemplateFile,"utf-8");

        List<Class> controllerClass = ClassUtils.scanPackage(packageName);
        for(Class clazz : controllerClass){

            //生成最终目录
            String subPackage = clazz.getName().substring(packageName.length()+1);
            StringBuffer outDir = new StringBuffer(outputDir).append("\\");
            String[] ss = subPackage.split("\\.");
            for(int i = 0 ; i < ss.length -1;i++){
                outDir.append(ss[i]).append("\\");
            }

            List<Method> controllerMethods = getControllerMethod(clazz);
            for(Method method : controllerMethods){
                String voClassName = clazz.getSimpleName()+"_"+method.getName();
                String classText = genrateReqVo(targetPackageName,voClassName,method,classTemplate);
                File file = new File(outDir.toString()+voClassName+".java");
                FileUtils.write(file,classText,"utf-8");
            }
        }
    }

    public static void main(String args[]) throws IOException {
        String packageName = "com.verificer.exchange.web.controller";
        String targetPackageName = "com.verificer.exchange.web.controller.vo.req";
        String outputDir = "D:\\devTemp\\reqvo";
        String templateFile = "D:\\devTemp\\classTemplate.txt";

        generateVoClassFile(outputDir,packageName,targetPackageName,templateFile);
    }
}
