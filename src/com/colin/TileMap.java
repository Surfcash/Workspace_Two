package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.constrain;

class TileMap {
    Tile[][] tiles;
    PApplet p;
    PVector mapSize, tileMapSize, scrollMin, scrollMax, currentScroll;

    TileMap(int width, int height, PApplet parent) {
        p = parent;
        tiles = new Tile[width][height];
        tileMapSize = new PVector(width, height);
        mapSize = new PVector(tileMapSize.x * 48, tileMapSize.y * 48);
        scrollMin = new PVector (0, 0);
        scrollMax = new PVector(mapSize.x - p.width / 2F, mapSize.y - p.height / 2F);
        currentScroll = new PVector(scrollMin.x, scrollMin.y);
    }

    void render() {
        for(int i = 0; i < tileMapSize.x; i++) {
            for(int j = 0; j < tileMapSize.y; j++) {
                if(tiles[i][j] != null) {
                    p.rectMode(p.CENTER);
                    p.fill(255);
                    p.noStroke();
                    p.rect(tiles[i][j].pos.x, tiles[i][j].pos.y, 48, 48);
                }
            }
        }
    }

    void scrollMap(PVector scroll) {
        currentScroll.x += scroll.x;
        currentScroll.y += scroll.y;
        constrainScroll();
        scrollTiles();
    }

    void constrainScroll() {
        currentScroll.x = constrain(currentScroll.x, scrollMin.x, scrollMax.x);
        currentScroll.y = constrain(currentScroll.y, scrollMin.y, scrollMax.y);
    }

    void scrollTiles() {
        for(int i = 0; i < tileMapSize.x; i++) {
            for(int j = 0; j < tileMapSize.y; j++) {
                if(tiles[i][j] != null) {
                    tiles[i][j].pos = new PVector (tiles[i][j].originalPos.x + -currentScroll.x, tiles[i][j].originalPos.y + currentScroll.y);
                    tiles[i][j].assignBounds();
                }
            }
        }
    }
}
