package org.music.bean;

import java.util.ArrayList;

public class Artist {
	private long id;
	private String name="";
	private String avatar="";
	private String desc="";//ΩÈ…‹
	public ArrayList<Track> trackList = new ArrayList<Track>();
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+"	name:"+name+"		avatar:"+avatar+"	desc"+desc;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<Track> getTrackList() {
		return trackList;
	}
	public void setTrackList(ArrayList<Track> trackList) {
		this.trackList = trackList;
	}
	
}
