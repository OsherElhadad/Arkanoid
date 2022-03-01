// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a ScoreTrackingListener that has currentScore counter.
 */

package listeners.lobjects;

import collisions.cobjects.Block;
import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import sprites.sobjects.Ball;

/**
 * This ScoreTrackingListener class has currentScore counter that defines a ScoreTrackingListener,
 * it is in charge of keeping count of the current score.
 * implements the HitListener interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class ScoreTrackingListener implements HitListener {

    // The number of the current score (in points).
    private Counter currentScore;

    // The default increase jump in the score when hit occurred.
    public static final int INCREASE_SCORES_JUMP = 15;

    /**
     * ScoreTrackingListener is the constructor method.
     * it defines the score counter of the ScoreTrackingListener.
     *
     * @param scoreCounter is the initial score of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(INCREASE_SCORES_JUMP);
    }
}
