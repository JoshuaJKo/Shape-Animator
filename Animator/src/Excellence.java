import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import animator.controller.IAnimationController;
import animator.model.AnimationModel;
import animator.model.IAnimationModel;
import animator.util.AnimationBuilder;
import animator.util.AnimationReader;
import animator.util.AnimatorViewFactory;
import animator.view.IAnimationView;

/**
 * A public class holding main method used to run animator application.
 */
public class Excellence {

  /**
   * Main method used to run animator application.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {

    String viewType = null;
    String outputFileName = null;
    String inputFileName = null;
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      inputFileName = checkForSpecifier(args, inputFileName, i, "-in");
      viewType = checkForSpecifier(args, viewType, i, "-view");
      outputFileName = checkForSpecifier(args, outputFileName, i, "-out");
      speed = checkSpeedSpecifier(args, speed, i);
    }

    if (viewType == null || inputFileName == null) {
      showError("Mandatory arguments not specified.");
    }

    AnimationBuilder builder = new AnimationModel.Builder();
    AnimationReader reader = new AnimationReader();
    IAnimationModel model = tryFileRead(inputFileName, builder, reader);
    AnimatorViewFactory factory = new AnimatorViewFactory();
    checkValidView(viewType, model, factory);
    IAnimationView view = factory.create(viewType, model);
    IAnimationController controller = view.getController(model, speed);
    try {
      controller.play();
    }
    catch (IllegalArgumentException e) {
      showError("Illegal Argument Exception");
    }
    catch (IllegalStateException e) {
      showError("Illegal State Exception");
    }

    if (controller.returnOutput() == null) {
      return ;
    }
    else if (outputFileName == null) {
      System.out.print(controller.returnOutput());
    }
    else {
      writeToFile(view, outputFileName);
    }

  }

  private static void checkValidView(String viewType, IAnimationModel model,
                                     AnimatorViewFactory factory) {
    try {
      IAnimationView view = factory.create(viewType, model);
    }
    catch (IllegalArgumentException e) {
      showError("Invalid view input.");
    }
  }

  private static IAnimationModel tryFileRead(String inputFileName, AnimationBuilder builder,
                                             AnimationReader reader) {
    try {
      AnimationModel model = (AnimationModel) reader.parseFile(new FileReader(inputFileName),
              builder);
      return model;
    }
    catch (FileNotFoundException e) {
      showError("File not found.");
      return null;
    }
  }

  private static int checkSpeedSpecifier(String[] args, int speed, int i) {
    if (args[i].equals("-speed")) {
      try {
        speed = Integer.parseInt(retrieveNextArg(args, i));
      } catch (NumberFormatException e) {
        showError("Invalid speed argument.");
      }
    }
    return speed;
  }

  private static String checkForSpecifier(String[] args,
                                          String inputFileName, int i, String specifier) {
    if (args[i].equals(specifier)) {
      inputFileName = retrieveNextArg(args, i);
    }
    return inputFileName;
  }

  private static void writeToFile(IAnimationView view, String fileName) {
    try {
      FileWriter writer = new FileWriter(fileName);
      writer.append(view.toString());
      writer.close();
    }
    catch (IOException e) {
      showError("Issue writing to file.");
    }
  }

  private static String retrieveNextArg(String[] args, int i) {
    try {
      List<String> specifiers = Arrays.asList("-in", "-out", "-view", "-speed");
      if (specifiers.contains(args[i + 1])) {
        showError("Invalid input.");
        return null;
      } else {
        return args[i + 1];
      }
    }
    catch (IndexOutOfBoundsException e) {
      showError("Invalid input.");
      return null;
    }
  }

  private static void showError(String message) {
    JOptionPane.showMessageDialog(null, message, "error", 0);
    System.exit(0);
  }
}
