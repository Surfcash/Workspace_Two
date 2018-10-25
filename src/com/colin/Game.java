package com.colin;

import processing.core.PApplet;
import processing.core.PFont;

class Game {
    private PApplet p;
    short level = 0;
    //InputManager input;
    SceneManager sceneManager;
    SpriteManager spriteManager;
    PFont font;

    Game(PApplet parent) {
        p = parent;
        font = p.createFont("assets/fonts/Serangkai.ttf", 150);
        //input = new InputManager(p);
        sceneManager = new SceneManager(p);
        spriteManager = new SpriteManager(p);
    }

    void update() {
        sceneManager.update();
    }

    void render() {
        sceneManager.render();
    }
}
