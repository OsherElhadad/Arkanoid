
/*
 * Osher Elhadad
 *
 * This file defines a Green3 that is level 3 in the game.
 */

package games.levels;

import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;
import sprites.sobjects.decoratorframes.Building;
import sprites.sobjects.decoratorframes.Frame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This Green3 class is level 3 in the game, extends level class..
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class Green3 extends Level {

    // The frame of the level.
    private Frame frame;

    // The default number of balls.
    public static final int NUMBERS_OF_BALLS = 2;

    // The default speed of the paddle.
    public static final int PADDLE_SPEED = 10;

    // The default width of the paddle.
    public static final int PADDLE_WIDTH = 100;

    // The default speed of the ball.
    public static final int BALL_SPEED = 6;

    // The default start angle of the ball.
    public static final int START_BALL_ANGLE = -45;

    // The default start angle of the ball.
    public static final int END_BALL_ANGLE = 45;

    // The background color of the of the level.
    public static final float[] HSB = Color.RGBtoHSB(42, 130, 21, null);

    // The color of the big block of the building.
    public static final float[] HSB1 = Color.RGBtoHSB(46, 42, 41, null);

    // The color of the middle block of the building.
    public static final float[] HSB2 = Color.RGBtoHSB(62, 58, 57, null);

    // The color of the small block of the building.
    public static final float[] HSB3 = Color.RGBtoHSB(78, 74, 73, null);

    // The width of the big block of the building.
    public static final int BIG_WIDTH = 100;

    // The width of the middle block of the building.
    public static final int MEDIUM_WIDTH = 30;

    // The width of the small block of the building.
    public static final int SMALL_WIDTH = 14;

    // The negative change in x of the big block of the building.
    public static final int BIG_MINUS_X_POINT = 50;

    // The negative change in x of the middle block of the building.
    public static final int MEDIUM_MINUS_X_POINT = 15;

    // The negative change in x of the small block of the building.
    public static final int SMALL_MINUS_X_POINT = 7;

    // The add to y of the big block of the building.
    public static final int BIG_ADD_Y_HEIGHT = 250;

    // The height of the middle block of the building.
    public static final int MEDIUM_HEIGHT = 50;

    // The height of the small block of the building.
    public static final int SMALL_HEIGHT = 200;

    // The x value of the head circle of the building.
    public static final int BALL_CENTER_X = GameLevel.WIDTH / 6;

    // The y value of the head circle of the building.
    public static final int BALL_CENTER_Y = GameLevel.HEIGHT / 3;

    // The default index of the first var.
    public static final int FIRST = 0;

    // The default index of the second var.
    public static final int SECOND = 1;

    // The default index of the third var.
    public static final int THIRD = 2;

    // The default y of the upper blocks game.
    public static final int Y_POINT_START_BLOCKS = 4;

    // The default x blocks step.
    public static final double X_BLOCKS_STEPS = ((double) GameLevel.WIDTH - (2 * GameLevel.BORDER_WIDTH)) / 15.0;

    /**
     * Green3 is the constructor method.
     * it sets the level and the base frame of the game.
     *
     */
    public Green3() {
        super("Green 3", NUMBERS_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH);
        Color color = Color.getHSBColor(HSB[FIRST], HSB[SECOND], HSB[THIRD]);
        this.frame = new Frame(new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, color));
        this.addBackground();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(START_BALL_ANGLE, BALL_SPEED));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(END_BALL_ANGLE, BALL_SPEED));
        return ballsVelocity;
    }

    @Override
    public Sprite initialBackground() {
        Point ballCenter = new Point(BALL_CENTER_X, BALL_CENTER_Y);

        // Creates the big building.
        List<Rectangle> rectangleList = new ArrayList<>();
        Color color1 = Color.getHSBColor(HSB1[FIRST], HSB1[SECOND], HSB1[THIRD]);
        rectangleList.add(new Rectangle(new Point(ballCenter.getX() - BIG_MINUS_X_POINT,
                ballCenter.getY() + BIG_ADD_Y_HEIGHT), BIG_WIDTH,
                GameLevel.HEIGHT - ballCenter.getY() - BIG_ADD_Y_HEIGHT, color1));

        // Creates the medium building.
        Color color2 = Color.getHSBColor(HSB2[FIRST], HSB2[SECOND], HSB2[THIRD]);
        rectangleList.add(new Rectangle(new Point(ballCenter.getX() - MEDIUM_MINUS_X_POINT,
                ballCenter.getY() + BIG_ADD_Y_HEIGHT - MEDIUM_HEIGHT), MEDIUM_WIDTH, MEDIUM_HEIGHT, color2));

        // Creates the small building.
        Color color3 = Color.getHSBColor(HSB3[FIRST], HSB3[SECOND], HSB3[THIRD]);
        rectangleList.add(new Rectangle(new Point(ballCenter.getX() - SMALL_MINUS_X_POINT,
                ballCenter.getY()), SMALL_WIDTH, SMALL_HEIGHT, color3));
        return new Building(this.frame, ballCenter, rectangleList, Color.WHITE);
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
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};

        /*
         * A loop that creates the blocks of this level from right to left,
         * and with less 1 block every row.
         */
        for (int j = Y_POINT_START_BLOCKS; j <= Y_POINT_START_BLOCKS * 2; j++) {
            for (double i = GameLevel.WIDTH - GameLevel.BORDER_WIDTH - X_BLOCKS_STEPS;
                 i > GameLevel.BORDER_WIDTH + (j * X_BLOCKS_STEPS);
                 i -= X_BLOCKS_STEPS) {
                point = new Point(i, ((j + Y_POINT_START_BLOCKS - 1) * GameLevel.BORDER_WIDTH));
                block = new Block(
                        new Rectangle(point, X_BLOCKS_STEPS, GameLevel.BORDER_WIDTH, colors[j - Y_POINT_START_BLOCKS]));
                blocks.add(block);
            }
        }
        return blocks;
    }
}
