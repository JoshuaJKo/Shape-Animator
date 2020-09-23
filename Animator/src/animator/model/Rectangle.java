package animator.model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents a two-dimensional rectangle.
 */
public class Rectangle extends AShape {

  private int width;
  private int height;

  /**
   * A public constructor which initializes all fields to 0. Used to create shapes when building
   * an animation.
   */
  public Rectangle() {
    super();
    this.width = 0;
    this.height = 0;
  }

  /**
   * Public constructor used for testing and creating copies.
   *
   * @param x x position of rectangle.
   * @param y y position of rectangle.
   * @param width width of rectangle.
   * @param height height of rectangle.
   * @param r red RGB color value of rectangle.
   * @param g green RBG color value of rectangle.
   * @param b blue RBG color value of rectangle.
   */
  public Rectangle(int x, int y, int width, int height, int r, int g, int b) {
    super(x, y, r, g, b);
    checkLessThanZero(width);
    checkLessThanZero(height);
    this.width = width;
    this.height = height;
  }

  /**
   * Public constructor used for creating mutable copies of rectangle.
   *
   * @param r view only rectangle to be copied.
   */
  public Rectangle(ViewOnlyRectangle r) {
    super(r.getX(), r.getY(), r.getRed(), r.getGreen(), r.getBlue());
    this.width = r.getDimensionX();
    this.height = r.getDimensionY();
  }

  @Override
  public int getDimensionX() {
    return this.width;
  }

  @Override
    public int getDimensionY() {
    return this.height;
  }

  @Override
  public void setDimX(int dimX) {
    checkLessThanZero(dimX);
    this.width = dimX;
  }

  @Override
  public void setDimY(int dimY) {
    checkLessThanZero(dimY);
    this.height = dimY;
  }

  @Override
  public IViewOnlyShape getCopy() {
    return new ViewOnlyRectangle(this);
  }

  @Override
  public IShape getMutableCopy() {
    return new Rectangle(this.getX(), this.getY(), this.getDimensionX(), this.getDimensionY(),
            this.getRed(), this.getGreen(), this.getBlue());
  }

  @Override
  public String toString() {
    return "rectangle";
  }
  
  @Override
  public boolean equals(Object toCompare) {
    if (this == toCompare) {
      return true;
    }
    if (!(toCompare instanceof Rectangle)) {
      return false;
    }
    Rectangle confirmedCompare = (Rectangle) toCompare;

    return this.x == confirmedCompare.getX()
      && this. y == confirmedCompare.getY()
      && this.width == confirmedCompare.getDimensionX()
      && this.height == confirmedCompare.getDimensionY()
      && this.red == confirmedCompare.getRed()
      && this.green == confirmedCompare.getGreen()
      && this.blue == confirmedCompare.getBlue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, width, height, red, green, blue);
  }

  @Override
  public void draw(Graphics2D g2) {
    g2.setColor(new Color(red, green, blue));
    g2.fillRect(x, y, width, height);
  }

  @Override
  public boolean inShape(int x, int y) {
    int topCornerX = this.x;
    int topCornerY = this.y;
    int bottomCornerX = this.x + (this.width);
    int bottomCornerY = this.y + (this.height);

    return (x > topCornerX && x < bottomCornerX && y > topCornerY && y < bottomCornerY);
  }
}
