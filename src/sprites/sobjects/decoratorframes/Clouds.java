// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a clouds background.
 */

package sprites.sobjects.decoratorframes;

import biuoop.DrawSurface;
import games.GameLevel;
import geometry.Point;
import sprites.shelpers.Sprite;
import sprites.sobjects.Ball;

import java.awt.Color;
import java.util.List;

/**
 * This Clouds class has sprite (decorator), color of shadow lines,
 * list of the circles, gap between the shadow lines
 * and the start and end points of the shadow lines,
 * implements the Sprite interface.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Clouds implements Sprite {

    // The sprite is a decorator- abstract background.
    private Sprite sprite;

    // The circles of the cloud.
    private List<Ball> circles;

    // The start left point of the shadow lines.
    private Point startLine;

    // The end right point of the shadow lines.
    private Point endLine;

    // The gap between the shadow lines.
    private double linesGap;

    // The color of the shadow lines of the clouds.
    private Color lineColor;

    // The default number of shadow lines.
    public static final int NUMBER_OF_SHADOW_LINES = 10;

    /**
     * Clouds is the constructor method.
     * it defines the sprite (decorator), color of shadow lines,
     * list of the circles, gap between the shadow lines
     * and the start and end points of the shadow lines.
     *
     * @param sprite is a decorator- abstract background.
     * @param circles is the circles of the cloud.
     * @param startLine is the start left point of the shadow lines.
     * @param endLine is the end right point of the shadow lines.
     * @param linesGap is the gap between the shadow lines.
     * @param lineColor is the color of the shadow lines of the clouds.
     */
    public Clouds(Sprite sprite, List<Ball> circles, Point startLine,
                  Point endLine, double linesGap, Color lineColor) {
        this.sprite = sprite;
        this.circles = circles;
        this.startLine = startLine;
        this.endLine = endLine;
        this.linesGap = linesGap;
        this.lineColor = lineColor;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.sprite.drawOn(surface);
        surface.setColor(this.lineColor);

        /*
         * A loop that draws the all shadow lines,
         * from the startLine from left to the endLine from right,
         * with gap of 10 between two lines.
         */
        for (int i = 1; i <= NUMBER_OF_SHADOW_LINES * this.linesGap; i += this.linesGap) {
            surface.drawLine((int) this.startLine.getX() + i, (int) this.startLine.getY(),
                    (int) this.endLine.getX() + i, (int) this.endLine.getY());
        }

        /*
         * A loop that draws the all circles of the clouds.
         */
        for (Ball circle : this.circles) {
            circle.drawOn(surface);
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
