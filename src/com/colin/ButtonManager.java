package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.*;

class ButtonManager {

    private ArrayList<Button> buttons = new ArrayList<>();
    private PApplet p;
    float width, height, tab;
    private PVector pos;


    ButtonManager(PApplet parent) {
        p = parent;
        pos = new PVector(p.width / 2F, p.height / 2F);
        width = 400;
        height = 100;
        tab = height * 1.2F;
    }

    ButtonManager(PVector startingPos, float wide, float tall, PApplet parent) {
        pos = startingPos;
        width = wide;
        height = tall;
        tab = height * 1.2F;
        p = parent;
    }

    void update() {
        if(MOUSE_LEFT) {
            for(Button i : buttons) {
                if(i.collidesPoint(new PVector(p.mouseX, p.mouseY))) {
                    switch(i.label.label) {
                        case "START" : {
                            game.sceneManager.level = 1;
                            game.sceneManager.game();
                            break;
                        }
                        case "RESUME" : {
                            game.sceneManager.scene = game.sceneManager.storedScene;
                            break;
                        }
                        case "OPTIONS" : {
                            break;
                        }
                        case "RETURN" : {
                            break;
                        }
                        case "QUIT TO TITLE" : {
                            game.sceneManager.storedScene = game.sceneManager.scene;
                            game.sceneManager.title();
                            break;
                        }
                        case "QUIT" : {
                            p.exit();
                            break;
                        }
                        default : {
                            break;
                        }
                    }
                    MOUSE_LEFT = false;
                }
            }
        }
    }

    void render() {
        for(Button i : buttons) {
            i.render();
        }
    }

    void add(String labelPrinted) {
        buttons.add(new Button(new PVector(pos.x, pos.y + (buttons.size() * tab)), width, height, labelPrinted, p));
    }
}
