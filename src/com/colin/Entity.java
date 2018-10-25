package com.colin;

import processing.core.PVector;

class Entity extends BoxCollider {

    PVector vel = new PVector(0, 0);
    private PVector friction = new PVector(3F, 3F);
    private PVector gravity = new PVector(0, 3F);

    Entity() {}

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
            vel.add(gravity);
        }
    }

    void applyFriction() {
        if(surfaceLeft || surfaceRight) {
            if(vel.y > 0) {
                if(vel.y + -friction.y >= 0) {
                    vel.y += -friction.y;
                }
                else {
                    vel.y = 0;
                }
            }
            else if(vel.y < 0) {
                if(vel.y + friction.y <= 0) {
                    vel.y += friction.y;
                }
                else {
                    vel.y = 0;
                }
            }
        }
        else if(surfaceBottom || surfaceTop) {
            if(vel.x > 0) {
                if(vel.x + -friction.x >= 0) {
                    vel.x += -friction.x;
                }
                else {
                    vel.x = 0;
                }
            }
            else if(vel.x < 0) {
                if (vel.x + friction.x <= 0) {
                    vel.x += friction.x;
                }
                else {
                    vel.x =0;
                }
            }
        }
    }
}
