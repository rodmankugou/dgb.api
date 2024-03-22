package com.verificer.exchange.admin.controller.debug;

import com.verificer.biz.beans.vo.req.OrdFormVo2;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.DebugController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.FastJson;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "API 索引")
@RequestMapping("/debug/api")
@RestController
@DebugController
public class ApiToolsController extends BaseController {

    @Autowired
    BizService bizService;



    static class Page{
        private String name;
        private Map<String,String> apis;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getApis() {
            return apis;
        }

        public void setApis(Map<String, String> apis) {
            this.apis = apis;
        }
    }

    @ApiOperation(
            value = "同步银豹数据",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页面索引，如BO-F4",paramType = "form",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    public Response sync(@RequestParam(required = true) String pageIndex) throws IOException {
        System.out.println(pageIndex);
        File file =  new ClassPathResource("api.json").getFile();
        String json = FileUtils.readFileToString(file,"utf-8");

        List<Page> pages = FastJson.parseArray(json,Page.class);

        Page target = null;
        for(Page page : pages){
            if(page.getName().equalsIgnoreCase(pageIndex)){
                target = page;
                break;
            }
        }
        return Response.dataSuccess(target);
    }



}
