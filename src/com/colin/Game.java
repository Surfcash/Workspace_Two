package com.colin;

import processing.core.PApplet;
import processing.core.PFont;

class Game {
    private PApplet p;
    SceneManager sceneManager;
    public PFont font;
    int level = 0;

    Game(PApplet parent) {
        p = parent;
        font = p.createFont("assets/fonts/Serangkai.ttf", 150);
        sceneManager = new SceneManager(p);
    }

    void update() {
        sceneManager.update();
    }

    void render() {
        sceneManager.render();
    }
}
