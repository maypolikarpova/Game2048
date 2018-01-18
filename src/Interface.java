import acm.program.*;
import acm.graphics.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Interface extends GraphicsProgram{
	
	private static final int LENGTH = 600;
	private static final int PANEL = 260;
	private static final int HEIGHT = 600; 
	private static final int SIZE = 125; 
	
	private Random r = new Random();
	private int score=0;
	private JButton newGame,manual,up,down,left,right;

	private Image[] imgs = new Image[16];
	
			
	public void run(){ 
		setup();
		interaction();
	} 

	private void setup(){ //настройка неизменного поля
		
		this.setSize(LENGTH+PANEL,HEIGHT); 	
		this.setBackground(Color.getHSBColor((float)0.1,(float) 0.9, 100)); 
		
		for(int x=20;x<LENGTH;x+=145){
			for(int y=20;y<LENGTH;y+=145){
				GRect rect = new GRect(SIZE,SIZE);
				rect.setColor(Color.getHSBColor((float)0.1,(float) 0.9, 100));
				rect.setFilled(true);
				rect.setFillColor(Color.GRAY);
				add(rect,x,y);
			}
		}
		
		random();
		buttons();
		scoreTable();
		
	}
	private void scoreTable(){//cчет
		
		GLabel s = new GLabel("Score :");
		s.setFont("Forte-40");
		add(s,660,40);
		
		GRect r = new GRect(640,60,150,50);
		r.setColor(Color.getHSBColor((float)0.1,(float) 0.9, 100));
		r.setFilled(true);
		r.setFillColor(Color.white);
		add(r);
		GLabel sc = new GLabel(score+"");
		sc.setFont("Copperplate Gothic Bold-40");
		add(sc,650,100);
	}
	
	private void buttons(){//кнопки
		newGame = new JButton("New game");
		newGame.setBounds(0,0,150, 50);
		add(newGame,640,130);
		
		manual = new JButton("Manual ");
		manual.setBounds(0,0,150, 50);
		add(manual,640,200);
		
		up = new JButton("Up");
		up.setBounds(0,0,90, 50);
		add(up,680,400);
		
		down = new JButton("Down");
		down.setBounds(0,0,90, 50);
		add(down,680,450);
		
		left = new JButton("Left");
		left.setBounds(0,0,90, 50);
		add(left,590,450);
		
		right = new JButton("Right");
		right.setBounds(0,0,90, 50);
		add(right,770,450);
		
	
	}
	private static int start=0;
	private void random(){//начальная инициализация, рандомно вставляем две цифры
		
		int a = r.nextInt(10);// будет здесь 4 или 2
		int counter=0;int[] coords1= new int[3];
		int[] coords2= new int[3];

		for(int i=0;i<16;i++){
			coords1 = placing();
			coords2 = placing();

			if(imgs[coords1[2]]!=null||imgs[coords2[2]]!=null){
			counter++;}
			
			else{break;}}

		if(counter==15){System.out.println("MAKE THE END METHOD!!!!!");}
		
		Image i2 = new Image(2);
		Image i22 = new Image(2);
		Image i4 = new Image(4);
		
		
		if(coords1[0]==coords2[0]&&coords1[1]==coords2[1]&&coords1[0]!=455){coords2[0]+=145; coords2[2]++;}
		else if(coords1[0]==coords2[0]&&coords1[1]==coords2[1]&&coords1[0]==455){coords2[0]-=145; coords2[2]--;}

		if(a==0){ 
			add(i2.img,coords1[0],coords1[1]);
			imgs[coords1[2]]=i2;
				
			add(i4.img,coords2[0],coords2[1]);
			imgs[coords2[2]] = i4;
		}
		else{
			add(i2.img,coords1[0],coords1[1]);
			imgs[coords1[2]] = i2;
			
			add(i22.img,coords2[0],coords2[1]);
			imgs[coords2[2]] = i22;
		}
	}	
	
	private void randomOne(){
		int a = r.nextInt(10);// будет здесь 4 или 2
		int counter=0;
		int[] coords= new int[3];


		for(int i=0;i<16;i++){
			coords = placing();

			if(imgs[coords[2]]!=null){
			counter++;}
			
			else{break;}}

		if(counter==15){System.out.println("MAKE THE END METHOD!!!!!");}
		
		Image i2 = new Image(2);
		Image i4 = new Image(4);
		
		if(a==0){ 
			add(i4.img,coords[0],coords[1]);
			imgs[coords[2]]=i4;}
		else{		
			add(i2.img,coords[0],coords[1]);
			imgs[coords[2]] = i2;	
	}}
		
	private int[] placing(){
		int x,y,num;
		int row = r.nextInt(4);// ряд
		int number= r.nextInt(4);//  номер
		
		int[] coords = new int[3];
		
		x = 20*(number+1)+125*number;
		y = 20*(row+1)+125*row;
		num = number+(4*row);//номер клетки от 0 до 15 сверху вниз слева направо
		
		coords[0]=x;
		coords[1]=y;
		coords[2]=num;
		
		
		return coords;
	}
	
	public void interaction(){

		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				score = 0; start=0;
				for(int i=0;i<16;i++)imgs[i]=null;
				scoreTable();
				run();
				repaint();
			}
		});
		
		manual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame manual = new JFrame("Manual");
				manual.setSize(320, 320);
				manual.setLocation(getHeight()/4, getWidth()/4);
				manual.setLayout(new GridLayout(1,1));
				JTextArea t = new JTextArea(16,12);
				t.setLineWrap(true);
				t.setText("2048 is played on a gray 4×4 grid, with numbered tiles that"
						+ " slide smoothly when"
						+ " a player moves them using the four buttons: "
						+ "up, down, right and left. Every turn, a new tile will randomly appear in an empty spot on the board"
						+ " with a value of either 2 or 4. Tiles slide as far as possible in the chosen direction until"
						+ " they are stopped by either another tile or the edge of the grid. If two tiles of the same"
						+ " number collide while moving, they will merge into a tile with the total value "
						+ "of the two tiles that collided. The resulting tile cannot merge with another "
						+ "tile again in the same move. Higher-scoring tiles emit a soft glow.A scoreboard "
						+ "on the upper-right keeps track of the users score. The users score starts at zero,"
						+ " and is incremented whenever two tiles combine, by the value of the new tile. The "
						+ "game is won when a tile with a value of 2048 appears on the board, hence the name "
						+ "of the game. After reaching the 2048 tile, players can continue to play (beyond the "
						+ "2048 tile) to reach higher scores. If the player has no legal moves (there are no"
						+ " empty spaces and no adjacent tiles with the same value), the game ends");
				JScrollPane p = new JScrollPane(t);
				manual.add(p);
				manual.setVisible(true);
			}
		});
		
		up.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Image i:imgs){
					if(i!=null)i.isNew = false;
				}
				changePlace(1);
				randomOne();
			}
		});
		
		down.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Image i:imgs){
					if(i!=null)i.isNew = false;
				}
				changePlace(2);
				randomOne();
			}
		});
		
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Image i:imgs){
					if(i!=null)i.isNew = false;
				}
				changePlace(3);
				randomOne();
			}
		});
		
		right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Image i:imgs){
					if(i!=null)i.isNew = false;
				}
				changePlace(4);
				randomOne();
			}
		});
		

	}
	
	
	private void changePlace(int dir){
		
		if(dir==1){//вверх
			for(int a=1;a<4;a++){
				for(int i=4;i<16;i++){
					if(imgs[i]!=null&&imgs[i-4]==null&&imgs[i].img.getY()!=20){
						imgs[i].img.move(0,-145);
						imgs[i-4]=imgs[i];
						imgs[i]=null;	
					}
					
					else if(imgs[i]!=null&&imgs[i-4]!=null){// [i-4]
						if(imgs[i].isNew!=true&&imgs[i].type==imgs[i-4].type){ //[i-4]
							score =score+ imgs[i].type*2;
							scoreTable();
							Image ab = new Image(imgs[i].type*2);
							imgs[i].img.move(0,-145);//(0,-145)
							add(ab.img,imgs[i].img.getX(),imgs[i].img.getY());
							remove(imgs[i-4].img);
							remove(imgs[i].img);
							imgs[i-4]=ab;//!
							imgs[i-4].isNew=true;
							imgs[i]=null;
						}
					}

					}}}
		
		if(dir==2){//вниз 
			for(int a=1;a<4;a++){
				for(int i=0;i<12;i++){
					if(imgs[i]!=null&&imgs[i+4]==null&&imgs[i].img.getY()!=455){
						imgs[i].img.move(0,145);
						imgs[i+4]=imgs[i];
						imgs[i]=null;	
					}
					
					else if(imgs[i]!=null&&imgs[i+4]!=null){// [i-4]
						if(imgs[i].isNew!=true&&imgs[i].type==imgs[i+4].type){ //[i-4]
							score = score +imgs[i].type*2;
							scoreTable();
							Image ab = new Image(imgs[i].type*2);
							imgs[i].img.move(0,145);//(0,-145)
							add(ab.img,imgs[i].img.getX(),imgs[i].img.getY());
							remove(imgs[i+4].img);
							remove(imgs[i].img);
							imgs[i+4]=ab;//!
							imgs[i+4].isNew=true;
							imgs[i]=null;
						}
					}

		}}}
	
		if(dir==3){//влево 
			for(int a=1;a<4;a++){
				for(int i=15;i>0;i--){
					if(i!=4&&i!=8&&i!=12&&imgs[i]!=null&&imgs[i-1]==null&&imgs[i].img.getX()!=20){
						imgs[i].img.move(-145,0);
						imgs[i-1]=imgs[i];
						imgs[i]=null;	
						}
					
					else if(i!=4&&i!=8&&i!=12&&imgs[i]!=null&&imgs[i-1]!=null){// [i-4]
						if(imgs[i].isNew==false&&imgs[i].type==imgs[i-1].type){ //[i-4]
							score = score + imgs[i].type*2;
							scoreTable();
							Image ab = new Image(imgs[i].type*2);
							imgs[i].img.move(-145,0);//(0,-145)
							add(ab.img,imgs[i].img.getX(),imgs[i].img.getY());
							remove(imgs[i-1].img);
							remove(imgs[i].img);
							imgs[i-1]=ab;//!
							imgs[i-1].isNew=true;
							imgs[i]=null;
						}
					}
				
		}}}
		
		if(dir==4){//вправо
			for(int a=1;a<4;a++){
				for(int i=0;i<16;i++){
					if(i!=3&&i!=7&&i!=11&&i!=15&&imgs[i]!=null&&imgs[i+1]==null){
						imgs[i].img.move(145,0);
						imgs[i+1]=imgs[i];
						imgs[i]=null;	
					}
					
					else if(i!=3&&i!=7&&i!=11&&i!=15&&imgs[i]!=null&&imgs[i+1]!=null){// [i-4]
						if(imgs[i].isNew!=true&&imgs[i].type==imgs[i+1].type){ //[i-4]
							score = score + imgs[i].type*2;
							scoreTable();
							Image ab = new Image(imgs[i].type*2);
							imgs[i].img.move(145,0);//(0,-145)
							add(ab.img,imgs[i].img.getX(),imgs[i].img.getY());
							
							remove(imgs[i+1].img);
							remove(imgs[i].img);
							imgs[i+1]=ab;//!
							imgs[i+1].isNew=true;
							imgs[i]=null;
						}
					}
				
			}}}
		
	}
}

		



