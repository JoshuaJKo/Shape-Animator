package animator.model;

import java.awt.Dimension;
import java.util.List;

/**
 * An interface that represents a viewable animation, keeping track of shapes and how they are
 * commanded to move, change size, and/or change color. This interface contains methods used to
 * view the data of a model, but not mutate it.
 */
public interface IViewOnlyAnimationModel {

  /**
   * A method that sets the screen size for the animation.
   *
   * @param top the top bound of the screen.
   * @param left the left bound of the screen.
   * @param width the width of the screen.
   * @param height the height of the screen.
   *
   * @throws IllegalStateException if screen has already been set.
   * @throws IllegalArgumentException if inputted parameters are invalid.
   */
  void setScreenSize(int top, int left, int width, int height);

  /**
   * Returns the size of the animation screen as a Dimension object.
   *
   * @return Dimension object representing size of screen.
   * @throws IllegalStateException if screen size has not been set yet.
   */
  Dimension getScreenSize();

  /**
   * Retrieves the top parameter of the screen.
   *
   * @return an integer representing the top of the screen.
   * @throws IllegalStateException if screen has not been set yet.
   */
  int getTop();

  /**
   * Retrieves the left parameter of the screen.
   *
   * @return an integer representing the left of the screen.
   * @throws IllegalStateException if screen has not been set yet.
   */
  int getLeft();

  /**
   * Retrieves the width parameter of the screen.
   *
   * @return an integer representing the width of the screen.
   * @throws IllegalStateException if screen has not been set yet.
   */
  int getWidth();

  /**
   * Retrieves the height parameter of the screen.
   *
   * @return an integer representing the height of the screen.
   * @throws IllegalStateException if screen has not been set yet.
   */
  int getHeight();

  /**
   * Retrieves the shape with given ID.
   *
   * @param id unique shape identifier.
   * @return a copy of requested shape object.
   * @throws if ID is invalid.
   */
  IViewOnlyShape getShape(String id);

  /**
   * Retrieves all of the shapes in a model.
   *
   * @return a list of view only shapes representing the shapes in the model.
   */
  List<IViewOnlyShape> getShapes();

  /**
   * Retrieves all of the commands for a shape with given ID.
   *
   * @param id the identifier of a shape
   * @return a list of commands representing the commands for a given shape.
   *
   * @throws IllegalArgumentException if ID is invalid.
   */
  List<ICommand> getCommands(String id);

  /**
   * Retrieves all unique shape identifiers from model.
   *
   * @return a list of strings representing the shape identifiers in the model.
   */
  List<String> getIds();

  /**
   * Retrieves the final tick of the animation.
   *
   * @returns an integer representing the final tick of the animation.
   */
  int getFinalTick();

  /**
   * Retrieves all of the KeyFrames for a shape with given ID.
   *
   * @param id the unique identifier for the shape.
   * @return a list of KeyFrames for a shape.
   */
  List<IKeyFrame> getKeyFrames(String id);

  /**
   * Retrieves the states of all mutable shapes at given tick.
   *
   * @param tick integer representing which tick the shapes should be set to.
   * @return a list of mutable shapes representing all of the shapes at the chosen tick.
   * @throws IllegalArgumentException if tick is below 1.
   */
  List<IShape> getShapesAtTick(int tick);

  /**
   * Retrieves the identifier of the top shape at a given point in time.
   *
   * @param tick the current time position of the shape.
   * @param x the x-coordinate of the shape.
   * @param y the y-coordinate of the shape.
   * @return the unique identifier of the shape.
   * @throws IllegalArgumentException if no shapes can be found at point.
   */
  String getIdOfTopShape(int tick, int x, int y);

}
