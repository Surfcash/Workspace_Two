package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static com.colin.LevelLoader.levelLoader;
import static com.colin.MainApp.game;

class LevelManager {
    Player player;
    TileMap tilemap;
    PApplet p;

    LevelManager(PApplet parent) {
        p = parent;
        loadLevel();
    }

    void update() {
        if(player != null) {
            player.update();
        }
    }

    void render() {
        if(player != null) {
            player.render();
        }
        tilemap.render();
    }

    private void loadLevel() {
        switch(game.sceneManager.level) {
            case 1 : {
                tilemap = levelLoader("1", p);
                player = new Player(new PVector( 100,  p.height - 500), p);
                break;
            }
            default : {
                break;
            }
        }
    }
}
