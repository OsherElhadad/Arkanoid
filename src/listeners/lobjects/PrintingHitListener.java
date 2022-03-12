
/*
 * Osher Elhadad
 *
 * This file defines a PrintingHitListener.
 */

package listeners.lobjects;

import collisions.cobjects.Block;
import listeners.lhelpers.HitListener;
import sprites.sobjects.Ball;

/**
 * This PrintingHitListener class has hitEvent method that prints hit has occurred.
 * implements the HitListener interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class PrintingHitListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
