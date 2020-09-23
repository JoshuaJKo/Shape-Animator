package animator.view;

import animator.controller.IAnimationController;
import animator.model.IAnimationModel;

/**
 * A public interface representing the view for the animator.
 */
public interface IAnimationView {

  /**
   * Produces the view with given speed.
   *
   * @param speed integer representing speed of animation.
   * @throws IllegalArgumentException if speed is less than 1.
   */
  void view(int speed);

  /**
   * Produces the representation of view.
   *
   * @return String representing the view.
   */
  String toString();

  /**
   * Repaints the screen at given tick.
   *
   * @param tick the selected tick to refresh the view to.
   */
  void refresh(int tick);

  /**
   * Retrieves the corresponding controller for this view.
   *
   * @param model the animation model.
   * @param speed the desired speed of the output.
   * @return a controller object corresponding to this view.
   */
  IAnimationController getController(IAnimationModel model, int speed);
}
