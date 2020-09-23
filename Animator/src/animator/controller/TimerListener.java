package animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A public class that represents the listener for the timer object. This class will call
 * handleTick on the controller every time the timer object ticks.
 */
public class TimerListener implements ActionListener {

  private IAnimationController controller;

  /**
   * A public constructor which initializes the controller object.
   *
   * @param controller animation controller object.
   */
  public TimerListener(IAnimationController controller) {
    this.controller = controller;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    this.controller.handleTick();
  }
}
