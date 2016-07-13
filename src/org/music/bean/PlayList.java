package org.music.bean;

import java.util.Set;

public class PlayList {
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
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
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
	public long getFavNum() {
		return favNum;
	}
	public void setFavNum(long favNum) {
		this.favNum = favNum;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
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
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+name+"	favNum"+favNum+"		date"+publishDate+
				"	desc"+desc+"		share:"+shareNum+"	 comment"+commentNum+"	tracknum"+trackNum+"	pic"+pic+
				"	playNUm:"+playNum;
	}
	
	public long getPlayNum() {
		return playNum;
	}
	public void setPlayNum(long playNum) {
		this.playNum = playNum;
	}

	private int id;
	private String name;
	private String pic;
	private String publishDate;//����ʱ��
	private long commentNum; //������
	private long shareNum; //ר����������
	private long favNum; //ר���ղ���
	private long uid;//������
	private String desc;//ר������
	private long trackNum;//������
	private Set<String> tags;
	private  long playNum;
}
