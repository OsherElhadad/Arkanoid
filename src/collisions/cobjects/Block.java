
/*
 * Osher Elhadad
 *
 * This file defines a Block that has a Rectangle.
 */

package collisions.cobjects;

import biuoop.DrawSurface;
import collisions.chelpers.Collidable;
import geometry.Point;
import geometry.Rectangle;
import sprites.sobjects.Ball;
import sprites.shelpers.Sprite;
import listeners.lhelpers.HitListener;
import listeners.lhelpers.HitNotifier;
import sprites.movement.Velocity;
import games.GameLevel;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * This Block class has rectangle value that defines a Block,
 * implements the Sprite, HitNotifier and Collidable interfaces.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // The rectangle of the block.
    private Rectangle rectangle;

    // The list of hit listeners.
    private List<HitListener> hitListeners;

    /**
     * Block is the constructor method.
     * it sets the rectangle of the block, and initialize the hitListeners list.
     *
     * @param rectangle is the Rectangle of the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * getColor is method that returns the color of the block's rectangle.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.getCollisionRectangle().getColor();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public void drawOn(DrawSurface surface) {

        // Draws the block with his color on surface.
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());

        // Draws border around the rectangle.
        surface.setColor(this.getCollisionRectangle().getBorderColor());
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() { }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        /*
         * Checks if the intersection point is in the up/down/right/left line,
         * then it reverses it's horizontal/vertical velocity.
         */
        if (currentVelocity.getDy() > 0 && this.rectangle.isInUpLine(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        } else if (currentVelocity.getDy() < 0 && this.rectangle.isInDownLine(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        } else if (currentVelocity.getDx() > 0 && this.rectangle.isInLeftLine(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        } else if (currentVelocity.getDx() < 0 && this.rectangle.isInRightLine(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        // Notifies the whole listeners there was a hit.
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * addToGame is a function that gets a game and adds it this block (sprite and collidable).
     *
     * @param g is the game we adds this block to.
     * @throws RuntimeException if the game is null.
     */
    public void addToGame(GameLevel g) throws RuntimeException {

        /*
         * Throws a RuntimeException if the game is null.
         */
        if (g == null) {
            throw new RuntimeException("Can't get an empty game");
        }
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame is a function that gets a game and removes from it this block (sprite and collidable).
     *
     * @param gameLevel is the game we removes this block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notifyHit is method that notify all listeners about a hit event.
     *
     * @param hitter is the ball hit the block.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        /*
         * Notify all listeners about a hit event.
         */
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
