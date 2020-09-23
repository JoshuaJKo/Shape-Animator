package animator.view;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


import animator.model.IShape;
import animator.model.IViewOnlyAnimationModel;

/**
 * A public class representing the panel where shapes will be drawn. This class overrides
 * the paintComponent method in order to draw the shapes.
 */
public class AnimationPanel extends JPanel {

  private IViewOnlyAnimationModel model;
  private int tick;

  /**
   * Public constructor takes in an animation model and initializes fields and sets background
   * color to white.
   *
   * @param model model object representing animation model.
   */
  public AnimationPanel(IViewOnlyAnimationModel model) {
    super();
    checkForInvalidInputs(model == null);
    this.setBackground(Color.WHITE);
    this.model = model;
  }

  private void checkForInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.translate(-model.getLeft(), -model.getTop());
    drawShapes(g2);
  }

  private void drawShapes(Graphics2D g2) {
    for (IShape s: model.getShapesAtTick(this.tick)) {
      s.draw(g2);
    }
  }

  /**
   * Sets current tick of panel.
   *
   * @param tick int which tick is to be set to.
   */
  public void setCurrentTick(int tick) {
    checkForInvalidInputs(tick < 1);
    this.tick = tick;
  }
}
