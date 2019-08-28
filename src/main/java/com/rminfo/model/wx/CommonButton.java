package com.rminfo.model.wx;

import com.rminfo.util.wx.Button;

/**
 * 普通按钮（子按钮） 
 *  
 *@author Engineer-Jsp 
 *@date 2014.06.23 
 */  
public class CommonButton extends Button {
    private String type;  
    private String key;  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getKey() {  
        return key;  
    }  
  
    public void setKey(String key) {  
        this.key = key;  
    }  
}  