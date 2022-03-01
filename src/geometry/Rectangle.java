// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a geometry.Rectangle that has a upper left point, width, height and color.
 */

package geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This geometry.Rectangle class has a upper left point, width, height and color values that defines a Rectangle,
 * can check if a point is in the rectangle.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class Rectangle {

    // The upperLeft Point of a rectangle.
    private Point upperLeft;

    // The width of a rectangle.
    private double width;

    // The height of a rectangle.
    private double height;

    // The color of a rectangle.
    private Color color;

    // The color of the rectangle border.
    private Color borderColor;

    // The default color of the rectangle's border.
    public static final Color BLOCK_BORDER_COLOR = Color.BLACK;

    /**
     * Rectangle is the constructor method.
     * it Create a new rectangle with location, color and width/height.
     *
     * @param upperLeft is the location of the rectangle (upper left point).
     * @param width is the width of the rectangle.
     * @param height is the height of the rectangle.
     * @param color is the color of the rectangle.
     * @param borderColor is the color of the rectangle's border.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color, Color borderColor) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.borderColor = borderColor;
    }

    /**
     * Rectangle is the constructor method.
     * it Create a new rectangle with location, color and width/height.
     *
     * @param upperLeft is the location of the rectangle (upper left point).
     * @param width is the width of the rectangle.
     * @param height is the height of the rectangle.
     * @param color is the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.borderColor = BLOCK_BORDER_COLOR;
    }

    /**
     * getWidth is a method that returns the width of this rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight is a method that returns the height of this rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getColor is a method that returns the color of this rectangle.
     *
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getBorderColor is a method that returns the border color of this rectangle.
     *
     * @return the border color of the rectangle.
     */
    public Color getBorderColor() {
        return this.borderColor;
    }

    /**
     * getUpperLeft is a method that returns the upper left point of this rectangle.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * setUpperLeft is a method that sets the upper left point of this rectangle.
     *
     * @param upperLeft1 is the upper left point of the rectangle we want to change to.
     */
    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }

    /**
     * getUpperRight is a method that returns the upper right point of this rectangle.
     *
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return (new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()));
    }

    /**
     * getLowerLeft is a method that returns the lower left point of this rectangle.
     *
     * @return the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return (new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * getLowerRight is a method that returns the lower right point of this rectangle.
     *
     * @return the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return (new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * getLeft is a method that returns the left line of this rectangle.
     *
     * @return the left line of the rectangle.
     */
    public Line getLeft() {
        Point start = this.getUpperLeft();
        Point end = new Point(start.getX(), start.getY() + this.getHeight());
        return (new Line(start, end));
    }

    /**
     * getUp is a method that returns the up line of this rectangle.
     *
     * @return the up line of the rectangle.
     */
    public Line getUp() {
        Point end = this.getUpperLeft();
        Point start = new Point(end.getX() + this.getWidth(), end.getY());
        return (new Line(start, end));
    }

    /**
     * getRight is a method that returns the right line of this rectangle.
     *
     * @return the right line of the rectangle.
     */
    public Line getRight() {
        Point start = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        Point end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        return (new Line(start, end));
    }

    /**
     * getDown is a method that returns the down line of this rectangle.
     *
     * @return the down line of the rectangle.
     */
    public Line getDown() {
        Point start = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Point end = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        return (new Line(start, end));
    }

    /**
     * isInLeftLine is a method that returns if the point is in the left line of this rectangle.
     *
     * @param p is the point we check if it is in the left line of the rectangle.
     * @return if the point is in the left line of the rectangle.
     */
    public boolean isInLeftLine(Point p) {
        return (this.getLeft().isIntersecting(new Line(p, p)));
    }

    /**
     * isInRightLine is a method that returns if the point is in the right line of this rectangle.
     *
     * @param p is the point we check if it is in the right line of the rectangle.
     * @return if the point is in the right line of the rectangle.
     */
    public boolean isInRightLine(Point p) {
        return (this.getRight().isIntersecting(new Line(p, p)));
    }

    /**
     * isInDownLine is a method that returns if the point is in the down line of this rectangle.
     *
     * @param p is the point we check if it is in the down line of the rectangle.
     * @return if the point is in the down line of the rectangle.
     */
    public boolean isInDownLine(Point p) {
        return (this.getDown().isIntersecting(new Line(p, p)));
    }

    /**
     * isInUpLine is a method that returns if the point is in the up line of this rectangle.
     *
     * @param p is the point we check if it is in the up line of the rectangle.
     * @return if the point is in the up line of the rectangle.
     */
    public boolean isInUpLine(Point p) {
        return (this.getUp().isIntersecting(new Line(p, p)));
    }

    /**
     * isPointInRectangle is a method that returns if the point is in this rectangle.
     *
     * @param p is the point we check if it is in the rectangle.
     * @return if the point is in the rectangle.
     */
    public boolean isPointInRectangle(Point p) {
        return ((p.getX() < this.getUpperRight().getX()
                || Math.abs(p.getX() - this.getUpperRight().getX()) < Point.EPSILON)
                && (p.getX() > this.getUpperLeft().getX()
                || Math.abs(p.getX() - this.getUpperLeft().getX()) < Point.EPSILON)
                && (p.getY() < this.getLowerLeft().getY()
                || Math.abs(p.getY() - this.getLowerLeft().getY()) < Point.EPSILON)
                && (p.getY() > this.getUpperLeft().getY()
                || Math.abs(p.getY() - this.getUpperLeft().getY()) < Point.EPSILON));
    }

    /**
     * intersectionPoints is a method that returns a list of intersection points with a line.
     *
     * @param line is the line we check it's intersection points with rectangle.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> l1 = new ArrayList<>();

        if (line == null) {
            return l1;
        }

        // Checks the intersection points with the rectangle lines and line.
        Point leftIntersection = line.intersectionWith(this.getLeft());
        Point upIntersection = line.intersectionWith(this.getUp());
        Point rightIntersection = line.intersectionWith(this.getRight());
        Point downIntersection = line.intersectionWith(this.getDown());

        /*
         * Checks if there is an intersection point with the left line of the rectangle and line,
         * and adds this point to the list.
         */
        if (leftIntersection != null) {
            l1.add(leftIntersection);
        }

        /*
         * Checks if there is an intersection point with the left line of the rectangle and line,
         * and adds this point to the list.
         */
        if (rightIntersection != null) {
            l1.add(rightIntersection);
        }

        /*
         * Checks if there is an intersection point with the left line of the rectangle and line,
         * and adds this point to the list.
         */
        if (upIntersection != null) {
            l1.add(upIntersection);
        }

        /*
         * Checks if there is an intersection point with the left line of the rectangle and line,
         * and adds this point to the list.
         */
        if (downIntersection != null) {
            l1.add(downIntersection);
        }
        return l1;
    }
}