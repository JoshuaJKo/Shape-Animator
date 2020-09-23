package animator.model;

/**
 * View only class of the oval shape. Only has getter methods.
 */
public class ViewOnlyOval extends AViewOnlyShape {

  private final int radiusX;
  private final int radiusY;
  
  /**
   * Copies the contents of a mutable oval.
   *
   * @param o the oval you want to copy.
   */
  public ViewOnlyOval(Oval o) {
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
  public String toString() {
    return "oval";
  }

  @Override
  public IViewOnlyShape getCopy() {
    Oval temp = new Oval(this);
    return new ViewOnlyOval(temp);
  }

  @Override
  public IShape getMutableCopy() {
    return new Oval(this);
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
