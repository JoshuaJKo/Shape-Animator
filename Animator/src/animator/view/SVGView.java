package animator.view;

import java.util.List;

import animator.controller.IAnimationController;
import animator.controller.SVGViewController;
import animator.model.IAnimationModel;
import animator.model.ICommand;
import animator.model.IViewOnlyAnimationModel;

/**
 * A public class representing the SVG view. This class returns an animation in SVG format.
 */
public class SVGView implements IAnimationView {

  private IViewOnlyAnimationModel model;
  private StringBuilder svgOutput;
  private int timePerTick;

  /**
   * A public constructor for an SVG view. Initializes fields and prepares output.
   *
   * @param model model object representing a view only animation model.
   * @throws IllegalArgumentException if model is null.
   */
  public SVGView(IViewOnlyAnimationModel model) {
    checkForInvalidInputs(model == null);
    this.model = model;
    this.svgOutput = new StringBuilder();
  }

  @Override
  public void view(int speed) {
    checkForInvalidInputs(speed < 1);
    this.timePerTick = 1000 / speed;

    String type;
    svgOutput.append("<svg width=\"" + model.getWidth() + "\" height=\""
            + model.getHeight() + "\" version=\"1.1\"\n    "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (String id: model.getIds()) {
      List<ICommand> commands = model.getCommands(id);
      switch (model.getShape(id).toString()) {
        case "rectangle":
          type = "rect";
          break;
        case "oval":
          type = "ellipse";
          break;
        default:
          throw new IllegalArgumentException();
      }
      this.svgOutput.append("<" + type + " id=\"" + id);
      if (commands.size() == 0) {
        svgOutput.append(this.buildStartNoCommands(type));
      }
      else {
        svgOutput.append(this.buildStartState(commands.get(0), type));
      }
      svgOutput.append(this.buildCommands(commands, type));
      svgOutput.append("</" + type + ">\n\n");
    }
    svgOutput.append("</svg>");
  }

  private void checkForInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  // builds SVG formatted commands for each shape
  private String buildCommands(List<ICommand> commands, String type) {
    StringBuilder output = new StringBuilder();
    String animationStart;
    String animationEnd = "\" fill=\"freeze\" />\n";

    for (ICommand c : commands) {
      animationStart = "    <animate attributeType=\"xml\" begin=\""
              + (c.getStartTime() * this.timePerTick) + "ms\" dur=\""
              + ((c.getEndTime() - c.getStartTime()) * this.timePerTick) + "ms\" "
              + "attributeName=\"";

      buildChangeX(type, output, animationStart, animationEnd, c);
      buildChangeY(type, output, animationStart, animationEnd, c);
      buildChangeHeight(type, output, animationStart, animationEnd, c);
      buildChangeWidth(type, output, animationStart, animationEnd, c);
      buildChangeColor(output, animationStart, animationEnd, c);

    }
    return output.toString();
  }

  // builds SVG formatted string to represent color change
  private void buildChangeColor(StringBuilder output, String animationStart,
                                String animationEnd, ICommand c) {
    if (c.getStartRed() != c.getEndRed()
            || c.getStartGreen() != c.getEndGreen()
            || c.getStartBlue() != c.getEndBlue()) {
      output.append(animationStart);
      output.append("fill\" from=\"rgb(" + c.getStartRed() + ",");
      output.append(c.getStartGreen() + "," + c.getStartBlue() + ")\" to=\"rgb(");
      output.append(c.getEndRed() + "," + c.getEndGreen() + ",");
      output.append(c.getEndBlue()).append(")" + animationEnd);
    }
  }

  // builds SVG formatted string to represent width change
  private void buildChangeWidth(String type, StringBuilder output, String animationStart,
                                String animationEnd, ICommand c) {
    if (c.getStartWidth() != c.getEndWidth()) {
      output.append(animationStart);
      if (type.equals("rect")) {
        output.append("width\" from=\"");
      }
      else {
        output.append("rx\" from=\"");
      }
      output.append(c.getStartWidth() + "\" to=\"" + c.getEndWidth());
      output.append(animationEnd);
    }
  }

  // builds SVG formatted string to represent height change
  private void buildChangeHeight(String type, StringBuilder output, String animationStart,
                                 String animationEnd, ICommand c) {
    if (c.getStartHeight() != c.getEndHeight()) {
      output.append(animationStart);
      if (type.equals("rect")) {
        output.append("height\" from=\"");
      }
      else {
        output.append("ry\" from=\"");
      }
      output.append(c.getStartHeight() + "\" to=\"" + c.getEndHeight());
      output.append(animationEnd);
    }
  }

  // builds SVG formatted string to represent Y change
  private void buildChangeY(String type, StringBuilder output, String animationStart,
                            String animationEnd, ICommand c) {
    if (c.getStartY() != c.getEndY()) {
      output.append(animationStart);
      if (type.equals("rect")) {
        output.append("y\" from=\"");
        output.append(c.getStartY() - this.model.getTop());
        output.append("\" to=\"" + (c.getEndY() - this.model.getTop()));
      }
      else {
        output.append("cy\" from=\"");
        output.append(c.getStartY() - this.model.getTop() + (c.getStartHeight() / 2));
        output.append("\" to=\"" + (c.getEndY() - this.model.getTop() + (c.getEndHeight() / 2)));
      }
      output.append(animationEnd);
    }
  }

  // builds SVG formatted string to represent X change
  private void buildChangeX(String type, StringBuilder output, String animationStart,
                            String animationEnd, ICommand c) {
    if (c.getStartX() != c.getEndX()) {
      output.append(animationStart);
      if (type.equals("rect")) {
        output.append("x\" from=\"");
        output.append(c.getStartX() - this.model.getLeft());
        output.append("\" to=\"" + (c.getEndX() - this.model.getLeft()));
      }
      else {
        output.append("cx\" from=\"");
        output.append(c.getStartX() - this.model.getLeft() + (c.getStartWidth() / 2));
        output.append("\" to=\"" + (c.getEndX() - this.model.getLeft() + (c.getEndWidth()) / 2));
      }
      output.append(animationEnd);
    }
  }

  // builds SVG formatted string to represent initial shape state
  private String buildStartState(ICommand command, String shape) {
    StringBuilder output = new StringBuilder();
    switch (shape) {
      case "rect":
        output = new StringBuilder("\" x=\"" + (command.getStartX() - this.model.getLeft()));
        output.append("\" y=\"" + (command.getStartY() - this.model.getTop()));
        output.append("\" width=\"");
        output.append(command.getStartWidth() + "\" height=\"");
        output.append(command.getStartHeight());
        break;
      case "ellipse":
        output = new StringBuilder("\" cx=\"");
        output.append(command.getStartX() - this.model.getLeft() + (command.getStartWidth() / 2));
        output.append("\" cy=\"");
        output.append(command.getStartY() - this.model.getTop() + (command.getStartHeight() / 2));
        output.append("\" rx=\"");
        output.append(command.getStartWidth() / 2 + "\" ry=\"");
        output.append(command.getStartHeight() / 2);
        break;
      default:
        throw new IllegalArgumentException();
    }
    output.append("\" fill=\"rgb(" + command.getStartRed() + ",");
    output.append(command.getStartGreen() + ",");
    output.append(command.getStartBlue());
    buildVisibilityTag(command, output);
    return output.toString();
  }

  private String buildStartNoCommands(String shape) {
    StringBuilder output = new StringBuilder();
    switch (shape) {
      case "rect":
        output = new StringBuilder("\" x=\"" + (-this.model.getLeft()));
        output.append("\" y=\"" + (-this.model.getTop()));
        output.append("\" width=\"0\" height=\"0");
        break;
      case "ellipse":
        output = new StringBuilder("\" cx=\"" + (-this.model.getLeft()));
        output.append("\" cy=\"" + (-this.model.getTop()));
        output.append("\" rx=\"0\" ry=\"0");
        break;
      default:
        throw new IllegalArgumentException();
    }
    output.append("\" fill=\"rgb(0,0,0)\"\n");
    return output.toString();
  }

  // builds SVG formatted string to represent visibility change
  private void buildVisibilityTag(ICommand command, StringBuilder output) {
    if (command.getStartTime() > 1) {
      output.append(")\" visibility=\"hidden\" >\n");
      output.append("    <animate attributeType=\"xml\" begin=\""
              + (command.getStartTime() * this.timePerTick) + "ms\" dur=\""
              + 1 + "ms\" "
              + "attributeName=\"");
      output.append("visibility\" from=\"hidden\" to=\"visible");
      output.append("\" fill=\"freeze\" />\n");
    }
    else {
      output.append(")\" visibility=\"visible\" >\n");
    }
  }

  @Override
  public String toString() {
    return this.svgOutput.toString();
  }

  @Override
  public void refresh(int tick) {
    return ;
  }

  @Override
  public IAnimationController getController(IAnimationModel model, int speed) {
    return new SVGViewController(this, speed);
  }

}
