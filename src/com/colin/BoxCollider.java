package com.colin;

import processing.core.PVector;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

class BoxCollider {

    PVector pos;
    float width, height;
    float left, right, top, bottom;
    boolean surfaceLeft, surfaceRight, surfaceTop, surfaceBottom;

    BoxCollider() {
        surfaceLeft = surfaceRight = surfaceTop = surfaceBottom = false;
    }

    BoxCollider(PVector position, float wide, float tall) {
        pos = position;
        width = wide;
        height = tall;
        assignBounds();
        surfaceLeft = surfaceRight = surfaceTop = surfaceBottom = false;
    }

    private void addPos(PVector delta) {
        pos.add(delta);
        assignBounds();
    }

    void subPos(PVector delta) {
        pos.sub(delta);
        assignBounds();
    }

    boolean collidesBox(BoxCollider collider) {
        return ((right > collider.left) && (left < collider.right) && (bottom > collider.top) && (top < collider.bottom));
    }

    boolean collidesPoint(PVector collider) {
        return ((right > collider.x) && (left < collider.x) && (bottom > collider.y) && (top < collider.y));
    }

    void deltaFixCollidesBox(BoxCollider collider, PVector vel) {
        PVector deltaFix = new PVector(0, 0);
        if(right > collider.left && floor(right - vel.x) <= floor(collider.left)) {
            deltaFix.x = collider.left - right;
        } else if(left < collider.right && floor(left - vel.x) >= floor(collider.right)) {
            deltaFix.x = collider.right - left;
        }

        if(bottom > collider.top && floor(bottom - vel.y) <= floor(collider.top)) {
            deltaFix.y = collider.top - bottom;
        } else if(top < collider.bottom && floor(top - vel.y) >= floor(collider.bottom)) {
            deltaFix.y = collider.bottom - top;
        }

        if(abs(deltaFix.x) > abs(deltaFix.y)) {
            deltaFix.y = 0;
        } else if(abs(deltaFix.x) < abs(deltaFix.y)) {
            deltaFix.x = 0;
        }

        System.out.println("\nCOLLISION");
        System.out.println("LEFT: " + left + ", " + collider.right);
        System.out.println("RIGHT: " + right + ", " + collider.left);
        System.out.println("BOTTOM: " + bottom + ", " + collider.top);
        System.out.println("TOP: " + top + ", " + collider.bottom);

        addPos(deltaFix);

        System.out.println("\nFIXED");
        System.out.println("LEFT: " + left + ", " + collider.right);
        System.out.println("RIGHT: " + right + ", " + collider.left);
        System.out.println("BOTTOM: " + bottom + ", " + collider.top);
        System.out.println("TOP: " + top + ", " + collider.bottom);

        System.out.println("\nDELTA " + deltaFix.x + ", " + deltaFix.y);
        System.out.println("VEL " + vel.x + ", " + vel.y);
    }

    void detectSurfaces(BoxCollider collider) {
        if((right > collider.left) && (left < collider.right)) {
            if (bottom == collider.top) {
                surfaceBottom = true;
            } else if (top == collider.bottom) {
                surfaceTop = true;
            }
        }
        if((bottom > collider.top) && (top < collider.bottom)) {
            if (left == collider.right) {
                surfaceLeft = true;
            } else if (right == collider.left) {
                surfaceRight = true;
            }
        }
    }

    void setSurfacesFalse() {
        surfaceLeft = surfaceRight = surfaceTop = surfaceBottom = false;
    }


    void assignBounds() {
        left = pos.x - width / 2F;
        right = pos.x + width / 2F;
        top = pos.y - height / 2F;
        bottom = pos.y + height / 2F;
    }
}
