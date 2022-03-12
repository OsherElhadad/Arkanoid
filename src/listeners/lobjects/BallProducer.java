
/*
 * Osher Elhadad
 *
 * This file defines a BallProducer that has game and remainingBalls counter.
 */

package listeners.lobjects;

import collisions.cobjects.Block;
import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import sprites.sobjects.Ball;
import games.GameLevel;

/**
 * This BallProducer class has game and remainingBalls counter that defines a BallProducer,
 * it is in charge of keeping count of the number of balls that remain.
 * implements the HitListener interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class BallProducer implements HitListener {

    // The game we observe.
    private GameLevel gameLevel;

    // The number of the remaining balls.
    private Counter remainingBalls;

    // The default increase jump in the counter.
    public static final int INCREASE_JUMP = 1;

    /**
     * BallProducer is the constructor method.
     * it defines the game and remainingBalls counter values of the BallProducer.
     *
     * @param gameLevel is the game we observe.
     * @param remainingBalls is the initial counter of balls.
     */
    public BallProducer(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.increase(INCREASE_JUMP);
        gameLevel.produceBall();
    }
}
