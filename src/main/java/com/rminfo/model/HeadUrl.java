package com.rminfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/11
 * Time: 14:21
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@Entity
@Table(name = "HeadUrl")
public class HeadUrl implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private String userid;
    private String headurl;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public HeadUrl(String userid, String headurl) {
        this.userid = userid;
        this.headurl = headurl;
    }

    public HeadUrl() {
    }
}
