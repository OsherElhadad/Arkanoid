
/*
 * Osher Elhadad
 *
 * This file defines a DirectHit that is level 1 in the game.
 */

package games.levels;

import collisions.cobjects.Block;
import games.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;
import sprites.sobjects.decoratorframes.Frame;
import sprites.sobjects.decoratorframes.Target;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This DirectHit class is level 1 in the game, extends level class.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class DirectHit extends Level {

    // The frame of the level.
    private Frame frame;

    // The default number of balls.
    public static final int NUMBERS_OF_BALLS = 1;

    // The default number of circles.
    public static final int NUMBERS_OF_CIRCLES = 3;

    // The default speed of the paddle.
    public static final int PADDLE_SPEED = 10;

    // The default width of the paddle.
    public static final int PADDLE_WIDTH = 100;

    // The default speed of the ball.
    public static final int BALL_SPEED = 6;

    // The default angle of the ball.
    public static final int BALL_ANGLE = 0;

    // The default y of the blocks point.
    public static final double Y_POINT_BLOCK = 6.5 * GameLevel.BORDER_WIDTH;

    /**
     * DirectHit is the constructor method.
     * it sets the level and the base frame of the game.
     *
     */
    public DirectHit() {
        super("Direct Hit", NUMBERS_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH);
        this.frame = new Frame(new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, Color.BLACK));
        this.addBackground();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
        return ballsVelocity;
    }

    @Override
    public Sprite initialBackground() {
        return new Target(this.frame, Color.BLUE, this.blocks().get(0), NUMBERS_OF_CIRCLES);
    }

    @Override
    public Frame getFrame() {
        return this.frame;
    }

    @Override
    public List<Block> initialBlocks() {

        // The upper left point of the rectangle block.
        Point point = new Point((double) GameLevel.WIDTH / GameLevel.HALF
                - (double) GameLevel.BORDER_WIDTH / GameLevel.HALF, Y_POINT_BLOCK);
        Block block = new Block(
                new Rectangle(point, GameLevel.BORDER_WIDTH, GameLevel.BORDER_WIDTH, Color.RED));
        List<Block> blocks = new ArrayList<>();
        blocks.add(block);
        return blocks;
    }
}
