package com.colin;

import processing.core.PApplet;
import processing.core.PVector;
import processing.opengl.PJOGL;

public class MainApp extends PApplet{

    static boolean MOUSE_LEFT, IN_LEFT, IN_RIGHT, IN_UP, IN_ESCAPE = false;
    static float previousMil, deltaTime;
    static Game game;
    PVector window;

    public static void main(String[] args) {
        String[] PApp = {"com.colin.MainApp", "com.colin.InputManager"};
        PApplet.main(PApp);
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
        if(key == ESC) key = 0;
        switch(keyCode) {
            case 27: {
                IN_ESCAPE = true;
                break;
            }
            case 32 :
            case 38 :
            case 87 : {
                IN_UP = true;
                break;
            }
            case 37 :
            case 65 : {
                IN_LEFT = true;
                break;
            }
            case 39 :
            case 68 : {
                IN_RIGHT = true;
                break;
            }
            default : {
                break;
            }
        }
    }

    public void keyReleased() {
        switch(keyCode) {
            case 27: {
                IN_ESCAPE = false;
                break;
            }
            case 32 :
            case 38 :
            case 87 : {
                IN_UP = false;
                break;
            }
            case 37 :
            case 65 : {
                IN_LEFT = false;
                break;
            }
            case 39 :
            case 68 : {
                IN_RIGHT = false;
                break;
            }
            default : {
                break;
            }
        }
    }

    public void mousePressed() {
        switch(mouseButton) {
            case LEFT : {
                MOUSE_LEFT = true;
                break;
            }
            default :{

            }
        }
    }

    public void mouseReleased() {
        switch(mouseButton) {
            case LEFT : {
                MOUSE_LEFT = false;
                break;
            }
            default : {
                break;
            }
        }
    }
}
