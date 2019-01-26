package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;
import cn.itcast.store.utils.PageModel;

public class ProductDaoImpl implements ProductDao{

	@Override
	public List<Product> findHots() throws SQLException {
		String sql = "select * from product where pflag = 0 and is_hot = 1 order by pdate desc limit 0,9";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));	
	
	}

	@Override
	public List<Product> findNews() throws SQLException {
		String sql = "select * from product where pflag = 0 order by pdate desc limit 0,9";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));	
	}

	@Override
	public Product findProductById(String pid) throws SQLException {
		String sql = "select * from product where pid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class),pid);	
	}

	@Override
	public int findTotalRecords(String cid) throws SQLException {
		String sql = "SELECT count(*) FROM product WHERE cid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) runner.query(sql, new ScalarHandler(),cid);
		return num.intValue();
		
	}

	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT * FROM product WHERE cid = ? limit ?,?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
	}
	
	
}
