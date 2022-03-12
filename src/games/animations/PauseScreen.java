
/*
 * Osher Elhadad
 *
 * This file defines a PauseScreen that shows a pause screen.
 */

package games.animations;

import biuoop.DrawSurface;
import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.sobjects.decoratorframes.Frame;

import java.awt.Color;

/**
 * This EndScreen class display pause screen,
 * implements the Animation interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class PauseScreen implements Animation {

    // The width of the 2 black block we show on the screen.
    public static final int BLOCK_WIDTH = 50;

    // The height of the 2 black block we show on the screen.
    public static final int BLOCK_HEIGHT = 120;

    // FONT_SIZE is the default size of the font of the text.
    public static final int FONT_SIZE = 35;

    // The number we divide with to get the quarter.
    public static final int QUARTER = 4;

    // The number we divide with to get the fifth.
    public static final int FIFTH = 5;

    // The default numbers to two times is 2.
    public static final int TWO_TIMES = 2;

    // The default numbers to ten times is 10.
    public static final int TEN_TIMES = 10;

    // The gap between the shadow of the text to it's black shadow.
    public static final int BLACK_SHADOW = 3;

    // The gap between the shadow of the text to it's same color shadow.
    public static final int SAME_SHADOW = 2;

    // CIRCLE_RADIUS is the default radius of the circle we show on the screen.
    public static final int CIRCLE_RADIUS = 120;

    // LEFT_AND_DOWN is the change in the position of the 2 blocks we shows on the screen from the screen's middle.
    public static final int LEFT_AND_DOWN = 60;

    @Override
    public void doOneFrame(DrawSurface d) {
        Frame frame = new Frame(new Rectangle(new Point(0, 0), d.getWidth(), d.getHeight(), Color.LIGHT_GRAY));
        frame.drawOn(d);

        // Draws the big circle with it's shadow.
        d.setColor(Color.GRAY);
        d.fillCircle(d.getWidth() / GameLevel.HALF, d.getHeight() / GameLevel.HALF, CIRCLE_RADIUS);
        d.setColor(Color.WHITE);
        d.fillCircle(d.getWidth() / GameLevel.HALF, d.getHeight() / GameLevel.HALF, CIRCLE_RADIUS - TWO_TIMES);
        d.setColor(Color.BLUE);
        d.fillCircle(d.getWidth() / GameLevel.HALF, d.getHeight() / GameLevel.HALF, CIRCLE_RADIUS - TEN_TIMES);
        d.setColor(Color.GRAY);
        d.fillCircle(d.getWidth() / GameLevel.HALF, d.getHeight() / GameLevel.HALF,
                CIRCLE_RADIUS - TEN_TIMES - TWO_TIMES);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / GameLevel.HALF, d.getHeight() / GameLevel.HALF,
                CIRCLE_RADIUS - TEN_TIMES - TWO_TIMES - TWO_TIMES);

        // Draws the 2 black blocks with their shadow.
        Block block1 = new Block(new Rectangle(
                new Point((double) d.getWidth() / GameLevel.HALF - LEFT_AND_DOWN - TWO_TIMES,
                        (double) d.getHeight() / GameLevel.HALF - LEFT_AND_DOWN - TWO_TIMES),
                BLOCK_WIDTH, BLOCK_HEIGHT, Color.GRAY));
        block1.drawOn(d);
        Block block2 = new Block(new Rectangle(new Point((double) d.getWidth() / GameLevel.HALF - LEFT_AND_DOWN,
                (double) d.getHeight() / GameLevel.HALF - LEFT_AND_DOWN),
                BLOCK_WIDTH, BLOCK_HEIGHT, Color.BLUE));
        block2.drawOn(d);
        Block block3 = new Block(new Rectangle(
                new Point((double) d.getWidth() / GameLevel.HALF + TEN_TIMES - TWO_TIMES,
                        (double) d.getHeight() / GameLevel.HALF - LEFT_AND_DOWN - TWO_TIMES),
                BLOCK_WIDTH, BLOCK_HEIGHT, Color.GRAY));
        block3.drawOn(d);
        Block block4 = new Block(new Rectangle(
                new Point((double) d.getWidth() / GameLevel.HALF + TEN_TIMES,
                        (double) d.getHeight() / GameLevel.HALF - LEFT_AND_DOWN),
                BLOCK_WIDTH, BLOCK_HEIGHT, Color.BLUE));
        block4.drawOn(d);

        // Draws the text- Press space to continue.
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / QUARTER - BLACK_SHADOW, d.getHeight() * QUARTER / FIFTH - BLACK_SHADOW,
                "Press space to continue", FONT_SIZE);
        d.setColor(Color.BLUE);
        d.drawText(d.getWidth() / QUARTER - SAME_SHADOW, d.getHeight() * QUARTER / FIFTH - SAME_SHADOW,
                "Press space to continue", FONT_SIZE);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / QUARTER, d.getHeight() * QUARTER / FIFTH,
                "Press space to continue", FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
