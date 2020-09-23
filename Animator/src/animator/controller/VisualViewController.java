package animator.controller;

import javax.swing.Timer;

import animator.view.VisualView;

/**
 * A public class that represents the controller for the visual view.
 */
public class VisualViewController implements IAnimationController {

  private VisualView view;
  private int currentSpeed;
  private int tick;

  /**
   * A public constructor that initializes the fields of the class.
   *
   * @param view visual view object.
   * @param speed the speed of the animation.
   */
  public VisualViewController(VisualView view, int speed) {
    this.view = view;
    this.currentSpeed = speed;
  }

  @Override
  public void play() {
    this.view.view(this.currentSpeed);
    Timer timer = new Timer(1000 / this.currentSpeed, new TimerListener(this));
    timer.start();
  }

  @Override
  public void handleTick() {
    this.tick = this.tick + 1;
    this.view.refresh(this.tick);
  }

  @Override
  public String returnOutput() {
    return view.toString();
  }
}
