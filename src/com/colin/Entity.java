package com.colin;

import processing.core.PVector;

import static com.colin.MainApp.deltaTime;
import static java.lang.StrictMath.round;
import static processing.core.PApplet.sq;

class Entity extends BoxCollider {

    PVector vel = new PVector(0, 0);
    private PVector friction = new PVector(2F, 2F);
    private PVector gravity = new PVector(0, 2F);

    Entity() {
        super();
    }

    Entity(PVector position, float wide, float tall) {
        super(position, wide, tall);
    }

    void applyVelocity() {
        if(!surfaceLeft && vel.x < 0) {
            pos.x += vel.x;
        } else if(!surfaceRight && vel.x > 0) {
            pos.x += vel.x;
        }
        if(!surfaceBottom && vel.y > 0) {
            pos.y += vel.y;
        } else if(!surfaceTop && vel.y < 0) {
            pos.y += vel.y;
        }
        assignBounds();
    }

    void applyGravity() {
        if(!surfaceBottom) {
            vel.y += round(gravity.y * sq(deltaTime));
        }
    }

    void applyFriction() {
        float frictionX = round(friction.x * deltaTime);
        if(surfaceBottom || surfaceTop) {
            if(vel.x >= frictionX) vel.x += -frictionX;
            else if (vel.x <= -frictionX) vel.x += frictionX;
            if(vel.x > -frictionX && vel.x < frictionX) vel.x = 0;
        }
    }
}
