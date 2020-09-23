package animator.view;

import animator.controller.IAnimationEditorController;

/**
 * A public interface representing the animator editor view.
 */
public interface IEditorView extends IAnimationView {

  /**
   * Sets controller as listener of GUI button inputs.
   *
   * @param c controller object that will receive GUI inputs.
   * @throws IllegalArgumentException if controller is null.
   */
  void addListener(IAnimationEditorController c);

  /**
   * Sets controller as listener of mouse clicks on drawing panel.
   *
   * @param c controller object that will receive mouse click information.
   * @throws IllegalArgumentException if controller is null.
   */
  void addMouseListener(IAnimationEditorController c);

  /**
   * Opens the shape editor for a shape with given ID.
   *
   * @param id unique shape identifier.
   */
  void openShapeEditor(String id);

  /**
   * Retrieves given text field input from GUI.
   *
   * @param field string identifier of field. For example, "tick" or "width."
   * @return String representing text field input.
   * @throws IllegalArgumentException if field is invalid.
   */
  String getTextFromField(String field);

}
