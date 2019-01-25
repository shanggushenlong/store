package cn.itcast.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Category;

public interface CategoryDao {
	
	/**
	 * 查询商品分类信息
	 * @return
	 */
	List<Category> getAllCats() throws SQLException;

}
