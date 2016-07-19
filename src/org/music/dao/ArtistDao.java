package org.music.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.music.bean.Artist;
import org.music.pool.MysqlPool;

public class ArtistDao {
	static Logger logger =  Logger.getLogger("artist");

		public void addArtist(Artist artist) {
			Connection conn = MysqlPool.pool.getConnection();
			String sql = "replace into artist(id,name,avatar,`desc`) "
					+ "values(?,?,?,?)";
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, artist.getId());
				ps.setString(2, artist.getName());
				ps.setString(3, artist.getAvatar());
				ps.setString(4, artist.getDesc());
				ps.addBatch();
				ps.executeBatch();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				MysqlPool.pool.returnConnection(conn);
				// ps.executeUpdate();无法判断是否已经插入
				// users.clear();
				System.out.println("插入失败 from:" + artist);
			     StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				logger.error(sw.toString());
			}finally {
				MysqlPool.pool.returnConnection(conn);
				// MysqlPool.pool.returnConnection(conn);
			}
			
		}
}
