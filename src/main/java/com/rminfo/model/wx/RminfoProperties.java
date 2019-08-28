package com.rminfo.model.wx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/5/30
 * Time: 15:41
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@Component
public class RminfoProperties {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
