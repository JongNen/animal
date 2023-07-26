package repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DAO {
	protected static SqlSessionFactory factory;

	
	static {
		
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/config.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
   