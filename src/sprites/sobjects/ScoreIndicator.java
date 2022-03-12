
/*
 * Osher Elhadad
 *
 * This file defines a ScoreIndicator that has rectangle, level name, lives counter and score counter.
 */

package sprites.sobjects;

import biuoop.DrawSurface;
import geometry.Rectangle;
import sprites.shelpers.Sprite;
import listeners.lhelpers.Counter;
import games.GameLevel;

import java.awt.Color;

/**
 * This ScoreIndicator class has rectangle, level name, lives counter,
 * and score counter values that defines a ScoreIndicator,
 * implements the Sprite interfaces.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class ScoreIndicator implements Sprite {

    // The name of the specified name.
    private String levelName;

    // The rectangle of the score indicator.
    private Rectangle rectangle;

    // score is the current score counter.
    private Counter score;

    // lives is the current lives counter remain.
    private Counter lives;

    /**
     * ScoreIndicator is the constructor method.
     * it sets the rectangle of the ScoreIndicator.
     *
     * @param rectangle is the geometryPrimitives.Rectangle of the ScoreIndicator.
     * @param score is the current score counter of the ScoreIndicator.
     * @param lives is the lives remain in the game.
     * @param levelName is the name of this level.
     */
    public ScoreIndicator(Rectangle rectangle, Counter score, Counter lives, String levelName) {
        this.rectangle = rectangle;
        this.score = score;
        this.lives = lives;
        this.levelName = levelName;
    }

    /**
     * getRectangle is a method that returns the rectangle of the ScoreIndicator.
     *
     * @return the rectangle of this ScoreIndicator.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * getScore is a method that returns the score of the ScoreIndicator.
     *
     * @return the score of this sprites.spriteObjects.ScoreIndicator.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * getLives is a method that returns the lives remained.
     *
     * @return the lives remained.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * getLevelName is a method that returns the name of this level.
     *
     * @return the name of this level.
     */
    public String getLevelName() {
        return this.levelName;
    }

    /**
     * getColor is method that returns the color of the ScoreIndicator's rectangle.
     *
     * @return the color of the ScoreIndicator.
     */
    public Color getColor() {
        return this.getRectangle().getColor();
    }

    @Override
    public void drawOn(DrawSurface surface) {

        // Draws the rectangle with his color on surface.
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getRectangle().getUpperLeft().getX(),
                (int) this.getRectangle().getUpperLeft().getY(),
                (int) this.getRectangle().getWidth(),
                (int) this.getRectangle().getHeight());

        // Draws a border around the rectangle.
        surface.setColor(this.getRectangle().getBorderColor());
        surface.drawRectangle((int) this.getRectangle().getUpperLeft().getX(),
                (int) this.getRectangle().getUpperLeft().getY(),
                (int) this.getRectangle().getWidth(),
                (int) this.getRectangle().getHeight());

        // Draws the score number in the middle of the rectangle.
        surface.drawText((int) ((this.getRectangle().getWidth() / 2) - 2 * this.getRectangle().getHeight()),
                (int) (this.getRectangle().getUpperLeft().getY() + this.getRectangle().getHeight() - 4),
                "Score: " + this.getScore().getValue(), (int) this.getRectangle().getHeight());
        surface.drawText((int) ((this.getRectangle().getWidth() / 5) - this.getRectangle().getHeight()),
                (int) (this.getRectangle().getUpperLeft().getY() + this.getRectangle().getHeight() - 4),
                "Lives: " + this.getLives().getValue(), (int) this.getRectangle().getHeight());
        surface.drawText((int) ((this.getRectangle().getWidth() * 2 / 3) - this.getRectangle().getHeight()),
                (int) (this.getRectangle().getUpperLeft().getY() + this.getRectangle().getHeight() - 4),
                "Level Name: " + this.getLevelName(), (int) this.getRectangle().getHeight());

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
