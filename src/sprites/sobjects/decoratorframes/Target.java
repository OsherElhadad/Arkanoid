
/*
 * Osher Elhadad
 *
 * This file defines a target background.
 */

package sprites.sobjects.decoratorframes;

import biuoop.DrawSurface;
import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import sprites.shelpers.Sprite;

import java.awt.Color;

/**
 * This Target class has sprite (decorator), color, block and number of circles,
 * implements the Sprite interface.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Target implements Sprite {

    // The sprite is a decorator- abstract background.
    private Sprite sprite;

    // The color of the circles of the target.
    private Color color;

    // The block we draw around him the target.
    private Block block;

    // The number of circles of the target.
    private int numberOfCircles;

    // The default radius of the small circle.
    public static final int SMALL_CIRCLE_RADIUS = 60;

    // The default radius of the medium circle.
    public static final int MEDIUM_CIRCLE_RADIUS = 90;

    // The default radius of the big circle.
    public static final int BIG_CIRCLE_RADIUS = 120;

    /**
     * Target is the constructor method.
     * it defines the sprite (decorator), color, block and number of circles of the target.
     *
     * @param sprite is a decorator- abstract background.
     * @param color is the color of the circles of the target.
     * @param block is the block we draw around him the target.
     * @param numberOfCircles is the number of circles of the target.
     */
    public Target(Sprite sprite, Color color, Block block, int numberOfCircles) {
        this.sprite = sprite;
        this.color = color;
        this.block = block;
        this.numberOfCircles = numberOfCircles;
    }

    /**
     * getColor is a method that returns the color of the target circles.
     *
     * @return the color of the target circles.
     */
    public Color getColor() {
        return (this.color);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.sprite.drawOn(surface);
        surface.setColor(this.getColor());
        Point middle = new Point(this.block.getCollisionRectangle().getUp().middle().getX(),
                this.block.getCollisionRectangle().getLeft().middle().getY());

        // Draws the all circles.
        surface.drawCircle((int) middle.getX(), (int) middle.getY(), SMALL_CIRCLE_RADIUS);
        surface.drawCircle((int) middle.getX(), (int) middle.getY(), MEDIUM_CIRCLE_RADIUS);
        surface.drawCircle((int) middle.getX(), (int) middle.getY(), BIG_CIRCLE_RADIUS);

        // Draws the all lines.
        surface.drawLine((int) middle.getX(), (int) (middle.getY() + this.block.getCollisionRectangle().getWidth()),
                (int) middle.getX(), (int) (middle.getY()
                        + this.block.getCollisionRectangle().getWidth() + BIG_CIRCLE_RADIUS));
        surface.drawLine((int) middle.getX(), (int) (middle.getY() - this.block.getCollisionRectangle().getWidth()),
                (int) middle.getX(), (int) (middle.getY()
                        - this.block.getCollisionRectangle().getWidth() - BIG_CIRCLE_RADIUS));
        surface.drawLine((int) (middle.getX() + this.block.getCollisionRectangle().getWidth()), (int) middle.getY(),
                (int) (middle.getX() + this.block.getCollisionRectangle().getWidth()
                        + BIG_CIRCLE_RADIUS), (int) middle.getY());
        surface.drawLine((int) (middle.getX() - this.block.getCollisionRectangle().getWidth()), (int) middle.getY(),
                (int) (middle.getX() - this.block.getCollisionRectangle().getWidth()
                        - BIG_CIRCLE_RADIUS), (int) middle.getY());
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
