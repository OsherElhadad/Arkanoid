// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a frame.
 */

package sprites.sobjects.decoratorframes;

import biuoop.DrawSurface;
import sprites.shelpers.Sprite;
import geometry.Rectangle;
import games.GameLevel;

import java.awt.Color;

/**
 * This Frame class has rectangle, implements the Sprite interface.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Frame implements Sprite {

    // rectangle is the rectangle of the frame (has size, position and color).
    private Rectangle rectangle;

    /**
     * Frame is the constructor method.
     * it defines the rectangle of the Frame.
     *
     * @param rectangle is the rectangle of the frame.
     */
    public Frame(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * getRectangle is a method that returns the rectangle of this frame.
     *
     * @return the rectangle of this frame.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * getColor is a method that returns the color of the rectangle of the frame.
     *
     * @return the color of the frame.
     */
    public Color getColor() {
        return (this.getRectangle().getColor());
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getRectangle().getUpperLeft().getX(),
                (int) this.getRectangle().getUpperLeft().getY(),
                (int) this.getRectangle().getWidth(),
                (int) this.getRectangle().getHeight());
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel g) throws RuntimeException {

        /*
         * Throws a RuntimeException if the game is null.
         */
        if (g == null) {
            throw new RuntimeException("Can't get an empty game");
        }
        g.addSprite(this);
    }
}
