package com.rminfo.controller;

import com.rminfo.enums.StatusEnums;
import com.rminfo.exception.GlobalException;
import com.rminfo.model.shiro1.LoginLog;
import com.rminfo.service.shiro.LoginLogService;
import com.rminfo.util.*;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.apache.shiro.util.ThreadContext.getSubject;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/28
 * Time: 12:31
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RestController
@Api(value = "LoginController", tags = {"登录模块接口"})
public class LoginController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginLogService loginLogService;


    @PostMapping("/login")
    public ResponseCode login(String username, String password, String code, Boolean remember) {
        if (StringUtils.isEmpty(code)) {
            return new ResponseCode(StatusEnums.CODE_ERROR);
        }
        Session session = super.getSession();
        String gifCode = (String) session.getAttribute("gifCode");
        if (!code.equalsIgnoreCase(gifCode)) {
            return new ResponseCode(StatusEnums.CODE_ERROR);
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember);
            Subject subject = getSubject();
            if (subject != null) {
                subject.logout();
            }
            super.login(token);
            logger.info("是否登录==>{}", subject.isAuthenticated());

            //记录登录日志
            LoginLog log = new LoginLog();
            //获取HTTP请求
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            String ip = IPUtil.getIpAddr(request);
            log.setIp(ip);
            log.setUsername(super.getCurrentUser().getUsername());
            log.setLocation(AddressUtil.getAddress(ip));
            log.setCreateTime(new Date());
            log.setDevice(request.getHeader("Users-Agent"));
            loginLogService.saveLog(log);
            return ResponseCode.success(super.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }


    @GetMapping("/gifCode")
    public String getCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setContentType("image/gif");
            Captcha captcha = new GifCaptcha(146, 33, 4);
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            session.removeAttribute("gifCode");
            session.setAttribute("gifCode", captcha.text().toLowerCase());
            return captcha.text().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("获取验证码异常");
        }
    }
}
