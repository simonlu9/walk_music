package org.music.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.music.bean.PlayList;
import org.music.pool.MysqlPool;

public class PlayListDao {
	static Logger logger =  Logger.getLogger("playlist");
	//tag未统计
	public void addPlayList(PlayList playlist) {
		Connection conn = MysqlPool.pool.getConnection();
		String sql = "insert into playlist(id,uid,name,pic,publish_date,comment_num,share_num,fav_num,track_num,play_num,`desc`) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, playlist.getId());
			ps.setLong(2, playlist.getUid());
			ps.setString(3, playlist.getName());
			ps.setString(4, playlist.getPic());
			ps.setString(5, playlist.getPublishDate());
			ps.setLong(6, playlist.getCommentNum());
			ps.setLong(7, playlist.getShareNum());
			ps.setLong(8, playlist.getFavNum());
			ps.setLong(9, playlist.getTrackNum());
			ps.setLong(10, playlist.getPlayNum());
			ps.setString(11, playlist.getDesc());
			ps.addBatch();
			ps.executeBatch();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MysqlPool.pool.returnConnection(conn);
			// ps.executeUpdate();无法判断是否已经插入
			// users.clear();
			System.out.println("插入失败 from:" +playlist);
		     StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}finally {
			MysqlPool.pool.returnConnection(conn);
			// MysqlPool.pool.returnConnection(conn);
		}
	}
}
