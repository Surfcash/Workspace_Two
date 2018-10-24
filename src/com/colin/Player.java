package com.colin;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static com.colin.MainApp.game;

class Player extends Entity{
    private PImage sprite;
    private PApplet p;

    Player(PVector position, PApplet parent) {
        super(position, 230, 92);
        p = parent;
        sprite = game.spriteManager.getSprite("e_player");
    }

    void update() {
        applyFriction();
        applyGravity();
        applyVelocity();
    }

    void render() {
        p.imageMode(p.CENTER);
        p.image(sprite, pos.x, pos.y);
    }
}
