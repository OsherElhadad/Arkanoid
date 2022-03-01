// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines an AnimationRunner that can run animation on the screen.
 */

package games.animations;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * This AnimationRunner class has GUI, framesPerSecond and a Sleeper,
 * it can run the animation on the screen.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class AnimationRunner {

    // gui is the whole screen of the game.
    private GUI gui;

    // framesPerSecond is the frames we show in every second.
    private int framesPerSecond;

    // sleeper is the sleeper that helps us sleep the program.
    private Sleeper sleeper;

    // MILLI_SECONDS is the default milli seconds value.
    public static final int MILLI_SECONDS = 1000;

    /**
     * AnimationRunner is the constructor method.
     * it sets the gui, framesPerSecond, and sleeper.
     *
     * @param gui is the whole screen of the game.
     * @param framesPerSecond is the frames we show in every second.
     * @param sleeper is the sleeper that helps us sleep the program.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * run is a method that runs an animation on the screen in framesPerSecond frames per second.
     *
     * @param animation is the animation we run on the screen.
     */
    public void run(Animation animation) {

        // Calculates the milliseconds per frame.
        int millisecondsPerFrame = MILLI_SECONDS / this.framesPerSecond;

        /*
         * shows frames on the screen until the animation should stop,
         * and does every frame with millisecondsPerFrame milliseconds.
         */
        while (!animation.shouldStop()) {

            // the start time we start from every frame.
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);

            // Sleeps the program in the remaining time.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
