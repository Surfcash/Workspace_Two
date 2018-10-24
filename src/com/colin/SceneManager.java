package com.colin;

import processing.core.PApplet;

class SceneManager {
    private Scene scene, storedScene;
    private PApplet p;

    SceneManager(PApplet parent) {
        p = parent;
        scene = new Scene(p);
        pause();
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
        scene.buttonManager.add("START");
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
}
