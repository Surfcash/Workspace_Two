package com.colin;

import processing.core.PApplet;
import processing.core.PVector;
import processing.opengl.PJOGL;

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
        surface.setLocation(-3, -3);
        previousMil = millis();
    }

    public void settings() {
        size(displayWidth, displayHeight - 61, P2D);
        PJOGL.setIcon("assets/icon.png");
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

    public void keyPressed() {
        if(key == ESC) {
            key = 0;
        }
    }
}
