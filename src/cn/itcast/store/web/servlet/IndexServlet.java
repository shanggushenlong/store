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
import cn.itcast.store.web.base.BaseServlet;



public class IndexServlet extends BaseServlet {

	@Override
	public String execute(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//调用业务层service功能，获取全部分类信息，返回集合
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> list = categoryService.getAllCats();
		//讲返回的集合放入request中
		requset.setAttribute("allCats", list);
		//转发到首页
		return "/jsp/index.jsp";
	}
	
	

}
