package cn.mldn.config;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import cn.mldn.util.solr.AuthRequestInterceptor; 

@Configuration
@Scope("prototype")
@PropertySource("classpath:config/solr.properties")
@EnableSolrRepositories(basePackages={"cn.mldn.dao"}) 
public class SolrConfig {
	@Value("#{'${solr.host.url}'.split(',')}") // 按照“,”拆分就成为了数组，而后利用List接收
	private List<String> solrHostList ;
	@Value("${solr.collection.name}")
	private String collectionName ;
	@Value("${solr.host.username}")
	private String username ; // 用户名
	@Value("${solr.host.password}")
	private String password ;
	@Value("${solr.connection.timeout}")
	private int connectionTimeout = 6000;
	@Value("${solr.socket.timeout}")
	private int socketTimeout = 6000;
	@Value("${solr.max.connection}")
	private int maxConnection = 1000;
	@Value("${solr.pre.connection}")
	private int preConnection = 1000; 
	@Bean(name="solrClient") 
	public CloudSolrClient getClient() {
		ModifiableSolrParams initParams = new ModifiableSolrParams() ;
		initParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, this.username); // 用户名
		initParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, this.password); // 密码
		initParams.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true); // 如果服务器支持压缩传输则启用
		initParams.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false); // 不进行重定向配置 
		initParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS, this.maxConnection); // 设置每台主机最大允许的连接数
		initParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, this.preConnection); // 设置最大允许的连接数
		HttpClientUtil.addRequestInterceptor(new AuthRequestInterceptor()); // 定义请求拦截器
		HttpClient httpClient = HttpClientUtil.createClient(initParams) ; // 根据配置的初始化参数创建httpClient对象
		CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(this.solrHostList).withConnectionTimeout(this.connectionTimeout)
				.withSocketTimeout(this.socketTimeout).withHttpClient(httpClient).build() ;
		cloudSolrClient.setDefaultCollection(this.collectionName); // 设置集合名称 
		return cloudSolrClient ;  
	}
}
