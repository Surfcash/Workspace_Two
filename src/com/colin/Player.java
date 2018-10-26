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

        jumpWait = jumpState = 3;

        assignBounds();
        p = parent;
    }

    void update() {
        setPreviousPosition();
        setSurfacesFalse();
        detectTileSurfaces();
        applySurfaces();
        detectWindowBounds();

        updateJumpWait();

        applyControls();
        applyFriction();
        applyGravity();
        handleVelocity();

        constrainToTiles();
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
        if(IN_LEFT && !surfaceLeft) {
            animationState = 0;
            vel.x = -8 * deltaTime;
        }
        if(IN_RIGHT && !surfaceRight) {
            animationState = 1;
            vel.x = 8 * deltaTime;
        }
        if(IN_UP && surfaceBottom && !surfaceTop) {
            if(jumpState >= jumpWait) {
                vel.y = -(1.75F + 26.25F * deltaTime);
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
        Tile[][] tiles = game.sceneManager.scene.level.tilemap.tiles;
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                if(tiles[i][j] != null) {
                    if(collidesBox(tiles[i][j])) {
                        System.out.println("\nCollided with " + i + ", " + j);
                        deltaFixCollidesBox(tiles[i][j], vel);
                    }
                }
            }
        }
    }

    private void handleVelocity() {
        PVector scrollAmount = new PVector(0, 0);
        PVector fixedVel = new PVector(0, 0);
        TileMap tilemap = game.sceneManager.scene.level.tilemap;

        if((pos.x < 0.4 * p.width && vel.x < 0) || (pos.x > 0.6 * p.width && vel.x > 0)) {
            scrollAmount.x = vel.x;
        }
        else {
            fixedVel.x = vel.x;
        }
        if((pos.y < 0.4 * p.height && vel.y < 0) || (pos.y > 0.6 * p.height && vel.y > 0)) {
            scrollAmount.y = -vel.y;
        }
        else {
            fixedVel.y = vel.y;
        }


        tilemap.scrollMap(scrollAmount);

        if(tilemap.currentScroll.x == tilemap.scrollMin.x || tilemap.currentScroll.x == tilemap.scrollMax.x) {
            fixedVel.x = vel.x;
        }
        if(tilemap.currentScroll.y == tilemap.scrollMin.y || tilemap.currentScroll.y == tilemap.scrollMax.y) {
            fixedVel.y = vel.y;
        }

        applyVelocity(fixedVel);
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

    private void constrainToWindow() {
        pos.x = constrain(pos.x, width / 2F, p.width - width / 2F);
        pos.y = constrain(pos.y, height / 2F, p.height - height / 2F);
    }
}
