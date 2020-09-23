package animator.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that represents a viewable animation, keeping track of shapes and how they are
 * commanded to move, change size, and/or change color. This class contains methods used to view
 * the data of a model, but not mutate it.
 */
public class ViewOnlyAnimationModel implements IViewOnlyAnimationModel {

  protected Map<String, IViewOnlyShape> shapes;
  protected Map<String, List<ICommand>> commands;
  protected Map<String, List<IKeyFrame>> keyframes;
  protected List<String> ids;
  protected int left;
  protected int top;
  protected int width;
  protected int height;
  protected boolean screenSet;

  /**
   * No parameter constructor that instantiates the lists to null and states that the screen has
   * not been set yet.
   */
  public ViewOnlyAnimationModel() {
    this.shapes = new HashMap<>();
    this.commands = new HashMap<>();
    this.keyframes = new HashMap<>();
    this.ids = new ArrayList<>();
    this.screenSet = false;
  }

  @Override
  public void setScreenSize(int left, int top, int width, int height) {
    checkIfScreenSet(true);
    checkInvalidInputs(width < 1);
    checkInvalidInputs(height < 1);
    this.left = left;
    this.top = top;
    this.width = width;
    this.height = height;
    this.screenSet = true;
  }

  @Override
  public Dimension getScreenSize() {
    checkIfScreenSet(false);
    return new Dimension(this.width, this.height);
  }

  @Override
  public int getTop() {
    checkIfScreenSet(false);
    return this.top;
  }

  @Override
  public int getLeft() {
    checkIfScreenSet(false);
    return this.left;
  }

  @Override
  public int getWidth() {
    checkIfScreenSet(false);
    return this.width;
  }

  @Override
  public int getHeight() {
    checkIfScreenSet(false);
    return this.height;
  }

  @Override
  public IViewOnlyShape getShape(String id) {
    if (!this.shapes.containsKey(id)) {
      throw new IllegalArgumentException();
    }
    return this.shapes.get(id).getCopy();
  }

  @Override
  public List<IViewOnlyShape> getShapes() {
    List<IViewOnlyShape> shapes = new ArrayList<>();
    for (Map.Entry<String, IViewOnlyShape> shapeSet: this.shapes.entrySet()) {
      IViewOnlyShape temp = shapeSet.getValue();
      shapes.add(temp.getCopy());
    }
    return shapes;
  }

  @Override
  public List<ICommand> getCommands(String id) {
    if (!this.commands.containsKey(id)) {
      throw new IllegalArgumentException();
    }
    List<ICommand> commands = new ArrayList<>();
    for (ICommand c: this.commands.get(id)) {
      commands.add(c.getCopy());
    }
    return commands;
  }

  @Override
  public List<String> getIds() {
    List<String> result = new ArrayList<>();
    for (String id: this.ids) {
      result.add(id);
    }
    return result;
  }

  @Override
  public int getFinalTick() {
    int tempMax = -1;
    for (String id: this.ids) {
      for (ICommand c: this.getCommands(id)) {
        if (c.getEndTime() >= tempMax) {
          tempMax = c.getEndTime();
        }
      }
    }
    return tempMax;
  }

  @Override
  public List<IKeyFrame> getKeyFrames(String id) {
    List<IKeyFrame> temp = new ArrayList<>();
    for (IKeyFrame k: this.keyframes.get(id)) {
      temp.add(k.getCopy());
    }
    return temp;
  }

  @Override
  public List<IShape> getShapesAtTick(int tick) {
    List<IShape> temp = new ArrayList<>();
    for (String id: this.getIds()) {
      IViewOnlyShape tempShape = this.getShape(id);
      List<IKeyFrame> tempFrames = this.getKeyFrames(id);
      if (tempFrames.size() <= 1) {
        continue ;
      }
      IShape shape = tempShape.getMutableCopy();
      for (int i = 0; i < tempFrames.size(); i++) {
        if (i == tempFrames.size() - 1) {
          continue ;
        }
        else if (tick >= tempFrames.get(i).getTick() && tick <= tempFrames.get(i + 1).getTick()) {
          IKeyFrame first = tempFrames.get(i);
          IKeyFrame second = tempFrames.get(i + 1);
          IShape newShape = first.getState(tick, second, shape);
          temp.add(newShape);
          this.shapes.put(id, newShape);
        }
      }
    }
    return temp;
  }

  @Override
  public String getIdOfTopShape(int tick, int x, int y) {
    List<IShape> tempShapes = this.getShapesAtTick(tick);
    List<IShape> shapesAtPos = new ArrayList<>();
    for (IShape s: tempShapes) {
      if (s.inShape(x + this.left, y + this.top)) {
        shapesAtPos.add(s);
      }
    }

    if (shapesAtPos.size() == 0) {
      throw new IllegalArgumentException();
    }

    return findId(shapesAtPos);
  }

  private String findId(List<IShape> shapesAtPos) {
    int size = shapesAtPos.size();
    IShape topShape = shapesAtPos.get(size - 1);

    for (String id: ids) {
      IViewOnlyShape s = getShape(id);
      if (s.getMutableCopy().equals(topShape)) {
        return id;
      }
    }
    return null;
  }

  private void checkIfScreenSet(boolean b) {
    if (screenSet == b) {
      throw new IllegalStateException();
    }
  }

  private void checkInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

}
