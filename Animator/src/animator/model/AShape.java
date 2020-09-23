package animator.model;

/**
 * An abstract class that represents all two-dimensional shapes. This is used so when we make
 * new shapes it can inherit and easily use these methods that are universal for shapes.
 */
public abstract class AShape extends AViewOnlyShape implements IShape {

  /**
   * 5 parameter constructor that takes in position and color of the shape.
   *
   * @param x represents the x coordinate position.
   * @param y represents the y coordinate position.
   * @param red represents the amount of red in the shape.
   * @param green represents the amount of green in the shape.
   * @param blue represents the amount of blue in the shape.
   */
  protected AShape(int x, int y, int red, int green, int blue) {
    super(x, y, red, green, blue);
  }

  /**
   * A public constructor for AShape that takes in no arguments and is used to create shapes for
   * an animation.
   */
  public AShape() {
    super();
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public void setRed(int red) {
    checkLessThanZero(red);
    this.red = red;
  }

  @Override
  public void setGreen(int green) {
    checkLessThanZero(green);
    this.green = green;
  }

  @Override
  public void setBlue(int blue) {
    checkLessThanZero(blue);
    this.blue = blue;
  }

  // protected parameter check
  protected void checkLessThanZero(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Value is less than 0: " + value);
    }
  }
}
