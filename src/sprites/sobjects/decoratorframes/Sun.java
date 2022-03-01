// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a sun background.
 */

package sprites.sobjects.decoratorframes;

import biuoop.DrawSurface;
import games.GameLevel;
import geometry.Point;
import sprites.shelpers.Sprite;

import java.awt.Color;

/**
 * This Sun class has sprite (decorator), colors of circles,
 * middle sun and the start and end points of the sunbeam,
 * implements the Sprite interface.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Sun implements Sprite {

    // The sprite is a decorator- abstract background.
    private Sprite sprite;

    // The color of the big circle of the sun.
    private Color bigColor;

    // The color of the middle circle of the sun.
    private Color middleColor;

    // The color of the small circle of the sun.
    private Color smallColor;

    // The middle point of the sun.
    private Point middleSun;

    // The start (left) point of the sunbeam.
    private Point startLine;

    // The end (right) point of the sunbeam.
    private Point endLine;

    // The default radius of the small circle.
    public static final int SMALL_CIRCLE_RADIUS = 50;

    // The default radius of the medium circle.
    public static final int MEDIUM_CIRCLE_RADIUS = 60;

    // The default radius of the big circle.
    public static final int BIG_CIRCLE_RADIUS = 70;

    // The default step between lines.
    public static final int LINES_STEP = 5;

    /**
     * Sun is the constructor method.
     * it defines the sprite (decorator), colors of circles,
     * middle sun and the start and end points of the sunbeam of the target.
     *
     * @param sprite is a decorator- abstract background.
     * @param smallColor is the color of the small circle of the sun.
     * @param middleColor is the color of the middle circle of the sun.
     * @param bigColor is the color of the big circle of the sun.
     * @param middleSun is the middle point of the sun.
     * @param startLine is the start (left) point of the sun.
     * @param endLine is the end (right) point of the sun.
     */
    public Sun(Sprite sprite, Color smallColor, Color middleColor, Color bigColor,
               Point middleSun, Point startLine, Point endLine) {
        this.sprite = sprite;
        this.smallColor = smallColor;
        this.middleColor = middleColor;
        this.bigColor = bigColor;
        this.middleSun = middleSun;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.sprite.drawOn(surface);

        // Draws the circles of the sun.
        surface.setColor(this.bigColor);
        surface.fillCircle((int) this.middleSun.getX(), (int) this.middleSun.getY(), BIG_CIRCLE_RADIUS);
        surface.setColor(this.middleColor);
        surface.fillCircle((int) this.middleSun.getX(), (int) this.middleSun.getY(), MEDIUM_CIRCLE_RADIUS);
        surface.setColor(this.smallColor);
        surface.fillCircle((int) this.middleSun.getX(), (int) this.middleSun.getY(), SMALL_CIRCLE_RADIUS);

        // Draws the sunbeams.
        for (int i = 0; i <= this.endLine.getX() - this.startLine.getX(); i += LINES_STEP) {
            surface.drawLine((int) this.middleSun.getX(), (int) this.middleSun.getY(),
                    (int) this.startLine.getX() + i, (int) this.startLine.getY());
        }
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
