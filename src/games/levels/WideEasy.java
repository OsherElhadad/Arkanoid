// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a WideEasy that is level 2 in the game.
 */

package games.levels;

import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;
import sprites.sobjects.decoratorframes.Frame;
import sprites.sobjects.decoratorframes.Sun;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This WideEasy class is level 2 in the game, extends level class..
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class WideEasy extends Level {

    // The frame of the level.
    private Frame frame;

    // The default number of balls.
    public static final int NUMBERS_OF_BALLS = 10;

    // The default speed of the paddle.
    public static final int PADDLE_SPEED = 2;

    // The default width of the paddle.
    public static final int PADDLE_WIDTH = 550;

    // The default speed of the ball.
    public static final int BALL_SPEED = 6;

    // The default start angle of the ball.
    public static final int START_BALL_ANGLE = 3;

    // The default end angle of the ball.
    public static final int END_BALL_ANGLE = 53;

    // The default steps angle of the ball.
    public static final int STEPS_BALL_ANGLE = 10;

    // The color of the small circle of the sun.
    public static final float[] HSB1 = Color.RGBtoHSB(255, 225, 24, null);

    // The color of the middle circle of the sun.
    public static final float[] HSB2 = Color.RGBtoHSB(236, 215, 73, null);

    // The color of the big circle of the sun.
    public static final float[] HSB3 = Color.RGBtoHSB(239, 231, 176, null);

    // The default index of the first var.
    public static final int FIRST = 0;

    // The default index of the second var.
    public static final int SECOND = 1;

    // The default index of the third var.
    public static final int THIRD = 2;

    // The default x and y of the middle sun.
    public static final double X_Y_POINT_MIDDLE_SUN = 6 * GameLevel.BORDER_WIDTH;

    // The default x and y of the middle sun.
    public static final double Y_POINT_START_AND_END_LINE = 10 * GameLevel.BORDER_WIDTH;

    // The default x and y of the middle sun.
    public static final double X_POINT_END_LINE = GameLevel.WIDTH - 3 * GameLevel.BORDER_WIDTH;

    // The default x blocks step.
    public static final double X_BLOCKS_STEPS = ((double) GameLevel.WIDTH - (2 * GameLevel.BORDER_WIDTH)) / 15.0;

    /**
     * WideEasy is the constructor method.
     * it sets the level and the base frame of the game.
     *
     */
    public WideEasy() {
        super("Wide Easy", NUMBERS_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH);
        this.frame = new Frame(new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, Color.WHITE));
        this.addBackground();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();

        /*
         * A loop that adds the balls with negative velocity.
         */
        for (int i = -END_BALL_ANGLE; i <= -START_BALL_ANGLE; i += STEPS_BALL_ANGLE) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(i, BALL_SPEED));
        }

        /*
         * A loop that adds the balls with positive velocity.
         */
        for (int i = START_BALL_ANGLE; i <= END_BALL_ANGLE; i += STEPS_BALL_ANGLE) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(i, BALL_SPEED));
        }
        return ballsVelocity;
    }

    @Override
    public Sprite initialBackground() {
        Color smallColor = Color.getHSBColor(HSB1[FIRST], HSB1[SECOND], HSB1[THIRD]);
        Color middleColor = Color.getHSBColor(HSB2[FIRST], HSB2[SECOND], HSB2[THIRD]);
        Color bigColor = Color.getHSBColor(HSB3[FIRST], HSB3[SECOND], HSB3[THIRD]);
        return new Sun(this.frame, smallColor, middleColor, bigColor,
                new Point(X_Y_POINT_MIDDLE_SUN, X_Y_POINT_MIDDLE_SUN),
                new Point(0, Y_POINT_START_AND_END_LINE),
                new Point(X_POINT_END_LINE, Y_POINT_START_AND_END_LINE));
    }

    @Override
    public Frame getFrame() {
        return this.frame;
    }

    @Override
    public List<Block> initialBlocks() {
        Point point;
        Block block;
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};

        /*
         * A loop that adds the blocks of the game.
         */
        for (double i = GameLevel.BORDER_WIDTH; i < GameLevel.WIDTH - GameLevel.BORDER_WIDTH; i += X_BLOCKS_STEPS) {
            point = new Point(i, Y_POINT_START_AND_END_LINE);
            block = new Block(
                    new Rectangle(point, X_BLOCKS_STEPS, GameLevel.BORDER_WIDTH, colors[blocks.size()]));
            blocks.add(block);
        }
        return blocks;
    }
}
