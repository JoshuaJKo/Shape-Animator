package animator.util;

import animator.model.IAnimationModel;
import animator.view.EditorView;
import animator.view.IAnimationView;
import animator.view.SVGView;
import animator.view.TextView;
import animator.view.VisualView;

/**
 * A public factory class used to produce animation views. This class contains one method used
 * to return a view based on a type parameter.
 */
public class AnimatorViewFactory {

  /**
   * Returns a view based on given specifier. Specifier can either be "text", "svg", "visual"
   * or "edit."
   * @param type String representing type of view to create.
   * @param model model object representing animation model.
   * @return IAnimationView object representing created view.
   */
  public IAnimationView create(String type, IAnimationModel model) {
    IAnimationView result;
    switch (type.toLowerCase()) {
      case "visual":
        result = new VisualView(model);
        break;
      case "text":
        result = new TextView(model);
        break;
      case "svg":
        result = new SVGView(model);
        break;
      case "edit":
        result = new EditorView(model);
        break;
      default:
        throw new IllegalArgumentException();
    }
    return result;
  }
}
