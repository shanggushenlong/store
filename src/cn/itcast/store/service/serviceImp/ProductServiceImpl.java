package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.dao.daoImp.ProductDaoImpl;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.utils.PageModel;


public class ProductServiceImpl implements ProductService{

	@Override
	public List<Product> findHots() throws SQLException {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.findHots();
	}

	@Override
	public List<Product> findNews() throws SQLException {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.findNews();
	}

	@Override
	public Product findProductById(String pid) throws SQLException {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.findProductById(pid);
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException {
		//1_创建PageModel对象 目的：计算分页参数
		
		//统计当前分类下商品个数
		ProductDao productDao = new ProductDaoImpl();
		int totalRecords = productDao.findTotalRecords(cid);
		System.out.println(totalRecords);
		//传入三个参数 curNum 当前页  totalRecords 总的数量 12 每一页的记录数
		PageModel pageModel = new PageModel(curNum, totalRecords, 12);
		//2_关联集合select * from product where cid = ? limit ?,?
		List list = productDao.findProductsByCidWithPage(cid,pageModel.getStartIndex(),pageModel.getPageSize());
		pageModel.setList(list);
		//3.关联url
		pageModel.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid); //当前商品类的ID
		return pageModel;
	}

}
