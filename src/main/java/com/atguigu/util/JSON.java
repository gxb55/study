package com.atguigu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @program: 2
 * @description:
 * @author: guoxiaobing
 * @create: 2020-11-15 12:04
 */
public class JSON {
   static ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}