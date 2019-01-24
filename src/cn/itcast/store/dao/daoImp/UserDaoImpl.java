package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.User;
import cn.itcast.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public void userRegist(User user) throws SQLException {
		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		runner.update(sql, params);
	}

	@Override
	public User userActive(String code) throws SQLException {
		String sql = "SELECT * FROM USER WHERE CODE = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		User user = runner.query(sql, new BeanHandler<User>(User.class), code);
//		System.out.println(user);
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		String sql = "update user set username = ?, password = ?, name=?, email=?, telephone=?, birthday=?, sex=?, state=?, code=? where uid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
		runner.update(sql, params);
	}

	@Override
	public User userLogin(User user) throws SQLException {
		String sql = "select * from user where username = ? and password = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		
	}
	

}
