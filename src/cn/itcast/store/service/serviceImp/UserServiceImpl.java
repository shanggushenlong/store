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

	@Override
	public boolean userActive(String code) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		//通过激活码查询数据库中是否存在该用户
		User user = userDao.userActive(code);
		
		if (user != null) {
			//若不为空，表面存在该用户
			//修改该用户的状态，清除数据库中该用户的激活码
			user.setState(1);
			user.setCode(null);
			//上面的更新操作在内存中，现在需要更新数据库
			userDao.updateUser(user);
			return true;
		}else {
			//查询无果，
			return false;
		}
		
	}

	@Override
	public User userLogin(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		User uu = userDao.userLogin(user);
		
		if (null == uu) {
			throw new RuntimeException("密码不正确！");
		}else if (uu.getState() == 0) {
			throw new RuntimeException("用户未激活！");
		}else {
			return uu;
		}
		
	}

	
}
