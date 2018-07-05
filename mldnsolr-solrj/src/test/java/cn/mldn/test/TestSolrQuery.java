package cn.mldn.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSolrQuery {
	@Autowired
	private HttpSolrClient solrClient;

	@Test
	public void testQuery() throws Exception {
		// 2、进行Solr查询可以利用SolrQuery来完成
		SolrQuery query = new SolrQuery(); // 创建数据查询对象
		query.setQuery("*:*"); // 查询全部内容
		// 3、当执行查询之后一定要有一个数据的返回结果，需要接收返回的数据回应
		QueryResponse response = solrClient.query(query); // 发送请求
		// 4、所有的回应的数据信息实际上都在response中包含了，需要通过回应对象获取信息
		SolrDocumentList documents = response.getResults(); // 获取所有的Solr返回内容
		// 5、通过SolrDocumentList获取Solr返回结果
		System.out.println("【数据行数】" + documents.getNumFound());
		// 6、获取全部的返回内容
		for (SolrDocument doc : documents) {
			System.out.println("【返回信息】id = " + doc.get("id") + "、name = " + doc.get("solr_s_name") + "、catalog = "
					+ doc.get("solr_s_catalog"));
		}
		solrClient.close();
	}
}
