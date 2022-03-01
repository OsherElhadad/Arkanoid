// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a BlockRemover that has game and remainingBlocks counter.
 */

package listeners.lobjects;

import collisions.cobjects.Block;
import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import sprites.sobjects.Ball;
import games.GameLevel;

/**
 * This BlockRemover class has game and remainingBlocks counter that defines a BlockRemover,
 * it is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class BlockRemover implements HitListener {

    // The game we observe.
    private GameLevel gameLevel;

    // The number of the remaining balls.
    private Counter remainingBlocks;

    // The number of the hits in this block.
    private Counter hits;

    // The default decrease jump in the counter.
    public static final int DECREASE_JUMP = 1;

    /**
     * BallRemover is the constructor method.
     * it defines the game and remainingBalls counter values of the BallRemover.
     *
     * @param gameLevel is the game we observe.
     * @param removedBlocks is the initial counter of balls.
     * @param hits is the counter of the hits.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks, Counter hits) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
        this.hits = hits;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.hits.decrease(DECREASE_JUMP);
        if (this.hits.getValue() <= 0) {
            this.remainingBlocks.decrease(DECREASE_JUMP);
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
        }
    }
}
