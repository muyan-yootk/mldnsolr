package cn.mldn.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartSolrImportMain {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml") ;
	}
}
