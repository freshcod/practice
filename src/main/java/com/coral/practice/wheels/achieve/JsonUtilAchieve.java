package com.coral.practice.wheels.achieve;

/**
 * Created by qiuhai on 2016/6/17.
 */
import com.coral.practice.utils.JsonUtils;
import com.coral.practice.wheels.bean.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by qiuhai on 2016/6/15.
 */
public class JsonUtilAchieve {

    /**
     *粗略实现json字符串转javabean
     * @param target
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static <T> T str2bean(String target, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        //json字符串解析
        Map<String,Object> kvMap = baseParse(target);
        T obj = clazz.newInstance();
        System.out.println("kvMap:"+kvMap);

        //成员变量获取方法名
        Field[] fields = clazz.getDeclaredFields();
        Map<String,Object> methodMap = new HashMap<String, Object>();
        for(Field field:fields){
            String fieldName = field.getName();
            String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            methodMap.put(methodName,kvMap.get(fieldName));
        }
        System.out.println("methodMap:"+methodMap);

        //获取方法
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            String mthName = method.getName();
            for(String key:methodMap.keySet()){
                if(key.equals(mthName)){
                    method.invoke(obj,methodMap.get(key));

                }
            }
        }
        return obj;
    }


    /**
     * 粗略实现javabean转json字符串
     *
     * @param obj
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String bean2str(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(Field field:fields){
            String fieldName = field.getName();
            sb.append("\"");
            sb.append(fieldName);
            sb.append("\":");
            String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            Method method = clazz.getDeclaredMethod(methodName);
            Class<?> returnType  = method.getReturnType();
            Object value = method.invoke(obj,new Object[]{});
            if(returnType == String.class){
                sb.append("\"");
                sb.append(value);
                sb.append("\"");
            }else if(returnType == Date.class){
                sb.append("\"");
                sb.append(value.toString());
                sb.append("\"");
            }else {
                sb.append(value);
            }
            sb.append(",");
        }
        int size = sb.length();
        sb.deleteCharAt(size-1);
        sb.insert(size-1,"}");
        return sb.toString();
    }


    private static Map<String,Object> baseParse(String target){
        //json字符串解析
        Map<String,Object> kvMap = new HashMap<String, Object>();
        //json体
        String jsonBody = target.substring(1,target.length()-1);
        //按,拆分
        List<String> jsonList = new ArrayList<String>();
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<jsonBody.length();i++){
            char temp = jsonBody.charAt(i);
            if(temp=='"')  flag++;
            if(temp == ','&&flag%2==0){
                jsonList.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            sb.append(temp);
        }
        jsonList.add(sb.toString());

        //按:拆分
        sb = new StringBuilder();
        List<String> kvList = new ArrayList<String>();
        for(String kv:jsonList){
            for(int j=0;j<kv.length();j++){
                char temp = kv.charAt(j);
                if(temp=='"')  flag++;
                if(temp == ':'&&flag%2==0){
                    kvList.add(sb.toString());
                    sb = new StringBuilder();
                    continue;
                }
                sb.append(temp);
            }
            kvList.add(sb.toString());
            sb = new StringBuilder();
        }

        //组装成Map
        for(int k=0;k<kvList.size();k++){
            String key = kvList.get(k).replaceAll("\"","");
            String val = kvList.get(k+1);
            Object value = null;
            if(val.startsWith("\"")){
                value = val.replaceAll("\"","");
            }else{
                value = parseStr(val);
            }
            kvMap.put(key,value);
            k++;
        }
        return kvMap;
    }


    public static Object parseStr(String target){
        Pattern intPattern = Pattern.compile("[0-9]*");//INT型
        Pattern floatPattern = Pattern.compile("[\\\\d]+\\\\.[\\\\d]+");//浮点型
        if(intPattern.matcher(target).matches()){
            return Integer.parseInt(target);
        }else if(floatPattern.matcher(target).matches()){
            return Double.parseDouble(target);
        }else{
           return Double.parseDouble(target);
        }

    }


    public static void main(String[] args){
        try {

            String target = "[{\"user_name\":\"z,:s\",\"userAge\":23,\"gender\":\"M\",\"mark\":654}]";
            List<Student> students = JsonUtils.jsonToList(target,Student.class,true);
            System.out.println(students);
//            String jsonStr = JsonUtilAchieve.bean2str(student);
//            System.out.println("jsonStr:"+jsonStr);
//            Student stu = JsonUtilAchieve.str2bean(jsonStr,Student.class);
//            System.out.println(stu);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}