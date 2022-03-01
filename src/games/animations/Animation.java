// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a Animation interface.
 */

package games.animations;

import biuoop.DrawSurface;

/**
 * This Animation class has doOneFrame method and shouldStop method.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public interface Animation {

    /**
     * doOneFrame is a method from the Animation interface,
     * it does a one frame round of this animation.
     *
     * @param d the draw surface we draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop is a method from the Animation interface,
     * it return if the animation should stop or not.
     *
     * @return if the animation should stop or not.
     */
    boolean shouldStop();
}
