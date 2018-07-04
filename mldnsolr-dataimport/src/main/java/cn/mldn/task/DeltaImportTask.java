package cn.mldn.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.mldn.util.HttpRequestUtil;

@Component
@PropertySource("classpath:config/dataimport.properties")
public class DeltaImportTask {
	@Value("${delta-import.url}")
	private String deltaImportUrl ;  // 全导入的配置地址
	@Scheduled(cron="0 * * * * ?")	// 每分钟进行一次增量索引 
	public void runFullImportTask() {
		System.out.println("【Delta-Import】进行Solr索引的增量导入。");
		HttpRequestUtil.send(this.deltaImportUrl) ;
	}
} 
