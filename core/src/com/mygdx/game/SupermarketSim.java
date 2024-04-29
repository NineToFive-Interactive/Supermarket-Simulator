package com.mygdx.game;

import com.mygdx.game.utils.BaseGame;

public class SupermarketSim extends BaseGame {
	@Override
	public void create() {
		super.create();
		setActiveScreen(new SupermarketScreen());
	}
}
