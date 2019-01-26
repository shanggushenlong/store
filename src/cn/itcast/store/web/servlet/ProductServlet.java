package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.SnmpStringFixed;

import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImpl;
import cn.itcast.store.utils.PageModel;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
				   //findProductById
	public String findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获取商品ID
		String pid = request.getParameter("pid");
//		System.out.println(pid);
		//调用业务层，根据商品ID查询商品信息
		ProductService productService = new ProductServiceImpl();
		Product product = productService.findProductById(pid);
		//将获取到的商品放入request
		request.setAttribute("product", product);
		//页面转发到/jsp/product_info.jsp
		return "/jsp/product_info.jsp";
	}
	
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获取cid，num
		//cid 商品分类ID num 客户端当前请求的页数，当前页
		String cid = request.getParameter("cid");
		int curNum = Integer.parseInt(request.getParameter("num"));
		System.out.println(cid+"--"+curNum);
		//调用业务层功能：以分页的形式查询当前类别下的商品信息
		//返回PageModel对象（PageModel包含1_当前页商品信息2_分页3_url）
		ProductService productService = new ProductServiceImpl();
		PageModel pageModel = productService.findProductsByCidWithPage(cid,curNum);
		//将PageModel对象放入request
		request.setAttribute("page", pageModel);
		//转发到/jsp/product_list.jsp
		return "/jsp/product_list.jsp";
	}
}
