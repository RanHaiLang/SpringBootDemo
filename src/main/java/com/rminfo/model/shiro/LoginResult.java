package com.rminfo.model.shiro;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/25
 * Time: 11:38
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
public class LoginResult {
    private boolean isLogin = false;
    private String result;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
