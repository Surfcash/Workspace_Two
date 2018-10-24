package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

public class MainApp extends PApplet{

    static float previousMil, deltaTime;
    static Game game;
    PVector window;

    public static void main(String[] args) {
        PApplet.main("com.colin.MainApp");
    }

    public void setup() {
        game = new Game(this);
        window = new PVector(width , height);
        surface.setTitle("Colin's Workspace Two");
        surface.setResizable(false);
        previousMil = millis();
    }

    public void settings() {
        size(displayWidth, displayHeight - 70, P2D);
        //PJOGL.setIcon("assets/icon.png");
    }

    public void draw() {
        updateDeltaTime();
        windowSizeListener();
        game.update();
        game.render();
    }

    private void updateDeltaTime() {
        deltaTime = (previousMil / millis()) * (60 / frameRate);
        previousMil = millis();
    }

    private void windowSizeListener() {
        if(window.x != width || window.y != height) {
            //window resize methods
            window = new PVector(width , height);
        }
    }
}