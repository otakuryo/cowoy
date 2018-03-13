package application;

import java.io.File;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CashItem{
	//parametros de la ventana
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	ImageView rocks;
	Circle bondRock;
	int positionX=0,positionY=0;
	int wImg = 180,rImg=wImg/2;
	private String uri,file="src/img/rock1.png";
	private int vel=6;
	
	public CashItem(int velocidad,String file) {vel = velocidad;this.file=file;}
	public void setVel(int vel) {this.vel = vel;}
	public ImageView getRocks() {return rocks;}
	public Circle getBondRock() {return bondRock;}
	
	void createObj() {
		int positionY = (int) (Math.random() * 600) + 1;
		//positionX = (int) (Math.random() * WITH/2) + WITH;
		rocks=createObj(new File(file), wImg, WITH, positionY);
		bondRock=boundObj(wImg, WITH, positionY);
		setPositionObj();
	}
	
	ImageView createObj(File file,int width,double posx,double posy) {
		uri = file.toURI().toString();
		ImageView imageview = new ImageView(uri);
		imageview.setFitWidth(width);
		imageview.setPreserveRatio(true);
		return imageview;
	}
	
	Circle boundObj(int width,double posx,double posy) {
		Circle cir = new Circle(width/2);
		cir.setFill(null);
		cir.setStroke(Color.TRANSPARENT);
		return cir;
	}
	
	void setPositionObj() {
		positionY = (int) (Math.random() * HEIGHT) + 1;
		positionX = (int) (Math.random() * WITH/1.2) + WITH;
		rocks.setTranslateX(positionX);
		rocks.setTranslateY(positionY);
		bondRock.setTranslateX(positionX+rImg);
		bondRock.setTranslateY(positionY+rImg);
	}
	boolean searchCollision(Circle nave) {
		boolean collision = false;
		if (nave.getBoundsInParent().intersects(bondRock.getBoundsInParent())) {
			double dx = nave.getTranslateX()-bondRock.getTranslateX();
			double dy = nave.getTranslateY()-bondRock.getTranslateY();
			double minDist = nave.getRadius() + bondRock.getRadius();
			double distancexx = Math.sqrt(dx * dx + dy * dy);
			if(distancexx<minDist){
				collision = true;
			}
		}
		return collision;
	}
	
	void move() {
		positionX-=vel;
		rocks.setTranslateX(positionX);
		bondRock.setTranslateX(positionX+rImg);
		if (positionX<-300) {
			setPositionObj();
		}
	}

}
