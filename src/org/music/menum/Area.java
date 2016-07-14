package org.music.menum;

public enum Area {
	 RED("红色", 1), 
	 GREEN("绿色", 2), 
	 BLANK("白色", 3), 
	 YELLO("黄色", 4);
	  private String name;
      private int index;
	private Area(String name, int index) {
		// TODO Auto-generated constructor stub
		this.name = name;
        this.index = index;
	}
	public static void main(String[] args) {
        System.out.println(Area.RED);
    }
}
