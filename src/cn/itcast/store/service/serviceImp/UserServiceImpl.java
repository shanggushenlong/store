package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImp.UserDaoImpl;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void userRegist(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		userDao.userRegist(user);
	}

}
