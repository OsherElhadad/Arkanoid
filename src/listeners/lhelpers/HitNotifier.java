// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a HitNotifier interface.
 */

package listeners.lhelpers;

/**
 * This HitNotifier interface has addHitListener and removeHitListener method
 * that adds/removes the the listener from the collection.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public interface HitNotifier {

    /**
     * addHitListener is a method that add hl as a listener to hit events.
     *
     * @param hl is the listener we add.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener is a method that remove hl from the list of listeners to hit events.
     *
     * @param hl is the listener we remove.
     */
    void removeHitListener(HitListener hl);
}
