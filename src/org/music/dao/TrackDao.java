package org.music.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.music.bean.Track;
import org.music.pool.MysqlPool;

public class TrackDao {
	static Logger logger =  Logger.getLogger(TrackDao.class);

		public static void main(String[] args) {
			
		}
		
		public  void addTrack(Track track) {
			Connection conn = MysqlPool.pool.getConnection();
			String sql = "insert into track(id,name,artist_id,album_id,duration,score,mv_id) "
					+ "values(?,?,?,?,?,?,?)";
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, track.getId());
				ps.setString(2, track.getName());
				ps.setLong(3, track.getArtistId());
				ps.setLong(4, track.getAlbumId());
				ps.setInt(5, track.getDuration());
				ps.setInt(6, track.getScore());
				ps.setInt(7, track.getMvid());
				ps.addBatch();
				ps.executeBatch();
				ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				MysqlPool.pool.returnConnection(conn);
				// ps.executeUpdate();无法判断是否已经插入
				// users.clear();
				System.out.println("插入失败 from:" +track);
				e.printStackTrace();
			     StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				logger.error(sw.toString());
			}finally {
				MysqlPool.pool.returnConnection(conn);
				// MysqlPool.pool.returnConnection(conn);
			}
		}
}
