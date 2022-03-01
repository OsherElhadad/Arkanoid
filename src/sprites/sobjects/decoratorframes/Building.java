// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a building background.
 */

package sprites.sobjects.decoratorframes;

import biuoop.DrawSurface;
import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.shelpers.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * This Building class has sprite (decorator), color of window,
 * ball center and list of rectangles of the building,
 * implements the Sprite interface.
 *
 * @version 1.00 22 March 2021
 * @author Osher Elhadad
 */
public class Building implements Sprite {

    // The sprite is a decorator- abstract background.
    private Sprite sprite;

    // The middle point of the head circle of the building.
    private Point ballCenter;

    // The rectangles of the building.
    private List<Rectangle> rectangleList;

    // The color of the windows of the building.
    private Color windowColor;

    // The default divide with the block's width to get the window's width.
    public static final int WINDOWS_DIVIDE_WIDTH = 18;

    // The default divide with the block's height to get the window's height.
    public static final int WINDOWS_DIVIDE_HEIGHT = 23;

    // The default radius of the small circle.
    public static final int SMALL_CIRCLE_RADIUS = 5;

    // The default radius of the medium circle.
    public static final int MEDIUM_CIRCLE_RADIUS = 10;

    // The default radius of the big circle.
    public static final int BIG_CIRCLE_RADIUS = 15;

    /**
     * Building is the constructor method.
     * it defines the sprite (decorator), color of window,
     *  * ball center and list of rectangles of the building.
     *
     * @param sprite is a decorator- abstract background.
     * @param ballCenter is the middle point of the head circle of the building.
     * @param rectangleList is the rectangles of the building.
     * @param windowsColor is the color of the windows of the building.
     */
    public Building(Sprite sprite, Point ballCenter, List<Rectangle> rectangleList, Color windowsColor) {
        this.sprite = sprite;
        this.ballCenter = ballCenter;
        this.rectangleList = rectangleList;
        this.windowColor = windowsColor;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.sprite.drawOn(surface);

        /*
         * A loop that draws the all blocks of the building,
         * and it's windows.
         */
        for (int i = 0; i < this.rectangleList.size(); i++) {
            Block block = new Block(this.rectangleList.get(i));
            block.drawOn(surface);

            /*
             * Draws the first block's windows.
             */
            if (i == 0) {
                double width = this.rectangleList.get(i).getWidth() / WINDOWS_DIVIDE_WIDTH;
                double startX = this.rectangleList.get(i).getUpperLeft().getX();
                double endX = this.rectangleList.get(i).getUpperRight().getX();
                double height = this.rectangleList.get(i).getHeight() / WINDOWS_DIVIDE_HEIGHT;
                double startY = this.rectangleList.get(i).getUpperLeft().getY();
                double endY = this.rectangleList.get(i).getLowerLeft().getY();
                for (double j = width * 2 + startX; j < endX - width * 3; j += width * 3) {
                    for (double z = height + startY; z < endY; z += height * 5) {
                        Point upperLeft = new Point(j, z);
                        Block window = new Block(
                                new Rectangle(upperLeft, width * 2, height * 4, this.windowColor));
                        window.drawOn(surface);
                    }
                }
            }
        }
        surface.setColor(Color.ORANGE);
        surface.fillCircle((int) this.ballCenter.getX(), (int) this.ballCenter.getY(), BIG_CIRCLE_RADIUS);
        surface.setColor(Color.RED);
        surface.fillCircle((int) this.ballCenter.getX(), (int) this.ballCenter.getY(), MEDIUM_CIRCLE_RADIUS);
        surface.setColor(Color.WHITE);
        surface.fillCircle((int) this.ballCenter.getX(), (int) this.ballCenter.getY(), SMALL_CIRCLE_RADIUS);
    }

    @Override
    public void timePassed() { }

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
}
