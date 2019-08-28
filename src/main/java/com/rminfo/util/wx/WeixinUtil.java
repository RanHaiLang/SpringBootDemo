package com.rminfo.util.wx;
/**
 * @author Engineer-Jsp
 * @date 2014.10.09
 * 请求数据通用类*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeixinUtil {
    /** 
     * 发起https请求并获取结果 
     *  
     * @param request 请求地址
     * @param RequestMethod 请求方式（GET、POST）
     * @param output 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
	public static JSONObject HttpRequest(String request , String RequestMethod , String output ){
		@SuppressWarnings("unused")
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			//建立连接
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(RequestMethod);
			if(output!=null){
				OutputStream out = connection.getOutputStream();
				out.write(output.getBytes("UTF-8"));
				out.close();
			}
			//流处理
			InputStream input = connection.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input,"UTF-8");
			BufferedReader reader = new BufferedReader(inputReader);
			String line;
			while((line=reader.readLine())!=null){
				buffer.append(line);
			}
			//关闭连接、释放资源
			reader.close();
			inputReader.close();
			input.close();
			input = null;
			connection.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (Exception e) {
		}
		return jsonObject;
	} 
//	 获取access_token的接口地址（GET）   
	public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CorpID&corpsecret=SECRET";  
	  
	/** 
	 * 获取access_token 
	 *  
	 * @param corpID 企业Id
	 * @param secret 管理组的凭证密钥，每个secret代表了对应用、通讯录、接口的不同权限；不同的管理组拥有不同的secret
	 * @return 
	 */  
	public static AccessToken getAccessToken(String corpID, String secret) throws IOException {
	    AccessToken accessToken = null;  
	  
	    String requestUrl = access_token_url.replace("CorpID", corpID).replace("SECRET", secret);  
	    //JSONObject jsonObject = HttpRequest(requestUrl, "GET", null);
		com.alibaba.fastjson.JSONObject jsonObject = doGetStr(requestUrl);
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	            accessToken = new AccessToken();  
	            accessToken.setToken(jsonObject.getString("access_token"));  
	            accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
	            System.out.println("获取token成功:"+jsonObject.getString("access_token")+"――――"+jsonObject.getInteger("expires_in"));
	        } catch (Exception e) {  
	            accessToken = null;  
	            // 获取token失败  
	            String error = String.format("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
	            System.out.println(error);
	        }  
	    }  
	    return accessToken;  
	}




//	 菜单创建（POST）   
	public static String menu_create_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=1000005";
	  
	/** 
	 * 创建菜单 
	 *  
	 * @param menu 菜单实例 
	 * @param accessToken 有效的access_token 
	 * agentid  企业应用的id，整型，可在应用的设置页面查看
	 * @return 0表示成功，其他值表示失败 
	 */  
	public static int createMenu(Menu menu, String accessToken) throws IOException {
	    int result = 0;  
	  
	    // 拼装创建菜单的url  
	    String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = com.alibaba.fastjson.JSONObject.toJSONString(menu);
	    // 调用接口创建菜单  
	    //JSONObject jsonObject = HttpRequest(url, "POST", jsonMenu);
	    com.alibaba.fastjson.JSONObject jsonObject = doPostStr(url,jsonMenu);

	    if (null != jsonObject) {  
	        if (0 != jsonObject.getInteger("errcode")) {
	            result = jsonObject.getInteger("errcode");
	            String error = String.format("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
	            System.out.println(error); 
	        }  
	    }  
	  
	    return result;  
	}  
	public static String URLEncoder(String str){
		String result = str ;
		try {
		result = java.net.URLEncoder.encode(result,"UTF-8");	
		} catch (Exception e) {
        e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}
	/**
	 * 数据提交与请求通用方法
	 * @param access_token 凭证
	 * @param RequestMt 请求方式
	 * @param RequestURL 请求地址
	 * @param outstr 提交json数据
	 * */
    public static int PostMessage(String access_token ,String RequestMt , String RequestURL , String outstr) throws IOException {
    	int result = 0;
    	RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonobject=null;
    	if(RequestMt.equals("POST")){
			jsonobject = doPostStr(RequestURL, outstr);
		}else {
    		jsonobject = doGetStr(RequestURL);
		}

    	 if (null != jsonobject) {  
 	        if (0 != jsonobject.getInteger("errcode")) {
 	            result = jsonobject.getInteger("errcode");
 	            String error = String.format("操作失败 errcode:{} errmsg:{}", jsonobject.getInteger("errcode"), jsonobject.getString("errmsg"));
 	            System.out.println(error); 
 	        }  
 	    }
    	return result;
    }





	/**
	 * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
	 *
	 * @param url 需要请求的URL
	 * @return 将请求URL后返回的数据，转为JSON格式，并return
	 */
	public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
		System.setProperty("jsse.enableSNIExtension", "false");
		HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
		JSONObject jsonObject = null;
		HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
		HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
			jsonObject = JSON.parseObject(result);//字符串类型转为JSON类型
		}
		return jsonObject;
	}

	/**
	 * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
	 *
	 * @param url 需要请求的URL
	 * @param outStr  需要传递的参数
	 * @return 将请求URL后返回的数据，转为JSON格式，并return
	 */
	public static JSONObject doPostStr(String url, String outStr) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
		HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
		JSONObject jsonObject = null;
		System.out.println("outStr:"+new StringEntity(outStr,"UTF-8"));
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
		HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
		System.out.println(response.getEntity());
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");//HttpEntity转为字符串类型
		jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);//字符串类型转为JSON类型
		return jsonObject;
	}

}  
