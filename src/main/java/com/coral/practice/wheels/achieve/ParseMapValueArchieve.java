package com.coral.practice.wheels.achieve;

import com.coral.practice.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiuhai on 2016/6/23.
 */
public class ParseMapValueArchieve {
    public static void main(String[] args) throws JsonProcessingException {
        String target="{\"name\":\"zs\",\"age\":23,\"gender\":\"M\",\"mark\":654,\"details\":[{\"name\":\"z1\",\"age\":22,\"gender\":\"M\",\"mark\":634},\n" +
                "{\"name\":\"z2\",\"age\":21,\"gender\":\"M\",\"mark\":534},{\"name\":\"z3\",\"age\":22,\"gender\":\"M\",\"mark\":654},{\"name\":\"z4\",\"age\":22,\"gender\":\"M\",\"mark\":598}],\"map\":{\"t1\":1,\"t2\":2,details:[{\"t1\":1.1,\"t2\":2.1},{\"t1\":1.2,\"t2\":2.2},{\"t1\":1.3,\"t2\":2.3}]}}";

        Map<String,Object> map = JsonUtils.parseJSON2Map(target);
        ParseMapValueArchieve parseMapValueArchieve = new ParseMapValueArchieve();
        Map<String,Object> newMap = parseMapValueArchieve.parseMapValue(map);
        String jsonStr = JsonUtils.map2Json(newMap);
        System.out.println(jsonStr);

    }

    /**
     * 将Map的Value转换为String类型
     * @param map
     * @return
     */
    public Map<String,Object> parseMapValue(Map<String,Object> map){
        Map<String,Object> newMap = new HashMap<String, Object>();
        for(String key:map.keySet()){
            Object value = map.get(key);
            if(value instanceof ArrayList){
                List<Map<String,Object>> tempList = (ArrayList<Map<String,Object>>)value;
                List<Map<String,Object>> newList = new ArrayList<Map<String, Object>>();
                for(Map<String,Object> tempMap:tempList){
                    newList.add(parseMapValue(tempMap));
                }
                newMap.put(key,newList);
            }else if(value instanceof net.sf.json.JSONObject){
                Map<String,Object> tempMap = JsonUtils.parseJSON2Map(value.toString());
                newMap.put(key,parseMapValue(tempMap));
            }else if(value instanceof HashMap){
                Map<String,Object> tempMap = JsonUtils.parseJSON2Map(value.toString());
                newMap.put(key,parseMapValue(tempMap));
            }else{
                newMap.put(key, String.valueOf(value));
            }
        }
        return  newMap;
    }
}
