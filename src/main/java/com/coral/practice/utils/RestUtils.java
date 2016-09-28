package com.coral.practice.utils;

import net.sf.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qiuhai on 2016/7/23.
 */
public class RestUtils {

    private static RestTemplate restTemplate = new RestTemplate();




    /**
     * RESTFUL调用post接口
     * @param url  REST接口地址
     * @param object 传入的数据json字符串
     * @param responseType 返回的类型
     * @param <T> 返回指定的类型
     * @return T 指定的类型
     */
    public static <T> T postData(String url,String object,Class<T> responseType){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(object, headers);
        T result = restTemplate.postForObject(url,formEntity,responseType);
        return result;
    }

    /**
     * RESTFUL调用put接口
     * @param url REST接口地址
     * @param object 传入的数据json字符串
     */
    public static void putData(String url,String object){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(object, headers);
        restTemplate.put(url,formEntity);

    }
}
