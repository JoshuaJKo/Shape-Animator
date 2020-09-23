package animator.model;

import java.awt.Graphics2D;

/**
 * An interface to represent a two-dimensional shape. Objects of this interface have retrievable
 * and mutable fields.
 */
public interface IShape extends IViewOnlyShape {

  /**
   * Sets the X position of the shape.
   *
   * @param x integer representing what the shape's X position should be set to.
   */
  void setX(int x);

  /**
   * Sets the Y position of the shape.
   *
   * @param y integer representing what the shape's Y position should be set to.
   */
  void setY(int y);

  /**
   * Sets the RGB red value of the shape.
   *
   * @param red integer representing what the shape's RGB red value should be set to.
   * @throws IllegalArgumentException if red is less than 0.
   */
  void setRed(int red);

  /**
   * Sets the RGB green value of the shape.
   *
   * @param green integer representing what the shape's RGB green value should be set to.
   * @throws IllegalArgumentException if green is less than 0.
   */
  void setGreen(int green);

  /**
   * Sets the RGB blue value of the shape.
   *
   * @param blue integer representing what the shape's RGB blue value should be set to.
   * @throws IllegalArgumentException if blue is less than 0.
   */
  void setBlue(int blue);

  /**
   * Sets the X dimension of the shape.
   *
   * @param dimX integer representing what the shape's X dimension should be set to.
   * @throws IllegalArgumentException if dimX is less than 0.
   */
  void setDimX(int dimX);

  /**
   * Sets the Y dimension of the shape.
   *
   * @param dimY integer representing what the shape's Y dimension should be set to.
   * @throws IllegalArgumentException if dimY is less than 0.
   */
  void setDimY(int dimY);

  /**
   * Draws this shape on a given 2D plane.
   *
   * @param g2 Graphics2D object representing plane to draw on.
   */
  void draw(Graphics2D g2);

}
