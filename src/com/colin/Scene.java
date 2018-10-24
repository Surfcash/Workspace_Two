package com.colin;

import processing.core.PApplet;

import java.util.ArrayList;

class Scene {
    ArrayList<Label> labels = new ArrayList<>();
    ButtonManager buttonManager;
    int color;
    Player player;
    TileMap level;
    PApplet p;


    Scene(PApplet parent) {
        p = parent;
        color = p.color(0, 0, 0);
        buttonManager = new ButtonManager(p);
    }

    void update() {
        buttonManager.update();
    }

    void render() {
        p.background(color);
        renderLabels();
        buttonManager.render();
    }

    private void renderLabels() {
        for(Label i : labels) {
            i.render();
        }
    }
}
