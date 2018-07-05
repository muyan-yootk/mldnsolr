package cn.mldn.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SuppressWarnings("serial")
@SolrDocument(collection="mldncore")	// 与Solr地址混合在一起就是core的路径
public class Goods implements Serializable {
	@Id
	@Indexed(name="id")
	private Long id ;
	@Indexed(name="solr_s_name")
	private String name ;
	@Indexed(name="solr_s_catalog")
	private String catalog ;
	@Indexed(name="solr_s_provider")
	private String provider ;
	@Indexed(name="solr_d_price")
	private Double price ;
	@Indexed(name="solr_s_note")
	private String note ;
	@Indexed(name="solr_s_photo")
	private String photo ;
	@Indexed(name="solr_date_recdate")
	private Date recdate ;
	@Indexed(name="solr_i_isdelete") 
	private Integer isdelete ;
	@Indexed(name="goods_keywords") 
	private String keywords ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getRecdate() {
		return recdate;
	}
	public void setRecdate(Date recdate) {
		this.recdate = recdate;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", catalog=" + catalog + ", provider=" + provider + ", price="
				+ price + ", note=" + note + ", photo=" + photo + ", recdate=" + recdate + ", isdelete=" + isdelete
				+ ", keywords=" + keywords + "]";
	}
	
	
}
