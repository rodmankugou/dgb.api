
package com.verificer.exchange.web.unittest;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 公共的响应
 */
@ApiModel(value = "所有接口的通用返回")
public class AppTResp {



    // 是否成功
    @ApiModelProperty(name = "result",value = "是否成功，true-成功 false-失败")
    private boolean result;

    // 消息描述
    @ApiModelProperty("消息描述")
    private String message;

    // 状态码
    @ApiModelProperty("状态码 1-成功 2-参数错误 3-需要重新登录 4-服务器异常 5-签名不合法 6-需要重新签名 8-权限不足")
    private Integer code ;

    // 总个数
    @ApiModelProperty("总数据条数，需要分页的接口才有返回")
    private int total = 0;

    // 返回数据
    @ApiModelProperty("返回的具体数据")
    private Object data ;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
