package cn.mldn.main.index;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;

import cn.mldn.util.solr.SolrConnectionUtil;

public class SolrDeleteDocument {

	public static void main(String[] args) throws Exception { 
		HttpSolrClient solrClient = SolrConnectionUtil.getClient() ;
		UpdateResponse response = solrClient.deleteById("99") ; // 删除指定的索引数据
		System.out.println("花费时间：" + response.getElapsedTime());
		solrClient.commit() ;
		solrClient.close(); 
	}
}
