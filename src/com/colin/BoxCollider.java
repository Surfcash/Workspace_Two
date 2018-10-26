package com.colin;

import processing.core.PVector;

import static java.lang.Math.abs;

class BoxCollider {

    PVector pos;
    BoxCollider previous;
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

    boolean collidesBox(BoxCollider collider) {
        return ((right > collider.left) && (left < collider.right) && (bottom > collider.top) && (top < collider.bottom));
    }

    boolean collidesPoint(PVector collider) {
        return ((right > collider.x) && (left < collider.x) && (bottom > collider.y) && (top < collider.y));
    }

    void deltaFixCollidesBox(BoxCollider collider, PVector vel) {
        PVector deltaFix = new PVector(0, 0);
        if(right > collider.left && previous.right < collider.left) {
            deltaFix.x = collider.left - right;
        }
        if(left < collider.right && previous.left > collider.right) {
            deltaFix.x = collider.right - left;
        }

        if(bottom > collider.top && previous.bottom < collider.top) {
            deltaFix.y = collider.top - bottom;
        }
        if(top < collider.bottom && previous.top > collider.bottom) {
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

    void setPreviousPosition() {
        previous = new BoxCollider(pos, width, height);
    }
}
