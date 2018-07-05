package cn.mldn.util.solr;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.common.params.ModifiableSolrParams;

public class SolrConnectionUtil {
	private static final String USER_NAME = "mldn"; // 用户名
	private static final String PASSWORD = "java"; // 密码
	private static final int CONNECTION_TIMEOUT = 6000;
	private static final int SOCKET_TIMEOUT = 6000;
	private static final int CONNECTION_MAX = 1000; 
	private static final int HOST_CONNECTION_MAX = 1000;
	private SolrConnectionUtil() {}
	public static CloudSolrClient getClient() {
		List<String> solrHostList = new ArrayList<String>() ; // 保存全部的Solr连接地址
		solrHostList.add("http://192.168.188.162:80/solr") ;	// Solr主机
		solrHostList.add("http://192.168.188.163:80/solr") ;	// Solr主机
		solrHostList.add("http://192.168.188.164:80/solr") ;	// Solr主机
		solrHostList.add("http://192.168.188.165:80/solr") ;	// Solr主机
		solrHostList.add("http://192.168.188.166:80/solr") ;	// Solr主机
		solrHostList.add("http://192.168.188.167:80/solr") ;	// Solr主机
		ModifiableSolrParams initParams = new ModifiableSolrParams() ;
		initParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, USER_NAME); // 用户名
		initParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, PASSWORD); // 密码
		initParams.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true); // 如果服务器支持压缩传输则启用
		initParams.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false); // 不进行重定向配置 
		initParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS, CONNECTION_MAX); // 设置每台主机最大允许的连接数
		initParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, HOST_CONNECTION_MAX); // 设置最大允许的连接数
		HttpClientUtil.addRequestInterceptor(new AuthRequestInterceptor()); // 定义请求拦截器
		HttpClient httpClient = HttpClientUtil.createClient(initParams) ; // 根据配置的初始化参数创建httpClient对象
		CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(solrHostList).withConnectionTimeout(CONNECTION_TIMEOUT)
				.withSocketTimeout(SOCKET_TIMEOUT).withHttpClient(httpClient).build() ;
		cloudSolrClient.setDefaultCollection("mldncloud"); // 设置集合名称
		return cloudSolrClient ; 
	}
}
