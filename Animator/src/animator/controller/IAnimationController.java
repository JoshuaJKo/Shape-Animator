package animator.controller;

/**
 * A public interface representing the controller for an animation.
 */
public interface IAnimationController {

  /**
   * Starts the controller and begins the animation.
   */
  void play();

  /**
   * Handles an arbitrary timer tick.
   */
  void handleTick();

  /**
   * Returns the string representation of the animation depending on the view.
   *
   * @return a string representing the animation.
   */
  String returnOutput();

}
