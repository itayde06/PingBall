
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import gameobjects.AIPaddle;
import gameobjects.Ball;
import gameobjects.Paddle;

import java.util.Random;

public class BouncingBallGameManger extends GameManager {

    private static final int BORDER_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 100;
    private static final int BALL_RADIUS = 35;
    private static final float BALL_SPEED = 350;

    private static final int BRICK_HEIGHT = 35;
    private static final int BRICK_WIDTH = 150;
    private Ball ball;
    private Vector2 windowDimensions;
    private WindowController windowController;

    public BouncingBallGameManger(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForGameEnd();


    }

    private void checkForGameEnd() {
        float ballHeight = ball.getCenter().y();
        String prompt = "";
        if(ballHeight > windowDimensions.y()) {
            prompt = "You Lose!";
        }
        if(ballHeight < 0) {
            prompt = "You win!";

        }
        if (!prompt.isEmpty()) {
            prompt += " Play again?";
            if(windowController.openYesNoDialog(prompt) == true) {
                windowController.resetGame();

            }
            if (windowController.openYesNoDialog(prompt) == false) {
                windowController.closeWindow();
            }

        }
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        this.windowController = windowController;
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        windowController.setTargetFramerate(80);


        GameObject background = new GameObject(
                Vector2.ZERO,
                windowController.getWindowDimensions(),
                imageReader.readImage("assets/DARK_BG2_small.jpeg", false));
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
        gameObjects().addGameObject(background);



        Renderable ballImage = imageReader.readImage("assets/mockBall.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
        ball = new Ball(Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), ballImage, collisionSound);
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean())
            ballVelX *= -1;
        if (rand.nextBoolean())
            ballVelY *= -1;
        ball.setVelocity(new Vector2(ballVelX, ballVelY));

         windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5f));
        gameObjects().addGameObject(ball);


        Renderable paddleImage = imageReader.readImage("assets/paddle.png", false);
        //create userpaddle
        GameObject userPaddle = new Paddle(
                Vector2.ZERO,
                new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                paddleImage,
                inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y() - 30));
        gameObjects().addGameObject(userPaddle);

        // create ai Paddle
        GameObject aiPaddle = new AIPaddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), paddleImage, ball);
        aiPaddle.setCenter(new Vector2(windowDimensions.x() / 2, 30));
        gameObjects().addGameObject(aiPaddle);


        Renderable borderImage = imageReader.readImage(
                "assets/border.png", false);
        gameObjects().addGameObject(
                new GameObject(
                        Vector2.ZERO,
                        new Vector2(BORDER_WIDTH, windowDimensions.y()),
                        borderImage)
        );
        gameObjects().addGameObject(
                new GameObject(
                        new Vector2(windowDimensions.x() - BORDER_WIDTH, 0),
                        new Vector2(BORDER_WIDTH, windowDimensions.y()),
                        borderImage)
        );

//        Renderable brickImage = imageReader.readImage("assets/brick.png", false);
//
//                GameObject brickers = new GameObject(
//                        Vector2.ZERO,
//                        new Vector2(BRICK_WIDTH, BRICK_HEIGHT),
//                        brickImage);
//         gameObjects().addGameObject(brickers);
//
//
//
//    }

    }



    public static void main(String[] args) {

        new BouncingBallGameManger("Bricker", new Vector2(700, 500)).run();
    }


}