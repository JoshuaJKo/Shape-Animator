package animator.view;

import java.util.List;

import animator.controller.IAnimationController;
import animator.controller.TextViewController;
import animator.model.IAnimationModel;
import animator.model.ICommand;
import animator.model.IViewOnlyAnimationModel;
import animator.model.IViewOnlyShape;

/**
 * A public class to represent a textual view of an animation. This class produces a representation
 * of an animation as a textual description.
 */
public class TextView implements IAnimationView {

  private IViewOnlyAnimationModel model;
  private StringBuilder output;

  /**
   * A public constructor that takes in a model. Initializes the output as a StringBuilder object.
   *
   * @param model animation model object.
   */
  public TextView(IViewOnlyAnimationModel model) {
    checkForInvalidInputs(model == null);
    this.model = model;
    this.output = new StringBuilder();
  }

  @Override
  public void view(int speed) {
    checkForInvalidInputs(speed < 1);
    this.output.append("canvas " + this.model.getLeft() + " " + this.model.getTop() + " "
            + this.model.getWidth() + " " + this.model.getHeight() + "\n");
    List<String> ids = this.model.getIds();
    //iterates through every shape and command.
    for (String id: ids) {
      IViewOnlyShape tempShape = this.model.getShape(id);
      this.output.append("shape " + id + " " + tempShape.toString() + "\n");
      for (ICommand command : model.getCommands(id)) {
        this.output.append(command.getType() + " " + id + " " + command.toString());
      }
    }
  }

  private void checkForInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
    return this.output.toString();
  }

  @Override
  public void refresh(int tick) {
    return ;
  }

  @Override
  public IAnimationController getController(IAnimationModel model, int speed) {
    return new TextViewController(this);
  }

}
