package org.music.bean;

import org.assertj.core.util.ToString;
import org.json.JSONObject;

public class Track {
		private long id;
		private long artistId;
		private long albumId;
		private long copyrightId;
		private int duration; //时长
		private int score;//评分
		private int status;
		private String transNames;
		private String name; //歌曲名字
		private int mvid; //mvid
		private int fee;
		private int ftype;
		
		public long getArtistId() {
			return artistId;
		}

		public void setArtistId(long artistId) {
			this.artistId = artistId;
		}

		public long getAlbumId() {
			return albumId;
		}

		public void setAlbumId(long albumId) {
			this.albumId = albumId;
		}

		@Override
		public String toString() {
		// TODO Auto-generated method stub
			return "id:"+id+"	name:"+name+"		score"+score+"	duration"+duration;
		}
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public long getCopyrightId() {
			return copyrightId;
		}
		public void setCopyrightId(long copyrightId) {
			this.copyrightId = copyrightId;
		}
		public int getDuration() {
			return duration;
		}
		public void setDuration(int duration) {
			this.duration = duration;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getTransNames() {
			return transNames;
		}
		public void setTransNames(String transNames) {
			this.transNames = transNames;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getMvid() {
			return mvid;
		}
		public void setMvid(int mvid) {
			this.mvid = mvid;
		}
		public int getFee() {
			return fee;
		}
		public void setFee(int fee) {
			this.fee = fee;
		}
		public int getFtype() {
			return ftype;
		}
		public void setFtype(int ftype) {
			this.ftype = ftype;
		}
//		public Track(JSONObject songInfo) {
//			// TODO Auto-generated constructor stub
//			this.duration = songInfo.getS
//		}
		
		
		
}
