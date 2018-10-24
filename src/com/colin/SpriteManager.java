package com.colin;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;

class SpriteManager {
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private PApplet p;

    SpriteManager(PApplet parent) {
        p = parent;
        loadSpriteFolders();
    }

    private void loadSpritesFromFolder(String filePath, String prefix) {
        File folder = new File(p.sketchPath(filePath));
        File[] files = folder.listFiles();
        if (files != null) {
            for (File i : files) {
                String fileName = i.getName();
                int pos = fileName.lastIndexOf(".");
                fileName = pos > 0 ? fileName.substring(0, pos) : fileName;
                if (i.isFile()) {
                    sprites.add(new Sprite(p.loadImage(i.getPath()), prefix + fileName));
                    System.out.println(fileName);
                }
            }
        }
    }

    private void loadSpriteFolders() {
        sprites.clear();
        System.out.println("Loading Sprites..\n");
        //loadSpritesFromFolder("assets/sprites/tiles", "t_");
        //loadSpritesFromFolder("assets/sprites/particles", "p_");
        loadSpritesFromFolder("assets/sprites/entity", "e_");
        loadSpritesFromFolder("assets/sprites/scene", "s_");
        //loadSpritesFromFolder("assets/sprites/map", "m_");
        System.out.println("\nFinished.");
    }

    PImage getSprite(String reference) {
        for(Sprite i : sprites) {
            if(i.reference.equals(reference)) {
                return i.img;
            }
        }
        return null;
    }

    private class Sprite {
        PImage img;
        String reference;

        Sprite(PImage sprite, String reference) {
            this.img = sprite;
            this.reference = reference;
        }
    }
}
