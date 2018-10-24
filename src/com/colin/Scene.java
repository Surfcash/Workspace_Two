package com.colin;

import processing.core.PApplet;

import java.util.ArrayList;

class Scene {
    ArrayList<Label> labels = new ArrayList<>();
    ButtonManager buttonManager;
    LevelManager level;
    PApplet p;


    Scene(PApplet parent) {
        p = parent;
        buttonManager = new ButtonManager(p);
    }

    void update() {
        if(level != null) {
            level.update();
        }
        buttonManager.update();
    }

    void render() {
        p.background(0);
        if(level != null) {
            level.render();
        }
        renderLabels();
        buttonManager.render();
    }

    private void renderLabels() {
        for(Label i : labels) {
            i.render();
        }
    }
}
