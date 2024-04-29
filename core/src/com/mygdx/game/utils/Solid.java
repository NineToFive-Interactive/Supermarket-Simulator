package com.mygdx.game.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Solid extends BaseActor {
    public Solid(float posX, float posY, float width, float height, Stage stage) {
        super(posX, posY, stage);

        setSize(width, height);
        setBoundaryRectangle();
    }
}
