package animator.model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents a two-dimensional oval. Can be used as a shape in an animation.
 */
public class Oval extends AShape {

  private int radiusX;
  private int radiusY;

  /**
   * A public constructor which initializes all fields to 0. Used to create shapes when building
   * an animation.
   */
  public Oval() {
    super();
    this.radiusX = 0;
    this.radiusY = 0;
  }

  /**
   * Public constructor used for testing and creating copies.
   *
   * @param x x position of oval.
   * @param y y position of oval.
   * @param radiusX x radius of oval.
   * @param radiusY y radius of oval.
   * @param r red RGB color value of oval.
   * @param g green RBG color value of oval.
   * @param b blue RBG color value of oval.
   */
  public Oval(int x, int y, int radiusX, int radiusY, int r, int g, int b) {
    super(x, y, r, g, b);
    checkLessThanZero(radiusX);
    checkLessThanZero(radiusY);
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  /**
   * Public constructor used for creating mutable copies of oval.
   *
   * @param o view only oval to be copied.
   */
  public Oval(ViewOnlyOval o) {
    super(o.getX(), o.getY(), o.getRed(), o.getGreen(), o.getBlue());
    this.radiusX = o.getDimensionX();
    this.radiusY = o.getDimensionY();
  }

  @Override
  public int getDimensionX() {
    return this.radiusX;
  }

  @Override
  public int getDimensionY() {
    return this.radiusY;
  }
  
  @Override
  public void setDimX(int dimX) {
    if (dimX < 0) {
      throw new IllegalArgumentException();
    }
    this.radiusX = dimX;
  }

  @Override
  public void setDimY(int dimY) {
    if (dimY < 0) {
      throw new IllegalArgumentException();
    }
    this.radiusY = dimY;
  }

  @Override
  public IViewOnlyShape getCopy() {
    return new ViewOnlyOval(this);
  }

  @Override
  public IShape getMutableCopy() {
    return new Oval(this.getX(), this.getY(), this.getDimensionX(), this.getDimensionY(),
            this.getRed(), this.getGreen(), this.getBlue());
  }

  @Override
  public String toString() {
    return "oval";
  }
  
  @Override
  public boolean equals(Object toCompare) {

    if (this == toCompare) {
      return true;
    }
    
    if (!(toCompare instanceof Oval)) {
      return false;
    }
  
    Oval confirmedCompare = (Oval) toCompare;
    
    return this.x == confirmedCompare.getX()
      && this. y == confirmedCompare.getY()
      && this.radiusX == confirmedCompare.getDimensionX()
      && this.radiusY == confirmedCompare.getDimensionY()
      && this.red == confirmedCompare.getRed()
      && this.green == confirmedCompare.getGreen()
      && this.blue == confirmedCompare.getBlue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, radiusX, radiusY, red, green, blue);
  }

  @Override
  public void draw(Graphics2D g2) {
    g2.setColor(new Color(red, green, blue));
    g2.fillOval(x, y, radiusX, radiusY);
  }

  @Override
  public boolean inShape(int x, int y) {
    int topCornerX = this.x;
    int topCornerY = this.y;
    int bottomCornerX = this.x + (2 * this.radiusX);
    int bottomCornerY = this.y + (2 * this.radiusY);

    return (x > topCornerX && x < bottomCornerX && y > topCornerY && y < bottomCornerY);
  }
}
