package animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A public class representing the GUI input handler. This class takes inputs from the view
 * and sends it to the controller.
 */
public class AnimatorEditorGuiHandler implements ActionListener {

  private IAnimationEditorController controller;

  /**
   * A public constructor that initializes the controller.
   *
   * @param c animation editor controller object.
   */
  public AnimatorEditorGuiHandler(IAnimationEditorController c) {
    this.controller = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    controller.handleGuiCommand(e.getActionCommand());
  }
}
