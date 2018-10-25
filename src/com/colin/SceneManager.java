package com.colin;

import processing.core.PApplet;

import static com.colin.MainApp.*;

class SceneManager {
    Scene scene, storedScene;
    private PApplet p;
    short level;

    SceneManager(PApplet parent) {
        p = parent;
        scene = new Scene(p);
        title();
    }

    void update() {
        sceneControls();
        scene.update();
    }

    void render() {
        scene.render();
    }

    private void sceneControls() {
        if(scene.level != null) {
            if(IN_ESCAPE) {
                storedScene = scene;
                pause();
            }
        }
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

    private void pause() {
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
