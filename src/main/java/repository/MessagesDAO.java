package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import data.vo.Message;

/*
 * 
 * Message 
 * 
 * DAO : Data Access Object의 약자로  
 * 데이터 처리하는 객체 만들때 붙여서 만들기도 한다.
 */
public class MessagesDAO extends DAO {
	// 데이터 등록을 처리할 메서드
	public static int createMessage(String target, String body, String pass) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> obj = new HashMap<>();
		obj.put("target", target);
		obj.put("body", body);
		obj.put("pass", pass);
		int r = session.insert("messages.create", obj);
		session.close();
		return r;
	}

	// 특정 동물에 대한 메세지들을 읽어오는 것을 처리할 메서드
	public static List<Message> readMessages(String target) {
		SqlSession session =factory.openSession(true);
		List<Message> li =session.selectList("messages.readByTarget", target);
		session.close();
		return li;
	}
}






