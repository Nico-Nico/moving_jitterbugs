class JitterBug{
  growingCircle gc;
  float x;
  float y;
  int diameter;
  color c;
  float speed = 15;
  
  JitterBug(float tempX, float tempY, color tempColor, int tempDiameter){
    x = tempX;
    y = tempY;
    c = tempColor;
    diameter = tempDiameter;
  }
  
  void setGrowingCircle(growingCircle tempGC){
    gc = tempGC;
  }
  
  void move(){
    x += random(-speed, speed);
    y += random(-speed, speed);
    x = constrain(x, 0, width);
    y = constrain(y, 0, height);
  }
  
  void display(){
    fill(0, 200);
    
    /*if (dist(mouseX, mouseY, x, y)-50<diameter){
      fill(c);
    }*/
    
    if (gc.active == true){
      if (dist(x, y, gc.x, gc.y) < gc.d/2 && dist(x, y, gc.x, gc.y) > gc.d/2-200){
        fill(c);
        stroke(255, 15);
        //line(x, y, gc.x, gc.y);
      }
    }
    noStroke();
    ellipse(x, y, diameter, diameter);
  }
}