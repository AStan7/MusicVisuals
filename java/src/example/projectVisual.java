package example;

import ie.tudublin.*;

public class projectVisual extends Visual
{

    public void settings()
    {
        size(800, 600, P3D);
    }


    public void setup()
    {
        startMinim();
        loadAudio("cars1.mp3");
    }

    float lineX, lineY;
    float sphereX, sphereY;
    float patternX, patternY;
    int pyrT;

    //when key pressed play music
    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    //draw orbiting circle
    void drawPattern(float x, float y)
    {
        stroke(255,0,0);
        strokeWeight(20);
        point(x1(t), y2(t));
        
        t++;
    }
    float x1(float t){
        return sin(t / 10) * 150 + sin(t / 20) * 20;
    }
    
    float y2(float t){
        return cos(t / 10) * 150 ;
    }

    //draw pyramid
    void drawPyramid(int t)
    {
        noFill();
        strokeWeight(1);
        
        beginShape(TRIANGLES);

        vertex(-t, -t, -t);
        vertex( t, -t, -t);
        vertex( 0, 0, t);

        vertex( t, -t, -t);
        vertex( t, t, -t);
        vertex( 0, 0, t);

        vertex( t, t, -t);
        vertex(-t, t, -t);
        vertex( 0, 0, t);

        vertex(-t, t, -t);
        vertex(-t, -t, -t);
        vertex( 0, 0, t);

        endShape();
    }


    float angle = 0;
    float t;

    //draw large and small spheres
    void drawSphere(float x, float y){
        
        calculateAverageAmplitude();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 0);        
        strokeWeight(1);
        noFill();
        lights();
        pushMatrix();   
        float boxSize= 100 + (120 * getSmoothedAmplitude()); 
      
        sphere(boxSize); 
       
        popMatrix();
        angle += 0.01f;
        
        calculateAverageAmplitude();
        stroke(map(getSmoothedAmplitude(), 255, 1, 255, 255), 0, 0);
        strokeWeight(1);
        rotateX(angle);
        rotateZ(angle);
        float boxSize1= 40 + (160 * getSmoothedAmplitude());
        sphere(boxSize1); 
    }


    public void draw()
    {
        background(0);
        translate(width/2, height/2);
        fill(255);
        camera(100, 500, 300, 0, 0, -1, 0, 1, 0);
        rotateX(angle);
        rotateZ(angle); 
        drawPattern(patternX, patternY);
        drawSphere(sphereX, sphereY);
        translate(0, 0, 70);
        stroke(0,0,255); 
        drawPyramid(150);
    }
    
}




