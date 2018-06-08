package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;

public class HttpClientUtil {

	public static Integer checkNode(Node node) throws ClientProtocolException, IOException{
		String url = node.getUrl();
		if(url.indexOf("http:")==-1) {
			url = "http://"+url;
		}
		// 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity("测试连接", ContentType.APPLICATION_JSON);
        stringEntity.setContentEncoding("utf-8");
        httpPost.setEntity(stringEntity);
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        /*if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }*/
        // 释放链接
        response.close();
        return response.getStatusLine().getStatusCode();
	}
	
	/**
	 *  post请求传输File文件
	 * @Title: sendPostFile 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param url
	 * @param @param map
	 * @param @param encoding
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月7日 下午5:28:54
	 */
	public static String sendPostFile(String url, Map<String,File> map, String encoding)throws ClientProtocolException, IOException  {
		String result = "";
		// 创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建post方式请求
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
		for(Entry<String,File> entry : map.entrySet()) {
			mEntityBuilder.addBinaryBody(entry.getKey(),entry.getValue());
		}
		httpPost.setEntity(mEntityBuilder.build());
		// 装填参数
       /* List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));*/
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-tar;boundary=THIS_STRING_SEPARATES");
        httpPost.setHeader("Accept-Language","zh-cn");
        //httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
     // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
	}
	/**
	 *post请求传输json数据
	 * @Title: sendPostDataByJson 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param url
	 * @param @param json
	 * @param @param encoding
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月7日 下午4:10:14
	 */
	public static String sendPostDataByJson(String url, String json, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        stringEntity.setContentEncoding("utf-8");
        httpPost.setEntity(stringEntity);

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }
	/**
	 * get请求传输数据
	 * @Title: sendGetData 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param url
	 * @param @param encoding
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月7日 下午4:11:23
	 */
	public String sendGetData(String url, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", "application/json");
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }
}
