
/*
 * Osher Elhadad
 *
 * This file defines a Sprite interface.
 */

package sprites.shelpers;

import biuoop.DrawSurface;
import games.GameLevel;

/**
 * This Sprite class has draw on method and time passed method.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public interface Sprite {

    /**
     * drawOn is a method from the Sprite interface,
     * it draws the sprite on the surface.
     *
     * @param d the DrawSurface we want to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed is a method from the Sprite interface,
     * it notify the sprite that time has passed.
     *
     */
    void timePassed();

    /**
     * addToGame is a function that gets a game and adds it this sprite.
     *
     * @param g is the game we adds this sprite to.
     * @throws RuntimeException if the game is null.
     */
    void addToGame(GameLevel g) throws RuntimeException;
}
