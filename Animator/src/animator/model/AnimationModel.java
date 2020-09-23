package animator.model;

import java.util.ArrayList;
import java.util.List;

import animator.util.AnimationBuilder;

/**
 * A class that represents a mutable animation, keeping track of shapes and how they are
 * commanded to move, change size, and/or change color. Shapes and commands can be added using
 * methods in this class. This interface extends its read-only counterpart in order to add
 * functionality to the existing model class.
 */
public class AnimationModel extends ViewOnlyAnimationModel implements IAnimationModel {

  /**
   * No parameter constructor that instantiates the lists to null and states that the screen has
   * not been set yet.
   */
  public AnimationModel() {
    super();
  }

  @Override
  public void addShape(String id, IShape shape) {
    checkAddParameters(id, shape);
    if (shapes.containsKey(id)) {
      throw new IllegalArgumentException("ID is already in use");
    }
    else if (shape.getX() > this.width || shape.getY() > this.height) {
      throw new IllegalArgumentException();
    }
    shapes.put(id, shape.getCopy());
    commands.put(id, new ArrayList<>());
    keyframes.put(id, new ArrayList<>());
    ids.add(id);
  }

  @Override
  public void addCommand(String id, ICommand command) {
    checkAddParameters(id, command);

    if (!shapes.containsKey(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }

    if (!isValidCommand(id, command)) {
      throw new IllegalArgumentException("Invalid command addition.");
    } else {
      commands.get(id).add(command);
      convertToKeyFrames(id, command);
    }
  }

  private void convertToKeyFrames(String id, ICommand command) {
    if (commands.get(id).size() == 1) {
      keyframes.get(id).add(new KeyFrame(command.getStartTime(), command.getStartX(),
              command.getStartY(), command.getStartWidth(), command.getStartHeight(),
              command.getStartRed(), command.getStartGreen(), command.getStartBlue()));
    }
    keyframes.get(id).add(new KeyFrame(command.getEndTime(), command.getEndX(),
            command.getEndY(), command.getEndWidth(), command.getEndHeight(),
            command.getEndRed(), command.getEndGreen(), command.getEndBlue()));
  }

  @Override
  public void removeShape(String id) {
    if (!shapes.containsKey(id)) {
      throw new IllegalArgumentException();
    }
    else {
      shapes.remove(id);
      commands.remove(id);
      ids.remove(id);
      keyframes.remove(id);
    }
  }

  @Override
  public void removeLastCommand(String id) {
    if (!commands.containsKey(id)) {
      throw new IllegalArgumentException();
    }
    else {
      int commandSetSize = commands.get(id).size();
      if (commandSetSize == 0) {
        throw new IllegalArgumentException();
      }
      commands.get(id).remove(commandSetSize - 1);
    }
  }

  @Override
  public void addKeyFrame(String id, IKeyFrame k) {
    if (!ids.contains(id)) {
      throw new IllegalArgumentException();
    }
    List<IKeyFrame> tempFrames = keyframes.get(id);
    checkIfFramesEmpty(id, k, tempFrames);
    int first = getFirstTick(tempFrames);
    int last = getLastTick(tempFrames);
    if (checkForFirstOrLast(id, k, first, last)) {
      return ;
    }
    findKeyFramePosition(id, k, tempFrames, last);
  }

  private boolean checkForFirstOrLast(String id, IKeyFrame k, int first, int last) {
    if (k.getTick() < first) {
      insertFirstKeyFrame(id, k);
      remakeCommands(id);
      return true;
    }
    else if (k.getTick() > last) {
      keyframes.get(id).add(k);
      remakeCommands(id);
      return true;
    }
    return false;
  }

  private void findKeyFramePosition(String id, IKeyFrame k, List<IKeyFrame> tempFrames, int last) {
    for (int i = 0; i < keyframes.get(id).size(); i++) {
      IKeyFrame tempFrame = keyframes.get(id).get(i);
      if (k.getTick() == tempFrame.getTick()) {
        keyframes.get(id).set(i, k);
        remakeCommands(id);
        System.out.print(keyframes.get(id).size());
      }
      else if (k.getTick() > last) {
        return ;
      }
      else if (k.getTick() > tempFrame.getTick()
              && k.getTick() < keyframes.get(id).get(i + 1).getTick()) {
        insertNewKeyFrame(id, k, tempFrames, i);
        return ;
      }
    }
  }

  private void checkIfFramesEmpty(String id, IKeyFrame k, List<IKeyFrame> tempFrames) {
    if (tempFrames.size() == 0) {
      keyframes.get(id).add(k);
      remakeCommands(id);
    }
  }

  private void insertFirstKeyFrame(String id, IKeyFrame k) {
    List<IKeyFrame> temp = new ArrayList<>();
    temp.add(k);
    temp.addAll(keyframes.get(id));
    keyframes.get(id).removeAll(keyframes.get(id));
    keyframes.get(id).addAll(temp);
    remakeCommands(id);
    return ;
  }

  private void insertNewKeyFrame(String id, IKeyFrame k, List<IKeyFrame> tempFrames, int i) {
    List<IKeyFrame> newFrames = new ArrayList<>();
    newFrames.addAll(keyframes.get(id).subList(0, i + 1));
    newFrames.add(k);
    newFrames.addAll(keyframes.get(id).subList(i + 1, keyframes.get(id).size()));
    keyframes.get(id).removeAll(tempFrames);
    keyframes.get(id).addAll(newFrames);
    remakeCommands(id);
  }

  private void remakeCommands(String id) {
    List<ICommand> tempCommands = new ArrayList<>();
    int framesSize = keyframes.get(id).size();
    for (int i = 0; i < framesSize; i++) {
      if (i == framesSize - 1) {
        commands.get(id).removeAll(commands.get(id));
        commands.get(id).addAll(tempCommands);
      }
      else {
        IKeyFrame first = keyframes.get(id).get(i);
        IKeyFrame second = keyframes.get(id).get(i + 1);
        tempCommands.add(first.convertToMotionCommand(second));
      }
    }
  }

  private int getFirstTick(List<IKeyFrame> keyFrames) {
    return keyFrames.get(0).getTick();
  }

  private int getLastTick(List<IKeyFrame> keyFrames) {
    int size = keyFrames.size();
    IKeyFrame lastFrame = keyFrames.get(size - 1);
    return lastFrame.getTick();
  }

  @Override
  public void deleteKeyFrame(String id, int tick) {
    if (!ids.contains(id)) {
      throw new IllegalArgumentException();
    }
    else {
      for (IKeyFrame k: this.keyframes.get(id)) {
        if (k.getTick() == tick) {
          keyframes.get(id).remove(k);
          remakeCommands(id);
          return ;
        }
      }
      throw new IllegalArgumentException("No KeyFrame at given tick " + tick);
    }
  }

  private boolean isValidCommand(String id, ICommand c) {
    List<ICommand> tempCommands = this.commands.get(id);
    if (tempCommands.size() == 0) {
      return true;
    } else {
      int commandsSize = tempCommands.size();
      ICommand lastCommand = tempCommands.get(commandsSize - 1);
      return checkForValidMatch(c, lastCommand);
    }
  }

  private boolean checkForValidMatch(ICommand c, ICommand lastCommand) {
    return !(lastCommand.getEndTime() != c.getStartTime() || lastCommand.getEndX() != c.getStartX()
            || lastCommand.getEndY() != c.getStartY()
            || lastCommand.getEndWidth() != c.getStartWidth()
            || lastCommand.getEndHeight() != c.getStartHeight()
            || lastCommand.getEndRed() != c.getStartRed()
            || lastCommand.getEndGreen() != c.getStartGreen()
            || lastCommand.getEndBlue() != c.getStartBlue());
  }

  private void checkAddParameters(String id, Object o) {
    if (!this.screenSet) {
      throw new IllegalStateException("Screen size has not been set.");
    }
    if (id.equals("") || o == null) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * A static public class representing a model builder. This class contains methods for building
   * a model including setting the screen size, adding shapes, and adding motions.
   */
  public static final class Builder implements AnimationBuilder<IAnimationModel> {

    private IAnimationModel model = new AnimationModel();

    @Override
    public IAnimationModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {
      this.model.setScreenSize(x, y, width, height);
      return null;
    }

    @Override
    public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          this.model.addShape(name, new Rectangle());
          break;
        case "ellipse":
          this.model.addShape(name, new Oval());
          break;
        default:
          throw new IllegalArgumentException("Invalid shape.");
      }
      return null;
    }

    @Override
    public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                       int h1, int r1, int g1, int b1, int t2,
                                                       int x2, int y2, int w2, int h2, int r2,
                                                       int g2, int b2) {
      this.model.addCommand(name, new MotionCommand(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2,
              h2, r2, g2, b2));
      return null;
    }

    @Override
    public AnimationBuilder<IAnimationModel> addKeyframe(String name, int t, int x, int y,
                                                         int w, int h, int r, int g, int b) {
      return null;
    }
  }
}
