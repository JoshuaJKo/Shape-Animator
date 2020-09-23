package animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import javax.swing.JPanel;

import animator.model.IViewOnlyAnimationModel;

/**
 * A public class representing the status panel for the GUI. This panel shows the current tick,
 * how many shapes are in the animation, and instructions on how to add/edit shapes.
 */
public class StatusPanel extends JPanel {

  private IViewOnlyAnimationModel model;
  private int tick;

  /**
   * A public constructor that initializes the model.
   *
   * @param model view only animation model.
   */
  public StatusPanel(IViewOnlyAnimationModel model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    this.setFont(new Font("Monaco", Font.PLAIN, 40));
    g2.drawString(("Tick: " + this.tick), 70, 70);
    int shapes = this.model.getShapes().size();
    g2.drawString(("Number of shapes:" + shapes), 70, 40);
    this.setFont(new Font("Monaco", Font.PLAIN, 15));
    g2.drawString("Click on a shape to edit that shape.",
            250, 40);
    g2.drawString("New shapes can be created by putting"
            + " an ID into the field to the right", 250, 60);
    g2.drawString("and clicking either Add Oval or Add Rectangle.", 250, 80);
  }

  /**
   * Sets the current tick for the panel.
   *
   * @param tick int which tick should be set to.
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

}
