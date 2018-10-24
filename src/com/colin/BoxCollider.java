package com.colin;

import processing.core.PVector;

import java.util.ArrayList;

class BoxCollider {

    PVector pos;
    float width, height;
    private float left, right, top, bottom;
    boolean surfaceLeft, surfaceRight, surfaceTop, surfaceBottom;

    BoxCollider() {}

    BoxCollider(PVector position, float wide, float tall) {
        pos = position;
        width = wide;
        height = tall;
        assignBounds();
        surfaceLeft = surfaceRight = surfaceTop = surfaceBottom = false;
    }

    boolean collidesBox(BoxCollider collider) {
        return ((right > collider.left) && (left < collider.right) && (bottom > collider.top) && (top < collider.bottom));
    }

    boolean collidesPoint(PVector collider) {
        return ((right > collider.x) && (left < collider.x) && (bottom > collider.y) && (top < collider.y));
    }

    PVector deltaFixCollidesBox(BoxCollider collider) {
        PVector deltaFix = new PVector(0, 0);
        if(right > collider.left && left < collider.left) {
            deltaFix.x = collider.left - right;
            surfaceRight = true;
        } else if(left < collider.right && right > collider.right) {
            surfaceLeft = true;
            deltaFix.x = collider.right - left;
        }

        if(bottom > collider.top && top < collider.top) {
            deltaFix.y = collider.top - bottom;
            surfaceBottom = true;
        } else if(top < collider.bottom && bottom > collider.bottom) {
            deltaFix.y = collider.bottom - top;
            surfaceTop = true;
        }

        return deltaFix;
    }

    void detectSurfaces(ArrayList<BoxCollider> colliders) {
        surfaceLeft = surfaceRight = surfaceTop = surfaceBottom = false;
        for(BoxCollider i : colliders) {
            if(left == i.right) {
                surfaceLeft = true;
                break;
            }
            if(right == i.left) {
                surfaceRight = true;
                break;
            }
            if(bottom == i.top) {
                surfaceBottom = true;
                break;
            }
            if(top == i.bottom) {
                surfaceTop = true;
                break;
            }
        }
    }

    void assignBounds() {
        left = pos.x - width / 2F;
        right = pos.x + width / 2F;
        top = pos.y - height / 2F;
        bottom = pos.y + height / 2F;
    }
}
