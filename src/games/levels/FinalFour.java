
/*
 * Osher Elhadad
 *
 * This file defines a FinalFour that is level 4 in the game.
 */

package games.levels;

import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;
import sprites.sobjects.Ball;
import sprites.sobjects.decoratorframes.Clouds;
import sprites.sobjects.decoratorframes.Frame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This FinalFour class is level 4 in the game, extends level class..
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class FinalFour extends Level {

    // The frame of the level.
    private Frame frame;

    // The default number of balls.
    public static final int NUMBERS_OF_BALLS = 3;

    // The default speed of the paddle.
    public static final int PADDLE_SPEED = 10;

    // The default width of the paddle.
    public static final int PADDLE_WIDTH = 100;

    // The default speed of the ball.
    public static final int BALL_SPEED = 6;

    // The default start angle of the ball.
    public static final int START_BALL_ANGLE = -45;

    // The default end angle of the ball.
    public static final int END_BALL_ANGLE = 45;

    // The default steps angle of the ball.
    public static final int STEPS_BALL_ANGLE = 45;

    // The background color of the of the level.
    public static final float[] HSB = Color.RGBtoHSB(23, 136, 208, null);

    // The strat rbg color of the clouds.
    public static final int START_RGB_COLOR = 204;

    // The change in the color of the clouds.
    public static final int COLORS_CHANGE = 17;

    // The default index of the first var.
    public static final int FIRST = 0;

    // The default index of the second var.
    public static final int SECOND = 1;

    // The default index of the third var.
    public static final int THIRD = 2;

    // The default y of the upper blocks game.
    public static final int Y_POINT_START_BLOCKS = 4;

    // The default y of the lower blocks game.
    public static final int Y_POINT_END_BLOCKS = 10;

    // The default x blocks step.
    public static final double X_BLOCKS_STEPS = ((double) GameLevel.WIDTH - (2 * GameLevel.BORDER_WIDTH)) / 15.0;

    // The default x of the upper left circle of cloud.
    public static final double START_X_LEFT_CLOUD = GameLevel.BORDER_WIDTH * 4;

    // The default y of the upper left circle of cloud.
    public static final int START_Y_LEFT_CLOUD = 3 * GameLevel.HEIGHT / 5;

    // The default x of the upper right circle of cloud.
    public static final double START_X_RIGHT_CLOUD = GameLevel.WIDTH - GameLevel.BORDER_WIDTH * 10;

    // The default y of the upper right circle of cloud.
    public static final int START_Y_RIGHT_CLOUD = 4 * GameLevel.HEIGHT / 5;

    // The default add to x for start line.
    public static final int START_LINE_ADD_X = 10;

    // The default first cloud radius.
    public static final int FIRST_CLOUD_RADIUS = 30;

    // The default second cloud radius.
    public static final int SECOND_CLOUD_RADIUS = 20;

    // The default third cloud radius.
    public static final int THIRD_CLOUD_RADIUS = 35;

    // The default third cloud radius.
    public static final int LINES_GAP = 10;

    // The default change in y value of the clouds.
    public static final int ADD_TO_Y_CLOUDS = 20;

    // The default change in x value of the clouds.
    public static final int ADD_TO_X_CLOUDS = 20;

    /**
     * FinalFour is the constructor method.
     * it sets the level and the base frame of the game.
     *
     */
    public FinalFour() {
        super("Final Four", NUMBERS_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH);
        Color color = Color.getHSBColor(HSB[FIRST], HSB[SECOND], HSB[THIRD]);
        this.frame = new Frame(new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, color));
        this.addBackground();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(START_BALL_ANGLE, BALL_SPEED));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(START_BALL_ANGLE + STEPS_BALL_ANGLE, BALL_SPEED));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(END_BALL_ANGLE, BALL_SPEED));
        return ballsVelocity;
    }

    @Override
    public Sprite initialBackground() {

        // Creates the left cloud.
        List<Ball> balls1 = new ArrayList<>();
        int rgbColor = START_RGB_COLOR;
        float[] hsb1 = Color.RGBtoHSB(rgbColor, rgbColor, rgbColor, null);
        Color color1 = Color.getHSBColor(hsb1[FIRST], hsb1[SECOND], hsb1[THIRD]);
        Point start = new Point(START_X_LEFT_CLOUD, START_Y_LEFT_CLOUD);
        Point startLine1 = new Point(start.getX() + START_LINE_ADD_X, start.getY());
        Point endLine1 = new Point(start.getX() - START_LINE_ADD_X, GameLevel.HEIGHT);
        balls1.add(new Ball(start, FIRST_CLOUD_RADIUS, color1));
        balls1.add(new Ball(start.getX() + ADD_TO_X_CLOUDS, start.getY() + ADD_TO_Y_CLOUDS,
                FIRST_CLOUD_RADIUS, color1));
        rgbColor -= COLORS_CHANGE;
        float[] hsb2 = Color.RGBtoHSB(rgbColor, rgbColor, rgbColor, null);
        Color color2 = Color.getHSBColor(hsb2[FIRST], hsb2[SECOND], hsb2[THIRD]);
        balls1.add(new Ball(start.getX() + ADD_TO_X_CLOUDS * 2, start.getY() - ADD_TO_Y_CLOUDS,
                FIRST_CLOUD_RADIUS, color2));
        rgbColor -= COLORS_CHANGE;
        float[] hsb3 = Color.RGBtoHSB(rgbColor, rgbColor, rgbColor, null);
        Color color3 = Color.getHSBColor(hsb3[FIRST], hsb3[SECOND], hsb3[THIRD]);
        balls1.add(new Ball(start.getX() + ADD_TO_X_CLOUDS * 3, start.getY() + ADD_TO_Y_CLOUDS,
                SECOND_CLOUD_RADIUS, color3));
        balls1.add(new Ball(start.getX() + ADD_TO_X_CLOUDS * 4, start.getY(), THIRD_CLOUD_RADIUS, color3));

        // Creates the right cloud.
        List<Ball> balls2 = new ArrayList<>();
        rgbColor = START_RGB_COLOR;
        hsb1 = Color.RGBtoHSB(rgbColor, rgbColor, rgbColor, null);
        color1 = Color.getHSBColor(hsb1[FIRST], hsb1[SECOND], hsb1[THIRD]);
        start = new Point(START_X_RIGHT_CLOUD, START_Y_RIGHT_CLOUD);
        Point startLine2 = new Point(start.getX() + START_LINE_ADD_X, start.getY());
        Point endLine2 = new Point(start.getX() - START_LINE_ADD_X, GameLevel.HEIGHT);
        balls2.add(new Ball(start, FIRST_CLOUD_RADIUS, color1));
        balls2.add(new Ball(start.getX() + ADD_TO_X_CLOUDS, start.getY() + ADD_TO_Y_CLOUDS * 2,
                FIRST_CLOUD_RADIUS, color1));
        rgbColor -= COLORS_CHANGE;
        hsb2 = Color.RGBtoHSB(rgbColor, rgbColor, rgbColor, null);
        color2 = Color.getHSBColor(hsb2[FIRST], hsb2[SECOND], hsb2[THIRD]);
        balls2.add(new Ball(start.getX() + ADD_TO_X_CLOUDS * 2, start.getY() + (double) ADD_TO_Y_CLOUDS / 2,
                FIRST_CLOUD_RADIUS, color2));
        rgbColor -= COLORS_CHANGE;
        hsb3 = Color.RGBtoHSB(rgbColor, rgbColor, rgbColor, null);
        color3 = Color.getHSBColor(hsb3[FIRST], hsb3[SECOND], hsb3[THIRD]);
        balls2.add(new Ball(start.getX() + ADD_TO_X_CLOUDS * 3, start.getY() + (double) ADD_TO_Y_CLOUDS / 2 + 5,
                SECOND_CLOUD_RADIUS, color3));
        balls2.add(new Ball(start.getX() + ADD_TO_X_CLOUDS * 4, start.getY() + (double) ADD_TO_Y_CLOUDS / 2,
                THIRD_CLOUD_RADIUS, color3));
        return new Clouds(new Clouds(this.frame, balls1, startLine1, endLine1, LINES_GAP, Color.WHITE),
                balls2, startLine2, endLine2, LINES_GAP, Color.WHITE);
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
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};

        /*
         * A loop that creates the blocks of this level from right to left,
         * and with Y_POINT_END_BLOCKS - Y_POINT_START_BLOCKS rows.
         */
        for (int j = Y_POINT_START_BLOCKS; j <= Y_POINT_END_BLOCKS; j++) {
            for (double i = GameLevel.WIDTH - GameLevel.BORDER_WIDTH - X_BLOCKS_STEPS; i >= GameLevel.BORDER_WIDTH;
                 i -= X_BLOCKS_STEPS) {
                point = new Point(i, (j * GameLevel.BORDER_WIDTH));

                /*
                 * Adds a regular block to the game,
                 * or remover block (black),
                 * or producer block (magenta- pink).
                 */
                if (j != Y_POINT_END_BLOCKS + 1 - Y_POINT_START_BLOCKS) {
                    block = new Block(
                            new Rectangle(point, X_BLOCKS_STEPS, GameLevel.BORDER_WIDTH,
                                    colors[j - Y_POINT_START_BLOCKS]));
                } else {
                    if (i == GameLevel.WIDTH - GameLevel.BORDER_WIDTH - X_BLOCKS_STEPS * (Y_POINT_START_BLOCKS + 1)) {
                        block = new Block(
                                new Rectangle(point, X_BLOCKS_STEPS, GameLevel.BORDER_WIDTH,
                                        GameLevel.REMOVER_BLOCK_COLOR, GameLevel.REMOVER_BORDER_COLOR));
                    } else if (i == GameLevel.WIDTH - GameLevel.BORDER_WIDTH
                            - X_BLOCKS_STEPS * (Y_POINT_END_BLOCKS + 2)) {
                        block = new Block(
                                new Rectangle(point, X_BLOCKS_STEPS, GameLevel.BORDER_WIDTH,
                                        GameLevel.PRODUCER_BLOCK_COLOR, GameLevel.PRODUCER_BORDER_COLOR));
                    } else {
                        block = new Block(
                                new Rectangle(point, X_BLOCKS_STEPS, GameLevel.BORDER_WIDTH,
                                        colors[j - Y_POINT_START_BLOCKS]));
                    }
                }
                blocks.add(block);
            }
        }
        return blocks;
    }
}
