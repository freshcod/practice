package com.coral.practice.wheels.achieve;


import com.coral.practice.wheels.anno.MyAutoired;
import com.coral.practice.wheels.dao.StudentDao;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qiuhai on 2016/6/17.
 */
public class DaoInjectAchieve {
    private String scanPath = "com.coral.practice.wheels.dao";
    private Map<String,Object> daoMap = new HashMap<String, Object>();


    public void init() throws UnsupportedEncodingException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InstantiationException,
            DocumentException, NoSuchFieldException, InvocationTargetException {


        String sysPath = this.getClass().getResource("/").getPath();
        String dirPath = sysPath+scanPath.replace(".","/");
        File dir = new File(java.net.URLDecoder.decode(dirPath,"UTF-8"));
        File[] files = dir.listFiles();
        String path = this.getClass().getResource("/").getPath()+"springTest.xml";
        Map<String,Object> beanMap = parseSpringXML(path);
        for(File file:files){
            String className = scanPath+"."+ StringUtils.split(file.getName(),".")[0];
            Class clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Method[] methods = clazz.getDeclaredMethods();

            for (Field field:fields){
                String fieldName = field.getName();
                if(field.isAnnotationPresent(MyAutoired.class)){
                    MyAutoired myAutoired = (MyAutoired)field.getAnnotation(MyAutoired.class);
                    String value = myAutoired.value();
                    String methodName = "set"+StringUtils.substring(fieldName,0,1).toUpperCase()+StringUtils.substring(fieldName,1);
                    for (Method method:methods){
                        if(methodName.equals(method.getName())){
                            System.out.println(method.getName());
                            method.invoke(obj,beanMap.get(value));
                        }
                    }
                }
            }
            daoMap.put(clazz.getName(),obj);
            System.out.println(daoMap);
        }

    }


    public Map<String,Object> parseSpringXML(String path) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        File file = new File(path);
        Map<String,Object> beanMap = new HashMap<String, Object>();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file);
        Element beans = document.getRootElement();
        for(Iterator it = beans.elementIterator(); it.hasNext();){
            Element bean = (Element)it.next();
            Map<String,Object> fieldMap = new HashMap<String, Object>();
            String classPath = bean.attribute("class").getValue();
            String id = bean.attribute("id").getValue();
            Class<?> clazz = Class.forName(classPath);
            Object obj = clazz.newInstance();

            for(Iterator iterator = bean.elementIterator(); iterator.hasNext();){
                Element property = (Element) iterator.next();
                String fieldName = property.attribute("name").getValue();
                String fieldValue = property.attribute("value").getValue();
                Object value = null;
                Field field = clazz.getDeclaredField(fieldName);
                Class<?> cClass = field.getType();
                if(cClass==Integer.class){
                    value = Integer.parseInt(fieldValue);
                }else if(cClass == Double.class){
                    value = Double.parseDouble(fieldValue);
                }else if(cClass == Date.class){
                    value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fieldValue);
                }else {
                    value = fieldValue;
                }
                fieldMap.put(fieldName,value);
                System.out.println(property.attribute("name").getValue()+":"+property.attribute("value").getValue());
            }
            obj = setValue(obj,fieldMap);
            beanMap.put(id,obj);
        }
        System.out.println(beanMap);
        return beanMap;
    }


    public Object setValue(Object obj, Map<String,Object> fieldMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            String fieldName = field.getName();
            if(fieldMap.get(fieldName)!=null){
                String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);

                Method[] methods = clazz.getMethods();
                for(Method method:methods){
                    if(methodName.equals(method.getName())){
                        method.invoke(obj,fieldMap.get(fieldName));
                    }
                }

            }

        }
        return obj;
    }

    public static void main(String[] args){
        try{
            DaoInjectAchieve daoInjectAchieve = new DaoInjectAchieve();
            daoInjectAchieve.init();
            StudentDao studentDao = (StudentDao)daoInjectAchieve.daoMap.get("com.coral.practice.wheels.dao.StudentDao");
            System.out.println(studentDao.getStudent());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
