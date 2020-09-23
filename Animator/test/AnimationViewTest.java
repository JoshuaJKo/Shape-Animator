import org.junit.Test;

import animator.model.AnimationModel;
import animator.model.IAnimationModel;
import animator.model.ICommand;
import animator.model.IShape;
import animator.model.MotionCommand;
import animator.model.Oval;
import animator.model.Rectangle;
import animator.util.AnimatorViewFactory;
import animator.view.IAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * A public class that represents the test class for the animation views. This class tests the two
 * text-based views of the animator.
 */
public class AnimationViewTest {

  ICommand moveRight = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 20, 0, 10, 10, 0, 0, 0);

  ICommand doNothing = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 0, 0, 10, 10, 0, 0, 0);

  ICommand moveDiagonal = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 20, 20, 10, 10, 0, 0, 0);

  ICommand changeColor = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 0, 0, 10, 10, 0, 20, 0);

  ICommand shrinkSize = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 0, 0, 5, 5, 0, 0, 0);

  ICommand growSize = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 0, 0, 30, 30, 0, 0, 0);

  ICommand doEverything = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
          10, 20, 10, 30, 30, 30, 20, 10);

  static IShape testOval = new Oval();
  static IShape testRectangle = new Rectangle();

  IAnimationModel validModel;

  @Test
  public void testTextView1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100, 100);
    validModel.addShape("b", testRectangle);
    validModel.addCommand("b", doEverything);
    ICommand command2 = new MotionCommand(10, 20, 10, 30, 30, 30, 20, 10,
            20, 10, 10, 30, 30, 30, 20, 10);
    validModel.addCommand("b", command2);
    validModel.addShape("a", testOval);
    ICommand command3 = new MotionCommand(5, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand command4 = new MotionCommand(10, 10, 10, 10, 10, 10, 10, 10,
            20, 20, 20, 20, 20, 20, 20, 20);
    validModel.addCommand("a", command3);
    validModel.addCommand("a", command4);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("text", validModel);
    view.view(1);
    assertEquals("canvas 0 0 100 100\n"
                    + "shape b rectangle\n"
                    + "motion b 0   0   0   10  10  0   0   0    10  20  10  30  30  30  20  10  \n"
                    + "motion b 10  20  10  30  30  30  20  10   20  10  10  30  30  30  20  10  \n"
                    + "shape a oval\n"
                    + "motion a 5   0   0   10  10  0   0   0    10  10  10  10  10  10  10  10  \n"
                    + "motion a 10  10  10  10  10  10  10  10 "
                    + "  20  20  20  20  20  20  20  20  \n",
            view.toString());
  }

  @Test
  public void testTextView2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(-100, -50, 100, 100);
    validModel.addShape("a", testRectangle);
    validModel.addCommand("a", doEverything);
    validModel.addShape("b", testOval);
    validModel.addCommand("b", doNothing);
    ICommand command1 = new MotionCommand(10, 0, 0, 10, 10, 0, 0, 0,
            20, -50, 0, 20, 20, 255, 255, 255);
    validModel.addCommand("b", command1);
    validModel.addShape("test", testOval);
    ICommand command2 = new MotionCommand(25, 0, 0, 10, 10, 0, 255, 0,
            30, -50, 0, 5, 5, 255, 200, 200);
    validModel.addCommand("test", command2);
    validModel.addShape("c", testRectangle);
    validModel.addCommand("c", moveRight);
    validModel.addShape("d", testOval);
    validModel.addCommand("d", moveDiagonal);
    validModel.addShape("e", testRectangle);
    validModel.addCommand("e", changeColor);
    validModel.addShape("f", testOval);
    validModel.addCommand("f", shrinkSize);
    validModel.addShape("g", testRectangle);
    validModel.addCommand("g", growSize);
    validModel.addShape("empty", testOval);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("text", validModel);
    view.view(1);
    assertEquals("canvas -100 -50 100 100\n"
                    + "shape a rectangle\n"
                    + "motion a 0   0   0   10  10  0   0   0    10  20  10  30  30  30  20  10  \n"
                    + "shape b oval\n"
                    + "motion b 0   0   0   10  10  0   0   0    10  0   0   10  10  0   0   0   \n"
                    + "motion b 10  0   0   10  10  0   0   0    20  -50 0   20  20  255 255 255 \n"
                    + "shape test oval\nmotion "
                    + "test 25  0   0   10  10  0   255 0    30  -50 0   5   5   255 200 200 \n"
                    + "shape c rectangle\n"
                    + "motion c 0   0   0   10  10  0   0   0    10  20  0   10  10  0   0   0   \n"
                    + "shape d oval\n"
                    + "motion d 0   0   0   10  10  0   0   0    10  20  20  10  10  0   0   0   \n"
                    + "shape e rectangle\n"
                    + "motion e 0   0   0   10  10  0   0   0    10  0   0   10  10  0   20  0   \n"
                    + "shape f oval\n"
                    + "motion f 0   0   0   10  10  0   0   0    10  0   0   5   5   0   0   0   \n"
                    + "shape g rectangle\n"
                    + "motion g 0   0   0   10  10  0   0   0    10  0   0   30  30  0   0   0   \n"
                    + "shape empty oval\n",
            view.toString());
  }

  @Test
  public void testTextViewEmpty() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("text", validModel);
    view.view(1);
    assertEquals("canvas 0 0 100 100\n", view.toString());
  }

  @Test
  public void testSvgView1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(-100, -50, 100, 100);
    validModel.addShape("a", testRectangle);
    validModel.addCommand("a", doEverything);
    validModel.addShape("b", testOval);
    validModel.addCommand("b", doNothing);
    ICommand command1 = new MotionCommand(10, 0, 0, 10, 10, 0, 0, 0,
            20, -50, 0, 20, 20, 255, 255, 255);
    validModel.addCommand("b", command1);
    validModel.addShape("test", testOval);
    ICommand command2 = new MotionCommand(25, 0, 0, 10, 10, 0, 255, 0,
            30, -50, 0, 5, 5, 255, 200, 200);
    validModel.addCommand("test", command2);
    validModel.addShape("c", testRectangle);
    validModel.addCommand("c", moveRight);
    validModel.addShape("d", testOval);
    validModel.addCommand("d", moveDiagonal);
    validModel.addShape("e", testRectangle);
    validModel.addCommand("e", changeColor);
    validModel.addShape("f", testOval);
    validModel.addCommand("f", shrinkSize);
    validModel.addShape("g", testRectangle);
    validModel.addCommand("g", growSize);
    validModel.addShape("empty", testOval);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("svg", validModel);
    view.view(10);
    System.out.print(view.toString());
    assertEquals("<svg width=\"100\" height=\"100\" version=\"1.1\"\n"
                    + "    xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "<rect id=\"a\" x=\"100\" y=\"50\" width=\"10\" height=\"10\" "
                    + "fill=\"rgb(0,0,0)\" "
                    + "visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\""
                    + " attributeName=\"x\" from=\"100\" to=\"120\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"y\" from=\"50\" to=\"60\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"height\" from=\"10\" to=\"30\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(30,20,10)\" "
                    + "fill=\"freeze\" />\n"
                    + "</rect>\n\n"
                    + "<ellipse id=\"b\" cx=\"105\" cy=\"55\" rx=\"5\" ry=\"5\" fill=\"rgb(0,0,0)\""
                    + " visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" "
                    + "attributeName=\"cx\" from=\"105\" to=\"60\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" "
                    + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" "
                    + "attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(255,255,255)\" "
                    + "fill=\"freeze\" />\n"
                    + "</ellipse>\n\n"
                    + "<ellipse id=\"test\" cx=\"105\" cy=\"55\" rx=\"5\" ry=\"5\" fill=\""
                    + "rgb(0,255,0)\" visibility=\"hidden\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"2500ms\" dur=\"1ms\" "
                    + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\""
                    + " fill=\"freeze\" />\n    <animate attributeType=\"xml\" begin=\"2500ms\" "
                    + "dur=\"500ms\" attributeName=\"cx\" from=\"105\" to=\"52\" fill=\"freeze\" "
                    + "/>\n    <animate attributeType=\"xml\" begin=\"2500ms\" dur=\"500ms\" "
                    + "attributeName=\"ry\" from=\"10\" to=\"5\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"2500ms\" dur=\"500ms\" "
                    + "attributeName=\"rx\" from=\"10\" to=\"5\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"2500ms\" dur=\"500ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(255,200,200)\" "
                    + "fill=\"freeze\" />\n" + "</ellipse>\n\n"
                    + "<rect id=\"c\" x=\"100\" y=\"50\" width=\"10\" height=\"10\" "
                    + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\""
                    + " attributeName=\"x\" from=\"100\" to=\"120\" fill=\"freeze\" />\n"
                    + "</rect>\n\n"
                    + "<ellipse id=\"d\" cx=\"105\" cy=\"55\" rx=\"5\" ry=\"5\" fill=\""
                    + "rgb(0,0,0)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"cx\" from=\"105\" to=\"125\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\""
                    + " attributeName=\"cy\" from=\"55\" to=\"75\" fill=\"freeze\" />\n"
                    + "</ellipse>\n\n"
                    + "<rect id=\"e\" x=\"100\" y=\"50\" width=\"10\" height=\"10\" fill=\""
                    + "rgb(0,0,0)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,20,0)\" "
                    + "fill=\"freeze\" />\n</rect>\n\n"
                    + "<ellipse id=\"f\" cx=\"105\" cy=\"55\" rx=\"5\" ry=\"5\" "
                    + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"ry\" from=\"10\" to=\"5\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"rx\" from=\"10\" to=\"5\" fill=\"freeze\" />\n"
                    + "</ellipse>\n\n"
                    + "<rect id=\"g\" x=\"100\" y=\"50\" width=\"10\" height=\"10\" "
                    + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"height\" from=\"10\" to=\"30\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" "
                    + "attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n"
                    + "</rect>\n\n"
                    + "<ellipse id=\"empty\" cx=\"100\" cy=\"50\" rx=\"0\" ry=\"0\" "
                    + "fill=\"rgb(0,0,0)\"\n</ellipse>\n\n</svg>",
            view.toString());
  }

  @Test
  public void testSvgViewEmpty() {
    validModel = new AnimationModel();
    validModel.setScreenSize(-100, -50, 100, 100);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    IAnimationView view = factory.create("svg", validModel);
    view.view(10);
    assertEquals("<svg width=\"100\" height=\"100\" version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "</svg>", view.toString());
  }

}
