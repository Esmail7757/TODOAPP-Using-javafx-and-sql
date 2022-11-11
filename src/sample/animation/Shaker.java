package sample.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {
    /*
    The class Animation provides the core functionality of all animations used in the JavaFX runtime.
    An animation can run in a loop by setting cycleCount. To make an animation run back and forth while looping,
set the autoReverse -flag.
Call play() or playFromStart() to play an Animation . The Animation progresses in the direction and speed s
pecified by rate, and stops when its duration is elapsed. An Animation with indefinite duration (a cycleCount of
 INDEFINITE) runs repeatedly until the stop() method is explicitly called, which will stop the
 running Animation and reset its play head to the initial position.

An Animation can be paused by calling pause(), and the next play() call will resume the Animation from where it was paused.

An Animation's play head can be randomly positioned, whether it is running or not.
If the Animation is running, the play head jumps to the specified position immediately
and continues playing from new position. If the Animation is not running, the
next play() will start the Animation from the specified position.

Inverting the value of rate toggles the play direction.
     */
    private TranslateTransition translateTransition;
    public Shaker(Node node){
        //node is any component that can be pass like button ,textFiled.....ete
        translateTransition= new TranslateTransition(Duration.millis(50),node); // passing time of animating
        translateTransition.setFromX(2f);
        translateTransition.setByY(25f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);


    }
    public void Shake(){
        translateTransition.playFromStart();
        // start the animation from the beginning
    }
}
