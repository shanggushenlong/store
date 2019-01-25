package cn.itcast.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Category;

public interface CategoryService {
	
	/**
	 * 查询分类信息
	 * @return
	 */
	List<Category> getAllCats() throws SQLException;

}
