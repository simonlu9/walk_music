package org.music.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.music.bean.Album;
import org.music.bean.PlayList;
import org.music.pool.MysqlPool;

public class AlbumDao {
	static Logger logger =  Logger.getLogger("album");
	public static void main(String[] args) {
	
		logger.error("sdfds");
	}
	
	public void addAlbumTrack(Album album, Set<Long> trackIds) {
		Connection conn = MysqlPool.pool.getConnection();
		String sql = "insert into album_track(album_id,track_id) " + "values(?,?)";
		PreparedStatement ps;
		Iterator<Long> it = trackIds.iterator();

		try {
			ps = conn.prepareStatement(sql);
			while (it.hasNext()) {
				ps.setLong(1, album.getId());
				ps.setLong(2, it.next());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MysqlPool.pool.returnConnection(conn);
			// ps.executeUpdate();无法判断是否已经插入
			// users.clear();
			System.out.println("插入失败 from:" + album);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}finally {
			MysqlPool.pool.returnConnection(conn);
		}

	}

	
	
	public void addAlbum(Album album) {
		Connection conn = MysqlPool.pool.getConnection();
		String sql = "replace into album(id,name,pic,publish_date,company,comment_num,share_num,track_num,artist_id,`desc`) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, album.getId());
			ps.setString(2,  album.getName());
			ps.setString(3,  album.getPic());
			ps.setString(4,  album.getPublishDate());
			ps.setString(5,  album.getCompany());
			ps.setLong(6,  album.getCommentNum());
			ps.setLong(7,  album.getShareNum());
			ps.setLong(8,  album.getTrackNum());
			ps.setLong(9,  album.getArtistId());
			ps.setString(10,  album.getDesc());
			ps.addBatch();
			ps.executeBatch();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MysqlPool.pool.returnConnection(conn);
			// ps.executeUpdate();无法判断是否已经插入
			// users.clear();
			System.out.println("插入失败 from:" + album);
		     StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			e.printStackTrace();
		}finally {
			MysqlPool.pool.returnConnection(conn);
			// MysqlPool.pool.returnConnection(conn);
		}
	}
}
