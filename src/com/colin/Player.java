package com.colin;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static com.colin.MainApp.*;
import static processing.core.PApplet.constrain;

class Player extends Entity{
    private PImage sprite;
    private PApplet p;

    Player(PVector position, PApplet parent) {
        super(position, 230, 92);
        p = parent;
        sprite = game.spriteManager.getSprite("e_player");
    }

    void update() {
        applyControls();

        applyFriction();
        applyGravity();
        applyVelocity();
        constrainToWindow();
    }

    void render() {
        p.imageMode(p.CENTER);
        p.image(sprite, pos.x, pos.y);
    }

    private void applyControls() {
        if(IN_LEFT) {
            vel.x = -6;
        }
        if(IN_RIGHT) {
            vel.x = 6;
        }
        if(IN_UP) {
        }
    }

    private void constrainToWindow() {
        pos.x = constrain(pos.x, width / 2F, p.width - width / 2F);
        pos.y = constrain(pos.y, height / 2F, p.height - height / 2F);

        if(pos.x == width / 2F && vel.x < 0) vel.x = 0;
        if(pos.x == p.width - width / 2F && vel.x > 0) vel.x = 0;
        if(pos.y == -height / 2F && vel.y < 0) vel.y = 0;
        //if(pos.y >= p.height + height) dead = true;
    }
}
