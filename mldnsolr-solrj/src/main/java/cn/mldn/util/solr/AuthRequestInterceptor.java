package cn.mldn.util.solr;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.ContextAwareAuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HttpContext;

public class AuthRequestInterceptor implements HttpRequestInterceptor { // 定义一个认证请求的拦截器
	// 对于当前的Solr-WEB服务器而言采用的是Basic模式实现的认证处理
	private ContextAwareAuthScheme authSchema = new BasicScheme(); 
	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		AuthState authState = (AuthState) context.getAttribute(HttpClientContext.TARGET_AUTH_STATE); // 获取目标的认证状态
		if (authState != null && authState.getAuthScheme() == null) { // 没有具体的认证处理模式
			CredentialsProvider provider = (CredentialsProvider) context.getAttribute(HttpClientContext.CREDS_PROVIDER); // 获取当前认证提供者
			HttpHost targetHost = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST); // 访问目标主机
			Credentials credentials = provider
					.getCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()));
			if (credentials == null) {
				throw new HttpException("{" + targetHost.getHostName() + "}并没有HTTP认证处理支持。");
			}
			request.addHeader(authSchema.authenticate(credentials, request, context));
		}
	}

}
