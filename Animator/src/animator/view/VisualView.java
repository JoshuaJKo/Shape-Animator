package animator.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import animator.controller.IAnimationController;
import animator.controller.VisualViewController;
import animator.model.IAnimationModel;
import animator.model.IViewOnlyAnimationModel;

/**
 * A public class representing a visual animation view. This class extends JFrame and creates a
 * window that displays an animation.
 */
public class VisualView extends JFrame implements IAnimationView {

  private AnimationPanel drawingPanel;

  /**
   * A public constructor that creates the animation window.
   *
   * @param model the animation model.
   */
  public VisualView(IViewOnlyAnimationModel model) {
    super();
    checkForInvalidInputs(model == null);
    this.setTitle("Animator");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(model.getScreenSize()));
    AnimationPanel p = new AnimationPanel(model);
    p.setPreferredSize(model.getScreenSize());
    this.drawingPanel = p;
    this.add(p);

    int windowCutoff = 800;
    int scrollBarVerticalBound;
    int scrollBarHorizontalBound;
    if (model.getScreenSize().width < windowCutoff) {
      scrollBarHorizontalBound = model.getScreenSize().width;
    }
    else {
      scrollBarHorizontalBound = windowCutoff;
    }
    if (model.getScreenSize().height < windowCutoff) {
      scrollBarVerticalBound = model.getScreenSize().height;
    }
    else {
      scrollBarVerticalBound = windowCutoff;
    }

    JPanel contentPane = createScrollPane(p, scrollBarVerticalBound, scrollBarHorizontalBound);
    this.setContentPane(contentPane);
    this.pack();
  }

  private void checkForInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  private JPanel createScrollPane(AnimationPanel p, int scrollBarVerticalBound,
                                  int scrollBarHorizontalBound) {
    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setBounds(0, 0, scrollBarHorizontalBound, scrollBarVerticalBound);
    JPanel contentPane = new JPanel(null);
    contentPane.setPreferredSize(new Dimension(scrollBarHorizontalBound, scrollBarVerticalBound));
    contentPane.add(scrollPane);
    return contentPane;
  }

  @Override
  public void view(int speed) {
    this.setVisible(true);
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public void refresh(int tick) {
    this.drawingPanel.setCurrentTick(tick);
    this.repaint();
  }

  @Override
  public IAnimationController getController(IAnimationModel model, int speed) {
    return new VisualViewController(this, speed);
  }
}
