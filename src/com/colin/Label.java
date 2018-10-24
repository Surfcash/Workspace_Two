package com.colin;

import processing.core.PApplet;
import processing.core.PVector;
import static com.colin.MainApp.game;

class Label {
    String label;
    private PVector pos;
    private int size;
    private PApplet p;
    int color;

    Label(String labelPrinted, PApplet parent) {
        p = parent;
        pos = new PVector(p.width / 2F, 250);
        label = labelPrinted;
        size = 200;
        this.color = p.color(255);
    }
    Label(String labelPrinted, PVector position, int textSize, int color, PApplet parent) {
        label = labelPrinted;
        pos = position;
        size = textSize;
        p = parent;
        this.color = color;
    }

    void render() {
        p.fill(color);
        p.textFont(game.font);
        p.textSize(size);
        p.textAlign(p.CENTER, p.CENTER);
        p.text(label, pos.x, pos.y - size * 0.15F);
    }
}
