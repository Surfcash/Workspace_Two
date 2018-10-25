package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

class Button extends BoxCollider{
    Label label;
    private PApplet p;
    boolean hovered;


    Button(PVector position, float wide, float tall, String labelPrinted, PApplet parent) {
        super(position, wide, tall);
        p = parent;
        hovered = false;
        label = new Label(labelPrinted, pos, (int)(height * 0.8F), p.color(0, 0, 0), p);
    }

    void update() {
        checkHover();
    }

    void render() {
        if(hovered) {
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

    void setHovered(boolean toSet) {
        hovered = toSet;
    }

    private void renderButton() {
        p.rectMode(p.CENTER);
        p.rect(pos.x, pos.y, width, height);
    }

    private void checkHover() {

        if(p.mouseX != p.pmouseX && p.mouseY != p.pmouseY) {
            hovered = collidesPoint(new PVector(p.mouseX, p.mouseY));
        }

    }
}
