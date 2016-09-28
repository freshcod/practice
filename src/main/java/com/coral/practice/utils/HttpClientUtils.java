package com.coral.practice.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * http client 帮助类
 * @author hb.li
 * @version 创建时间：2016年4月11日 上午10:05:47
 * 类说明 
 */
public abstract class HttpClientUtils {
	

	public static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 发送get请求
     * @param url 请求地址
     * @param params 请求参数
     * @return
     */
	public static String sendGet(String url,Map<String,Object> params) {
		String responseBody = "";
        StringBuilder sb = new StringBuilder();
        for(String key:params.keySet()){
            sb.append(key+"="+(String)params.get(key)+"&");

        }
        String kvs = sb.toString().substring(0, sb.length()-1);
        url=url+"?"+kvs;
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet httpGet=new HttpGet(url);

		try {
			HttpClientContext context = HttpClientContext.create();
			response = client.execute(httpGet,context);
			//判断是否是重定向
			if(response.getStatusLine().getStatusCode() == 302){
				Header header = response.getFirstHeader("location");
				String urlRedirect = header.getValue();
				responseBody = sendGet(urlRedirect,params);
			}else{
				responseBody = EntityUtils.toString(response.getEntity());
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return responseBody;
	}



    /**
     * 发送post请求
     * @param url 地址
     * @param params 参数
     * @param encoding 编码格式
     * @return
     */
    public static String sendPost(String url,Map<String,Object> params,String encoding){
        String responseBody = "";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        CloseableHttpResponse response = null;
        for(String key:params.keySet()){
            nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,encoding));
            HttpClientContext context = HttpClientContext.create();
            response = client.execute(httpPost,context);
            //判断是否是重定向
            if(response.getStatusLine().getStatusCode() == 302){
                Header header = response.getFirstHeader("location");
                String urlRedirect = header.getValue();
                responseBody = sendPost(urlRedirect,params,encoding);
            }else{
                responseBody = EntityUtils.toString(response.getEntity());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return responseBody;
    }

    /**
     * HTTP发送xml字符串
     * @param postUrl webservice地址
     * @param requestXML 发送的xml字符串
     * @param soapAction soap地址
     * @param encoding 编码格式
     * @return
     * @throws IOException
     */
    public static String invokeService(String postUrl, String requestXML, String soapAction, String encoding)
            throws  IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(postUrl);

        StringEntity entity = new StringEntity(requestXML, encoding);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "text/xml; charset="+encoding);
        httpPost.setHeader("SOAPAction", soapAction);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        String result = null;
        int iCode = httpResponse.getStatusLine().getStatusCode();
        if (HttpStatus.SC_OK == iCode) {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, encoding);
        } else { // 如果返回非200，则手动关闭连接
            httpPost.abort();
            log.info("get status code {} from server", httpResponse.getStatusLine().getStatusCode());
        }
        return result;
    }



    /**
     * 发送post请求json格式(REST请求)
     * @param url 地址
     * @param params 参数 json字符串
     * @param encoding 编码格式
     * @return
     */
    public static String sendPostJson(String url,String params,String encoding) throws IOException {
        String responseBody = "";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(params, encoding);
        httpPost.setEntity(entity);
        CloseableHttpResponse httpResponse = client.execute(httpPost);

        String response = null;
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (HttpStatus.SC_OK == statusCode) {
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, encoding);
        } else { // 如果返回非200，则手动关闭连接
            httpPost.abort();
            log.info("get response status code {} from server", httpResponse.getStatusLine().getStatusCode());
        }
        return response;

    }


    public static void main(String [] args) throws IOException {
        String url = "http://10.57.115.179/service/order/get_provider_comments/";
        String params = "{\"provideCode\":\"T20316\",\"level\":\"\",\"includeMore\":\"\",\"orderAttr\":\"DESC\",\"offset\":1,\"limit\":10}";
        String result = HttpClientUtils.sendPostJson(url,params,"utf-8");
        System.out.println(result);
    }

}
