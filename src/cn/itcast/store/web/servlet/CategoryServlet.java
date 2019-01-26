package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImp.CategoryServiceImpl;
import cn.itcast.store.utils.JedisUtils;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//提升性能，将mysql中的分类信息，放入redis缓存中
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if (null == jsonStr || "".equals(jsonStr)) {
			//调用业务层获取全部分类
			CategoryService categoryService = new CategoryServiceImpl();
			List<Category> list = categoryService.getAllCats();
			//将全部分类转换为JSON数据格式
			jsonStr = JSONArray.fromObject(list).toString();
			
			//将从mysql数据库获取到的JSON数据格式数据存入redis
			jedis.set("allCats", jsonStr);
			
			//System.out.println("redis缓存中没有数据");
			
			//将全部分类信息相应到客户端
			//告诉浏览器本次响应的数据是JSON格式的字符串
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(jsonStr);	
		}else {
			//System.out.println("redis缓存中有数据");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(jsonStr);
		}
		
		
	
		return null;
	}
}
