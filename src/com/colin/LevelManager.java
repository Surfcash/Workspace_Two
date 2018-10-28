package com.colin;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static com.colin.LevelLoader.levelLoader;
import static com.colin.MainApp.game;

class LevelManager {
    Player player;
    private PImage foreground, background;
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
        renderBackground();

        if(player != null) {
            player.render();
        }
        //tilemap.render();

        renderForeground();
    }

    private void loadLevel() {
        switch(game.sceneManager.level) {
            case 1 : {
                foreground = game.spriteManager.getSprite("s_level_one_foreground");
                background = game.spriteManager.getSprite("s_level_one_background");
                tilemap = levelLoader("1", p);
                player = new Player(new PVector( 100,  p.height - 500), p);
                break;
            }
            default : {
                break;
            }
        }
    }

    private void renderForeground() {
        if(background!= null) {
            p.imageMode(p.CORNER);
            p.image(foreground, -tilemap.currentScroll.x, tilemap.currentScroll.y - tilemap.scrollMax.y);
        }
    }

    private void renderBackground() {
        if(background!= null) {
            p.imageMode(p.CORNER);
            p.image(background, -tilemap.currentScroll.x, tilemap.currentScroll.y - tilemap.scrollMax.y);
        }
    }
}
