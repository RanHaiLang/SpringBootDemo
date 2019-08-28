package com.rminfo.model.wx;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/4
 * Time: 15:17
 * 项目名：springboot
 * 描述：文本卡片model
 * Description: No Description
 */
public class TextCard {

    private String title;
    private String description;
    private String url;
    private String btntxt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBtntxt() {
        return btntxt;
    }

    public void setBtntxt(String btntxt) {
        this.btntxt = btntxt;
    }
}
