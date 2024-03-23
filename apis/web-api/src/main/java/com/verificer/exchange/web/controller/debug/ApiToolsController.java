package com.verificer.exchange.web.controller.debug;

import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.utils.FastJson;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "API 索引")
@RequestMapping("/debug/api")
@RestController
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
            value = "查询所有页面",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页面索引，如BO-F4",paramType = "form",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/page/list", method = RequestMethod.POST)
    public Response list() throws IOException {
        System.out.println(pageIndex);

        InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream("api.json");
        String json = IOUtils.toString(io,"utf-8");

        List<Page> pages = FastJson.parseArray(json,Page.class);

        List<String> names = new LinkedList<>();
        for(Page page : pages)
            names.add(page.getName());
        return Response.dataSuccess(names);
    }


    @ApiOperation(
            value = "查询接口索引",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页面索引，如BO-F4",paramType = "form",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Response search(@RequestParam(required = true) String pageIndex) throws IOException {
        System.out.println(pageIndex);

        InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream("api.json");
        String json = IOUtils.toString(io,"utf-8");

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
