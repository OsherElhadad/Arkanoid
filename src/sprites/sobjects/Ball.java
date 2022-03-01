// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a Ball that has a position, size, color, frame, gameEnvironment and velocity.
 */

package sprites.sobjects;

import biuoop.DrawSurface;
import sprites.shelpers.Sprite;
import geometry.Point;
import sprites.movement.Velocity;
import collisions.chelpers.GameEnvironment;
import geometry.Rectangle;
import geometry.Line;
import collisions.chelpers.Collidable;
import collisions.chelpers.CollisionInfo;
import games.GameLevel;
import sprites.sobjects.decoratorframes.Frame;

import java.awt.Color;

/**
 * This Ball class has center point, radius, color, frame and velocity values
 * that defines a Ball, implements the Sprite interface.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class Ball implements Sprite {

    // The location of the ball.
    private Point center;

    // The size of the ball.
    private int radius;

    // The color of the ball.
    private java.awt.Color color;

    // The velocity of the ball.
    private Velocity velocity;

    // The frame of the ball (his borders).
    private Frame frame;

    // The game environment of the ball.
    private GameEnvironment gameEnvironment;

    // The color of the ball's border.
    private Color borderColor = Color.BLACK;

    // The default width of the screen.
    public static final int WIDTH = 200;

    // The default height of the screen.
    public static final int HEIGHT = 200;

    // The minimum radius possible.
    public static final int MIN_RADIUS = 1;

    // The maximum times we  change the ball's velocity (above the ball is stuck).
    public static final int MAX_HIT_TRIES = 4;

    /**
     * Ball is the constructor method.
     * it defines the center point, radius, color, frame and velocity (0) values of the Ball.
     *
     * @param center is the center point of the ball.
     * @param r is the radius of the ball.
     * @param color is the color of the ball.
     * @param frame is the frame of the ball.
     * @param gameEnvironment is the game environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, Frame frame, GameEnvironment gameEnvironment) {
        this.center = center;

        /*
         * Checks if the radius is 0 or negative,
         * then it change the radius to be 1.
         */
        if (r <= 0) {
            r = MIN_RADIUS;
        }

        /*
         * It change the radius to be the max,
         * if the size of the ball is not inside the frame borders.
         */
        if ((2 * r >= frame.getRectangle().getUpperRight().getX()
                - frame.getRectangle().getUpperLeft().getX())
                || (2 * r >= frame.getRectangle().getLowerLeft().getY()
                - frame.getRectangle().getUpperLeft().getY())) {
            r = (int) Math.min((frame.getRectangle().getUpperRight().getX()
                            - frame.getRectangle().getUpperLeft().getX()) / 2,
                    (frame.getRectangle().getLowerLeft().getY()
                            - frame.getRectangle().getUpperLeft().getY()) / 2) - 1;
        }
        this.radius = r;
        this.color = color;
        this.frame = frame;
        this.gameEnvironment = gameEnvironment;

        // Sets as default the velocity to be (0,0).
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Ball is the constructor method.
     * it defines the center point, radius, color,
     * frame (the default screen), game environment is null and velocity (0) values of the Ball.
     *
     * @param center is the center point of the ball.
     * @param r is the radius of the ball.
     * @param color is the color of the ball.
     */
      public Ball(Point center, int r, java.awt.Color color) {
          this (center, r, color,
                  new Frame(new Rectangle(new Point(0, 0), WIDTH, HEIGHT, null)), null);
          this.borderColor = color;
    }

    /**
     * Ball is the constructor method.
     * it defines the center point, radius, color,
     * frame (the default screen), game environment is null and velocity (0) values of the Ball.
     *
     * @param x is the x value of the center point of the ball.
     * @param y is the y value of the center point of the ball.
     * @param r is the radius of the ball.
     * @param color is the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this (new Point(x, y), r, color,
                new Frame(new Rectangle(new Point(0, 0), WIDTH, HEIGHT, null)), null);
        this.borderColor = color;
    }

    /**
     * Ball is the constructor method.
     * it defines the center point, radius, color, frame and velocity values of the Ball.
     * The game environment is null.
     *
     * @param x is the x value of the center point of the ball.
     * @param y is the y value of the center point of the ball.
     * @param r is the radius of the ball.
     * @param color is the color of the ball.
     * @param frame is the frame of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, Frame frame) {
        this (new Point(x, y), r, color, frame, null);
    }

    /**
     * getX is a method that returns the x value of the center point of the ball.
     *
     * @return the x value of the center point of the ball (integer).
     */
    public int getX() {
        return ((int) this.center.getX());
    }

    /**
     * getY is a method that returns the y value of the center point of the ball.
     *
     * @return the y value of the center point of the ball (integer).
     */
    public int getY() {
        return ((int) this.center.getY());
    }

    /**
     * getDx is a method that returns the dx value of the velocity of the ball.
     *
     * @return the dx value of the velocity of the ball.
     */
    public double getDx() {
        return (this.getVelocity().getDx());
    }

    /**
     * getDy is a method that returns the dy value of the velocity of the ball.
     *
     * @return the dy value of the velocity of the ball.
     */
    public double getDy() {
        return (this.getVelocity().getDy());
    }

    /**
     * getCenter is a method that returns the center point of the ball.
     *
     * @return the center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * getSize is a method that returns the size value of the ball.
     *
     * @return the size value of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor is a method that returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * getFrame is a method that returns the frame of the ball.
     *
     * @return the frame of the ball.
     */
    public Frame getFrame() {
        return this.frame;
    }


    /**
     * getVelocity is a method that returns the velocity value of the ball.
     *
     * @return the velocity value of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getGameEnvironment is a method that returns the gameEnvironment value of the ball.
     *
     * @return the gameEnvironment value of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * setVelocity is a method that sets the velocity value of the ball.
     *
     * @param v the velocity value we want to set for the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setCenter is a method that sets the center point of the ball.
     *
     * @param c the center point we want to set for the ball.
     */
    public void setCenter(Point c) {
        this.center = c;
    }

    /**
     * setFrame is a method that sets the frame of the ball.
     *
     * @param frame1 the frame value we want to set for the ball.
     */
    public void setFrame(Frame frame1) {
        this.frame = frame1;
    }

    /**
     * setGameEnvironment is a method that sets the gameEnvironment of the ball.
     *
     * @param g the gameEnvironment value we want to set for the ball.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * setVelocity is a method that sets the velocity value of the ball.
     *
     * @param dx the delta x value we want to set for the ball.
     * @param dy the delta y value we want to set for the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {

        // Draws the ball with his color on surface.
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());

        // Draws a black circle around the ball.
        surface.setColor(this.borderColor);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * getTrajectory is a method that calculates the full trajectory of the ball
     * (from his center to the border of the ball).
     *
     * @return the full trajectory of the ball.
     */
    public Line getTrajectory() {

        // Initialize the end point of the trajectory to be the center of the ball.
        Point end = this.velocity.applyToPoint(this.center);

        /*
         * A loop that calculate every time the next point with the ball velocity,
         * until we arrived the end of the frame.
         */
        while (this.frame.getRectangle().isPointInRectangle(end)) {
            end = this.velocity.applyToPoint(end);
        }
        end = this.velocity.applyToPoint(end);
        end = this.velocity.applyToPoint(end);
        end = this.velocity.applyToPoint(end);

        // Returns the trajectory line with the ball's center until the end of the frame.
        return (new Line(this.center, end));
    }

    /**
     * moveOneStep is a method that moves the ball one step with it's velocity.
     * (without getting inside collidables).
     *
     */
    public void moveOneStep() {

        /*
         * Checks if the velocity is 0,
         * then the ball doesn't move.
         */
        if (this.getVelocity().getDx() == 0 && this.getVelocity().getDy() == 0) {
            return;
        }

        // opposite is the opposite velocity of this ball's velocity.
        Velocity opposite = new Velocity(-this.getDx(), -this.getDy());

        // shortTrajectory is a line from the center of the ball to it's next position (by it's velocity).
        Line shortTrajectory = new Line(this.getCenter(), this.getVelocity().applyToPoint(this.getCenter()));

        // shortClosestCollision gets the closest collision point and collidable object.
        CollisionInfo shortClosestCollision = this.getGameEnvironment().getClosestCollision(shortTrajectory);

        /*
         * A loop that check every time if there is a collision,
         * then it change it's velocity by hit function,
         * and if the ball is stuck (4 times that it hit without move),
         * then it turns the ball back (opposite velocity).
         */
        int count = 0;
        while (shortClosestCollision != null) {

            /*
             * if the ball is stuck (4 times that it hit without move),
             * then it turns the ball back (opposite velocity).
             */
            if (count >= MAX_HIT_TRIES) {
                this.setVelocity(opposite);
                break;
            }

            // shortCollisionBlock is the collision object that intersect with trajectory.
            Collidable shortCollisionBlock = shortClosestCollision.collisionObject();

            /*
             * if the ball is trying to move inside the collision object,
             *  then it change it velocity with hit function.
             */
            this.setVelocity(shortCollisionBlock.hit(this, shortClosestCollision.collisionPoint(),
                    this.getVelocity()));

            // checks the trajectory and the collision again and counts this change.
            shortTrajectory = new Line(this.getCenter(), this.getVelocity().applyToPoint(this.getCenter()));
            shortClosestCollision = this.getGameEnvironment().getClosestCollision(shortTrajectory);
            count++;
        }

        // Moves the ball out of a puddle that override it.
        this.moveBallOutOfPuddle();

        // Moves the ball finaly to the next step.
        this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
    }

    /**
     * moveBallOutOfPuddle is a method that moves the ball out of the paddle.
     * (when they didn't hit, the paddle overrides the ball).
     *
     */
    public void moveBallOutOfPuddle() {

        /*
         * trajectory is a line from the center of the ball to it's last position
         * (by it's velocity), until the end of the frame.
         */
        Line trajectory = this.getTrajectory();

        // closestCollision gets the closest collision point and collidable object.
        CollisionInfo closestCollision = this.getGameEnvironment().getClosestCollision(trajectory);

        /*
         * Checks if there is a collision,
         * then it checks it the ball is inside the collidable object, and get it out.
         */
        if (closestCollision != null) {

            // collisionBlock is the collision object that intersect with trajectory.
            Collidable collisionBlock = closestCollision.collisionObject();

            /*
             * A loop that checks if the ball is inside the collidable object,
             * then it get it out (up and to the closest side- left or right).
             */
            boolean f = false;
            while (collisionBlock.getCollisionRectangle().isPointInRectangle(this.getCenter())) {

                /*
                 * Checks if the velocity has changed once before,
                 * then it goes over.
                 */
                if (!f) {

                    // Change the velocity to be positive (move down).
                    this.getVelocity().setDy(Math.abs(this.getVelocity().getDy()));

                    // Calculates the distance from the left side of the rectangle (positive- absolute).
                    double distanceFromLeft = Math.abs(collisionBlock.getCollisionRectangle().getUpperLeft().getX()
                            - this.getCenter().getX());

                    // Calculates the distance from the right side of the rectangle (positive- absolute).
                    double distanceFromRight = Math.abs(collisionBlock.getCollisionRectangle().getUpperRight().getX()
                            - this.getCenter().getX());

                    /*
                     * Checks if the ball is more close to the right side then it moves it right (positive dx),
                     * else moves it left (negative dx).
                     */
                    if (distanceFromLeft >= distanceFromRight) {
                        this.getVelocity().setDx(Math.abs(this.getVelocity().getDx()));
                    } else {
                        this.getVelocity().setDx(-Math.abs(this.getVelocity().getDx()));
                    }
                }

                // Moves the ball by it's velocity until it get out.
                this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
                f = true;
            }

            /*
             * Checks if the ball was inside a collidable object,
             * then it moves the ball another step.
             */
            if (f) {
                this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
            }
        }
    }

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

    /**
     * removeFromGame is a function that gets a game and removes from it this ball (sprite).
     *
     * @param gameLevel is the game we removes this ball from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
