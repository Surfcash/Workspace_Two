package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static com.colin.MainApp.*;
import static processing.core.PApplet.constrain;

class Player extends Entity{
    private SpriteSheet spritesheet;
    private short animationState = 1;
    private float jumpWait, jumpState;
    private PApplet p;

    Player(PVector position, PApplet parent) {
        super();
        super.pos = position;
        spritesheet = new SpriteSheet(game.spriteManager.getSprite("e_player"), 2, 1);
        super.width = spritesheet.size.x - 46;
        super.height = spritesheet.size.y;

        jumpWait = jumpState = 3;

        assignBounds();
        p = parent;
    }

    void update() {
        ArrayList<BoxCollider> colliders = new ArrayList<>();

        detectSurfaces(colliders);
        detectWindowBounds();

        updateJumpWait();

        applyControls();
        applyFriction();
        applyGravity();
        applyVelocity();

        constrainToWindow();
    }

    void render() {
        p.imageMode(p.CENTER);
        p.image(spritesheet.sprites[animationState], pos.x, pos.y);
    }

    private void updateJumpWait() {
        if(surfaceBottom) {
            if(jumpState < jumpWait) jumpState += 1 * deltaTime;
        }
    }

    private void applyControls() {
        if(IN_LEFT) {
            animationState = 0;
            vel.x = -8 * deltaTime;
        }
        if(IN_RIGHT) {
            animationState = 1;
            vel.x = 8 * deltaTime;
        }
        if(IN_UP && surfaceBottom) {
            if(jumpState >= jumpWait) {
                vel.y = -(1.75F + 26.25F * deltaTime);
                jumpState = 0;
            }
        }
    }

    private void detectWindowBounds() {
        if(pos.x == width / 2F && vel.x < 0) vel.x = 0;
        if(pos.x == p.width - width / 2F && vel.x > 0) vel.x = 0;
        if(pos.y == -height / 2F && vel.y < 0) vel.y = 0;
        if(pos.y == p.height - height / 2F && vel.y > 0) {
            surfaceBottom = true;
            vel.y = 0;
        }
    }

    private void constrainToWindow() {
        pos.x = constrain(pos.x, width / 2F, p.width - width / 2F);
        pos.y = constrain(pos.y, height / 2F, p.height - height / 2F);
    }
}
