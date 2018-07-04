package cn.mldn.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpRequestUtil {
	private HttpRequestUtil() {}

	public static boolean send(String address) { 
		// 创建可以关闭的HttpClient的程序类
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(address); // 创建post请求
		try { // 发送HTTP-GET请求
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return response.getStatusLine().getStatusCode() == 200 ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		} 
	}
}
