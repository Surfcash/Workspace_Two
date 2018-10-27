package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.constrain;

class TileMap {
    Tile[][] tiles;
    PApplet p;
    PVector scrollMin, scrollMax, currentScroll;
    private PVector mapSize, tileMapSize;

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

    PVector scrollMap(PVector scroll) {
        PVector constrainedScroll = new PVector(0, 0);

        if(currentScroll.x + scroll.x > scrollMax.x) {
            constrainedScroll.x = scrollMax.x - currentScroll.x;
        } else if(currentScroll.x + scroll.x < scrollMin.x) {
            constrainedScroll.x = -currentScroll.x;
        } else {
            constrainedScroll.x = scroll.x;
        }

        if(currentScroll.y - scroll.y > scrollMax.y) {
            constrainedScroll.y = -(scrollMax.y - currentScroll.y);
        } else if(currentScroll.y - scroll.y < scrollMin.y) {
            constrainedScroll.y = currentScroll.y;
        } else {
            constrainedScroll.y = scroll.y;
        }

        System.out.println("\nAttempting to scroll " + constrainedScroll.x + ", " + constrainedScroll.y);
        System.out.println("X: " + currentScroll.x + " (" + scrollMin.x + ", " + scrollMax.x + ")");
        System.out.println("Y: " + currentScroll.y + " (" + scrollMin.y + ", " + scrollMax.y + ")");
        currentScroll.x += constrainedScroll.x;
        currentScroll.y -= constrainedScroll.y;
        scrollTiles();

        return constrainedScroll;
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
