package cn.mldn.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.SolrCrudRepository;

import cn.mldn.vo.Goods;

public interface IGoodsRepository extends SolrCrudRepository<Goods, Long> {
	
	@Highlight(prefix="<strong>",postfix="</strong>")
	public HighlightPage<Goods> findByKeywordsContaining(String keywords,Pageable page) ;
	@Highlight(prefix="<strong>",postfix="</strong>") 
	public HighlightPage<Goods> findByNameContaining(String name,Pageable page) ;
}
