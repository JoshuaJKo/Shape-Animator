import org.junit.Test;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import animator.model.AnimationModel;
import animator.model.IAnimationModel;
import animator.model.ICommand;
import animator.model.MotionCommand;
import animator.model.Oval;
import animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * A class to represent the tests for the animation model.
 */
public class AnimationModelTest {

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
  
  static Oval testOval = new Oval();
  static Rectangle testRectangle = new Rectangle();
  
  AnimationModel validModel = new AnimationModel();
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTime1() {
    MotionCommand invalidTimeOne = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTime2() {
    MotionCommand invalidTimeTwo = new MotionCommand(20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTime3() {
    MotionCommand invalidTimeThree = new MotionCommand(10, 0, 10, 10, 0, 0, 0, 0,
            1, 20, 0, 10, 10, 0, 0, 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColors1() {
    MotionCommand invalid = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, 0, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColors2() {
    MotionCommand invalid = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, 0, 300);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColors3() {
    MotionCommand invalid = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, -100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColors4() {
    MotionCommand invalid = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, 260, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColors5() {
    MotionCommand invalid = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColors6() {
    MotionCommand invalid = new MotionCommand(-20, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 500, 0, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidDimensions1() {
    MotionCommand invalidDimensionsOne = new MotionCommand(0, 0, 10, 10, -1, 0, 0, 0,
              10, 20, 0, 10, 10, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensions2() {
    MotionCommand invalidDimensionsTwo = new MotionCommand(0, 0, 10, 10, 10, 0, 0, 0,
            10, 20, 0, 0, 10, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimensions3() {
    MotionCommand invalidDimensionsThree = new MotionCommand(0, 0, 10, 10, 0, 0, 0, 0,
            10, 20, 0, 10, 10, 0, 0, 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionGetStateInvalidTime() {
    moveRight.getState(20, testOval);
    moveRight.getState(-5, testOval);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentExceptionGetStateInvalidShape() {
    moveRight.getState(3, null);
  }
  
  @Test
  public void testGetStateInitalMove() {
    assertEquals(moveRight.getState(0, testOval), testOval);
  }
  
  @Test
  public void testGetStateDoNothing() {
    Oval expected = new Oval(0, 0, 10,10,0,0,0);
    assertEquals(doNothing.getState(6, testOval), expected);
  }
  
  @Test
  public void testGetStateMoveRightFull() {
    Oval expected = new Oval(20, 0, 10,10,0,0,0);
    assertEquals(moveRight.getState(10, testOval), expected);
  }
  
  @Test
  public void testGetStateMoveRightHalfWay() {
    Oval expected = new Oval(10, 0, 10,10,0,0,0);
    assertEquals(moveRight.getState(5, testOval), expected);
  }
  
  @Test
  public void testGetStateMoveDiagonalHalfWay() {
    Rectangle expected = new Rectangle(10, 10, 10,10,0,0,0);
    assertEquals(moveDiagonal.getState(5, testRectangle), expected);
  }
  
  @Test
  public void testGetStateMoveDiagonalFully() {
    Rectangle expected = new Rectangle(20, 20, 10,10,0,0,0);
    assertEquals(moveDiagonal.getState(10, testRectangle), expected);
  }
  
  @Test
  public void testGetStateGrow() {
    Rectangle expected = new Rectangle(0, 0, 30,30,0,0,0);
    assertEquals(growSize.getState(10, testRectangle), expected);
  }
  
  @Test
  public void testGetStateShrink() {
    Rectangle expected = new Rectangle(0, 0, 5,5,0,0,0);
    assertEquals(shrinkSize.getState(10, testRectangle), expected);
  }
  
  @Test
  public void testGetStateChangeColor() {
    Rectangle expected = new Rectangle(0, 0, 10,10,0,20,0);
    assertEquals(changeColor.getState(10, testRectangle), expected);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSizeParameters1() {
    validModel.setScreenSize(0, 0,-1, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIllegalSizeParameters2() {
    validModel.setScreenSize(0, 0, 10, 0);
  }

  @Test (expected = IllegalStateException.class)
  public void testSetSizeTwice() {
    validModel.setScreenSize(0, 0, 10, 10);
    validModel.setScreenSize(0, 0, 10, 10);
  }

  @Test (expected = IllegalStateException.class)
  public void testAnimationModelTestAddIllegalState() {
    validModel.addShape("a", testOval);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAnimationModelTestNullShape() {
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("a", null);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAnimationModelTestBlankID() {
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("", testOval);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAddShapeNoDuplicateID() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("a", testOval);
    validModel.addShape("a", testRectangle);
  }
  
  @Test
  public void testAddShape() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.addShape("a", testOval);
    assertEquals(0, validModel.getCommands("b").size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch1() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(9, 10, 10 ,10, 10, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch2() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 9, 10 ,10, 10, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch3() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 10, 9 ,10, 10, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch4() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 10, 10 , 12, 10, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch5() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 10, 10 ,10, 2, 10, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch6() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 10, 10 ,10, 10, 250, 10, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch7() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 10, 10 ,10, 10, 10, 100, 10,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMatch8() {
    IAnimationModel model = new AnimationModel();
    MotionCommand validCommand = new MotionCommand(0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand invalidCommand = new MotionCommand(10, 10, 10 ,10, 10, 10, 10, 1,
            10, 10, 10, 10, 10, 10, 10, 10);
    model.setScreenSize(0, 0, 100, 100);
    model.addShape("test", testRectangle);
    model.addCommand("test", validCommand);
    model.addCommand("test", invalidCommand);
  }

  @Test
  public void testAddValidCommands() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.addCommand("b" , doEverything);
    ICommand command2 = new MotionCommand(10, 20, 10, 30, 30 ,30 , 20, 10,
            20, 10, 10, 30, 30, 30, 20, 10);
    validModel.addCommand("b", command2);
    validModel.addShape("a", testOval);
    ICommand command3 = new MotionCommand(5, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 10, 10, 10, 10, 10);
    ICommand command4 = new MotionCommand(10, 10, 10, 10, 10, 10, 10, 10,
            20, 20, 20, 20, 20, 20, 20, 20);
    validModel.addCommand("a", command3);
    validModel.addCommand("a", command4);
    assertEquals(2, validModel.getCommands("b").size());
    assertEquals(2, validModel.getCommands("a").size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidKey() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.removeShape("a");
  }

  @Test
  public void testRemoveShape1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.removeShape("b");
    assertEquals(0, validModel.getShapes().size());
  }

  @Test
  public void testRemoveShape2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.addShape("a", testOval);
    validModel.removeShape("b");
    assertEquals(1, validModel.getShapes().size());
    validModel.removeShape("a");
    assertEquals(0, validModel.getShapes().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidKeyRemoveCommands() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.addCommand("b", doEverything);
    validModel.removeLastCommand("a");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEmptyCommandSet() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.removeLastCommand("b");
  }

  @Test
  public void testValidRemoveCommands() {
    IAnimationModel model = new AnimationModel();
    model.setScreenSize(0, 0,100,100);
    model.addShape("b", testRectangle);
    model.addCommand("b", doEverything);
    model.removeLastCommand("b");
    assertEquals(0, model.getCommands("b").size());
  }

  @Test
  public void testGetShape() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("a", testOval);
    validModel.addShape("b", testRectangle);
    assertEquals(testRectangle, validModel.getShape("b").getMutableCopy());
    assertEquals(testOval, validModel.getShape("a").getMutableCopy());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetShapeInvalidKey() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("a", testOval);
    validModel.addShape("b", testRectangle);
    validModel.getShape("c");
  }

  @Test
  public void testGetShapes() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    assertEquals(1, validModel.getShapes().size());
    validModel.addShape("a", testOval);
    assertEquals(2, validModel.getShapes().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidKeyGetCommands() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    validModel.getCommands("a");
  }

  @Test
  public void testGetCommands() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    assertEquals(0, validModel.getCommands("b").size());
    validModel.addCommand("b", doEverything);
    assertEquals(1, validModel.getCommands("b").size());
  }

  @Test
  public void testGetIds() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    validModel.addShape("b", testRectangle);
    List<String> expected1 = Arrays.asList("b");
    assertEquals(expected1, validModel.getIds());
    List<String> expected2 = Arrays.asList("b", "a");
    validModel.addShape("a", testOval);
    assertEquals(expected2, validModel.getIds());
  }

  @Test
  public void testGetLeft1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(-50, 0, 100,100);
    assertEquals(-50, validModel.getLeft());
  }

  @Test
  public void testGetLeft2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(50, 0, 100,100);
    assertEquals(50, validModel.getLeft());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetLeftFail() {
    validModel = new AnimationModel();
    validModel.getLeft();
  }

  @Test
  public void testGetTop1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, -25, 100,100);
    assertEquals(-25, validModel.getTop());
  }

  @Test
  public void testGetTop2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(50, 10, 100,100);
    assertEquals(10, validModel.getTop());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetTopFail() {
    validModel = new AnimationModel();
    validModel.getTop();
  }

  @Test
  public void testGetWidth1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(-50, 0, 100,100);
    assertEquals(100, validModel.getWidth());
  }

  @Test
  public void testGetWidth2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(50, 0, 51, 100);
    assertEquals(51, validModel.getWidth());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetWidthFail() {
    validModel = new AnimationModel();
    validModel.getWidth();
  }

  @Test
  public void testGetHeight1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,1);
    assertEquals(1, validModel.getHeight());
  }

  @Test
  public void testGetHeight2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    assertEquals(100, validModel.getHeight());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetHeightFail() {
    validModel = new AnimationModel();
    validModel.getHeight();
  }

  @Test (expected = IllegalStateException.class)
  public void testGetScreenSizeFail() {
    validModel = new AnimationModel();
    validModel.getScreenSize();
  }

  @Test
  public void testGetScreenSize1() {
    validModel = new AnimationModel();
    validModel.setScreenSize(0, 0, 100,100);
    assertEquals(new Dimension(100, 100), validModel.getScreenSize());
  }

  @Test
  public void testGetScreenSize2() {
    validModel = new AnimationModel();
    validModel.setScreenSize(-100, -100, 100,100);
    assertEquals(new Dimension(100, 100), validModel.getScreenSize());
  }

  @Test
  public void testGetScreenSize3() {
    validModel = new AnimationModel();
    validModel.setScreenSize(50, 50, 50,150);
    assertEquals(new Dimension(50, 150), validModel.getScreenSize());
  }

  
}
