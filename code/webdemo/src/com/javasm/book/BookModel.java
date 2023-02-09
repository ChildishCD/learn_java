package com.javasm.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BookModel {
    private Integer bookId;
    private String bookName;
    private String authorName;
    private Integer attr;
    private Integer ftypeId;
    private Integer stypeId;
    private Integer status;
    private Integer vip;
    private String description;
    private String coverUrl;
    private String keyword;
    private String wordCount;
    private Integer chapterId;
    private String chapterName;
    private Integer isRecommand;

    public Integer getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Integer getAttr() {
        return attr;
    }

    public Integer getFtypeId() {
        return ftypeId;
    }

    public Integer getStypeId() {
        return stypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getVip() {
        return vip;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getWordCount() {
        return wordCount;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public Integer getIsRecommand() {
        return isRecommand;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAttr(Integer attr) {
        this.attr = attr;
    }

    public void setFtypeId(Integer ftypeId) {
        this.ftypeId = ftypeId;
    }

    public void setStypeId(Integer stypeId) {
        this.stypeId = stypeId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setIsRecommand(Integer isRecommand) {
        this.isRecommand = isRecommand;
    }
}
