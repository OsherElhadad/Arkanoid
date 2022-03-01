// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a Collidable interface.
 */

package collisions.chelpers;

import geometry.Point;
import geometry.Rectangle;
import sprites.sobjects.Ball;
import sprites.movement.Velocity;

/**
 * This Collidable class has getCollisionRectangle method and hit method.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public interface Collidable {

    /**
     * getCollisionRectangle is a method from the Collidable interface,
     * it return the "collision shape" of the object.
     *
     * @return the rectangle of this collidable.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit is a method from the Collidable interface,
     * it notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * and the return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter the ball hit this collidable.
     * @param collisionPoint the point of the collision.
     * @param currentVelocity the current velocity of the object hit this collidable.
     * @return the new velocity expected after the hit (based on
     *         the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
