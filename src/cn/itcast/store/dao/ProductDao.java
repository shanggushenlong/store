package cn.itcast.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Product;

public interface ProductDao {
	
	/**
	 * 查询最热门商品
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
	 * @param pid
	 * @return
	 */
	Product findProductById(String pid) throws SQLException;
	
	/**
	 * 根据商品分类ID查询商品总数
	 * @param cid
	 * @return
	 */
	int findTotalRecords(String cid) throws SQLException;
	
	List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;

}
