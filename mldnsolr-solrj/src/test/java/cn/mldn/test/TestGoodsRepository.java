package cn.mldn.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dao.IGoodsRepository;
import cn.mldn.vo.Goods;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGoodsRepository {
	@Autowired
	private IGoodsRepository goodsRepository ;
	
	@Test
	public void testName() {
		Sort sort = new Sort(Sort.Direction.DESC, "solr_d_price");
		Pageable pageable = PageRequest.of(0, 5, sort);
		HighlightPage<Goods> all = this.goodsRepository.findByNameContaining("人", pageable) ;
		System.out.println("总记录数：" + all.getTotalElements());
		System.out.println("总页数：" + all.getTotalPages());
		List<HighlightEntry<Goods>> list = all.getHighlighted() ; // 高亮数据内容
		// 在进行高亮数据查询的时候返回的内容不再直接是Goods类型，而是HighlightEntry类型
		list.forEach((entry)->{
			System.out.println(entry.getEntity());	// 获得实体中的数据对象
		}); 
	} 
	@Test
	public void testKeywords() {
		Sort sort = new Sort(Sort.Direction.DESC, "solr_d_price");
		Pageable pageable = PageRequest.of(0, 5, sort);
		HighlightPage<Goods> all = this.goodsRepository.findByKeywordsContaining("空", pageable) ;
		System.out.println("总记录数：" + all.getTotalElements());
		System.out.println("总页数：" + all.getTotalPages());
		List<HighlightEntry<Goods>> list = all.getHighlighted() ; // 高亮数据内容
		// 在进行高亮数据查询的时候返回的内容不再直接是Goods类型，而是HighlightEntry类型
		list.forEach((entry)->{
			System.out.println(entry.getEntity());	// 获得实体中的数据对象
		}); 
	}
	
	@Test
	public void testFindAll() {
		Sort sort = new Sort(Sort.Direction.DESC, "solr_d_price");
		Pageable pageable = PageRequest.of(0, 5, sort);
		Page<Goods> all = this.goodsRepository.findAll(pageable) ;
		System.out.println("总记录数：" + all.getTotalElements());
		System.out.println("总页数：" + all.getTotalPages());
		List<Goods> list = all.getContent() ;
		list.forEach(System.out::println);
	}
}
