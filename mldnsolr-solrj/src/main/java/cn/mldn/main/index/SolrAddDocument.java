package cn.mldn.main.index;

import java.util.Date;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import cn.mldn.util.solr.SolrConnectionUtil;

public class SolrAddDocument {

	public static void main(String[] args) throws Exception { 
		CloudSolrClient solrClient = SolrConnectionUtil.getClient() ;
		SolrInputDocument document = new SolrInputDocument() ; // 创建一个新的索引对象
		document.addField("id", "99");
		document.addField("solr_s_name", "小强王中王火腿");
		document.addField("solr_s_note", "德国进口产品，价格实惠，治百病！");
		document.addField("solr_s_provider", "高氏食品无限无责任公司");
		document.addField("solr_s_catalog", "熟食");
		document.addField("solr_d_price", 89.67);
		document.addField("solr_s_photo", "nophoto.jpg");
		document.addField("solr_i_isdelete", 0);
		document.addField("solr_date_recdate", new Date());
		UpdateResponse response = solrClient.add(document) ;
		System.out.println("花费时间：" + response.getElapsedTime());
		solrClient.commit() ;
		solrClient.close();  
	}
}
