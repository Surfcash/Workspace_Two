package com.colin;

import processing.core.PImage;

import java.util.ArrayList;

class SpriteManager {
    ArrayList<Sprite> sprites;

    SpriteManager() {

    }


    class Sprite {
        Sprite(String labelReference, PImage image) {
            label = labelReference;
            img = image;
        }
        String label;
        PImage img;
    }
}
