package org.music.bean;

public class Album {
	private long id;
	private String name;
	private String pic;
	private String picUrl;
	private String publishDate;//发行时间
	private String company=""; //发行公司
	private long commentNum=0; //评论数
	private long shareNum=0; //专辑分享数
	private long artistId;//演唱者
	private String desc;//专辑描述
	private long trackNum;//歌曲数
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+name+"	company"+company+"		date"+publishDate+
				"	desc"+desc+"		share:"+shareNum+"	 comment"+commentNum+"	tracknum"+trackNum+"	pic"+pic;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public long getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(long commentNum) {
		this.commentNum = commentNum;
	}
	public long getShareNum() {
		return shareNum;
	}
	public void setShareNum(long shareNum) {
		this.shareNum = shareNum;
	}
	public long getArtistId() {
		return artistId;
	}
	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public long getTrackNum() {
		return trackNum;
	}
	public void setTrackNum(long trackNum) {
		this.trackNum = trackNum;
	}
	
	
	
	
}
