package com.colin;

import processing.core.PApplet;
import processing.core.PVector;

import static com.colin.MainApp.*;
import static processing.core.PApplet.constrain;

class Player extends Entity{
    private SpriteSheet spritesheet;
    private short animationState = 1;
    private float jumpWait, jumpState;
    private PApplet p;

    Player(PVector position, PApplet parent) {
        super();
        super.pos = position;
        spritesheet = new SpriteSheet(game.spriteManager.getSprite("e_player"), 2, 1);
        super.width = spritesheet.size.x - 46;
        super.height = spritesheet.size.y;

        jumpWait = jumpState = 2;

        assignBounds();
        p = parent;
    }

    void update() {
        setSurfacesFalse();
        detectTileSurfaces();
        applySurfaces();
        detectWindowBounds();

        updateJumpWait();

        applyControls();
        applyFriction();
        applyGravity();
        applyVelocity();

        constrainToTiles();
        scrollScreen();
        constrainToWindow();
    }

    void render() {
        p.imageMode(p.CENTER);
        p.image(spritesheet.sprites[animationState], pos.x, pos.y);
    }

    private void updateJumpWait() {
        if(surfaceBottom) {
            if(jumpState < jumpWait) jumpState += 1 * deltaTime;
        }
    }

    private void applyControls() {
        if(IN_LEFT) {
            if(!surfaceLeft) {
                animationState = 0;
                vel.x = round(-8 * deltaTime);
            }
            else {
                if(vel.y > 0) {
                    if(jumpState < jumpWait) jumpState += 1 * deltaTime;
                    vel.y = 0;
                }
            }
        }
        if(IN_RIGHT) {
            if(!surfaceRight) {
                animationState = 1;
                vel.x = round(8 * deltaTime);
            }
            else {
                if(vel.y > 0)  {
                    if(jumpState < jumpWait) jumpState += 1 * deltaTime;
                    vel.y = 0;
                }
            }
        }
        if(IN_UP && !surfaceTop) {
            if(jumpState >= jumpWait) {
                vel.y = round(-(1.75F + 26.25F * deltaTime));
                jumpState = 0;
            }
        }
    }

    private void detectTileSurfaces() {
        Tile[][] tiles = game.sceneManager.scene.level.tilemap.tiles;
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                if(tiles[i][j] != null) {
                    detectSurfaces(tiles[i][j]);
                }
            }
        }
    }

    private void detectWindowBounds() {
        if(pos.x == width / 2F && vel.x < 0) {
            vel.x = 0;
        }
        if(pos.x == p.width - width / 2F && vel.x > 0) {
            vel.x = 0;
        }
        if(pos.y == height / 2F && vel.y < 0) {
            vel.y = 0;
        }
        if(pos.y == p.height - height / 2F && vel.y > 0) {
            surfaceBottom = true;
            vel.y = 0;
        }
    }

    private void constrainToTiles() {
        boolean wasCollision;
        Tile[][] tiles = game.sceneManager.scene.level.tilemap.tiles;
        int looped = 0;
        do {
            looped++;
            wasCollision = false;
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (tiles[i][j] != null) {
                        if (collidesBox(tiles[i][j])) {
                            System.out.println("\nCollided with " + i + ", " + j);
                            deltaFixCollidesBox(tiles[i][j], vel);
                            wasCollision = true;
                        }
                    }
                }
            }
        } while(wasCollision && looped < 3);
        if(looped >= 3) {
            p.exit();
        }
    }

    private void applySurfaces() {
        if(surfaceLeft && vel.x < 0) {
            vel.x = 0;
        }
        if(surfaceRight && vel.x > 0) {
            vel.x = 0;
        }
        if(surfaceBottom && vel.y > 0) {
            vel.y = 0;
        }
        if(surfaceTop && vel.y < 0) {
            vel.y = 0;
        }
    }

    private void scrollScreen() {
        PVector scrollValue = new PVector(0, 0);
        TileMap tilemap = game.sceneManager.scene.level.tilemap;
        if((pos.x > p.width * 0.65 && tilemap.currentScroll.x != tilemap.scrollMax.x && vel.x > 0) || (pos.x < p.width * 0.35 && tilemap.currentScroll.x != tilemap.scrollMin.x && vel.x < 0)) {
            scrollValue.x = vel.x;
        }
        if((pos.y > p.height * 0.65 && tilemap.currentScroll.y != tilemap.scrollMin.y && vel.y > 0) || (pos.y < p.height * 0.35 && tilemap.currentScroll.y != tilemap.scrollMax.y && vel.y < 0)) {
            scrollValue.y = vel.y;
        }

        if (scrollValue.x != 0 || scrollValue.y != 0) {
            subPos(tilemap.scrollMap(scrollValue));
        }
    }

    private void constrainToWindow() {
        pos.x = constrain(pos.x, width / 2F, p.width - width / 2F);
        pos.y = constrain(pos.y, -height / 2F, p.height - height / 2F);
    }
}
