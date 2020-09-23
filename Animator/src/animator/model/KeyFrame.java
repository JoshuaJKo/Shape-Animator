package animator.model;

import java.util.Objects;

/**
 * A public class representing an animation KeyFrame. This represents a single motion endpoint
 * that a shape is associated with in an animation.
 */
public class KeyFrame implements IKeyFrame {

  private int tick;
  private int x;
  private int y;
  private int width;
  private int height;
  private int red;
  private int green;
  private int blue;

  /**
   * A public constructor to initialize the fields of a KeyFrame.
   *
   * @param tick the tick of this KeyFrame.
   * @param x the x value of this KeyFrame.
   * @param y the y value of this KeyFrame.
   * @param width the width value of this KeyFrame.
   * @param height the height value of this KeyFrame.
   * @param red the red value of this KeyFrame.
   * @param green the green value of this KeyFrame.
   * @param blue the blue value of this KeyFrame.
   */
  public KeyFrame(int tick, int x, int y, int width, int height, int red, int green, int blue) {
    checkForInvalidInputs(tick < 0);
    checkForInvalidInputs(width < 1);
    checkForInvalidInputs(height < 1);
    checkForInvalidInputs(red < 0 || red > 255);
    checkForInvalidInputs(green < 0 || green > 255);
    checkForInvalidInputs(blue < 0 || blue > 255);
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * A public constructor that copies a KeyFrame object.
   *
   * @param k keyframe to be copied.
   */
  public KeyFrame(KeyFrame k) {
    this.tick = k.getTick();
    this.x = k.getX();
    this.y = k.getY();
    this.width = k.getWidth();
    this.height = k.getHeight();
    this.red = k.getRed();
    this.green = k.getGreen();
    this.blue = k.getBlue();
  }

  @Override
  public int getTick() {
    return this.tick;
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
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
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

  @Override
  public IKeyFrame getCopy() {
    return new KeyFrame(this);
  }

  private void checkForInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public ICommand convertToMotionCommand(IKeyFrame second) {
    return new MotionCommand(this.getTick(), this.getX(), this.getY(), this.getWidth(),
            this.getHeight(), this.getRed(), this.getGreen(), this.getBlue(), second.getTick(),
            second.getX(), second.getY(), second.getWidth(), second.getHeight(), second.getRed(),
            second.getGreen(), second.getBlue());
  }

  @Override
  public IShape getState(int time, IKeyFrame second, IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }

    float period = second.getTick() - this.getTick();
    int startTime = this.getTick();
    shape.setX(getPointAt(time, this.getX(), second.getX(), period, startTime));
    shape.setY(getPointAt(time, this.getY(), second.getY(), period, startTime));
    shape.setDimX(getPointAt(time, this.getWidth(), second.getWidth(), period, startTime));
    shape.setDimY(getPointAt(time, this.getHeight(), second.getHeight(), period, startTime));
    shape.setRed(getPointAt(time, this.getRed(), second.getRed(), period, startTime));
    shape.setGreen(getPointAt(time, this.getGreen(), second.getGreen(), period, startTime));
    shape.setBlue(getPointAt(time, this.getBlue(), second.getBlue(), period, startTime));

    return shape;
  }

  private int getPointAt(int time, int initial, int fin, float period, int startTime) {
    float timeElapsed = time - startTime;
    float percentElapsed = timeElapsed / period;
    int change = fin - initial;
    return (initial  + Math.round(percentElapsed * change));
  }

  @Override
  public String toString() {
    return "tick = " + this.getTick()
            + ", x = " + this.getX()
            + ", y = " + this.getY()
            + ", width = " + this.getWidth()
            + ", height = " + this.getHeight()
            + ", red = " + this.getRed()
            + ", green = " + this.getGreen()
            + ", blue = " + this.getBlue();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    else if (!(that instanceof KeyFrame)) {
      return false;
    }
    else {
      IKeyFrame temp = (KeyFrame) that;
      return this.getTick() == temp.getTick()
              && this.getX() == temp.getX()
              && this.getY() == temp.getY()
              && this.getWidth() == temp.getWidth()
              && this.getHeight() == temp.getHeight()
              && this.getRed() == temp.getRed()
              && this.getGreen() == temp.getGreen()
              && this.getBlue() == temp.getBlue();
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(tick, x, y, width, height, red, green, blue);
  }

}
