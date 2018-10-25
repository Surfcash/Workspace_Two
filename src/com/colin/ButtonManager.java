package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.*;

class ButtonManager {

    private ArrayList<Button> buttons = new ArrayList<>();
    private PApplet p;
    float width;
    private float height, tab;
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
        for(Button i : buttons) {
            i.update();
        }
        if(MOUSE_LEFT || IN_ENTER) {
            MOUSE_LEFT = false;
            IN_ENTER = false;
            for(Button i : buttons) {
                if(i.hovered) {
                    switch(i.label.label) {
                        case "START" : {
                            game.sceneManager.level = 1;
                            game.sceneManager.game();
                            break;
                        }
                        case "OPTIONS" : {
                            break;
                        }
                        case "CONTINUE" :
                        case "RESUME" :
                        case "RETURN" : {
                            game.sceneManager.scene = game.sceneManager.storedScene;
                            break;
                        }
                        case "RESTART" : {
                            game.sceneManager.level = 0;
                            game.sceneManager.title();
                        }
                        case "QUIT TO TITLE" : {
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

    void hoverNext() {
        boolean buttonHovered = false;
        int current = 0;
        for(int i = 0; i < buttons.size(); i++) {
            System.out.println(buttons.get(i).hovered);
            if(buttons.get(i).hovered) {
                buttonHovered = true;
                current = i;
            }
        }
        if(buttonHovered) {
            if (current == buttons.size() - 1) {
                buttons.get(current).setHovered(false);
                buttons.get(0).setHovered(true);
            }
            else {
                buttons.get(current).setHovered(false);
                buttons.get(current + 1).setHovered(true);
            }
        }
        else {
            System.out.println("TEST");
            buttons.get(0).setHovered(true);
        }
    }

    void hoverPrevious() {
        boolean buttonHovered = false;
        int current = 0;
        for(int i = 0; i < buttons.size(); i++) {
            System.out.println(buttons.get(i).hovered);
            if(buttons.get(i).hovered) {
                buttonHovered = true;
                current = i;
            }
        }
        if(buttonHovered) {
            if (current == 0) {
                buttons.get(0).setHovered(false);
                buttons.get(buttons.size() - 1).setHovered(true);
            }
            else {
                buttons.get(current).setHovered(false);
                buttons.get(current - 1).setHovered(true);
            }
        }
        else {
            buttons.get(0).setHovered(true);
        }
    }
}
