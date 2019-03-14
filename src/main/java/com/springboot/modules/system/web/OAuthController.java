package com.springboot.modules.system.web;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.springboot.modules.system.entity.GithubUser;
import com.springboot.modules.system.entity.User;
import com.springboot.modules.system.service.UserService;
import com.github.scribejava.core.model.Response;

@Controller
@RequestMapping("/OAuth")
public class OAuthController {

	/**
	 * 1.访问用户登录的验证接口
	 * https://github.com/login/oauth/authorize?client_id=xxxxxxxxxxxxxxxxxx&scope=user,public_repo
	 * 2.访问上面接口后会github会让其跳转到你预定的url(Authorization callback URL)，并且带上code参数,例如
	 * http://localhost:8080/callback?code=****************
	 * 3.然后，开发者可以通过code,client_id以及client_secret这三个参数获取用户的access_token即用户身份标识，请求如下
	 * https://github.com/login/oauth/access_token?client_id=xxxxxxxxxxxxxxxxxxx&client_secret=xxxxxxxxxxxxxxxxx&code=xxxxxxxxxxxxxxxxxxx
	 * 这样就会返回access_token,如下
	 * access_token=xxxxxxxxxxxxxxxxxxxxxxxxx&scope=public_repo%2
	 * Cuser&token_type=bearer 
	 * 4. 这样我们就可以用这个access_token来获取用户的信息
	 * https://api.github.com/user?access_token=xxxxxxxxxxxxxxxxxxxxxxxxx
	 * 
	 */

	@Autowired
    private UserService userService;

	private static final String PROTECTED_RESOURCE_URL = "https://api.github.com/user";
	private static final String PASSWORD = "123456";
	
	@Value("${github.appId}")  
	private String appId;
	@Value("${github.appSecret}")  
	private String appSecret; 
	@Value("${github.callbackUrl}")  
	private String callbackUrl; 
	@Value("${github.redrictUrl}")  
	private String redrictUrl; 

	@RequestMapping(value = "/authLogin", method = RequestMethod.GET)
	public void authLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(redrictUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/callback/getOAuth", method = RequestMethod.GET)
	public String getOAuth(@RequestParam(value = "code", required = true) String code, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String secretState = "secret" + new Random().nextInt(999_999);
		OAuth20Service service = new ServiceBuilder(appId)
				.apiSecret(appSecret).state(secretState)
				.callback(callbackUrl).build(GitHubApi.instance());
		OAuth2AccessToken accessToken = null;
		GithubUser githubUser = null;
		try {
			accessToken = service.getAccessToken(code);
			final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			service.signRequest(accessToken, oAuthRequest);
			final Response oAuthresponse = service.execute(oAuthRequest);
			githubUser = JSON.parseObject(oAuthresponse.getBody(), new TypeReference<GithubUser>() {});
		} catch (IOException e) {
			model.addAttribute("error", "github登录失败！");
			return "system/login";
		} catch (InterruptedException e) {
			model.addAttribute("error", "github登录失败！");
			return "system/login";
		} catch (ExecutionException e) {
			model.addAttribute("error", "github登录失败！");
			return "system/login";
		} catch (OAuthException e) {
			model.addAttribute("error", "github登录失败！");
			return "system/login";
		}
		// 1、判断是不是第一次授权登录,新增用户写进数据库，给一个默认角色
		User user = userService.findByUsername(githubUser.getName());;
		if(user == null || (user != null && "".equals(user.getUsername()))) {
			user = new User();
			user.setUsername(user.getUsername());
			user.setPassword(PASSWORD);
			user.setOrganizationId((long) 1);
			user.setGroupIds("1");
			user.setRoleIds("1");
			user.setLocked(Boolean.FALSE);
			userService.createUser(user);
		}
		autoLogin(user);
		return "base/main";
	}
	
	public void autoLogin(User user) {
		//获取SecurityManager工厂
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory();
        //得到SecurityManager实例并绑定给SecurityUtils  
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
        SecurityUtils.setSecurityManager(securityManager);  
        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证） 
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try{
            //登录，即身份验证  
            subject.login(token);
        }catch (AuthenticationException e){
            token.clear();
        }

	}
}
