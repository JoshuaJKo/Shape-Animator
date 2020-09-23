package animator.controller;

/**
 * A public interface representing the animation editor controller. This controller offers various
 * functionality to edit the animation.
 */
public interface IAnimationEditorController extends IAnimationController {

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Restarts the animation.
   */
  void restart();

  /**
   * Increases the speed of the animation by 1 tick per second.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the animation by 1 tick per second.
   */
  void decreaseSpeed();

  /**
   * Toggles the looping of the animation.
   */
  void toggleLooping();

  /**
   * Handles an arbitrary mouse click at a given point.
   *
   * @param x the x value of the mouse click.
   * @param y the y value of the mouse click.
   */
  void handleMouseClick(int x, int y);

  /**
   * Handles a GUI command. This method takes in inputs from the view.
   *
   * @param command the string representation of a command for the controller to execute.
   */
  void handleGuiCommand(String command);

}
