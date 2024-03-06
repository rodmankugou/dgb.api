package com.verificer.utils.serialization;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.verificer.beans.BasePrecisionVo;

import java.io.IOException;

/**
 */
public class PrecisionSerializer extends JsonSerializer<BasePrecisionVo> {

    @Override
    public void serialize(BasePrecisionVo basePrecisionVo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        BasePrecisionVoFilter basePrecisionVoFilter = new BasePrecisionVoFilter(basePrecisionVo.getPrecision());
        String jsonString = JSONObject.toJSONString(basePrecisionVo, basePrecisionVoFilter,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero);
        JSONObject object = JSONObject.parseObject(jsonString);
        jsonGenerator.writeObject(object);
    }
}
