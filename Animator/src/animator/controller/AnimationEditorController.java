package animator.controller;

import javax.swing.Timer;

import animator.model.IAnimationModel;
import animator.model.IKeyFrame;
import animator.model.KeyFrame;
import animator.model.Oval;
import animator.model.Rectangle;
import animator.view.IEditorView;

/**
 * A public class representing the animation editor controller. This controller offers
 * functionality that allows the animation to be edited. This class also keeps track of the
 * timer for the animation.
 */
public class AnimationEditorController implements IAnimationEditorController {

  private IAnimationModel model;
  private IEditorView view;
  private int currentSpeed;
  private int tick;
  private boolean isPaused;
  private Timer timer;
  private boolean isLooping;
  private boolean hasStarted;
  private String currentlyEditingId;

  /**
   * A public constructor that initializes all of the fields of the class and sets up any
   * needed connections with the view.
   *
   * @param model animation model object.
   * @param view animation view object.
   * @param initSpeed initial speed of animation.
   * @param looping boolean representing if the animation will loop.
   */
  public AnimationEditorController(IAnimationModel model,
                                   IEditorView view, int initSpeed, boolean looping) {
    checkForValidInputs(model == null);
    checkForValidInputs(view == null);
    checkForValidInputs(initSpeed < 1);
    this.model = model;
    this.view = view;
    this.view.addListener(this);
    this.view.addMouseListener(this);
    this.currentSpeed = initSpeed;
    this.tick = 0;
    this.isPaused = false;
    this.isLooping = looping;
    this.hasStarted = false;
  }

  @Override
  public void pause() {
    this.isPaused = !this.isPaused;
  }

  @Override
  public void restart() {
    if (!this.hasStarted) {
      throw new IllegalStateException();
    }
    else {
      this.tick = 1;
      this.view.refresh(this.tick);
    }
  }

  @Override
  public void increaseSpeed() {
    this.timer.stop();
    this.currentSpeed = this.currentSpeed + 1;
    this.timer = new Timer(1000 / this.currentSpeed, new TimerListener(this));
    this.timer.start();
  }

  @Override
  public void decreaseSpeed() {
    if (this.currentSpeed == 1) {
      throw new IllegalStateException();
    }
    this.timer.stop();
    this.currentSpeed = this.currentSpeed - 1;
    this.timer = new Timer(1000 / this.currentSpeed, new TimerListener(this));
    this.timer.start();
  }

  @Override
  public void toggleLooping() {
    this.isLooping = !this.isLooping;
  }

  @Override
  public void play() {
    if (this.hasStarted) {
      throw new IllegalStateException();
    }
    this.hasStarted = true;
    this.view.view(this.currentSpeed);
    this.timer = new Timer(1000 / this.currentSpeed, new TimerListener(this));
    this.timer.start();
  }

  @Override
  public void handleTick() {
    if (this.isPaused) {
      return ;
    }
    else if (this.model.getFinalTick() == this.tick && this.isLooping) {
      this.tick = 1;
      this.view.refresh(this.tick);
    }
    else if (this.model.getFinalTick() == this.tick && !this.isLooping) {
      this.isPaused = true;
    }
    else {
      this.tick = this.tick + 1;
      this.view.refresh(this.tick);
    }
  }

  @Override
  public String returnOutput() {
    return this.view.toString();
  }

  private void checkForValidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void handleMouseClick(int x, int y) {
    String id = this.model.getIdOfTopShape(this.tick, x, y);
    this.currentlyEditingId = id;
    this.view.openShapeEditor(id);
  }

  @Override
  public void handleGuiCommand(String command) {
    switch (command) {
      case "pause":
        pause();
        break;
      case "speed":
        increaseSpeed();
        break;
      case "slow":
        decreaseSpeed();
        break;
      case "looping":
        toggleLooping();
        break;
      case "restart":
        restart();
        break;
      case "addOval":
        addNewOval();
        break;
      case "addRectangle":
        addNewRectangle();
        break;
      case "deleteShape":
        removeShape();
        break;
      case "addKeyFrame":
        addNewKeyFrame();
        break;
      case "deleteKeyFrame":
        deleteKeyFrame();
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  private void deleteKeyFrame() {
    int tick = convertToInt(view.getTextFromField("tick"));
    this.model.deleteKeyFrame(this.currentlyEditingId, tick);
  }

  private void removeShape() {
    this.model.removeShape(this.currentlyEditingId);
  }

  private void addNewOval() {
    String ovalId = this.view.getTextFromField("id");
    this.model.addShape(ovalId, new Oval());
    this.currentlyEditingId = ovalId;
    this.view.openShapeEditor(ovalId);
  }

  private void addNewRectangle() {
    String rectId = this.view.getTextFromField("id");
    this.model.addShape(rectId, new Rectangle());
    this.currentlyEditingId = rectId;
    this.view.openShapeEditor(rectId);
  }

  private void addNewKeyFrame() {
    int tempTick = convertToInt(view.getTextFromField("tick"));
    int tempX = convertToInt(view.getTextFromField("x"));
    int tempY = convertToInt(view.getTextFromField("y"));
    int tempWidth = convertToInt(view.getTextFromField("width"));
    int tempHeight = convertToInt(view.getTextFromField("height"));
    int tempRed = convertToInt(view.getTextFromField("red"));
    int tempGreen = convertToInt(view.getTextFromField("green"));
    int tempBlue = convertToInt(view.getTextFromField("blue"));
    IKeyFrame temp = new KeyFrame(tempTick, tempX, tempY, tempWidth, tempHeight, tempRed,
            tempGreen, tempBlue);
    this.model.addKeyFrame(this.currentlyEditingId, temp);
  }

  private int convertToInt(String input) {
    try {
      int result = Integer.parseInt(input);
      return result;
    }
    catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input.");
    }
  }

}
