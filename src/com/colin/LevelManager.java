package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

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
    }

    private void loadLevel() {
        switch(game.sceneManager.level) {
            case 1 : {
                player = new Player(new PVector(p.width / 2F, 0), p);
                break;
            }
        }
    }
}
