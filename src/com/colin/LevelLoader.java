package com.colin;

import processing.core.PApplet;
import processing.core.PImage;

import static com.colin.MainApp.game;

abstract class LevelLoader {
    static TileMap levelLoader(String reference, PApplet p) {
        TileMap map;
        PImage level = game.spriteManager.getSprite("m_" + reference);
        if(level != null) {
            map = new TileMap(level.width, level.height, p);
        }
        else map = new TileMap(0, 0, p);
        if(level != null) {
            System.out.println("Loading tiles for " + reference + "...");
            for (int i = level.height; i >= 0; i--) {
                for (int j = 0; j < level.width; j++) {
                    int x = j;
                    int y = p.abs(i - level.height);
                    int pixelColor = level.get(j, i);
                    float alphaValue = p.alpha(pixelColor);

                    if(alphaValue != 0) {
                        System.out.println("(" + j + "x, " + (y - 1) + "y)");
                        map.tiles[x][y] = new Tile(j, y - 1);
                    }
                }
            }
            System.out.println("Finished.");
            return map;
        }
        else return null;
    }

}
