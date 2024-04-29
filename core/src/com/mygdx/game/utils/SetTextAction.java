package com.mygdx.game.utils;

import com.badlogic.gdx.scenes.scene2d.Action;

public class SetTextAction extends Action {
    protected String textToDisplay;

    public SetTextAction(String text) {
        textToDisplay = text;
    }
    @Override
    public boolean act(float v) {
        DialogBox db = (DialogBox)target;
        db.setText( textToDisplay );
        return true;
    }
}
