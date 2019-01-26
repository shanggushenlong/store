package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.CategoryServiceImpl;
import cn.itcast.store.service.serviceImp.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;



public class IndexServlet extends BaseServlet {

	@Override
	public String execute(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//调用业务层service功能，获取全部分类信息，返回集合
		//CategoryService categoryService = new CategoryServiceImpl();
		//List<Category> list = categoryService.getAllCats();
		//讲返回的集合放入request中
		//requset.setAttribute("allCats", list);
		
		//调用业务层查询最新商品，查询最热商品，返回2个集合
		ProductService productService = new ProductServiceImpl();
		List<Product> list01 = productService.findHots();
		List<Product> list02 = productService.findNews();
		
		//将2个集合放入到request中
		requset.setAttribute("hots", list01);
		requset.setAttribute("news", list02);
		//转发到首页
		return "/jsp/index.jsp";
	}
	
	

}
