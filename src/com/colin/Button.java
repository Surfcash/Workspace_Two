package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

class Button extends BoxCollider{
    Label label;
    private PApplet p;

    Button(PVector position, float wide, float tall, String labelPrinted, PApplet parent) {
        pos = position;
        width = wide;
        height = tall;
        assignBounds();
        p = parent;
        label = new Label(labelPrinted, pos, (int)(height * 0.8F), p.color(0, 0, 0), p);
    }


    void render() {
        if(collidesPoint(new PVector(p.mouseX, p.mouseY))) {
            p.fill(255);
            p.stroke(0);
            p.strokeWeight(4);
            renderButton();
            label.color = p.color(0, 0, 0);
            label.render();
        }
        else {
            p.fill(0);
            p.stroke(255);
            p.strokeWeight(4);
            renderButton();
            label.color = p.color(255, 255, 255);
            label.render();
        }
    }

    private void renderButton() {
        p.rectMode(p.CENTER);
        p.rect(pos.x, pos.y, width, height);
    }
}
