package jdbc.book.model;


import lombok.*;
import java.io.Serializable;;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChuangbieBook implements Serializable{

	private Long chapterNum;
	private Long priceTotal;
	private ChuangbieBookFtype ftype;
	private ChuangbieBookStype stype;

	private static final long serialVersionUID =  1L;

	/**
	 * 书籍ID
	 */
	private Long bookId;
	/**
	 * 书籍名称
	 */
	private String bookName;
	/**
	 * 作者名称
	 */
	private String authorName;
	/**
	 * 1.男频 2.女频 3.出版
	 */
	private Byte attribution;
	/**
	 * 一级分类ID
	 */
	private Integer ftypeId;
	/**
	 * 二级分类ID
	 */
	private Integer stypeId;
	/**
	 * 书籍状态：1连载 2完本
	 */
	private Integer status;
	/**
	 * 是否收费：0免费 1收费
	 */
	private Byte isVip;
	/**
	 * 图书简介
	 */
	private String description;
	/**
	 * 封面url地址
	 */
	private String coverUrl;
	/**
	 * 图书关键字
	 */
	private String keyword;
	/**
	 * 图书当前总字数
	 */
	private String wordCount;
	/**
	 * 最新章节ID
	 */
	private Integer lastUpdateChapterId;
	/**
	 * 最新章名
	 */
	private String lastUpdateChapterName;
	/**
	 * 是否推荐首页：1推荐 0不推荐
	 */
	private Byte isRecommand;

	public String getAttributionName() {
		switch (attribution){
			case 1:
				return "男频";
			case 2:
				return "女频";
			case 3:
				return "出版";
		}
		return "出版";
	}

	public ChuangbieBook(ResultSet rs) throws SQLException{
		this.bookId = rs.getLong("book_id");
		this.bookName = rs.getString("book_name");
		this.authorName = rs.getString("author_name");
		this.attribution = rs.getByte("attribution");
		this.ftypeId = rs.getInt("ftype_id");
		this.stypeId = rs.getInt("stype_id");
		this.status = rs.getInt("status");
		this.isVip = rs.getByte("is_vip");
		this.description = rs.getString("description");
		this.coverUrl = rs.getString("cover_url");
		this.keyword = rs.getString("keyword");
		this.wordCount = rs.getString("word_count");
		this.lastUpdateChapterId = rs.getInt("last_update_chapter_id");
		this.lastUpdateChapterName = rs.getString("last_update_chapter_name");
		this.isRecommand = rs.getByte("isRecommand");
	}
}
