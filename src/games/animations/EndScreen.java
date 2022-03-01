// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines an EndScreen that shows a end screen- win or loose.
 */

package games.animations;

import biuoop.DrawSurface;
import games.GameLevel;
import listeners.lhelpers.Counter;

import java.awt.Color;

/**
 * This EndScreen class display win or loose screen,
 * implements the Animation interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class EndScreen implements Animation {

    // The result of the game- win or loose.
    private String result;

    // The end score of the game.
    private Counter score;

    // FONT_SIZE is the default size of the font of the text.
    public static final int FONT_SIZE = 35;

    // FACE_RADIUS is the default radius of the face of the smiley.
    public static final int FACE_RADIUS = 100;

    // The number we divide with to get the quarter.
    public static final int QUARTER = 4;

    // The number we divide with to get the third.
    public static final int THIRD = 3;

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

    /**
     * EndScreen is the constructor method.
     * it sets the result, and score.
     *
     * @param result is the number of seconds we count down.
     * @param score is the number we start from to count down.
     */
    public EndScreen(String result, Counter score) {
        this.result = result;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Sets the color of the text to be green if we won and red else.
        Color color;
        if (result.equals("Game Over.")) {
            color = Color.RED;
        } else {
            color = Color.GREEN;
        }

        // Draws the result and the end score.
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / THIRD + TEN_TIMES * TWO_TIMES - BLACK_SHADOW,
                d.getHeight() / FIFTH - BLACK_SHADOW, result, FONT_SIZE);
        d.setColor(color);
        d.drawText(d.getWidth() / THIRD + TEN_TIMES * TWO_TIMES - SAME_SHADOW,
                d.getHeight() / FIFTH - SAME_SHADOW, result, FONT_SIZE);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / THIRD + TEN_TIMES * TWO_TIMES, d.getHeight() / FIFTH, result, FONT_SIZE);
        d.drawText(d.getWidth() / QUARTER + TEN_TIMES * THIRD - BLACK_SHADOW,
                d.getHeight() * TWO_TIMES / FIFTH - BLACK_SHADOW,
                " Your Score is " + score.getValue(), FONT_SIZE);
        d.setColor(color);
        d.drawText(d.getWidth() / QUARTER + TEN_TIMES * THIRD - SAME_SHADOW,
                d.getHeight() * TWO_TIMES / FIFTH - SAME_SHADOW,
                " Your Score is " + score.getValue(), FONT_SIZE);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / QUARTER + TEN_TIMES * THIRD, d.getHeight() * TWO_TIMES / FIFTH,
                " Your Score is " + score.getValue(), FONT_SIZE);

        // Draws the smiley on the screen- if we won it's shocked and depressed else.
        d.setColor(Color.ORANGE);
        d.fillCircle(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER,
                d.getHeight() * THIRD / FIFTH - TEN_TIMES, FACE_RADIUS + SAME_SHADOW);
        d.setColor(Color.YELLOW);
        d.fillCircle(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER,
                d.getHeight() * THIRD / FIFTH - TEN_TIMES, FACE_RADIUS);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER * TWO_TIMES,
                d.getHeight() * THIRD / FIFTH - TEN_TIMES * TWO_TIMES * THIRD, FACE_RADIUS / FIFTH);
        d.fillCircle(d.getWidth() / GameLevel.HALF,
                d.getHeight() * THIRD / FIFTH - TEN_TIMES * TWO_TIMES * THIRD, FACE_RADIUS / FIFTH);
        if (result.equals("Game Over.")) {
            d.setColor(Color.WHITE);
            d.fillCircle(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER * TWO_TIMES,
                    d.getHeight() * THIRD / FIFTH - TEN_TIMES * FIFTH, FACE_RADIUS / FIFTH / QUARTER);
            d.fillCircle(d.getWidth() / GameLevel.HALF,
                    d.getHeight() * THIRD / FIFTH - TEN_TIMES * FIFTH, FACE_RADIUS / FIFTH / QUARTER);
            d.setColor(Color.BLACK);
            d.drawLine(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER * TWO_TIMES,
                    d.getHeight() * THIRD / FIFTH + TEN_TIMES,
                    d.getWidth() / GameLevel.HALF, d.getHeight() * THIRD / FIFTH + TEN_TIMES * THIRD);
        } else {
            d.setColor(Color.WHITE);
            d.fillCircle(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER * TWO_TIMES,
                    d.getHeight() * THIRD / FIFTH - TEN_TIMES * TWO_TIMES * THIRD, FACE_RADIUS / FIFTH / QUARTER);
            d.fillCircle(d.getWidth() / GameLevel.HALF,
                    d.getHeight() * THIRD / FIFTH - TEN_TIMES * TWO_TIMES * THIRD, FACE_RADIUS / FIFTH / QUARTER);
            d.setColor(Color.BLACK);
            d.fillCircle(d.getWidth() / GameLevel.HALF - TEN_TIMES * QUARTER,
                    d.getHeight() * THIRD / FIFTH + TEN_TIMES * THIRD, FACE_RADIUS / QUARTER);
        }

        // Draws the text- Press space to continue.
        d.drawText(d.getWidth() / QUARTER - TEN_TIMES * TWO_TIMES - BLACK_SHADOW,
                d.getHeight() * QUARTER / FIFTH - BLACK_SHADOW, "Press space to continue", FONT_SIZE);
        d.setColor(color);
        d.drawText(d.getWidth() / QUARTER - TEN_TIMES * TWO_TIMES - SAME_SHADOW,
                d.getHeight() * QUARTER / FIFTH - SAME_SHADOW, "Press space to continue", FONT_SIZE);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / QUARTER - TEN_TIMES * TWO_TIMES,
                d.getHeight() * QUARTER / FIFTH, "Press space to continue", FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
