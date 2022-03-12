
/*
 * Osher Elhadad
 *
 * This file defines a geometry.Point.
 */

package geometry;

/**
 * This geometry.Point class has x, y values that defines a point,
 * can calculate the distance between 2 points, and can check if 2 points are equal.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Point {

    // A x value in a (x,y) point.
    private double x;

    // A y value in a (x,y) point.
    private double y;

    // The exponent is 2 (to calculate the distance).
    private static final int EXPONENT = 2;

    // The epsilon helps us to compare between doubles.
    public static final double EPSILON = Math.pow(10, -11);

    /**
     * Point is the constructor method.
     * it defines the x, y values of the point.
     *
     * @param x is the x value of a point.
     * @param y is the y value of a point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getX is a method that returns the x value of this point instance.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY is a method that returns the y value of this point instance.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * distance is a method that calculate the distance between 2 points.
     *
     * @param other is the second point (this is the first) we get.
     * @throws RuntimeException if other is null (Can't calculate distance).
     * @return the returned value is the root square of- (x1-x2)^2 + (y1-y2)^2.
     */
    public double distance(Point other) throws RuntimeException {
        if (other == null) {
            throw new RuntimeException("Can't calculate distance with null Point");
        }
        return Math.sqrt(Math.pow(this.getX() - other.getX(), EXPONENT)
                + Math.pow(this.getY() - other.getY(), EXPONENT));
    }

    /**
     * equals is a method that checks if two point are equal.
     *
     * @param other is the second point (this is the first) we compare to.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return ((other != null) && (Math.abs(this.getX() - other.getX()) < EPSILON)
                && (Math.abs(this.getY() - other.getY()) < EPSILON));
    }

    /**
     * toString is a method that returns the string value of a Point (override).
     *
     * @return the a string of a Point (for example- (1,2)).
     */
    public String toString() {
        return ("(" + this.getX() + "," + this.getY() + ")");
    }

    /**
     * isInteger is a method that checks if the string is an integer number.
     *
     * @param str is the string we check if it's an integer.
     * @return true if the string is an integer, and false otherwise.
     */
    public static boolean isInteger(String str) {

        /*
         * We use try and catch to check
         * if the string is parsable or not.
         */
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * isDouble is a method that checks if the string is a double number.
     *
     * @param str is the string we check if it's a double.
     * @return true if the string is a double, and false otherwise.
     */
    public static boolean isDouble(String str) {

        /*
         * We use try and catch to check
         * if the string is parsable or not.
         */
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
