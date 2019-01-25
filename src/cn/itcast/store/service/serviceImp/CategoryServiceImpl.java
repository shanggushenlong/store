package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.dao.daoImp.CategoryDaoImpl;
import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.UserService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> getAllCats() throws SQLException {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.getAllCats();	
	}

	
}
