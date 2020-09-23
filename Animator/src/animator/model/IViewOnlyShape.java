package animator.model;

/**
 * An interface representing a shape which is view only, meaning
 * the fields of the shape are retrievable but not mutable.
 */
public interface IViewOnlyShape {

  /**
   * Retrieves the X position of the shape.
   *
   * @return an integer representing the X position of the shape.
   */
  int getX();

  /**
   * Retrieves the Y position of the shape.
   *
   * @return an integer representing the Y position of the shape.
   */
  int getY();

  /**
   * Retrieves the RGB red value of the shape.
   *
   * @return an integer representing the RGB red value of the shape.
   */
  int getRed();

  /**
   * Retrieves the RGB green value of the shape.
   *
   * @return an integer representing the RGB green value of the shape.
   */
  int getGreen();

  /**
   * Retrieves the RGB blue value of the shape.
   *
   * @return an integer representing the RGB blue value of the shape.
   */
  int getBlue();

  /**
   * Retrieves the X dimension of the shape.
   *
   * @return an integer representing the X dimension of the shape.
   */
  int getDimensionX();

  /**
   * Retrieves the Y dimension of the shape.
   *
   * @return an integer representing the Y dimension of the shape.
   */
  int getDimensionY();

  /**
   * Returns a string representation of the shape.
   *
   * @return a string representing the shape.
   */
  String toString();

  /**
   * Creates a deep copy of this shape.
   *
   * @return a view only copy of this shape.
   */
  IViewOnlyShape getCopy();

  /**
   * Returns a mutable copy of this shape.
   *
   * @return an IShape object representing a mutable copy of this shape.
   */
  IShape getMutableCopy();

  /**
   * Returns a boolean representing if an x, y point is within a shape.
   *
   * @param x the x coordinate to be checked.
   * @param y the y coordinate to be checked.
   * @return a boolean representing if x,y is within shape.
   */
  boolean inShape(int x, int y);

}
