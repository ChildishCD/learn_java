package jdbc.book.model;


import jdbc.book.base.JavasmId;
import jdbc.book.base.JavasmTable;
import lombok.*;
import java.io.Serializable;;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JavasmTable("chuangbei_book_chapter")
public class ChuangbieBookChapter implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 小说id
	 */
	private Long bookId;
	/**
	 * 章节id
	 */
	@JavasmId("chapter_id")
	private Integer chapterId;
	/**
	 * 章节名字
	 */
	private String chapterName;
	/**
	 * 分卷id 没有分卷则为0
	 */
	private Integer volumeId;
	/**
	 * 是否收费: 0免费 1收费
	 */
	private Byte isVip;
	/**
	 * 价格：创别币
	 */
	private Integer price;
	/**
	 * 章节排序id
	 */
	private Integer sortid;
	/**
	 * 章节字数
	 */
	private Integer wordCount;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 小说内容
	 */
	private String chapterContent;

	public ChuangbieBookChapter(ResultSet rs) throws SQLException{
		this.bookId = rs.getLong("book_id");
		this.chapterId = rs.getInt("chapter_id");
		this.chapterName = rs.getString("chapter_name");
		this.volumeId = rs.getInt("volume_id");
		this.isVip = rs.getByte("is_vip");
		this.price = rs.getInt("price");
		this.sortid = rs.getInt("sortid");
		this.wordCount = rs.getInt("word_count");
		this.updateTime = rs.getTimestamp("update_time");
		this.chapterContent = rs.getString("chapter_content");
	}
}
