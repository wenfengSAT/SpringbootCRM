package com.springboot.modules.system.web;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.utils.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Description： 登录
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:31:13]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping("/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        logger.info("begin to login");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "登陆失败次数过多";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "system/login";
    }

    @RequestMapping("/main")
    public String main() throws Exception {
        logger.info("begin to main");
        return "/system/main";
    }
}
