import acm.graphics.GImage;

public class Image {
	int type;
	boolean isNew;
	GImage img;

	public Image(int type){
		this.type = type;
		isNew = false;
		img = new GImage("C:\\Users\\May\\workspace\\Game2048(1)\\"+type+".jpg");
	}
}