package cn.itcast.store.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import cn.itcast.store.domain.User;

public class TestBeanUtils {
	
	/**
	 * 测试beanUtils类，相当于利用反射获取对应的set方法赋值
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	//反射
	// Class clazz = User.getClass();
	// clazz.getMethod();
	// BeanUtils中含有User.class，它找到User.class文件上的各种setr get方法
	//
	@Test
	public void test01() throws Exception {
		Map<String, String[]> map = new HashMap<String, String[]>();
		
		//map集合加入数据
		map.put("username", new String[]{"lisi"});
		map.put("password", new String[]{"123456"});
		
		User user = new User();
		//数据填充。
		BeanUtils.populate(user, map);
		System.out.println(user);
		
	}
}
