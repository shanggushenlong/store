package cn.itcast.store.service;

import java.sql.SQLException;

import cn.itcast.store.domain.User;

public interface UserService {
	
	/**
	 * 用户注册
	 * @param user 
	 * @throws SQLException
	 */
	void userRegist(User user) throws SQLException;
	
	/**
	 * 用户邮箱激活
	 * @param code 邮箱激活码
	 * @return
	 */
	boolean userActive(String code) throws SQLException;

}
