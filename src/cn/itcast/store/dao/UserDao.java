package cn.itcast.store.dao;

import java.sql.SQLException;

import cn.itcast.store.domain.User;

public interface UserDao {

	void userRegist(User user) throws SQLException;
	
	/**
	 * 根据激活码查询用户
	 * @param code 激活码
	 * @return
	 */
	User userActive(String code) throws SQLException;
	
	/**
	 * 更新数据库中的数据
	 * @param user
	 */
	void updateUser(User user) throws SQLException ;

}
