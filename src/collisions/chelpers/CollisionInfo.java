// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a CollisionInfo that has collision point and collision object.
 */

package collisions.chelpers;

import geometry.Point;

/**
 * This CollisionInfo class has collision point and collision object,
 * and it represents a collision.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class CollisionInfo {

    // cPoint is the collision point.
    private Point cPoint;

    // cObject is the collision object.
    private Collidable cObject;

    /**
     * CollisionInfo is the constructor method.
     * it defines the collision point and the collision object of the CollisionInfo.
     *
     * @param cPoint is the collision point.
     * @param cObject is the collision object.
     */
    public CollisionInfo(Point cPoint, Collidable cObject) {
        this.cPoint = cPoint;
        this.cObject = cObject;
    }

    /**
     * collisionPoint is a method that returns the point at which the collision occurs.
     *
     * @return the collision point of the CollisionInfo.
     */
    public Point collisionPoint() {
        return this.cPoint;
    }

    /**
     * collisionObject is a method that returns the collidable object involved in the collision.
     *
     * @return the collision object of the CollisionInfo.
     */
    public Collidable collisionObject() {
        return this.cObject;
    }
}
