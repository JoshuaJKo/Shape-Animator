package animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A public class that represents the mouse click handler for the GUI. This class sends mouse click
 * information from the view to the controller.
 */
public class MouseClickHandler extends MouseAdapter {

  private IAnimationEditorController controller;

  /**
   * A public constructor that initializes the controller object.
   *
   * @param c animation editor controller object.
   */
  public MouseClickHandler(IAnimationEditorController c) {
    this.controller = c;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.controller.handleMouseClick(e.getX(), e.getY());
  }
}
