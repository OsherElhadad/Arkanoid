// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a HitListener interface.
 */

package listeners.lhelpers;

import collisions.cobjects.Block;
import sprites.sobjects.Ball;

/**
 * This HitListener interface has hitEvent method that notify that there was an hit.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public interface HitListener {

    /**
     * hitEvent is a method that is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that was hit.
     * @param hitter is the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
