package animator.model;

/**
 * An abstract class that holds the position and color of all shapes. Class is view only, meaning
 * the fields of the shape are retrievable but not mutable.
 */
public abstract class AViewOnlyShape implements IViewOnlyShape {

  protected int x;
  protected int y;
  protected int red;
  protected int green;
  protected int blue;

  /**
   * Zero argument constructor initializes all fields to 0. This constructor is used to create
   * shape objects for an animation.
   */
  public AViewOnlyShape() {
    this.x = 0;
    this.y = 0;
    this.red = 0;
    this.green = 0;
    this.blue = 0;
  }

  // test constructor
  protected AViewOnlyShape(int x, int y, int red, int green, int blue) {
    checkConstructorParameters(red, green, blue);
    this.x = x;
    this.y = y;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  protected void checkConstructorParameters(int red, int green, int blue) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

}
