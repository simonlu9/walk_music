package org.music.bean;

public class Album {
	private int id;
	private String name;
	private String pic;
	private String picUrl;
	private String publishDate;//����ʱ��
	private String company; //���й�˾
	private long commentNum; //������
	private long shareNum; //ר��������
	private long artistId;//�ݳ���
	private String desc;//ר������
	private long trackNum;//������
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+name+"	company"+company+"		date"+publishDate+
				"	desc"+desc+"		share:"+shareNum+"	 comment"+commentNum+"	tracknum"+trackNum+"	pic"+pic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
