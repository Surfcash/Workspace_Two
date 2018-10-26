package com.colin;

import processing.core.PVector;

import static com.colin.MainApp.deltaTime;
import static processing.core.PApplet.sq;

class Entity extends BoxCollider {

    PVector vel = new PVector(0, 0);
    private PVector friction = new PVector(1F, 1F);
    private PVector gravity = new PVector(0, 2F);

    Entity() {
        super();
    }

    Entity(PVector position, float wide, float tall) {
        super(position, wide, tall);
    }

    void applyVelocity(PVector deltaVel) {
        if(!surfaceLeft && vel.x < 0) {
            pos.x += deltaVel.x;
        } else if(!surfaceRight && deltaVel.x > 0) {
            pos.x += deltaVel.x;
        }
        if(!surfaceBottom && deltaVel.y > 0) {
            pos.y += deltaVel.y;
        } else if(!surfaceTop && deltaVel.y < 0) {
            pos.y += deltaVel.y;
        }
        assignBounds();
    }

    void applyGravity() {
        if(!surfaceBottom) {
            vel.y += gravity.y * sq(deltaTime);
        }
    }

    void applyFriction() {
        if(surfaceBottom || surfaceTop) {
            if(vel.x >= friction.x * deltaTime) vel.x += -friction.x * deltaTime;
            else if (vel.x <= -friction.x * deltaTime) vel.x += friction.x * deltaTime;
            if(vel.x > -friction.x * deltaTime && vel.x < friction.x * deltaTime) vel.x = 0;
        }
    }
}
