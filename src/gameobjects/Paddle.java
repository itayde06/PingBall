package gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class Paddle extends GameObject {

    private static final float MOVMENT_SPEED = 300;
//    private static final int MIN_DISTANCE_FROM_SCREEN_EDGE = 100; לזכור לסיים

    private final UserInputListener inputListener;
    private Vector2 windowDimensions;

    /**
     * Construct a new GameObject instance.
     *
     * @param inputListenern
     * @param topLeftCorner  Position of the object, in window coordinates (pixels).
     *                       Note that (0,0) is the top-left corner of the window.
     * @param dimensions     Width and height in window coordinates.
     * @param renderable     The renderable representing the object. Can be null, in which case
     *                       the GameObject will not be rendered.
     * @param inputListener
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, UserInputListener inputListener) {

        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movmentDir = Vector2.ZERO;
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
          movmentDir = movmentDir.add(Vector2.LEFT);

        }
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT))  {
            movmentDir = movmentDir.add(Vector2.RIGHT);

        }


        setVelocity(movmentDir.mult(MOVMENT_SPEED));
    }
}

