package com.colin;

import processing.core.PApplet;

import static com.colin.MainApp.game;

class SceneManager {
    Scene scene, storedScene;
    private PApplet p;
    short level;

    SceneManager(PApplet parent) {
        p = parent;
        scene = new Scene(p);
        level = 0;
        title();
    }

    void update() {
        scene.update();
    }

    void render() {
        scene.render();
    }

    void title() {
        scene = new Scene(p);
        scene.labels.add(new Label("WORKSPACE TWO", p));
        if(level == 0) {
            scene.buttonManager.add("START");
        }
        else {
            scene.buttonManager.add("CONTINUE");
            scene.buttonManager.add("RESTART");
        }
        scene.buttonManager.add("OPTIONS");
        scene.buttonManager.add("QUIT");
    }

    void pause() {
        scene = new Scene(p);
        scene.labels.add(new Label("PAUSED", p));
        scene.buttonManager.width = 500;
        scene.buttonManager.add("RETURN");
        scene.buttonManager.add("OPTIONS");
        scene.buttonManager.add("QUIT TO TITLE");
    }

    void game() {
        scene = new Scene(p);
        scene.level = new LevelManager(p);
    }
}
