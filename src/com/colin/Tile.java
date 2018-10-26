package com.colin;

import processing.core.PVector;
import static com.colin.MainApp.*;

class Tile extends BoxCollider{
    private int size = 48;
    PVector originalPos;

    Tile(int x, int y) {
        super();
        super.pos = new PVector(x * size + size / 2F, window.y - (y * size) - size / 2F);
        super.width = size;
        super.height = size;
        assignBounds();
        originalPos = new PVector(pos.x, pos.y);
        System.out.println("Real position (" + pos.x + ", " + pos.y + ")");
    }
}
