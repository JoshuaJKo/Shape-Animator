import org.junit.Test;

import animator.controller.IAnimationController;
import animator.controller.IAnimationEditorController;
import animator.model.AnimationModel;
import animator.model.IAnimationModel;
import animator.util.AnimatorViewFactory;
import animator.view.IAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * A test class for the AnimationController.
 */
public class AnimationControllerTest {

  @Test
  public void testGuiInput() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("edit", model);
    IAnimationController controller = view.getController(model, 20);
    controller.play();

    ((IAnimationEditorController) controller).handleGuiCommand("pause");
    ((IAnimationEditorController) controller).handleGuiCommand("speed");
    ((IAnimationEditorController) controller).handleGuiCommand("slow");
    ((IAnimationEditorController) controller).handleGuiCommand("restart");
    ((IAnimationEditorController) controller).handleGuiCommand("looping");

    assertEquals(0, model.getShapes().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCommand0() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("edit", model);
    IAnimationController controller = view.getController(model, 20);
    controller.play();

    ((IAnimationEditorController) controller).handleGuiCommand("addOval");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCommand1() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("edit", model);
    IAnimationController controller = view.getController(model, 20);
    controller.play();

    ((IAnimationEditorController) controller).handleGuiCommand("addRectangle");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCommand2() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("edit", model);
    IAnimationController controller = view.getController(model, 20);
    controller.play();

    ((IAnimationEditorController) controller).handleGuiCommand("addKeyFrame");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCommand3() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("edit", model);
    IAnimationController controller = view.getController(model, 20);
    controller.play();

    ((IAnimationEditorController) controller).handleGuiCommand("deleteKeyFrame");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCommand4() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("edit", model);
    IAnimationController controller = view.getController(model, 20);
    controller.play();

    ((IAnimationEditorController) controller).handleGuiCommand("deleteShape");
  }



}
