
/*
 * Osher Elhadad
 *
 * This file defines a LevelInformation interface.
 */

package games.levels;

import collisions.cobjects.Block;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;
import sprites.sobjects.decoratorframes.Frame;

import java.util.List;

/**
 * This LevelInformation class has getters to the information of the level.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public interface LevelInformation {

    /**
     * numberOfBalls is a method from the LevelInformation interface,
     * it returns the number of balls in the level.
     *
     * @return the number of balls in this level.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities is a method from the LevelInformation interface,
     * it returns a list of the initial velocity of each ball.
     *
     * @return a list of the initial velocity of each ball in this level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed is a method from the LevelInformation interface,
     * it returns the speed of the paddle of the level.
     *
     * @return the speed of the paddle in this level.
     */
    int paddleSpeed();

    /**
     * paddleWidth is a method from the LevelInformation interface,
     * it returns the width of the paddle of the level.
     *
     * @return the width of the paddle in this level.
     */
    int paddleWidth();

    /**
     * levelName is a method from the LevelInformation interface,
     * it returns the name of the level.
     *
     * @return the name of this level.
     */
    String levelName();

    /**
     * levelName is a method from the LevelInformation interface,
     * it returns a sprite with the background of the level.
     *
     * @return a sprite with the background of this level.
     */
    Sprite getBackground();

    /**
     * blocks is a method from the LevelInformation interface,
     * it returns a list of the all blocks of the level.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list of the all blocks of this level.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove is a method from the LevelInformation interface,
     * it returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed from this level.
     */
    int numberOfBlocksToRemove();

    /**
     * frameWidth is a method from the LevelInformation interface,
     * it returns the width of the frame of the level.
     *
     * @return the width of the frame of this level.
     */
    double frameWidth();

    /**
     * frameHeight is a method from the LevelInformation interface,
     * it returns the height of the frame of the level.
     *
     * @return the height of the frame of this level.
     */
    double frameHeight();

    /**
     * getFrame is a method from the LevelInformation interface,
     * it returns the frame of the level.
     *
     * @return the frame of this level.
     */
    Frame getFrame();
}
