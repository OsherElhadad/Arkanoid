
/*
 * Osher Elhadad
 *
 * This file defines the velocity of a ball (delta x, and delta y).
 */

package sprites.movement;

import geometry.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Velocity {

    // The change in position on the x.
    private double dx;

    // The change in position on the y.
    private double dy;

    /**
     * Velocity is the constructor method.
     * it defines the change in position on the `x` and the `y` axes.
     *
     * @param dx the delta x value we want to set for the ball.
     * @param dy the delta y value we want to set for the ball.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDx is a method that returns the change in position on the `x` axe of the ball.
     *
     * @return the change in position on the `x` axe of the ball.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy is a method that returns the change in position on the `y` axe of the ball.
     *
     * @return the change in position on the `y` axe of the ball.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * getAngle is a method that returns the angle of the velocity.
     *
     * @return the angle of the velocity.
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan(((-1) * this.dy) / this.dx));
    }

    /**
     * setDx is a method that sets the change in position on the `x` axe of the ball.
     *
     * @param deltaX the change in position on the `x` axe we want to set for the ball.
     */
    public void setDx(double deltaX) {
        this.dx = deltaX;
    }

    /**
     * setDy is a method that sets the change in position on the `y` axe of the ball.
     *
     * @param deltaY the change in position on the `y` axe we want to set for the ball.
     */
    public void setDy(double deltaY) {
        this.dy = deltaY;
    }

    /**
     * applyToPoint is a method that takes a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p the point we want to change it's position.
     * @return the new point with the change in dx and dy.
     */
    public Point applyToPoint(Point p) {
        return (new Point(this.getDx() + p.getX(), this.getDy() + p.getY()));
    }

    /**
     * fromAngleAndSpeed is a method that returns the velocity value of the ball
     * by the angel and speed.
     *
     * @param angle is the angle of the velocity (in degrees).
     * @param speed is the change in position by time.
     * @return the new point with the change in dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // The change in position on the `x` axe is the speed * sin(angle).
        double dx = speed * Math.sin(Math.toRadians(angle));

        // The change in position on the `x` axe is the speed * (-1) * cos(angle).
        double dy = speed * (-1) * Math.cos(Math.toRadians(angle));
        return (new Velocity(dx, dy));
    }
}
