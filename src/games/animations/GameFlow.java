
/*
 * Osher Elhadad
 *
 * This file defines a GameFlow that controls the flow of the game.
 */

package games.animations;

import biuoop.KeyboardSensor;
import games.GameLevel;
import games.levels.LevelInformation;
import listeners.lhelpers.Counter;

import java.util.List;

/**
 * This GameFlow class controls the levels flow of the game.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class GameFlow {

    // The border width of the screen.
    private int borderWidth;

    // The key board sensor of the game.
    private KeyboardSensor keyboardSensor;

    // The object that runs animations in the game.
    private AnimationRunner animationRunner;

    // scoreCounter counts the scores in the game.
    private Counter scoreCounter;

    // livesCounter counts the lives in the game.
    private Counter livesCounter;

    // The default decrease jump in the lives.
    public static final int DECREASE_LIVES_JUMP = 1;

    /**
     * GameFlow is the constructor method.
     * it sets the animationRunner, keyboardSensor, borderWidth, scoreCounter and livesCounter.
     *
     * @param ar is the object that runs animations in the game.
     * @param ks is the key board sensor of the game.
     * @param borderWidth is the border width of the scree.
     * @param lives is the number of lives in the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int borderWidth, int lives) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.borderWidth = borderWidth;
        this.scoreCounter = new Counter(GameLevel.EMPTY);
        this.livesCounter = new Counter(lives);
    }

    /**
     * runLevels is a method that runs the whole game and it's levels.
     *
     * @param levels the list of the levels in the game.
     */
    public void runLevels(List<LevelInformation> levels) {

        /*
         * A loop that goes over every level in the game and runs it.
         */
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.keyboardSensor, this.animationRunner,
                    this.borderWidth, levelInfo, this.scoreCounter, this.livesCounter);
            level.initialize();

            /*
             * A loop that runs the level if there is balls and blocks left.
             */
            while (level.getBallsCounter().getValue() > 0 && level.getBlocksCounter().getValue() > 0) {
                level.run();
            }

            /*
             * if the all blocks removed we continue to the next level.
             */
            if (level.getBlocksCounter().getValue() > 0) {

                // if the all balls removed then we decrease our lives.
                this.livesCounter.decrease(DECREASE_LIVES_JUMP);

                /*
                 * A loop that runs the level if there is lives left.
                 */
                while (this.livesCounter.getValue() != 0) {
                    level.initialBalls();
                    level.initialPaddle();

                    /*
                     * A loop that runs the level if there is balls and blocks left.
                     */
                    while (level.getBallsCounter().getValue() > 0 && level.getBlocksCounter().getValue() > 0) {
                        level.run();
                    }

                    /*
                     * if the all blocks removed we continue to the next level.
                     */
                    if (level.getBlocksCounter().getValue() <= 0) {
                        break;
                    } else {
                        this.livesCounter.decrease(DECREASE_LIVES_JUMP);
                    }
                }

                /*
                 * if the all lives over we shows the game over screen.
                 */
                if (this.livesCounter.getValue() == 0) {
                    this.animationRunner.run(new KeyPressStoppableAnimation(
                            this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                            new EndScreen("Game Over.", this.scoreCounter)));
                    return;
                }
            }
        }

        /*
         * if the all levels over and there is more lives we shows the winning screen.
         */
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen("You Win!", this.scoreCounter)));
    }
}
