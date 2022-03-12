
/*
 * Osher Elhadad
 *
 * This file defines a Paddle that has rectangle, steps, keyboard and frame.
 */

package collisions.cobjects;

import biuoop.DrawSurface;

import biuoop.KeyboardSensor;
import collisions.chelpers.Collidable;
import geometry.Point;
import geometry.Rectangle;
import sprites.sobjects.Ball;
import sprites.sobjects.decoratorframes.Frame;
import sprites.shelpers.Sprite;
import sprites.movement.Velocity;
import games.GameLevel;

/**
 * This Paddle class has rectangle, keyboard, steps and frame values
 * that defines a Paddle,
 * implements the Sprite and Collidable interfaces.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class Paddle implements Sprite, Collidable {

    // The KeyboardSensor of the paddle.
    private KeyboardSensor keyboard;

    // The rectangle of the paddle.
    private Rectangle rectangle;

    // The frame of the paddle.
    private Frame frame;

    // The velocity of the paddle.
    private int steps;

    // The number of regions in the up line of the paddle.
    public static final int REGION = 5;

    // The start angle of the hit velocity of the paddle's left region.
    public static final int START_ANGLE = -60;

    // The difference between the angles of the hit velocity of the regions.
    public static final int ANGLE_STEPS = 30;

    /**
     * Paddle is the constructor method.
     * it creates a new KeyboardSensor, and sets the rectangle and the frame of the paddle.
     *
     * @param keyboard is used to create the KeyboardSensor of the paddle.
     * @param steps is the velocity of the paddle.
     * @param rectangle is the geometryPrimitives.Rectangle of the paddle.
     * @param frame is the Frame of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, int steps, Rectangle rectangle, Frame frame) {
        this.keyboard = keyboard;
        this.steps = steps;
        this.rectangle = rectangle;
        this.frame = frame;
    }

    /**
     * getRectangle is a method that return the rectangle of the paddle.
     *
     * @return the rectangle of this paddle.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * moveLeft is a method that moves the paddle left STEPS left steps
     * (until the left border of the frame).
     *
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() - this.steps
                 > this.frame.getRectangle().getUpperLeft().getX()) {
            this.rectangle.setUpperLeft(new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.steps,
                    this.getCollisionRectangle().getUpperLeft().getY()));
        } else {
            this.rectangle.setUpperLeft(new Point(this.frame.getRectangle().getUpperLeft().getX(),
                    this.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * moveRight is a method that moves the paddle right STEPS right steps
     * (until the right border of the frame).
     *
     */
    public void moveRight() {
        if (this.getCollisionRectangle().getUpperRight().getX()
                + this.steps < this.frame.getRectangle().getUpperRight().getX()) {
            this.rectangle.setUpperLeft(new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.steps,
                    this.getCollisionRectangle().getUpperLeft().getY()));
        } else {
            this.rectangle.setUpperLeft(new Point(this.frame.getRectangle().getUpperRight().getX()
                    - this.rectangle.getWidth(), this.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {

        Block block = new Block(this.getCollisionRectangle());
        block.drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(),
                this.rectangle.getHeight(), this.rectangle.getColor());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        // The speed is the square root of (dx*dx + dy*dy).
        double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));

        /*
         * Checks if the intersection point is in the left line,
         * then it reverses it's vertical velocity.
         */
        if (this.getCollisionRectangle().isInLeftLine(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        /*
         * Checks if the intersection point is in the right line,
         * then it reverses it's vertical velocity.
         */
        if (this.getCollisionRectangle().isInRightLine(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        /*
         * Checks if the intersection point is in the up line,
         * then it changes it's velocity by it's region on the upper puddle.
         */
        if (this.getCollisionRectangle().isInUpLine(collisionPoint)) {

            // The width of every region.
            double region = this.getCollisionRectangle().getWidth() / REGION;

            /*
             * A loop that checks in which region the collision point is in,
             * and changes it's velocity by it's region.
             */
            for (int i = 0; i < REGION; i++) {
                if (collisionPoint.getX() >= this.getCollisionRectangle().getUpperLeft().getX() + (i * region)
                        && collisionPoint.getX() <= this.getCollisionRectangle().getUpperLeft().getX()
                        + ((i + 1) * region)) {
                    newVelocity = Velocity.fromAngleAndSpeed(START_ANGLE + i * ANGLE_STEPS, speed);
                    break;
                }
            }
        }

        /*
         * Checks if the intersection point is in the down line,
         * then it reverses it's horizontal velocity.
         */
        if (this.getCollisionRectangle().isInDownLine(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * addToGame is a function that gets a game and adds it this paddle (sprite and collidable).
     *
     * @param g is the game we adds this paddle to.
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
}
