package animator.controller;

import animator.view.TextView;

/**
 * A public class representing the controller for the text view.
 */
public class TextViewController implements IAnimationController {

  private TextView view;

  /**
   * A public constructor that initializes the fields of the class.
   *
   * @param view text view object.
   */
  public TextViewController(TextView view) {
    this.view = view;
  }

  @Override
  public void play() {
    this.view.view(0);
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
