package animator.model;

/**
 * An interface that represents a mutable animation, keeping track of shapes and how they are
 * commanded to move, change size, and/or change color. Shapes and commands can be added using
 * methods in this interface. This interface extends its read-only counterpart in order to add
 * functionality to the existing model interface.
 */
public interface IAnimationModel extends IViewOnlyAnimationModel {

  /**
   * Adds a shape to the HashMap of shapes as the key to a unique ID.
   *
   * @param id Unique ID to identify the shape in the HashMap.
   * @param shape the shape that is going to be added to the HashMap.
   *
   * @throws IllegalArgumentException if the ID is already in use.
   * @throws IllegalArgumentException if shape is outside of screen parameters.
   * @throws IllegalStateException if screen size has not been set.
   */
  void addShape(String id, IShape shape);

  /**
   * Adds a command to the list of commands for a given shape ID.
   *
   * @param id unique shape identifier.
   * @param command the command that is going to be put into the list and tied to the shape.
   *
   * @throws IllegalArgumentException if the key (ID) is invalid.
   * @throws IllegalArgumentException if the command parameters are invalid.
   * @throws IllegalStateException if screen sizes have not been set yet.
   */
  void addCommand(String id, ICommand command);

  /**
   * Removes a shape from the shape HashMap with the given identifier (key).
   *
   * @param id unique shape identifier.
   *
   * @throws IllegalArgumentException if the key (ID) is invalid.
   * @throws IllegalStateException if screen sizes have not been set yet.
   */
  void removeShape(String id);

  /**
   * Removes the last command from the list of commands of a shape with the given identifier (key).
   *
   * @param id unique shape identifier.
   *
   * @throws IllegalArgumentException if the key (ID) is invalid.
   * @throws IllegalArgumentException if command list is empty.
   */
  void removeLastCommand(String id);

  /**
   * Adds a KeyFrame to a shape with given ID.
   *
   * @param id unique shape identifier.
   * @param k KeyFrame object to be added.
   * @throws IllegalArgumentException if ID is invalid.
   */
  void addKeyFrame(String id, IKeyFrame k);

  /**
   * Deletes a KeyFrame from a shape with given ID.
   *
   * @param id unique shape identifier.
   * @param tick the tick of the KeyFrame to be removed.
   * @throws IllegalArgumentException if no KeyFrame can be found at tick.
   */
  void deleteKeyFrame(String id, int tick);

}
