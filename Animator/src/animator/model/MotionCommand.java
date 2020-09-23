package animator.model;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a command on a shape that changes its position, size, and/or color with the
 * starting and ending values of the shape.
 */
public class MotionCommand implements ICommand {

  private final int startTime;
  private final int startX;
  private final int startY;
  private final int startWidth;
  private final int startHeight;
  private final int startRed;
  private final int startGreen;
  private final int startBlue;
  private final int endTime;
  private final int endX;
  private final int endY;
  private final int endWidth;
  private final int endHeight;
  private final int endRed;
  private final int endGreen;
  private final int endBlue;
  
  /**
   * 16 parameter constructor that takes in the start shape parameters and end shape parameters.
   *
   * @param t1 represents the start time of the shape moving.
   * @param x1 represents the x position at the start of the shape moving.
   * @param y1 represents the y position at the start of the shape moving.
   * @param w1 represents the width at the start of the shape moving.
   * @param h1 represents the height at the start of the shape moving.
   * @param r1 represents the amount of red value at the start of the shape moving.
   * @param g1 represents the amount of green value at the start.
   * @param b1 represents the amount of blue value at the start.
   * @param t2 represents the end time of the shape moving.
   * @param x2 x position at the end of the shape moving.
   * @param y2 x position at the end of the shape moving.
   * @param w2 represents the width at the end of the shape moving.
   * @param h2 represents the height at the end of the shape moving.
   * @param r2 represents the amount of red value at the end.
   * @param g2 represents the amount of green value at the end.
   * @param b2 represents the amount of blue value at the end.
   */
  public MotionCommand(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                       int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

    checkConstructorParameters(t1, w1, h1, r1, g1, b1, t2, w2, h2, r2, g2, b2);

    this.startTime = t1;
    this.startX = x1;
    this.startY = y1;
    this.startWidth = w1;
    this.startHeight = h1;
    this.startRed = r1;
    this.startGreen = g1;
    this.startBlue = b1;
    this.endTime = t2;
    this.endX = x2;
    this.endY = y2;
    this.endWidth = w2;
    this.endHeight = h2;
    this.endRed = r2;
    this.endGreen = g2;
    this.endBlue = b2;

  }

  // checks for valid constructor parameters
  private void checkConstructorParameters(int t1, int w1, int h1, int r1, int g1, int b1,
                                          int t2, int w2, int h2, int r2, int g2, int b2) {
    if (t1 < 0 || t2 < 0 || t2 < t1) {
      throw new IllegalArgumentException("Illegal time parameters.");
    }
    if (w1 < 1 || w2 < 1 || h1 < 1 || h2 < 1) {
      throw new IllegalArgumentException();
    }
    List<Integer> colorValues = Arrays.asList(r1, g1, b1, r2, g2, b2);
    for (int colorValue: colorValues) {
      if (colorValue < 0 || colorValue > 255) {
        throw new IllegalArgumentException("Invalid color parameters.");
      }
    }
  }
  
  @Override
  public IShape getState(int time, IShape shape) {
    if (time < startTime || time > endTime) {
      throw new IllegalArgumentException("Invalid time parameter.");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }

    shape.setX(getPointAt(time, startX, endX));
    shape.setY(getPointAt(time, startY, endY));
    shape.setDimX(getPointAt(time, startWidth, endWidth));
    shape.setDimY(getPointAt(time, startHeight, endHeight));
    shape.setRed(getPointAt(time, startRed, endRed));
    shape.setGreen(getPointAt(time, startGreen, endGreen));
    shape.setBlue(getPointAt(time, startBlue, endBlue));

    return shape;
  }

  private int getPointAt(int time, int initial, int fin) {
    float period = endTime - startTime;
    float timeElapsed = time - startTime;
    float percentElapsed = timeElapsed / period;

    int change = fin - initial;

    return (initial  + Math.round(percentElapsed * change));
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getStartX() {
    return this.startX;
  }

  @Override
  public int getStartY() {
    return this.startY;
  }

  @Override
  public int getStartWidth() {
    return this.startWidth;
  }

  @Override
  public int getStartHeight() {
    return this.startHeight;
  }

  @Override
  public int getStartRed() {
    return this.startRed;
  }

  @Override
  public int getStartGreen() {
    return this.startGreen;
  }

  @Override
  public int getStartBlue() {
    return this.startBlue;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public int getEndX() {
    return this.endX;
  }

  @Override
  public int getEndY() {
    return this.endY;
  }

  @Override
  public int getEndWidth() {
    return this.endWidth;
  }

  @Override
  public int getEndHeight() {
    return this.endHeight;
  }

  @Override
  public int getEndRed() {
    return this.endRed;
  }

  @Override
  public int getEndGreen() {
    return this.endGreen;
  }

  @Override
  public int getEndBlue() {
    return this.endBlue;
  }

  @Override
  public String toString() {
    String output = "";
    output = output + toStringFormatHelper(Integer.toString(this.startTime));
    output = output + toStringFormatHelper(Integer.toString(this.startX));
    output = output + toStringFormatHelper(Integer.toString(this.startY));
    output = output + toStringFormatHelper(Integer.toString(this.startWidth));
    output = output + toStringFormatHelper(Integer.toString(this.startHeight));
    output = output + toStringFormatHelper(Integer.toString(this.startRed));
    output = output + toStringFormatHelper(Integer.toString(this.startGreen));
    output = output + toStringFormatHelper(Integer.toString(this.startBlue)) + " ";
    output = output + toStringFormatHelper(Integer.toString(this.endTime));
    output = output + toStringFormatHelper(Integer.toString(this.endX));
    output = output + toStringFormatHelper(Integer.toString(this.endY));
    output = output + toStringFormatHelper(Integer.toString(this.endWidth));
    output = output + toStringFormatHelper(Integer.toString(this.endHeight));
    output = output + toStringFormatHelper(Integer.toString(this.endRed));
    output = output + toStringFormatHelper(Integer.toString(this.endGreen));
    output = output + toStringFormatHelper(Integer.toString(this.endBlue)) +  "\n";

    return output;
  }

  @Override
  public String getType() {
    return "motion";
  }

  private String toStringFormatHelper(String s) {
    String output = "";
    if (s.length() == 1) {
      output = s + "   ";
    }
    else if (s.length() == 2) {
      output = s + "  ";
    }
    else if (s.length() >= 3) {
      output = s + " ";
    }
    return output;
  }

  @Override
  public ICommand getCopy() {
    return new MotionCommand(this.startTime, this.startX, this.startY, this.startWidth,
            this.startHeight, this.startRed, this.startGreen, this.startBlue, this.endTime,
            this.endX, this.endY, this.endWidth, this.endHeight, this.endRed, this.endGreen,
            this.endBlue);
  }
}
