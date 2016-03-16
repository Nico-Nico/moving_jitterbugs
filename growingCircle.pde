class growingCircle{
  int x;
  int y;
  int d = 100;
  int speed = 50;
  boolean active = false;
  
  growingCircle(){
  };

  void setOrigin(int tempX, int tempY){
    x = tempX;
    y = tempY;
    d = 0;
    active = true;
  };
  
  void display(){
    if (active == true){
      stroke(#FF0000, 0);
      noFill();
      ellipse(x, y, d, d);
      d += speed;
    }
  }
}