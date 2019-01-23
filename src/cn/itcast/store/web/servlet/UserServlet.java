package cn.itcast.store.web.servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
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
}
