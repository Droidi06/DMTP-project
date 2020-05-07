import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DMTP_UI extends PApplet {




ControlP5 cp5;
Serial port;
PFont font;
String cablelength  = "";
String error1       = "";
String error2       = "";
String sending      = "";


public void setup(){
  
                                                                            //(Width, Height) Window Size
  
  cp5 = new ControlP5(this);
  font = createFont("calibri bold", 14);                                                   //(font, font size) Sets the font variable to the selected font
  printArray(Serial.list()); 
  port = new Serial(this, "COM4");
  
  cp5.addButton("Send Instructions to Arduino")                                            //Text in button
    .setPosition(30, 230)                                                                  //(x, y) of top left corner of the button
    .setSize(220, 30)                                                                      //(Width, Height)
    .setFont(font)                                                                         //Sets font to the font variable
  ;
  
}


public void draw(){

  background(255, 255, 255);      //(r, g, b) Window Color  
  
  if(mousePressed){
    if(mouseX>30 && mouseX <30+220 && mouseY>230 && mouseY <230+30){
      sending = "The measurements have been sent to the Arduino";
      port.write(cablelength);
    }
  }
  
  fill(0, 0, 0);                                                                           //(r, g, b) Text color
  text("Automatic Wire Cutting Machine", 140, 30);                                         //(text, x, y) Shows the text on the window
  text("Click on the window and enter the desired length of the cable (in mm):", 30, 100); //(text, x, y) Shows the text on the window
  text(sending, 30, 210);                                                                  //(text, x, y) Shows the text on the window
  
  fill(0, 125, 125);
  text(cablelength, 30, 120, 540, 300);                                                    //(text, x, y) Shows the text on the window
  
  fill(255, 0, 0);
  text(error1, 30, 160);                                                                   //(text, x, y) Shows the text on the window
  text(error2, 30, 190);                                                                   //(text, x, y) Shows the text on the window

}


public void keyTyped(){
  if ((key >='0' && key<='9') && cablelength.length() < 4){
    cablelength = cablelength + key;
  }
  else {
    error2 = ("The length of the cable cannot be larger than 99cm");
  }
  if (key >='A' && key<='z'){ 
    error1 = ("Only numbers are allowed");
  }
  if (key == BACKSPACE){
    cablelength= cablelength.substring(0, max(0, cablelength.length()-1));
  }
}
  public void settings() {  size(500, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DMTP_UI" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
