package cn.mldn.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.mldn.util.HttpRequestUtil; 

@Component
@PropertySource("classpath:config/dataimport.properties")
public class FullImportTask {
	@Value("${full-import.url}")
	private String fullImportUrl ;  // 全导入的配置地址
	@Scheduled(cron="0 0 0 1,5,15,20,25 * ?")
	public void runFullImportTask() {
		System.out.println("【Full-Import】进行Solr索引的完全导入。");
		HttpRequestUtil.send(this.fullImportUrl) ;
	}
}
