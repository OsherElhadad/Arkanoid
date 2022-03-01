// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines a KeyPressStoppableAnimation that is has animation (decorator).
 */

package games.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This KeyPressStoppableAnimation class stops an animation with a key pressed,
 * implements the Animation interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class KeyPressStoppableAnimation implements Animation {

    // The keyboard sensor of the game- and used to stop an animation.
    private KeyboardSensor sensor;

    // The key that when it's pressed it stops the animation.
    private String key;

    // The animation we run and stop.
    private Animation animation;

    // Represents if the animation should stop.
    private boolean stop;

    // Represents if the key has already pressed the previous time.
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation is the constructor method.
     * it sets the sensor, key, animation, stop and isAlreadyPressed.
     *
     * @param sensor is the keyboard sensor of the game- and used to stop an animation.
     * @param key is the key that when it's pressed it stops the animation.
     * @param animation is the animation we run and stop.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);

        // If this key has pressed and not already pressed in the previous time it should stop the animation.
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
