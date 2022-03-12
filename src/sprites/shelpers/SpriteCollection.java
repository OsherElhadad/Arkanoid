
/*
 * Osher Elhadad
 *
 * This file defines a SpriteCollection that has a list of Sprites.
 */

package sprites.shelpers;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * This SpriteCollection class has list of sprites that defines a SpriteCollection,
 * can add sprites, notify the time passed to the whole sprites, and draws them.
 *
 * @version 1.00 22 April 2021
 * @author Osher Elhadad
 */
public class SpriteCollection {

    // spriteList is a list of Sprite objects in this SpriteCollection.
    private List<Sprite> spriteList;

    /**
     * SpriteCollection is the constructor method.
     * it initialize the spriteList of the sprites.helpers.SpriteCollection.
     *
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * getSpriteList is a method that returns the spriteList of this sprite collection.
     *
     * @return the spriteList of the sprite collection.
     */
    public List<Sprite> getSpriteList() {
        return new ArrayList<>(this.spriteList);
    }

    /**
     * addSprite is a method that add the Sprite we got to the list of this SpriteCollection.
     *
     * @param s the sprite we add to the SpriteCollection.
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.spriteList.add(s);
        }
    }

    /**
     * removeSprite is a method that removes the Sprite we got from the list of this SpriteCollection.
     *
     * @param s the sprite we remove from the SpriteCollection.
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            this.spriteList.remove(s);
        }
    }

    /**
     * notifyAllTimePassed is a method that notify the time passed on all sprites.
     *
     */
    public void notifyAllTimePassed() {
        for (Sprite s : this.getSpriteList()) {
            s.timePassed();
        }
    }

    /**
     * drawAllOn is a method that draws the all sprites on the surface.
     *
     * @param d the draw surface we draws the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.getSpriteList()) {
            s.drawOn(d);
        }
    }
}
