// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a Level that is abstract and represents a level in the game.
 */

package games.levels;

import collisions.cobjects.Block;
import games.GameLevel;
import listeners.lhelpers.Counter;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;

import java.util.List;

/**
 * This Level class is abstract class that represents a level in the game,
 * and implements LevelInformation.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public abstract class Level implements LevelInformation {

    // The name of the level.
    private String name;

    // The number of balls in the level.
    private int numberOfBalls;

    // A list of the balls velocities of the level.
    private List<Velocity> ballsVelocity;

    // The speed of the paddle of the level.
    private int paddleSpeed;

    // The width of the paddle of the level.
    private int paddleWidth;

    // A list of the blocks of the level.
    private List<Block> blocks;

    // The background draws of the level.
    private Sprite background;

    // blocksCounter counts the blocks in the level.
    private Counter blocksCounter;

    /**
     * Level is the constructor method.
     * it sets the name, numberOfBalls, paddleSpeed, paddleWidth and blocksCounter of the game.
     *
     * @param name is the name of the level.
     * @param numberOfBalls is the number of balls in the level.
     * @param paddleSpeed is the speed of the paddle of the level.
     * @param paddleWidth is the width of the paddle of the level.
     */
    public Level(String name, int numberOfBalls, int paddleSpeed, int paddleWidth) {
        this.name = name;
        this.numberOfBalls = numberOfBalls;
        this.ballsVelocity = this.initialBallVelocities();
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.blocksCounter = new Counter(GameLevel.EMPTY);
        this.blocks = this.initialBlocks();
        this.blocksCounter.increase(this.blocks.size());
        this.background = this.initialBackground();
    }

    /**
     * addBackground is a method that calls an initialize method to the background of the level.
     *
     */
    public void addBackground() {
        this.background = this.initialBackground();
    }

    /**
     * initialBackground is an abstract method that creates the background of the level and returns it.
     *
     * @return the background (sprite) of the level.
     */
    public abstract Sprite initialBackground();

    /**
     * initialBlocks is an abstract method that creates the blocks of the level and returns a list of them.
     *
     * @return a list of blocks of this level.
     */
    public abstract List<Block> initialBlocks();

    @Override
    public double frameWidth() {
        return this.getFrame().getRectangle().getWidth();
    }

    @Override
    public double frameHeight() {
        return this.getFrame().getRectangle().getHeight();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size() - this.blocksCounter.getValue();
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public String levelName() {
        return this.name;
    }
}
