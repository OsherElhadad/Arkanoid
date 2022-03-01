// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a GameEnvironment that has a list of collidable.
 */

package collisions.chelpers;

import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This GameEnvironment class has list of collidable that defines a GameEnvironment,
 * and can get the closest collision info of a line and the collidable objects.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class GameEnvironment {

    // collidableList is a list of collidable objects in this GameEnvironment.
    private List<Collidable> collidableList;

    // 0 is the first index in a list.
    private static final int FIRST_INDEX = 0;

    /**
     * GameEnvironment is the constructor method.
     * it initialize the collidableList of the GameEnvironment.
     *
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * getCollidableList is a method that returns the collidableList of this GameEnvironment.
     *
     * @return the collidableList of the GameEnvironment.
     */
    public List<Collidable> getCollidableList() {
        return new ArrayList<>(this.collidableList);
    }

    /**
     * addCollidable is a method that add the collidable we got to the list of this GameEnvironment.
     *
     * @param c is the collidable we add to the list.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidableList.add(c);
        }
    }

    /**
     * removeCollidable is a method that removes the collidable we got from the list of this GameEnvironment.
     *
     * @param c is the collidable we remove from the list.
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.collidableList.remove(c);
        }
    }

    /**
     * getCollisionsList is a method that creates a new list of the collision info
     * of the closest intersection points to the start of the line and it's rectangle.
     *
     * @param trajectory is the line of the ball's move.
     * @return a list of CollisionInfo of the closest intersection points to start of the trajectory.
     * @throws RuntimeException if the trajectory is null.
     */
    public List<CollisionInfo> getCollisionsList(Line trajectory) throws RuntimeException {

        /*
         * Throws a RuntimeException if the trajectory is null.
         */
        if (trajectory == null) {
            throw new RuntimeException("Can't get an empty trajectory");
        }

        /*
         * Creates a new list of CollisionInfos,
         * and goes over the collision list of the environment,
         * and adds the closest intersection points between them and the trajectory to the start of trajectory.
         */
        List<CollisionInfo> infos = new ArrayList<>();
        for (Collidable c : this.getCollidableList()) {
            Point closestIntersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (closestIntersection != null) {
                infos.add(new CollisionInfo(closestIntersection, c));
            }
        }
        return infos;
    }

    /**
     * getClosestCollision is a method that returns the closest collision info to the start of trajectory.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory is the line of the ball's move.
     * @return the closest collision info to the start of trajectory.
     * @throws RuntimeException if the trajectory is null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) throws RuntimeException {

        /*
         * Throws a RuntimeException if the trajectory is null.
         */
        if (trajectory == null) {
            throw new RuntimeException("Can't get an empty trajectory");
        }

        /*
         * Gets the list of CollisionInfo of the closest collisions,
         * and if it's empty- returns null (there is no collision at all).
         */
        List<CollisionInfo> infos = this.getCollisionsList(trajectory);
        if (infos.size() == 0) {
            return null;
        }

        /*
         * Sets the CollisionInfo with the minimum distance to the start of the line to be the first,
         * and removes it from list.
         */
        CollisionInfo minInfo = infos.get(FIRST_INDEX);
        double minDistance = trajectory.start().distance(minInfo.collisionPoint());
        infos.remove(FIRST_INDEX);

        /*
         * A loop that checks the whole CollisionInfo in the list,
         * and checks which one has the minimum distance from start.
         */
        for (CollisionInfo info : infos) {
            double distance = trajectory.start().distance(info.collisionPoint());

            /*
             * Checks if this distance is the minimum,
             * then it saves this distance and this CollisionInfo.
             */
            if (distance < minDistance) {
                minDistance = distance;
                minInfo = info;
            }
        }

        // returns the CollisionInfo that has the minimum distance from the start of the line.
        return minInfo;
    }

}
