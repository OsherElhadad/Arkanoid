
/*
 * Osher Elhadad
 *
 * This file defines a BallRemover that has game and remainingBalls counter.
 */

package listeners.lobjects;

import collisions.cobjects.Block;
import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import sprites.sobjects.Ball;
import games.GameLevel;

/**
 * This BallRemover class has game and remainingBalls counter that defines a BallRemover,
 * it is in charge of keeping count of the number of balls that remain.
 * implements the HitListener interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class BallRemover implements HitListener {

    // The game we observe.
    private GameLevel gameLevel;

    // The number of the remaining balls.
    private Counter remainingBalls;

    // The default decrease jump in the counter.
    public static final int DECREASE_JUMP = 1;

    /**
     * BallRemover is the constructor method.
     * it defines the game and remainingBalls counter values of the BallRemover.
     *
     * @param gameLevel is the game we observe.
     * @param removedBalls is the initial counter of balls.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(DECREASE_JUMP);
        hitter.removeFromGame(this.gameLevel);
    }
}
