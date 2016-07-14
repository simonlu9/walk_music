package org.music.bean;

public class User {
		private long uid;
		private int level;
		private int fans_num;
		private int follow_num;
		private int feed_num;
		private int age;
		private int gender;
		private String username;
		private long weibo_id;
		private String area="";
		private String channel;
		private String sign;
		private String userCard;
		private long record;
		private String avatar;
		private int province;
		private int city;
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		   return "uid:"+uid+"	username:"+username+"	level:"+level+"	 age:"+age+"	gender:"+gender+"	follow_num:"+follow_num+"	area:"+area
				   +"	record:"+record+"	avatar:"+avatar+"	weibo"+weibo_id+"	sign"+sign;
		}
		
		
		public String getAvatar() {
			return avatar;
		}


		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}


		public long getRecord() {
			return record;
		}
		public void setRecord(long record) {
			this.record = record;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getUserCard() {
			return userCard;
		}
		public void setUserCard(String userCard) {
			this.userCard = userCard;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public long getUid() {
			return uid;
		}
		public void setUid(long uid) {
			this.uid = uid;
		}
		public int getFans_num() {
			return fans_num;
		}
		public void setFans_num(int fans_num) {
			this.fans_num = fans_num;
		}
		public int getFollow_num() {
			return follow_num;
		}
		public void setFollow_num(int follow_num) {
			this.follow_num = follow_num;
		}
		public int getFeed_num() {
			return feed_num;
		}
		public void setFeed_num(int feed_num) {
			this.feed_num = feed_num;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getGender() {
			return gender;
		}
		public void setGender(int gender) {
			this.gender = gender;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public long getWeibo_id() {
			return weibo_id;
		}
		public void setWeibo_id(long weibo_id) {
			this.weibo_id = weibo_id;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}


	

	
		
}
