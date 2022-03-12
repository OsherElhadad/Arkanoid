
/*
 * Osher Elhadad
 *
 * This file defines a geometry.Line that has a start and end points.
 */

package geometry;

import java.util.List;

/**
 * This Line class has start, end values that defines a Line,
 * can intersect with other lines.
 * It can also tell if it is the same as another line segment.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Line {

    // The start Point of a line.
    private Point start;

    // The end Point of a line.
    private Point end;

    // The half between two x or y numbers is the sum of them divided by 2.
    private static final int HALF = 2;

    // If the delta x of a line is 0 it's parallel to y.
    private static final int PARALLEL_TO_Y = 0;

    // If the length of a line is 0 then it's a point.
    private static final int LINE_IS_A_POINT = 0;

    // 0 is the first index in a list.
    private static final int FIRST_INDEX = 0;

    /**
     * Line is the constructor method.
     * it defines the start, end values of the Line.
     *
     * @param start is the start point of a line.
     * @param end is the end point of a line.
     * @throws RuntimeException if the start or end point is null (can't create a Line).
     */
    public Line(Point start, Point end) throws RuntimeException {

        /*
         * Throws a RuntimeException if the start or end point is null
         * (can't create a Line).
         */
        if ((start == null) || (end == null)) {
            throw new RuntimeException("Can't create a Line with null start or end Point");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Line is the constructor method.
     * it defines the start, end values of the Line.
     *
     * @param x1 is the x value of the start point.
     * @param y1 is the y value of the start point.
     * @param x2 is the x value of the end point.
     * @param y2 is the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this (new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * length is a method that returns the length value of this line instance.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle is a method that calculates and returns the middle point of this line instance.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.end.getX() + this.start.getX()) / HALF,
                (this.end.getY() + this.start.getY()) / HALF);
    }

    /**
     * start is a method that returns the start point of this line instance.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end is a method that returns the end point of this line instance.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * setStart is a method that sets the start point of this line instance.
     *
     * @param start1 the start point of the line.
     */
    public void setStart(Point start1) {
        this.start = start1;
    }

    /**
     * setEnd is a method that sets the end point of this line instance.
     *
     * @param end1 the end point of the line.
     */
    public void setEnd(Point end1) {
        this.end = end1;
    }

    /**
     * equals is a method that checks if two Lines are equal.
     *
     * @param other is the second line (this is the first) we compare to.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (other != null)
                && ((this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.start().equals(other.end()) && this.end().equals(other.start())));
    }

    /**
     * isIntersecting is a method that checks if 2 lines intersect.
     *
     * @param other is the second point (this is the first) we get.
     * @return true if the lines intersect (has exactly 1 point), false otherwise.
     */
    public boolean isIntersecting(Line other) {

        /*
         * Checks if the two lines has an intersection point,
         * and if so, then it returns true, and false otherwise (the intersection point is null).
         */
        return (this.intersectionWith(other) != null);
    }

    /**
     * intersectionWith is a method that calculate the intersection point between 2 lines.
     *
     * @param other is the second line (this is the first) we get.
     * @return Returns the intersection point if the lines has exactly 1 point, or null otherwise.
     */
    public Point intersectionWith(Line other) {

        /*
         * Checks if other is null (this can't be null),
         * and if so, then other is not a line and they are not intersecting.
         */
        if (other == null) {
            return null;
        }

        /*
         * Checks if the 2 lines are equals,
         * and if so, then if their length is 0 they have 1 intersection point,
         * otherwise, they are intersecting in more then 1 point- returns null.
         */
        if (this.equals(other)) {
            if (Math.abs(this.length() - LINE_IS_A_POINT) < Point.EPSILON
                    && Math.abs(other.length() - LINE_IS_A_POINT)  < Point.EPSILON) {
                return this.start();
            }
            return null;
        }

        /*
         * Checks if one of the lines is parallel to y,
         * and if so, returns oneIsParallelToYIntersection point field.
         */
        if (this.isParallelToY() || other.isParallelToY()) {
            return (this.oneIsParallelToYIntersection(other));
        }

        // Returns oneIsParallelToYIntersection Point field.
        return (this.noOneParallelToYIntersection(other));
    }

    /**
     * isParallelToY is a method that checks if the line is parallel to y.
     *
     * @return true if the line is parallel to y, false otherwise.
     */
    private boolean isParallelToY() {
        return (this.end().getX() - this.start().getX() == PARALLEL_TO_Y);
    }

    /**
     * oneIsParallelToYIntersection is a method that checks if the lines has an intersection point
     * (and at least one of them is parallel to y).
     *
     * @param other is the second line (this is the first) we get.
     * @return the intersection point if the 2 lines have one intersection point,
     *         or null if the 2 lines have two or more intersection points,
     *         or if there isn't any intersection point.
     */
    private Point oneIsParallelToYIntersection(Line other) {
        Point intersection = null;

        /*
         * Checks if one of the lines is not parallel to y,
         * and checks if there is an intersection point between them.
         */
        if (!this.isParallelToY() || !other.isParallelToY()) {
            double yIntersection;
            Point intersectionPoint;

            /*
             * Checks if this line is not parallel to y (other is parallel to y),
             * and if so, calculate the intersection point between the 2 infinity lines.
             */
            if (!this.isParallelToY()) {

                // Calculates the y of the other x value.
                yIntersection = this.incline() * other.start().getX() + this.intersectionWithY();
                intersectionPoint = new Point(other.start().getX(), yIntersection);
            } else {

                // Calculates the intersection point between the 2 infinity lines.
                yIntersection = other.incline() * this.start().getX() + other.intersectionWithY();
                intersectionPoint = new Point(this.start().getX(), yIntersection);
            }

            /*
             * Checks if the intersection point between the 2 infinity lines
             * is on the 2 lines segments, and if so then this point is the intersection point.
             */
            if (this.isPointInLineRange(intersectionPoint) && other.isPointInLineRange(intersectionPoint)) {
                intersection = intersectionPoint;
            }
        } else {

            /*
             * Checks if the line is a point and if it on the other line,
             * and if so then the line point is the intersection point.
             */
            if (Math.abs(other.length() - LINE_IS_A_POINT) < Point.EPSILON
                    && this.isPointInLineRange(other.start())) {
                intersection = other.start();
            } else if (Math.abs(this.length() - LINE_IS_A_POINT) < Point.EPSILON
                    && other.isPointInLineRange(this.start())) {
                intersection = this.start();
            } else {

                /*
                 * If the 2 lines are parallel to y and their length is bigger then 0,
                 * then it calls parallelIntersection that calculates the intersection point.
                 */
                intersection = this.parallelIntersection(other);
            }
        }
        return intersection;
    }

    /**
     * noOneParallelToYIntersection is a method that checks if the lines has an intersection point
     * (and the both of them are not parallel to y).
     *
     * @param other is the second line (this is the first) we get.
     * @return the intersection point if the 2 lines have one intersection point,
     *         or null if the 2 lines have two or more intersection points,
     *         or if there isn't any intersection point.
     */
    private Point noOneParallelToYIntersection(Line other) {
        Point intersection = null;

        // Gets the incline and the intersection with y of this and other lines.
        double thisIncline = this.incline();
        double otherIncline = other.incline();
        double thisIntersectionY = this.intersectionWithY();
        double otherIntersectionY = other.intersectionWithY();

        /*
         * Checks if the 2 lines are parallel,
         * and if so, it calls parallelIntersection that calculates the intersection point,
         * otherwise, it calculates the intersection point between the 2 lines,
         * and checks if this point is on the 2 lines segments.
         */
        if (Math.abs(thisIncline - otherIncline) < Point.EPSILON) {
            intersection = this.parallelIntersection(other);
        } else {

            // Calculates the x value of the intersection point between the 2 infinity lines.
            double newX = (otherIntersectionY - thisIntersectionY) / (thisIncline - otherIncline);

            // Calculates the y value of the intersection point between the 2 infinity lines.
            double newY = (((otherIncline * thisIntersectionY) - (thisIncline * otherIntersectionY))
                    / (otherIncline - thisIncline));
            Point intersectionPoint = new Point(newX, newY);

            /*
             * Checks if this point is on the 2 lines segments,
             * and if so then this point is the intersection point between the 2 lines segments.
             */
            if (this.isPointInLineRange(intersectionPoint) && other.isPointInLineRange(intersectionPoint)) {
                intersection = intersectionPoint;
            }
        }
        return intersection;
    }

    /**
     * parallelIntersection is a method that checks if the lines has an intersection point
     * (and the both of them are parallel).
     *
     * @param other is the second line (this is the first) we get.
     * @return the intersection point if the 2 lines have one intersection point,
     *         or null if the 2 lines have two or more intersection points,
     *         or if there isn't any intersection point.
     */
    private Point parallelIntersection(Line other) {
        Point intersection = null;

        /*
         * edge is the point in the edge of other point
         * if it's the start of this line, or null otherwise.
         * and another is the other point of other line (not the intersection point).
         */
        Point edge = other.pointInEdgeOfLine(this.start());
        Point another;

        /*
         * Checks if the start/end point of this line is on the edge of other line,
         * and if so then there is n intersection point and maybe it's only one.
         */
        if (edge != null) {

            // another gets the other point of other line (not the intersection point).
            another = edge.equals(other.start()) ? other.end() : other.start();

            /*
             * Checks if the 2 parallel lines have only
             * one intersection point (they continue each other from one point),
             * and if so then this point is the intersection point between the 2 lines segments.
             */
            if (!other.isPointInLineRange(this.end()) && !this.isPointInLineRange(another)) {
                intersection = other.pointInEdgeOfLine(this.start());
            }
        } else if (other.pointInEdgeOfLine(this.end()) != null) {

            /*
             * edge is the point in the edge of other point
             * if it's the end of this line, or null otherwise.
             * and another is the other point of other line (not the intersection point).
             */
            edge = other.pointInEdgeOfLine(this.end());
            another = other.start().equals(edge) ? other.end() : other.start();

            /*
             * Checks if the 2 parallel lines have only
             * one intersection point (they continue each other from one point),
             * and if so then this point is the intersection point between the 2 lines segments.
             */
            if (!other.isPointInLineRange(this.start()) && !this.isPointInLineRange(another)) {
                intersection = other.pointInEdgeOfLine(this.end());
            }
        }
        return intersection;
    }

    /**
     * isXInLine is a method that checks if the x value is in the range of x values of line.
     *
     * @param x is a x value we want to check inside this line.
     * @return true if x is in this line, false otherwise.
     */
    private boolean isXInLine(double x) {
        return ((x <= Math.max(this.start().getX(), this.end().getX())
                || Math.abs(Math.max(this.start().getX(), this.end().getX()) - x) < Point.EPSILON)
                && (x >= Math.min(this.start().getX(), this.end().getX())
                || Math.abs(x - Math.min(this.start().getX(), this.end().getX())) < Point.EPSILON));
    }

    /**
     * isYInLine is a method that checks if the y value is in the range of y values of line.
     *
     * @param y is a y value we want to check inside this line.
     * @return true if y is in this line, false otherwise.
     */
    private boolean isYInLine(double y) {
        return ((y <= Math.max(this.start().getY(), this.end().getY())
                || Math.abs(Math.max(this.start().getY(), this.end().getY()) - y) < Point.EPSILON)
                && (y >= Math.min(this.start().getY(), this.end().getY())
                || Math.abs(y - Math.min(this.start().getY(), this.end().getY())) < Point.EPSILON));
    }

    /**
     * isPointInLineRange is a method that checks if the point is in the range of the points in line.
     *
     * @param p is a point value we want to check inside this line.
     * @return true if point is in this line, false otherwise.
     */
    private boolean isPointInLineRange(Point p) {
        return (this.isXInLine(p.getX()) && this.isYInLine(p.getY()));
    }

    /**
     * pointInEdgeOfLine is a method that checks if the point is in the edge of the line,
     * if it is then it returns this point, and null otherwise.
     *
     * @param p is a point value we want to check inside this edge line.
     * @return point of the edge of the line, and null otherwise (if the point isn't in the edge).
     */
    private Point pointInEdgeOfLine(Point p) {

        /*
         * Checks if the point is in the start of the line
         * (if it's (x,y) are equals to the start).
         */
        if (p.equals(this.start())) {
            return (p);
        }

        /*
         * Checks if the point is in the end of the line
         * (if it's (x,y) are equals to the end).
         */
        if (p.equals(this.end())) {
            return (p);
        }
        return null;
    }

    /**
     * incline is a method that calculates and returns the incline of this line
     * (doesn't get a line that is parallel to y).
     *
     * @throws RuntimeException if the line is parallel to y.
     * @return the incline of this line.
     */
    private double incline() throws RuntimeException {

        /*
         * Throws a RuntimeException if there is no incline
         * (parallel to y).
         */
        if (this.isParallelToY()) {
            throw new RuntimeException("Can't get a line that is parallel to y");
        }
        return ((this.end().getY() - this.start().getY())
                / (this.end().getX() - this.start().getX()));
    }

    /**
     * intersectionWithY is a method that calculates and returns the intersection with y of this line
     * (doesn't get a line that is parallel to y).
     *
     * @throws RuntimeException if the line is parallel to y.
     * @return the intersection with y of this line.
     */
    private double intersectionWithY() throws RuntimeException {

        /*
         * Throws a RuntimeException if there is no intersection point with y
         * (parallel to y).
         */
        if (this.isParallelToY()) {
            throw new RuntimeException("Can't get a line that is parallel to y");
        }
        return (this.start().getY() - (this.incline() * this.start().getX()));
    }

    /**
     * closestIntersectionToStartOfLine is a method that returns the intersection point
     * with the rectangle and this line,
     * that has the minimum distance from the start of the line.
     * If this line does not intersect with the rectangle, return null.
     *
     * @param rect is the rectangle we checks it's intersection with this line.
     * @return the closest intersection point to the start of the line.
     * @throws RuntimeException if the geometryPrimitives.Rectangle is null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) throws RuntimeException {

        /*
         * Throws a RuntimeException if the rect is null.
         */
        if (rect == null) {
            throw new RuntimeException("Can't get an empty Rectangle");
        }

        // Gets a list of intersection points (rectangle with the line).
        List<Point> l1 = rect.intersectionPoints(this);

        /*
         * If this line does not intersect with the rectangle,
         * then it returns null.
         */
        if (l1.size() == 0) {
            return null;
        }

        /*
         * Sets the point with the minimum distance to the start of the line to be the first,
         * and removes it from list.
         */
        Point minPoint = l1.get(FIRST_INDEX);
        double minDistance = this.start().distance(minPoint);
        l1.remove(FIRST_INDEX);

        /*
         * A loop that checks the whole points in the list,
         * and checks which one is the minimum distance from start.
         */
        for (Point point : l1) {
            double distance = this.start().distance(point);

            /*
             * Checks if this distance is the minimum,
             * then it saves this distance and this point.
             */
            if (distance < minDistance) {
                minDistance = distance;
                minPoint = point;
            }
        }

        // returns the point that has the minimum distance from the start of the line.
        return minPoint;
    }
}
