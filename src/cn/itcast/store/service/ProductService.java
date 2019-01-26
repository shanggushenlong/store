package cn.itcast.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.PageModel;

public interface ProductService {
	
	/**
	 * 查询热门商品
	 * @return
	 */
	List<Product> findHots() throws SQLException;
	
	/**
	 * 查询最新商品
	 * @return
	 */

	List<Product> findNews() throws SQLException;
	
	/**
	 * 根据商品ID查询商品信息
	 * @param pid 商品ID
	 * @return 商品Product
	 */
	Product findProductById(String pid) throws SQLException;
	
	/**
	 * 根据商品分类Cid与当前页curNum查询信息，分页显示
	 * @param cid
	 * @param curNum
	 * @return
	 */
	PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException;
}
