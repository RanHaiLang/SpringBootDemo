package com.rminfo.controller.wx;
/** 
 * Oauth2 Servlet类 
 * @author Engineer.Jsp
 * @date 2014.10.13 
 */

import com.alibaba.fastjson.JSONObject;
import com.rminfo.service.wx.GOauth2Core;
import com.rminfo.util.wx.ParamesAPI;
import com.rminfo.util.wx.WeixinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.rminfo.service.wx.GOauth2Core.GetticketToUserInfo;


@RequestMapping("/oauth2Servlet")
@Controller
public class OAuth2Servlet {

	private static final long serialVersionUID = 1L;


	@RequestMapping("/doget")
	 public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("Userbinding.html");
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8"); 
		  PrintWriter out = response.getWriter();
		  String code = request.getParameter("code");
		  if (!"authdeny".equals(code)) {
			  String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();
			  if(access_token!=""&&access_token!=null){
				  // agentid 跳转链接时所在的企业应用ID 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
				  String UserID = GOauth2Core.GetUserID(access_token, code, "1000005");
				  //使用user_ticket获取成员详情
				  JSONObject userInfo = GetticketToUserInfo(access_token,ParamesAPI.user_ticket);
				  request.setAttribute("UserId", UserID);
				  request.setAttribute("userInfo",userInfo);
				  mv.addObject("UserId",UserID);
				  mv.addObject("userInfo",userInfo);
			  }

		  }
		  else{
			  out.print("授权获取失败，至于为什么，自己找原因。。。");
		  }
		  // 跳转到index.jsp
		  //request.getRequestDispatcher("userinfo").forward(request, response);
		  return mv;
	 }

}
