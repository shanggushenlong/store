package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImp.UserServiceImpl;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;
import javafx.css.PseudoClass;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	
	/**
	 * 跳转向页面登录，通过jsp servlet转向jsp页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	/**
	 * 用户注册userRegist
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收数据
			Map<String, String[]> map = request.getParameterMap();
			//通过BeanUtils工具类
			User user = new User();
			//调用MyBeanUtils工具类
			MyBeanUtils.populate(user, map);
			//为user用户的其他属性赋值
			//用户ID
			user.setUid(UUIDUtils.getId());
			//用户状态
			user.setState(0);
			//用户激活码
			user.setCode(UUIDUtils.getCode());
			
/*		
		//遍历map中的数据
		Set<String> KeySet = map.keySet();
		Iterator<String> iterator = KeySet.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
			String[] strs  = map.get(str);
			for(String string:strs) {
				System.out.println(string);
			}
			
			System.out.println();
		}
*/		
			
		//2.调用业务层注册功能
		UserService userService = new UserServiceImpl();
		try {
			/**
			 * 用户注册成功与失败（方法）
			 * 1.通过返回值 true 注册成功 false 注册失败
			 * 2.通过try catch，捕获信心，在jsp页面显示出提示信息
			 * 此处使用第二种方法
			 */
			//调用用户注册方法，
			userService.userRegist(user);
			//注册成功，向用户邮箱发送信息，跳转到提示页面
			//发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功，请激活！");
		} catch (Exception e) {
			//注册失败，跳转到提示页面
			request.setAttribute("msg", "用户注册失败，请重新注册！");
		}
		return "/jsp/info.jsp";
	}
	
	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取邮箱激活码,(邮箱激活码参数)
		String code = request.getParameter("code");
		System.out.println(code);
		//调用service业务层功能，完成激活
		UserService userService = new UserServiceImpl();
		boolean flag = userService.userActive(code);
		System.out.println(flag);
		//激活提示
		if (flag == true) {
			//用户激活成功，向request放入提示信息，转发到登录页面
			request.setAttribute("msg", "用户激活成功，请登录！");
			return "/jsp/login.jsp";
		}else {
			//用户激活失败,向request放入信息，转发到提示页面
			request.setAttribute("msg", "用户激活失败，请重新激活");
			return "/jsp/info.jsp";
		}	
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取用户数据
		//方法一：通过BeanUtils封装整个user数据
		User user = new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		
		//方法二：通过request.getParameter获取表单参数
	/*	String username = request.getParameter("username");
		String password = request.getParameter("password");	
		System.out.println(username+"--"+password);、
	*/
		//调用业务层登录功能
		UserService userService = new UserServiceImpl();
		User user2 = null;
		try {
			//通过传入user信息，查询数据库中是否存在
			user2 = userService.userLogin(user);
			//用户登录成功，讲用户信息放入session中
			request.getSession().setAttribute("loginUser", user2);
			//页面冲定向
			response.sendRedirect("/store/index.jsp");
			return null;
		} catch (Exception e) {
			//用户登录失败
			String msg = e.getMessage();
			System.out.println(msg);
			//向request中放入失败的信息
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}			
	}
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清除session，让其失效
		request.getSession().invalidate();
		//重定向到首页
		response.sendRedirect("/store/index.jsp");
		return null;
	}
}








