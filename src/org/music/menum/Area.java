package org.music.menum;

public enum Area {
	 RED("��ɫ", 1), 
	 GREEN("��ɫ", 2), 
	 BLANK("��ɫ", 3), 
	 YELLO("��ɫ", 4);
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
