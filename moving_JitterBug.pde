color[] MyKulerTheme = {
  #255B9B, #FDD645, #F15656, #F2F8FF, #31313B
};
color[] Analogous = {
  #FF530D, #E82C0C, #FF0000, #E80C7A, #FF0DFF
};
color[] palette = MyKulerTheme;
growingCircle gc = null;

int x;
int y;

int bugNum = 6000;
JitterBug[] bugs = new JitterBug[bugNum];

void setup() {
  strokeWeight(1);
  size(displayWidth, displayHeight);
  smooth();
  noStroke();
  frameRate(40);
  //noCursor();
  gc = new growingCircle();
  for (int i=0; i<bugs.length; i++) {
    bugs[i] = new JitterBug(random(width), random(height), palette[int(random(1, 5))], 
    int(random(5, 50)));
    bugs[i].setGrowingCircle(gc);
  }
}

void draw() {  
  noStroke();
  fill(palette[0], 30);
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

void mousePressed(){
  gc.setOrigin(mouseX, mouseY);
}