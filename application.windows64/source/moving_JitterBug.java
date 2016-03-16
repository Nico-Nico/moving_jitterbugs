import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class moving_JitterBug extends PApplet {

int[] MyKulerTheme = {
  0xff255B9B, 0xffFDD645, 0xffF15656, 0xffF2F8FF, 0xff31313B
};
int[] Analogous = {
  0xffFF530D, 0xffE82C0C, 0xffFF0000, 0xffE80C7A, 0xffFF0DFF
};
int[] palette = MyKulerTheme;
growingCircle gc = null;

int x;
int y;

int bugNum = 2000;
JitterBug[] bugs = new JitterBug[bugNum];

public void setup() {
  strokeWeight(1);
  size(displayWidth, displayHeight);
  smooth();
  noStroke();
  frameRate(40);
  //noCursor();
  gc = new growingCircle();
  for (int i=0; i<bugs.length; i++) {
    bugs[i] = new JitterBug(random(width), random(height), palette[PApplet.parseInt(random(1, 5))], 
    PApplet.parseInt(random(5, 50)));
    bugs[i].setGrowingCircle(gc);
  }
}

public void draw() {  
  noStroke();
  fill(palette [0], 30);
  rect(0, 0, width, height);
  noStroke();
  for (int i=0; i<bugs.length; i++) {
    bugs[i].move();
    bugs[i].display();
  }
  if(gc != null){
    gc.display();
  }
}

public void mousePressed(){
  gc.setOrigin(mouseX, mouseY);
}
class JitterBug{
  growingCircle gc;
  float x;
  float y;
  int diameter;
  int c;
  float speed = 15;
  
  JitterBug(float tempX, float tempY, int tempColor, int tempDiameter){
    x = tempX;
    y = tempY;
    c = tempColor;
    diameter = tempDiameter;
  }
  
  public void setGrowingCircle(growingCircle tempGC){
    gc = tempGC;
  }
  
  public void move(){
    x += random(-speed, speed);
    y += random(-speed, speed);
    x = constrain(x, 0, width);
    y = constrain(y, 0, height);
  }
  
  public void display(){
    fill(0, 200);
    
    /*if (dist(mouseX, mouseY, x, y)-50<diameter){
      fill(c);
    }*/
    
    if (gc.active == true){
      if (dist(x, y, gc.x, gc.y) < gc.d && dist(x, y, gc.x, gc.y) > gc.d-200){
        fill(c);
        stroke(255, 25  );
        //line(x, y, gc.x, gc.y);
      }
    }
    noStroke();
    ellipse(x, y, diameter, diameter);
  }
}
class growingCircle{
  int x;
  int y;
  int d = 100;
  int speed = 50;
  boolean active = false;
  
  growingCircle(){
  };

  public void setOrigin(int tempX, int tempY){
    x = tempX;
    y = tempY;
    d = 0;
    active = true;
  };
  
  public void display(){
    if (active == true){
      stroke(0xffFF0000, 0);
      noFill();
      ellipse(x, y, d, d);
      d += speed;
    }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "moving_JitterBug" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
