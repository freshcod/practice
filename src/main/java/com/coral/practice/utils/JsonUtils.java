package com.coral.practice.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class JsonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();

    /**
     * jsonArr转换为List<Map<String,Object>>
     * @param jsonStr
     * @return
     */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> parseJSON2List(String jsonStr){  
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){  
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));  
        }  
        return list;  
    }


	
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanClass);
		return bean;

	}
	
	public static Map<String,Map<String,Object>> json2Map2(String json) throws Exception{
		Map<String, String> maps=parseJSON2MapString(json);
    	Map<String,Map<String,Object>> resultMap=new HashMap<String, Map<String,Object>>();
    	Set<String> keys=maps.keySet();
    	Iterator<String> it=keys.iterator();
    	while(it.hasNext()){
    		String key=it.next();
    		Map<String,Object> map = parseJSON2Map(maps.get(key).toString());
    		resultMap.put(key,map);
    	}
		return resultMap;
	}
	
	public static String map2Json(Map map) throws JsonProcessingException {
		return mapper.writeValueAsString(map);
	}



	/**
	 * 将json字符串转换为java对象
	 * @param jsonString json字符串
	 * @param beanClass 要转换的class
	 * @param prefix 子项前缀
	 * @param childClass 子项对应的class
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T jsonToBean(String jsonString, Class<T> beanClass,String prefix,Class childClass) {
		Map<String, Class> classMap = new HashMap<String, Class>();  
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(beanClass);  
        classMap.put(prefix, childClass); // 指定JsonRpcRequest的request字段的内部类型  
        jsonConfig.setClassMap(classMap); 
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, jsonConfig);
		return bean;

	}
	
	
	
	/**
	 * 将json字符串转换成实体
	 * @param jsonString json字符串
	 * @param beanClass 需要转换的实体class
	 * @param isUnderlineToCamel 是否下划线转驼峰
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T jsonToBean(String jsonString,Class<T> beanClass,boolean isUnderlineToCamel) throws JsonParseException, JsonMappingException, IOException{
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		if(isUnderlineToCamel){
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		T bean = mapper.readValue(jsonString, beanClass);
		return bean;
	}


    /**
     *
     * @param bean
     * @param isCamelToUnderline 是否将驼峰转化成下划线
     * @return
     * @throws JsonProcessingException
     */
	public static String beanToJson(Object bean,boolean isCamelToUnderline) throws JsonProcessingException {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		if(isCamelToUnderline){
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		String jsonStr = mapper.writeValueAsString(bean);
		return jsonStr;
	}



	
	/**
	 *  将json字符串转换成实体数组
	 * @param jsonString json字符串
	 * @param beanClass  需要转换的实体class
	 * @param isUnderlineToCamel 是否转下划线
	 * @return
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> List<T> jsonToList(String jsonString,Class<T> beanClass,boolean isUnderlineToCamel) throws JsonMappingException, IOException{
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		if(isUnderlineToCamel){
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		JavaType javaType = getCollectionType(ArrayList.class, beanClass);
		List<T> datas = mapper.readValue(jsonString, javaType); 
		return datas;
	}

	/**
	 *  将json字符串转换成实体数组
	 * @param jsonString json字符串
	 * @param beanClass  需要转换的实体class
	 * @param isUnderlineToCamel 是否转下划线
	 * @param isKeyUpperCase key是否大写
	 * @return
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> List<T> jsonToList(String jsonString,Class<T> beanClass,boolean isUnderlineToCamel,boolean isKeyUpperCase) throws JsonMappingException, IOException{
		if(isKeyUpperCase){
			List<Map<String,Object>> beforeJsonData = parseJSON2List(jsonString);
			List<Map<String,Object>> convertedDatas = new ArrayList<Map<String, Object>>();
			for(Map<String,Object> jsonData:beforeJsonData){
				Map<String,Object> convertData = new HashMap<String, Object>();
				for(String key:jsonData.keySet()){
					convertData.put(key.toLowerCase(),jsonData.get(key));
				}
			}
		}


		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		if(isUnderlineToCamel){
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		JavaType javaType = getCollectionType(ArrayList.class, beanClass);
		List<T> datas = mapper.readValue(jsonString, javaType);
		return datas;
	}
	
	public static String listToJson(List datas){
		try {
			return mapper.writeValueAsString(datas);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToListBean(String jsonString,Class<T> beanClass){
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<T> list = new ArrayList<T>();
		for(int i = 0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			T bean = (T) JSONObject.toBean(jsonObject, beanClass);
			list.add(bean);
		}
		return list;
	}
	
   /**   
    * 获取泛型的Collection Type  
    * @param collectionClass 泛型的Collection   
    * @param elementClasses 元素类   
    * @return JavaType Java类型   
    * @since 1.0   
    */   
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }


	
	
	private static  String underscoreName(String name)  
	 {  
	   StringBuilder result = new StringBuilder();  
	   if ((name != null) && (name.length() > 0)) {  
	     result.append(name.substring(0, 1).toLowerCase());  
	     for (int i = 1; i < name.length(); ++i) {  
	       String s = name.substring(i, i + 1);  
	       if (s.equals(s.toUpperCase())) {  
	         result.append("_");  
	         result.append(s.toLowerCase());  
	       }  
	       else {  
	         result.append(s);  
	       }  
	     }  
	   }  
	   return result.toString();  
	 } 
	

	public static Map<String, Object> parseJSON2Map(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){  
            Object v = json.get(k);
            if(v!=null){
                //如果内层还是数组的话，继续解析
                if(v instanceof JSONArray){
                    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                    Iterator<JSONObject> it = ((JSONArray)v).iterator();
                    while(it.hasNext()){
                        JSONObject json2 = it.next();
                        list.add(parseJSON2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else {
                    map.put(k.toString(), v);
                }
            }

        }  
        return map;  
    }
	
	 public static String object2json(Object obj) {  
	        StringBuilder json = new StringBuilder();  
	        if (obj == null) {  
	            json.append("\"\"");  
	        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float   
	                || obj instanceof Boolean || obj instanceof Short || obj instanceof Double   
	                || obj instanceof Long || obj instanceof BigDecimal  
	                || obj instanceof BigInteger || obj instanceof Byte) {  
	            json.append("\"").append(string2json(obj.toString())).append("\"");  
	        } else if (obj instanceof Object[]) {  
	            json.append(array2json((Object[]) obj));  
	        } else if (obj instanceof List) {  
	            json.append(list2json((List<?>) obj));  
	        } else if (obj instanceof Map) {  
	            json.append(map2json((Map<?, ?>) obj));  
	        } else if (obj instanceof Set) {  
	            json.append(set2json((Set<?>) obj));  
	        } else {  
	            json.append(bean2json(obj));  
	        }  
	        return json.toString();  
	    }  
	    /** 
	     * @param bean bean对象 
	     * @return String 
	     */  
	    public static String bean2json(Object bean) {  
	        StringBuilder json = new StringBuilder();  
	        json.append("{");  
	        PropertyDescriptor[] props = null;  
	        try {  
	            props = Introspector.getBeanInfo  
	                (bean.getClass(), Object.class).getPropertyDescriptors();  
	        } catch (IntrospectionException e) {  
	            e.printStackTrace();  
	        }  
	        if (props != null) {  
	            for (int i = 0; i < props.length; i++) {  
	                try {  
	                    String name = object2json(props[i].getName());  
	                    String value = object2json(props[i].getReadMethod().invoke(bean));  
	                    json.append(name);  
	                    json.append(":");  
	                    json.append(value);  
	                    json.append(",");  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            json.setCharAt(json.length() - 1, '}');  
	        } else {  
	            json.append("}");  
	        }  
	        return json.toString();  
	    }  
	    /** 
	     * @param list list对象 
	     * @return String 
	     */  
	    public static String list2json(List<?> list) {  
	        StringBuilder json = new StringBuilder();  
	        json.append("[");  
	        if (list != null && list.size() > 0) {  
	            for (Object obj : list) {  
	                json.append(object2json(obj));  
	                json.append(",");  
	            }  
	            json.setCharAt(json.length() - 1, ']');  
	        } else {  
	            json.append("]");  
	        }  
	        return json.toString();  
	    }  
	    /** 
	     * @param array 对象数组 
	     * @return String 
	     */  
	    public static String array2json(Object[] array) {  
	    	String jsonStr = "";
			try {
				jsonStr = mapper.writeValueAsString(array);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return jsonStr;  
	    }

	    /** 
	     * @param map map对象 
	     * @return String 
	     */  
	    public static String map2json(Map<?, ?> map) {  
	    	String jsonStr = "";
			try {
				jsonStr = mapper.writeValueAsString(map);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return jsonStr;  
	    }  
	    /** 
	     * @param set 集合对象 
	     * @return String 
	     */  
	    public static String set2json(Set<?> set) {  
	    	String jsonStr = "";
			try {
				jsonStr = mapper.writeValueAsString(set);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return jsonStr;   
	    }  
	    /** 
	     * @param s 参数 
	     * @return String 
	     */  
	    public static String string2json(String s) {  
	        if (null == s){  
	            return "";  
	        }  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0; i < s.length(); i++) {  
	            char ch = s.charAt(i);  
	            switch (ch) {  
	            case '"':  
	                sb.append("\\\"");  
	                break;  
	            case '\\':  
	                sb.append("\\\\");  
	                break;  
	            case '\b':  
	                sb.append("\\b");  
	                break;  
	            case '\f':  
	                sb.append("\\f");  
	                break;  
	            case '\n':  
	                sb.append("\\n");  
	                break;  
	            case '\r':  
	                sb.append("\\r");  
	                break;  
	            case '\t':  
	                sb.append("\\t");  
	                break;  
	            case '/':  
	                sb.append("\\/");  
	                break;  
	            default:  
	                if (ch >= '\u0000' && ch <= '\u001F') {  
	                    String ss = Integer.toHexString(ch);  
	                    sb.append("\\u");  
	                    for (int k = 0; k < 4 - ss.length(); k++) {  
	                        sb.append('0');  
	                    }  
	                    sb.append(ss.toUpperCase());  
	                } else {  
	                    sb.append(ch);  
	                }  
	            }  
	        }  
	        return sb.toString();  
	    }

    public static <T> Map<String,T> jsonToMapBean(String jsonString,Class<T> beanClass) throws JsonParseException, JsonMappingException {
    	Map<String, Object> maps=parseJSON2Map(jsonString);
    	Map<String,T> resultMap=new HashMap<String, T>();
    	Set<String> keys=maps.keySet();
    	Iterator<String> it=keys.iterator();
    	while(it.hasNext()){
    		String key=it.next();
    		T t=jsonToBean(maps.get(key).toString(),beanClass);
    		resultMap.put(key,t);
    	}
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, String>> parseJSON2ListString(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2MapString(json2.toString()));
        }
        return list;
    }

    public static Map<String, String> parseJSON2MapString(String jsonStr){  
        Map<String, String> map = new HashMap<String, String>();  
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){ 
            Object v = json.get(k);
            if(null!=v){
                map.put(k.toString(), v.toString());
            }
        }  
        return map;  
    }
    
    /**
     * 把json字符串转换成Map<String,List<T>> 格式
     * @param jsonString json字符串
     * @param beanClass 对应T的实体
     * @return Map<String,List<T>>
     */
    public static <T> Map<String,List<T>> json2MapListBean(String jsonString,Class<T> beanClass){
    	Map<String, String> maps=parseJSON2MapString(jsonString);
    	Map<String,List<T>> resultMap=new HashMap<String, List<T>>();
    	Set<String> keys=maps.keySet();
    	Iterator<String> it=keys.iterator();
    	while(it.hasNext()){
    		String key=it.next();
    		System.out.println(maps.get(key).toString());
    		List<T> list = jsonToListBean(maps.get(key).toString().replaceAll("'", ""), beanClass);
    		resultMap.put(key,list);
    	}
    	return resultMap;
    	
    }


    public static void main(String[] args) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, JsonMappingException, IOException {
//    	  Map<String,Object> params=new HashMap<String, Object>();
//    	  params.put("Json", "sds");
//    	  System.out.println("上单："+map2json(params));
//			UploadPacks uploadPacks = new UploadPacks();
//			uploadPacks.setBatch_code("123");
//			List<TempleExcelPacks> details = new ArrayList<TempleExcelPacks>();
//			TempleExcelPacks templeExcelPacks = new TempleExcelPacks();
//			templeExcelPacks.setAct_length(1.2);
//			details.add(templeExcelPacks);
//			uploadPacks.setDetails(details);
//			JSONObject jsonObject = JSONObject.fromObject(uploadPacks);
//			String jsonStr = jsonObject.toString();
//			UploadPacks test = jsonToBean(jsonStr, UploadPacks.class,"details",TempleExcelPacks.class);
//			JsonConfig jsonConfig = new JsonConfig();  
//	        jsonConfig.setRootClass(UploadPacks.class);  
//	        Map<String, Class> classMap = new HashMap<String, Class>();  
//	        classMap.put("details", TempleExcelPacks.class); // 指定JsonRpcRequest的request字段的内部类型  
//	        jsonConfig.setClassMap(classMap); 
//			JSONObject jsonObject = JSONObject.fromObject(uploadPacks);
//			String jsonStr = jsonObject.toString();
//			JSONObject jsonObject2 = JSONObject.fromObject(jsonStr);
//			UploadPacks test = (UploadPacks)jsonObject2.toBean(jsonObject, jsonConfig);
//			System.out.println(test.getOverride_duplicate() +"    123");
//	    	OfflineErrorPack offlineJsonResult = new OfflineErrorPack("111",1, "123");
//	    	OfflineErrorPack offlineJsonResult2 = new OfflineErrorPack("111",1, "345");
//	    	List<OfflineErrorPack> lists = new ArrayList<OfflineErrorPack>();
//	    	lists.add(offlineJsonResult);
//	    	lists.add(offlineJsonResult2);
//	    	String jsonStr = list2json(lists);
//	    	System.out.println(jsonStr);
//	    	List<OfflineErrorPack2> convertDatas = convertUnderlineToCamel(jsonStr, OfflineErrorPack2.class);
//	    	for(OfflineErrorPack2 errorPack:convertDatas){
//	    		System.out.println(errorPack.getErrorMessage());
//	    	}
//	    	String jsonData = "{\"biddingFlag\":\"1\",\"cancelPutCause\":\"\",\"cancelPutName\":\"\",\"cancelPutTime\":\"\",\"companyCode\":\"DS\",\"contractNum\":\"\",\"contractSort\":\"X\",\"factoryId\":\"LB1\",\"goodId\":\"L0B010188\",\"isProdCode\":\"L0B0\",\"lockBatchNo\":\"\",\"machineDesc\":\"宝钢股份\",\"machineId\":\"B\",\"maxLength\":0,\"minLength\":0,\"operatorFlag\":\"T\",\"orderNum\":\"\",\"orderTypeCode\":\"XZZ\",\"packAmount\":0,\"packNo\":\"\",\"pieceWt\":1.34,\"preOrderItemNum\":\"L6W0009914\",\"preferentialRuleDesc\":\"\",\"preferentialRuleFlag\":\"0\",\"prodCode\":\"L0\",\"prodDscr\":\"FA冷轧卷\",\"prodStd\":\"BXNQ2012-006\",\"productDetails\":\"不切边*610*斑迹*参考牌号:B1500HS*\",\"putMode\":\"0\",\"putPrice\":3170,\"qualityGrade\":\"\",\"refShopsign\":\"B1500HS\",\"resourceNum\":\"16110256104\",\"resourceSign\":\"W\",\"saleInTransitFlag\":\" \",\"salePattern\":\"A\",\"settleShiftFlag\":\" \",\"shopPart\":\"A\",\"shopsign\":\"FA冷轧卷\",\"sizeDesc\":\"1.50*1190*C\",\"specExplain\":\"\",\"specVer\":\"005\",\"storageLocation\":\"2BA223\",\"storeTime\":\"20160311195300\",\"targetUser\":\"\",\"thickTbthDim\":1.5,\"warehouseId\":\"001518050\",\"width\":1190}";
//	    	ResourceManage uploadPacks = jsonToBean(jsonData, ResourceManage.class, false);
//	    	System.out.println(uploadPacks.toString());
	    	
//	    	data.put("id", 1);
//	    	data.put("test", "sdf");
//	    	System.out.println(map2json(data));
//            String s = null;
//            System.out.println(JsonUtils.parseJSON2List(s));
//			List<ContractInfo> infos = new ArrayList<ContractInfo>();
//			ContractInfo contractInfo = new ContractInfo();
//			contractInfo.setContractDetailKey("123");
//			infos.add(contractInfo);
//			String jsonStr = JsonUtils.beanToJson(infos,true).toUpperCase();
//			infos = JsonUtils.jsonToList(jsonStr,ContractInfo.class,true);
//			for(ContractInfo contractInfo1 : infos){
//				System.out.println(contractInfo1.getContractDetailKey());
//			}

//		String json ="{     \"tenantId\": \"\",     \"spec\": \"0.420*805.00*236000\",     \"weightType\": \"1\",     \"weight\": 0.58,     \"batchCode\": \"442b27eaa7f34161acf3c5adafc6f0\",     \"actLength\": 236000,     \"location\": \"513000000\",     \"inDate\": \"20131129083703\",     \"platCommonSchema\": \"\",     \"actThick\": 0.42,     \"warehouseCode\": \"WP00530\",     \"id\": 0,     \"factoryResCode\": \"L8300965902\",     \"brandCode\": \"BBHS\",     \"processTime\": \"20160627104531\",     \"grade\": \"B01\",     \"coat\": \"0.0\",     \"platSchema\": \"\",     \"productCode\": \"TL41\",     \"shopSign\": \"TDC51D Z\",     \"techStandard\": \"Q/BHD501\",     \"providerCode\": \"T11791\",     \"pieces\": 1,     \"price\": 9000,     \"color\": \"Z70/30,2/1,聚酯-宝钢白803(20)\",     \"contactName\": \"151760\",     \"packCode\": \"L8300965902\",     \"specialDesc\": \"正式环境测试\",     \"projectCommonSchema\": \"\",     \"special\": \"因内套筒打滑导致卷取溢出边（新品研发）\",     \"contactMobile\": \"13477750600\",     \"actWidth\": 805,     \"projectSchema\": \"\" }";
//		TempHsZiv501 ziv501=JsonUtils.jsonToBean(json, TempHsZiv501.class, false);
//		System.out.println(ziv501.toString());
//			Map<String,Object> data = new HashMap<String, Object>();
//			List<ContractInfo> infos = new ArrayList<ContractInfo>();
//			ContractInfo contractInfo = new ContractInfo();
//			contractInfo.setContractDetailKey("123");
//		ResourceManage resourceManage = new ResourceManage();
//		resourceManage.setAddPrice(new BigDecimal(1));
//			infos.add(contractInfo);
//			data.put("test",infos);
//			data.put("test1",resourceManage);
//			System.out.println(map2JsonStr(data));
		}
}
