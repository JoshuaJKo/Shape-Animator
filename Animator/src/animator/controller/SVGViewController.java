package animator.controller;

import animator.view.SVGView;

/**
 * A public class representing the controller for the SVG view.
 */
public class SVGViewController implements IAnimationController {

  private SVGView view;
  private int speed;

  /**
   * A public constructor that initializes all of the fields of the class.
   *
   * @param view SVG view object.
   * @param speed int representing speed of animation.
   */
  public SVGViewController(SVGView view, int speed) {
    this.view = view;
    this.speed = speed;
  }

  @Override
  public void play() {
    this.view.view(speed);
  }

  @Override
  public void handleTick() {
    return ;
  }

  @Override
  public String returnOutput() {
    return view.toString();
  }
}
