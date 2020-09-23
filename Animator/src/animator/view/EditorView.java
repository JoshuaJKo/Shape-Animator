package animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.WindowConstants;

import animator.controller.AnimationEditorController;
import animator.controller.AnimatorEditorGuiHandler;
import animator.controller.IAnimationController;
import animator.controller.IAnimationEditorController;
import animator.controller.MouseClickHandler;
import animator.model.IAnimationModel;
import animator.model.IViewOnlyAnimationModel;

/**
 * A public class representing the animation editor view. This class launches a GUI with several
 * controls used to edit the animation including starting, stopping, restarting, changing speed, or
 * changing any of the shapes/keyframes of the animation.
 */
public class EditorView extends JFrame implements IEditorView, ActionListener {

  private IViewOnlyAnimationModel model;
  private AnimationPanel mainPanel;
  private StatusPanel statusPanel;
  private AnimatorEditorGuiHandler listener = null;
  private JTextField tickField = new JTextField();
  private JTextField xField = new JTextField();
  private JTextField yField = new JTextField();
  private JTextField widthField = new JTextField();
  private JTextField heightField = new JTextField();
  private JTextField redField = new JTextField();
  private JTextField greenField = new JTextField();
  private JTextField blueField = new JTextField();
  private JTextField idField = new JTextField();

  /**
   * The public constructor for the EditorView. This constructor sets up the GUI window.
   *
   * @param model animation model object.
   */
  public EditorView(IViewOnlyAnimationModel model) {
    super();
    checkForInvalidInputs(model == null);
    this.model = model;
    this.setTitle("Animator");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Dimension screenSize = model.getScreenSize();
    getScreenParameters(screenSize);
    this.setSize(screenSize);
    this.setFont(new Font("Monaco", Font.PLAIN, 20));
    final Dimension buttonSize = new Dimension(120, 60);

    AnimationPanel p = new AnimationPanel(model);
    p.setPreferredSize(model.getScreenSize());
    this.mainPanel = p;
    this.add(p);

    int windowCutoff = 650;
    int scrollBarVerticalBound;
    int scrollBarHorizontalBound;
    if (model.getScreenSize().width < windowCutoff) {
      scrollBarHorizontalBound = model.getScreenSize().width;
    }
    else {
      scrollBarHorizontalBound = windowCutoff;
    }
    if (model.getScreenSize().height < windowCutoff) {
      scrollBarVerticalBound = model.getScreenSize().height;
    }
    else {
      scrollBarVerticalBound = windowCutoff;
    }

    JPanel contentPane = createScrollPane(p, scrollBarVerticalBound, scrollBarHorizontalBound);
    contentPane.setBackground(Color.PINK);
    this.add(contentPane);

    addPanels(buttonSize);
  }

  private void addPanels(Dimension buttonSize) {
    JPanel editorPanel = new JPanel();
    editorPanel.setPreferredSize(new Dimension(120, 250));
    editorPanel.setBackground(Color.PINK);

    makeButton(buttonSize, editorPanel, "Pause/Play", "pause");
    makeButton(buttonSize, editorPanel, "Restart", "restart");
    makeButton(buttonSize, editorPanel, "Increase Speed", "speed");
    makeButton(buttonSize, editorPanel, "Decrease Speed", "slow");
    makeButton(buttonSize, editorPanel, "Toggle Looping", "looping");
    makeButton(buttonSize, editorPanel, "Add Rectangle", "addRectangle");
    makeButton(buttonSize, editorPanel, "Add Oval", "addOval");

    idField.setPreferredSize(new Dimension(100, 60));
    JLabel idLabel = new JLabel("Enter new shape ID:");
    editorPanel.add(idLabel);
    editorPanel.add(idField);

    this.add(editorPanel, BorderLayout.EAST);

    StatusPanel s = new StatusPanel(this.model);
    s.setPreferredSize(new Dimension(100, 100));
    s.setBackground(Color.PINK);
    this.statusPanel = s;
    this.add(s, BorderLayout.SOUTH);
  }

  private void makeButton(Dimension buttonSize, JPanel editorPanel, String s2, String pause) {
    JButton pauseButton = new JButton(s2);
    pauseButton.setPreferredSize(buttonSize);
    pauseButton.setActionCommand(pause);
    pauseButton.addActionListener(this);
    editorPanel.add(pauseButton, BorderLayout.SOUTH);
  }

  private void getScreenParameters(Dimension screenSize) {
    if (screenSize.getWidth() >= 650) {
      screenSize.setSize(790, screenSize.getHeight());
    }
    else {
      screenSize.setSize(screenSize.getWidth() + 140, screenSize.getHeight());
    }
    if (screenSize.getHeight() >= 650) {
      screenSize.setSize(screenSize.getWidth(), 790);
    }
    else {
      screenSize.setSize(screenSize.getWidth(), screenSize.getHeight() + 140);
    }
  }

  private JPanel createScrollPane(AnimationPanel p, int scrollBarVerticalBound,
                                  int scrollBarHorizontalBound) {
    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setBounds(0, 0, scrollBarHorizontalBound, scrollBarVerticalBound);
    JPanel contentPane = new JPanel(null);
    contentPane.setPreferredSize(new Dimension(scrollBarHorizontalBound, scrollBarVerticalBound));
    contentPane.add(scrollPane);
    return contentPane;
  }

  @Override
  public void view(int speed) {
    this.setVisible(true);
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public void refresh(int tick) {
    this.mainPanel.setCurrentTick(tick);
    this.statusPanel.setTick(tick);
    this.repaint();
  }

  private void checkForInvalidInputs(boolean b) {
    if (b) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (this.listener == null) {
      throw new IllegalStateException("Listener must be set for view.");
    }
    else {
      this.listener.actionPerformed(actionEvent);
    }
  }

  @Override
  public void addListener(IAnimationEditorController c) {
    checkForInvalidInputs(c == null);
    this.listener = new AnimatorEditorGuiHandler(c);
  }

  @Override
  public void addMouseListener(IAnimationEditorController c) {
    checkForInvalidInputs(c == null);
    this.mainPanel.addMouseListener(new MouseClickHandler(c));
  }

  @Override
  public void openShapeEditor(String id) {
    JFrame editorWindow = new JFrame();
    editorWindow.setSize(new Dimension(500, 500));
    editorWindow.setTitle("Shape Editor");
    JPanel editorGui = new JPanel();
    editorGui.setPreferredSize(new Dimension(250, 500));
    editorGui.setBackground(Color.PINK);

    JLabel shapeEditLabel = new JLabel("Please enter new KeyFrame data\n");
    editorGui.add(shapeEditLabel);

    makeTextField(editorGui, "tick = ", tickField);
    makeTextField(editorGui, "x = ", xField);
    makeTextField(editorGui, "y = ", yField);
    makeTextField(editorGui, "width = ", widthField);
    makeTextField(editorGui, "height = ", heightField);
    makeTextField(editorGui, "red = ", redField);
    makeTextField(editorGui, "green = ", greenField);
    makeTextField(editorGui, "blue = ", blueField);

    addShapeEditorButton(editorGui, "Add Frame", "addKeyFrame");
    addShapeEditorButton(editorGui, "Delete Frame", "deleteKeyFrame");
    addShapeEditorButton(editorGui, "Delete Shape", "deleteShape");

    JPanel framesWindow = makeKeyFramesPanel(id);

    editorWindow.add(framesWindow);
    editorWindow.add(editorGui, BorderLayout.WEST);
    editorWindow.setVisible(true);
  }

  private JPanel makeKeyFramesPanel(String id) {
    JPanel framesWindow = new JPanel();
    framesWindow.setBackground(Color.PINK);
    framesWindow.setPreferredSize(new Dimension(250, 500));
    JLabel framesLabel = new JLabel("Current frames for shape with ID: " + id);
    framesLabel.setPreferredSize(new Dimension(150, 100));
    framesWindow.add(framesLabel, BorderLayout.NORTH);
    Object[] frames = this.model.getKeyFrames(id).toArray();
    String[] stringFrames = new String[frames.length];
    for (int i = 0; i < frames.length; i++) {
      stringFrames[i] = frames[i].toString() + "\n";
    }
    JList list = new JList(stringFrames);
    list.setVisibleRowCount(5);
    JScrollPane framesPane = new JScrollPane(list);
    framesPane.setPreferredSize(new Dimension(200, 300));
    framesWindow.add(framesPane);
    return framesWindow;
  }

  private void addShapeEditorButton(JPanel editorGui, String s, String addKeyFrame) {
    JButton addFrameButton = new JButton(s);
    addFrameButton.setActionCommand(addKeyFrame);
    addFrameButton.addActionListener(this);
    addFrameButton.setPreferredSize(new Dimension(200, 30));
    editorGui.add(addFrameButton);
  }

  private void makeTextField(JPanel editorGui, String s, JTextField tickField) {
    JLabel tickLabel = new JLabel(s);
    tickLabel.setPreferredSize(new Dimension(75, 30));
    tickField.setPreferredSize(new Dimension(100, 30));
    editorGui.add(tickLabel);
    editorGui.add(tickField);
  }

  @Override
  public String getTextFromField(String field) {
    switch (field) {
      case "tick":
        return tickField.getText();
      case "x":
        return xField.getText();
      case "y":
        return yField.getText();
      case "width":
        return widthField.getText();
      case "height":
        return heightField.getText();
      case "red":
        return redField.getText();
      case "green":
        return greenField.getText();
      case "blue":
        return blueField.getText();
      case "id":
        return idField.getText();
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public IAnimationController getController(IAnimationModel model, int speed) {
    return new AnimationEditorController(model, this, speed, true);
  }
}
