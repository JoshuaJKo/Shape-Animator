package animator.model;

/**
 * An interface that represents an animation command on a shape.
 */
public interface ICommand {

  /**
   * Mutates a shape to be at the updated state during a command at a given time.
   *
   * @param time the time that the shape is at.
   * @param shape the shape that is being mutated at during the given time.
   * @return the shape with the new set position, color, width, and height.
   *
   * @throws IllegalArgumentException if the time is invalid.
   * @throws IllegalArgumentException if the shape is null.
   */
  IShape getState(int time, IShape shape);

  /**
   * Returns the start time of a command.
   *
   * @return an integer representing the start time of a command.
   */
  int getStartTime();

  /**
   * Returns the start x position of a command.
   *
   * @return an integer representing the start x position of a command.
   */
  int getStartX();

  /**
   * Returns the start y position of a command.
   *
   * @return an integer representing the start y position of a command.
   */
  int getStartY();

  /**
   * Returns the start width of a command.
   *
   * @return an integer representing the start width of a command.
   */
  int getStartWidth();

  /**
   * Returns the start height of a command.
   *
   * @return an integer representing the start height of a command.
   */
  int getStartHeight();

  /**
   * Returns the start RGB red value of a command.
   *
   * @return an integer representing the start RGB red value of a command.
   */
  int getStartRed();

  /**
   * Returns the start RGB green value of a command.
   *
   * @return an integer representing the start RGB green value of a command.
   */
  int getStartGreen();

  /**
   * Returns the start RGB blue value of a command.
   *
   * @return an integer representing the start RGB blue value of a command.
   */
  int getStartBlue();

  /**
   * Returns the end time of a command.
   *
   * @return an integer representing the end time of a command.
   */
  int getEndTime();

  /**
   * Returns the end x position of a command.
   *
   * @return an integer representing the end x position of a command.
   */
  int getEndX();

  /**
   * Returns the end y position of a command.
   *
   * @return an integer representing the end y position of a command.
   */
  int getEndY();

  /**
   * Returns the end width of a command.
   *
   * @return an integer representing the end width of a command.
   */
  int getEndWidth();

  /**
   * Returns the end height of a command.
   *
   * @return an integer representing the end height of a command.
   */
  int getEndHeight();

  /**
   * Returns the end RGB red value of a command.
   *
   * @return an integer representing the end RGB red value of a command.
   */
  int getEndRed();

  /**
   * Returns the end RGB green value of a command.
   *
   * @return an integer representing the end RGB green value of a command.
   */
  int getEndGreen();

  /**
   * Returns the end RGB blue value of a command.
   *
   * @return an integer representing the end RGB blue value of a command.
   */
  int getEndBlue();

  /**
   * Returns the string representation of a command.
   *
   * @return a string representing a command.
   */
  String toString();

  /**
   * Returns the type of command in string form.
   *
   * @return a string representing the type of a command.
   */
  String getType();

  /**
   * Returns a copy of this command.
   *
   * @return a command representing a copy of this command.
   */
  ICommand getCopy();
}
