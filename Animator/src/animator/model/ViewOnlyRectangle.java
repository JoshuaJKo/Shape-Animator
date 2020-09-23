package animator.model;


/**
 * View only class of the rectangle shape. Only has getter methods.
 */
public class ViewOnlyRectangle extends AViewOnlyShape {

  private final int width;
  private final int height;
  
  /**
   * Copies the contents of a mutable rectangle.
   *
   * @param r the rectangle you want to copy.
   */
  public ViewOnlyRectangle(Rectangle r) {
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
  public String toString() {
    return "rectangle";
  }

  @Override
  public IViewOnlyShape getCopy() {
    Rectangle r = new Rectangle(this);
    return new ViewOnlyRectangle(r);
  }

  @Override
  public IShape getMutableCopy() {
    return new Rectangle(this);
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
