package org.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.music.bean.User;
import org.music.pool.MysqlPool;

public class UserDao {
	static ArrayList<User> users = new ArrayList<User>();

	public void addUsers(User _user) throws SQLException {
		users.add(_user);
		if (users.size() > 5) {
			Connection conn = MysqlPool.pool.getConnection();
			String sql = "insert into user(username,weibo_id,gender,age,area,fans_num,follow_num,feed_num,record,avatar,level,uid,sign) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			for (User user : users) {
				ps.setString(1, user.getUsername());
				ps.setLong(2, user.getWeibo_id());
				ps.setInt(3, user.getGender());
				ps.setInt(4, user.getAge());
				ps.setString(5, user.getArea());
				ps.setLong(6, user.getFans_num());
				ps.setInt(7, user.getFollow_num());
				ps.setInt(8, user.getFeed_num());
				ps.setLong(9, user.getRecord());
				ps.setString(10, user.getAvatar());
				ps.setInt(11, user.getLevel());
				ps.setLong(12, user.getUid());
				ps.setString(13, user.getSign());
				ps.addBatch();
			}

			ps.executeBatch();
			ps.close();

			MysqlPool.pool.returnConnection(conn);

			// ps.executeUpdate();�޷��ж��Ƿ��Ѿ�����
			users.clear();
			System.out.println("����ɹ�");

		}else{
			
		}
	}
}