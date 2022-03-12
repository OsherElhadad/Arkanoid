
/*
 * Osher Elhadad
 *
 * This file defines a GameLevel that can run on the screen.
 */

package games;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.cobjects.Block;
import collisions.chelpers.Collidable;
import collisions.chelpers.GameEnvironment;
import collisions.cobjects.Paddle;
import games.animations.Animation;
import games.animations.AnimationRunner;
import games.animations.CountdownAnimation;
import games.animations.KeyPressStoppableAnimation;
import games.animations.PauseScreen;
import games.levels.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import listeners.lobjects.BallProducer;
import listeners.lobjects.BallRemover;
import listeners.lobjects.BlockRemover;
import listeners.lhelpers.Counter;
import listeners.lobjects.ScoreTrackingListener;
import sprites.movement.Velocity;
import sprites.shelpers.Sprite;
import sprites.shelpers.SpriteCollection;
import sprites.sobjects.Ball;
import sprites.sobjects.decoratorframes.Frame;
import sprites.sobjects.ScoreIndicator;

import java.awt.Color;

/**
 * This GameLevel class has SpriteCollection, GameEnvironment,
 * GUI, Frame and a border width,
 * it can run the game on the screen,
 * and implements Animation interface.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class GameLevel implements Animation {

    // sprites is the SpriteCollection of the game (the objects that are alive).
    private SpriteCollection sprites;

    // environment is the GameEnvironment of the game (the objects that are collidable).
    private GameEnvironment environment;

    // The KeyboardSensor of the game.
    private KeyboardSensor keyboard;

    // runner is the AnimationRunner object of the game.
    private AnimationRunner runner;

    // The levelInformation of the game.
    private LevelInformation levelInformation;

    // The paddle of the game.
    private Paddle paddle;

    // running is a boolean variable that says if the game is running or not.
    private boolean running;

    // blocksCounter counts the blocks in the game.
    private Counter blocksCounter;

    // ballsCounter counts the balls in the game.
    private Counter ballsCounter;

    // scoreCounter counts the scores in the game.
    private Counter scoreCounter;

    // livesCounter counts the lives remain in the game.
    private Counter livesCounter;

    // borderWidth is the width of the border blocks of the game screen.
    private int borderWidth;

    // RADIUS is the default radius of the ball in the game.
    public static final int RADIUS = 5;

    // FRAMES_PER_SECOND is the default frames per second.
    public static final int FRAMES_PER_SECOND = 60;

    // TWO_BORDERS represents 2 borders.
    public static final int TWO_BORDERS = 2;

    // ALMOST_TWO_BORDERS represents 1.9 border.
    public static final double ALMOST_TWO_BORDERS = 1.9;

    // HALF represent the 2 parts (integer / 2).
    public static final int HALF = 2;

    // SCORE_LEVEL_JUMP is the default increase in the score when the level is up.
    public static final int SCORE_LEVEL_JUMP = 100;

    // EMPTY is the value 0.
    public static final int EMPTY = 0;

    // REMOVER_BORDER_COLOR is the color of the remover block's border.
    public static final Color REMOVER_BORDER_COLOR = Color.RED;

    // REMOVER_BLOCK_COLOR is the color of the remover block.
    public static final Color REMOVER_BLOCK_COLOR = Color.BLACK;

    // PRODUCER_BORDER_COLOR is the color of the remover block's border.
    public static final Color PRODUCER_BORDER_COLOR = Color.BLUE;

    // PRODUCER_BLOCK_COLOR is the color of the remover block.
    public static final Color PRODUCER_BLOCK_COLOR = Color.MAGENTA;

    // ONE_OBJECT represents 1 object (block or ball).
    public static final int ONE_OBJECT = 1;

    // The default width of the borders.
    public static final int BORDER_WIDTH = 25;

    // The default width of the screen.
    public static final int WIDTH = 800;

    // The default height of the screen.
    public static final int HEIGHT = 600;

    // The default speed of a new ball.
    public static final int BALL_SPEED = 6;

    // The default number of collisions of a regular block.
    public static final int ONE_COLLISION = 1;

    // The default number of collisions of a gray block.
    public static final int TWO_COLLISIONS = 2;

    // The default y times border width we adds to the balls location.
    public static final int ADD_TO_Y_BALLS = 3;

    // The default number of seconds we count down.
    public static final int NUMBER_OF_SECONDS = 2;

    // The default number we starts to count from.
    public static final int COUNTS_FROM = 3;

    /**
     * GameLevel is the constructor method.
     * it initialize the SpriteCollection, the GameEnvironment, the random
     * and sets the gui, levelInformation, frame, border width, and counters.
     *
     * @param keyboard is the keyboard sensor of the game.
     * @param runner is the animation runner of the game.
     * @param borderWidth is the width of the border of the screen.
     * @param levelInformation is the levelInformation of the game.
     * @param scoreCounter is the score achieved the game.
     * @param livesCounter is the lives remain in the game.
     */
    public GameLevel(KeyboardSensor keyboard, AnimationRunner runner, int borderWidth,
                     LevelInformation levelInformation, Counter scoreCounter, Counter livesCounter) {
        this.runner = runner;
        this.running = false;
        this.keyboard = keyboard;
        this.borderWidth = borderWidth;
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(EMPTY);
        this.ballsCounter = new Counter(EMPTY);
        this.scoreCounter = scoreCounter;
        this.livesCounter = livesCounter;
        this.paddle = null;
    }

    /**
     * getEnvironment is a method that returns this GameEnvironment of the game.
     *
     * @return the GameEnvironment of the game.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * getBorderWidth is a method that returns this width of the borders of the game screen.
     *
     * @return the width of the borders of the game screen.
     */
    public int getBorderWidth() {
        return this.borderWidth;
    }

    /**
     * getSprites is a method that returns this SpriteCollection of the game.
     *
     * @return the SpriteCollection of the game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * getBallsCounter is a method that returns this ballsCounter of the game.
     *
     * @return the ballCounter of the game.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * getBlocksCounter is a method that returns this blocksCounter of the game.
     *
     * @return the blocksCounter of the game.
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * addCollidable is a method that add the collidable we got to this GameEnvironment.
     *
     * @param c is the collidable we add to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * addSprite is a method that add the sprite we got to this sprites collection.
     *
     * @param s is the Sprite we add to the sprites collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removeCollidable is a method that removes the collidable we got from this GameEnvironment.
     *
     * @param c is the collidable we remove from the game environment.
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }

    /**
     * removeSprite is a method that removes the sprite we got from this sprites collection.
     *
     * @param s is the Sprite we remove from the sprites collection.
     */
    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, scoreIndicator, background and Balls
     * (and Paddle)
     * and add them to the game.
     *
     */
    public void initialize() {
        this.levelInformation.getBackground().addToGame(this);
        this.addBorders();
        this.addScoreIndicator();
        this.initialPaddle();
        this.initialBalls();
        this.initialBlocks();
    }

    /**
     * initialPaddle is a method that adds a new paddle to the game (with his speed and width).
     *
     */
    public void initialPaddle() {
        // The upper left point of the rectangle paddle.
        Point point = new Point(this.levelInformation.frameWidth() / HALF
                - (double) this.levelInformation.paddleWidth() / HALF,
                this.levelInformation.frameHeight() - TWO_BORDERS * this.getBorderWidth());

        if (this.paddle != null) {
            this.paddle.getRectangle().setUpperLeft(point);
            return;
        }

        // The rectangle of the paddle.
        Rectangle rectangle = new Rectangle(point, this.levelInformation.paddleWidth(),
                this.getBorderWidth(), Color.YELLOW);

        // The upper left point of the rectangle frame of the paddle.
        Point point2 = new Point(this.getBorderWidth(),
                this.levelInformation.frameHeight() - TWO_BORDERS * this.getBorderWidth());

        // The rectangle of the frame of the paddle.
        Rectangle rectangle2 = new Rectangle(point2,
                this.levelInformation.frameWidth() - TWO_BORDERS * this.getBorderWidth(),
                this.getBorderWidth(), null);

        // Creates the paddle and adds it to the game.
        Paddle paddle1 = new Paddle(this.keyboard, this.levelInformation.paddleSpeed(),
                rectangle, new Frame(rectangle2));
        paddle1.addToGame(this);
        this.paddle = paddle1;
    }

    /**
     * initialBalls is a method that adds the balls of this level to the game.
     *
     */
    public void initialBalls() {
        Point point;
        Ball ball;

        /*
         * A loop that creates the all balls of the level,
         * with their velocities, and adds them to the game.
         */
        for (Velocity velocity : this.levelInformation.initialBallVelocities()) {
            point = new Point(this.levelInformation.frameWidth() / HALF,
                    this.levelInformation.frameHeight() - (ADD_TO_Y_BALLS * this.getBorderWidth()));
            ball = new Ball(point, RADIUS, Color.WHITE, this.levelInformation.getFrame(), this.getEnvironment());
            ball.setVelocity(velocity);
            this.ballsCounter.increase(ONE_OBJECT);
            ball.addToGame(this);
        }
    }

    /**
     * initialBlocks is a method that adds the whole blocks of this level to the game.
     *
     */
    public void initialBlocks() {

        /*
         * A loop that creates the all blocks of the level,
         * and adds them to the game.
         */
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);

            /*
             * adds a remover block and it's listener,
             * a producer block and it's listener,
             * and regular block.
             */
            if (block.getCollisionRectangle().getBorderColor().equals(REMOVER_BORDER_COLOR)) {
                block.addHitListener(new BallRemover(this, this.ballsCounter));
            } else if (block.getCollisionRectangle().getBorderColor().equals(PRODUCER_BORDER_COLOR)) {
                block.addHitListener(new BallProducer(this, this.ballsCounter));
            } else {
                this.blocksCounter.increase(ONE_OBJECT);
                Counter hits;

                /*
                 * if the block's color is gray then it should be hit 2 times to disappear,
                 * other it's a regular block that disappears after one hit.
                 */
                if (block.getColor().equals(Color.GRAY)) {
                    hits = new Counter(TWO_COLLISIONS);
                } else {
                    hits = new Counter(ONE_COLLISION);
                }
                block.addHitListener(new BlockRemover(this, this.blocksCounter, hits));
                block.addHitListener(new ScoreTrackingListener(this.scoreCounter));
            }
        }
    }

    /**
     * produceBall is a method that produces a new random ball to the game- from hit with the ball producer
     * (in the x, above paddle and under the blocks rows).
     *
     */
    public void produceBall() {
        Point point = new Point(this.levelInformation.frameWidth() / HALF,
                this.levelInformation.frameHeight() - (ADD_TO_Y_BALLS * this.getBorderWidth()));
        Ball ball = new Ball(point, RADIUS, Color.WHITE, this.levelInformation.getFrame(), this.getEnvironment());
        ball.setVelocity(Velocity.fromAngleAndSpeed(0, BALL_SPEED));
        ball.addToGame(this);
    }

    /**
     * addBorders is a method that adds the borders blocks to the game (with this border width).
     *
     */
    public void addBorders() {

        // Creates the up border of the game.
        Point point1 = new Point(this.levelInformation.getFrame().getRectangle().getUpperLeft().getX(),
                this.getBorderWidth());
        Block b1 = new Block(new Rectangle(point1, this.levelInformation.frameWidth(),
                this.getBorderWidth(), Color.GRAY, Color.GRAY));

        // Creates the left border of the game.
        Point point2 = new Point(this.levelInformation.getFrame().getRectangle().getUpperLeft().getX(),
                this.getBorderWidth() * TWO_BORDERS);
        Block b2 = new Block(new Rectangle(point2, this.getBorderWidth(),
                this.levelInformation.frameHeight() - (this.getBorderWidth() * ALMOST_TWO_BORDERS),
                Color.GRAY, Color.GRAY));

        // Creates the right border of the game.
        Point point3 = new Point(this.levelInformation.frameWidth()
                - this.getBorderWidth(), this.getBorderWidth() * TWO_BORDERS);
        Block b3 = new Block(new Rectangle(point3, this.getBorderWidth(),
                this.levelInformation.frameHeight() - (this.getBorderWidth() * ALMOST_TWO_BORDERS),
                Color.GRAY, Color.GRAY));

        // Creates the down border of the game.
        Point point4 = new Point(this.getBorderWidth(), this.levelInformation.frameHeight());
        Block b4 = new Block(new Rectangle(point4,
                this.levelInformation.frameWidth() - (TWO_BORDERS * this.getBorderWidth()),
                this.getBorderWidth(), Color.GRAY, Color.GRAY));

        // Adds the borders blocks to the game.
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
        b4.addHitListener(new BallRemover(this, this.ballsCounter));
    }

    /**
     * addScoreIndicator is a method that adds a new scoreIndicator in the top of the window.
     *
     */
    public void addScoreIndicator() {

        // Creates the top scoreIndicator of the game.
        ScoreIndicator scoreIndicator = new ScoreIndicator(
                new Rectangle(this.levelInformation.getFrame().getRectangle().getUpperLeft(),
                this.levelInformation.getFrame().getRectangle().getWidth(), this.getBorderWidth(), Color.WHITE),
                this.scoreCounter, this.livesCounter, this.levelInformation.levelName());

        // Adds the scoreIndicator to the game.
        scoreIndicator.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return (!this.running);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        /*
         * if there is no balls or blocks we exit,
         * and if there is no blocks it also increases the score (in 100 points).
         */
        if (this.blocksCounter.getValue() == EMPTY || this.ballsCounter.getValue() == EMPTY) {
            if (this.blocksCounter.getValue() == EMPTY) {
                this.scoreCounter.increase(SCORE_LEVEL_JUMP);
            }
            this.running = false;
        }

        /*
         * if we pressed on p,
         * we shows the pause screen.
         */
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        // Draws the whole sprites, shows them on the screen, and dynamic change the sprites.
        this.getSprites().drawAllOn(d);
        this.getSprites().notifyAllTimePassed();
    }

    /**
     * Run the game -- start the animation loop.
     *
     */
    public void run() {

        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(NUMBER_OF_SECONDS, COUNTS_FROM,
                this.getSprites(), this.levelInformation.getFrame().getColor()));
        this.running = true;

        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }
}
