package animator.model;

/**
 * A public interface representing an animation KeyFrame. This represents a single motion endpoint
 * that a shape is associated with in an animation.
 */
public interface IKeyFrame {

  /**
   * Retrieves the tick field of this KeyFrame.
   *
   * @return int representing tick of this KeyFrame.
   */
  int getTick();

  /**
   * Retrieves the x field of this KeyFrame.
   *
   * @return int representing x value of this KeyFrame.
   */
  int getX();

  /**
   * Retrieves the y field of this KeyFrame.
   *
   * @return int representing y value of this KeyFrame.
   */
  int getY();

  /**
   * Retrieves the width field of this KeyFrame.
   *
   * @return int representing width value of this KeyFrame.
   */
  int getWidth();

  /**
   * Retrieves the height field of this KeyFrame.
   *
   * @return int representing height value of this KeyFrame.
   */
  int getHeight();

  /**
   * Retrieves the red field of this KeyFrame.
   *
   * @return int representing red value of this KeyFrame.
   */
  int getRed();

  /**
   * Retrieves the green field of this KeyFrame.
   *
   * @return int representing green value of this KeyFrame.
   */
  int getGreen();

  /**
   * Retrieves the blue field of this KeyFrame.
   *
   * @return int representing blue value of this KeyFrame.
   */
  int getBlue();

  /**
   * Returns a copy of this KeyFrame.
   *
   * @return a keyframe object representing a copy of this keyframe.
   */
  IKeyFrame getCopy();

  /**
   * Converts two IKeyFrame objects into an ICommand object.
   *
   * @param second the next keyframe (endpoint) of a shape.
   * @return an ICommand object representing the merging of two KeyFrame objects.
   */
  ICommand convertToMotionCommand(IKeyFrame second);

  /**
   * Mutates a shape to be at the proper state at a given tick in its animation.
   *
   * @param time the tick that the shape's position should reflect.
   * @param second the next keyframe of the animation.
   * @param shape the shape to be mutated.
   * @return a shape object at the proper state in its animation.
   * @throws IllegalArgumentException if shape is null.
   */
  IShape getState(int time, IKeyFrame second, IShape shape);

  /**
   * Returns a string representation of the keyframe.
   *
   * @return a string representation of this keyframe.
   */
  String toString();

}
