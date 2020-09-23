MotionCommand:
A class that represents one movement of a shape. Implements the ICommand interface. Stores the start time, end time, start x, end x, start y, end y, start height, end height,
start width, end with, start color, and end color. Allows the user to get all of the parameters of a command. Allows the user to set a state in which a shape is in at any time. 
For example, there a rectangle was passed into the method with the start time 0, end time 10, and any value besides null for the rest of the parameters. If you were to pass in time 5,
it would find the correct position  at time 5 (in this case it would be in between time 0 and time 10) and create a new shape with the same parameters but with a new position
in the calculated position in time 5. This class is used to keep track of a shape moving from one coordinate to another, changing colors, or changing sizes.

ICommand:
An interface that represents anything that can be a command (like in the future we could implement a rotate command). Provides the method headers for the user to get all the 
instance variables in the command. Also provides the method headers to set the current state of a shape at a given time. This is useful to ensure all commands have the same basic
methods and have just one method that actually sets the state of the shape.

IShape:
An interface that represents any 2d shape. Provides method headers for any shape that implements the interface to set the position, color, height, and width of the shape and also 
allows the user to make a copy of the shape. This is useful in order to ensure all shapes have the same basic methods.

AShape:
An abstract class that represents any 2d shape that also implements IShape. Implements all the methods within IShape. This class is useful so when we make other shapes it can just
inherit the concrete methods in this class and override any necessary methods pertaining specifically to that shape.

Rectangle:
A class that represents a 2d rectangle. Extends. Keeps track of the width(int) and height(int) of the rectangle. Overrides the toString, getting the dimensions and setting the dimensions
to fit the rectangles parameters. This class is useful to represent a rectangle.

Oval:
A class that represents a 2d oval. Implements the oval interface.Keeps track of the radius(int) of the oval. Overrides the toString, getting the dimensions and setting the dimensions
to fit the circles parameters. This class is useful to represent a rectangle.

AnimationModel:
A class that represents the animation as a whole. Keeps track of individual shapes and their commands (Which currently is only a movement command) and also the width and height of the
entire view as parameters. The commands are represented by a HashMap with a string that represents a shape unique ID for its key and a list of commands corresponding to that shape for
its values. The shapes are also represented by a HashMap in which the unique shape id (a string) is its key and the shape itself is the value. This class is used in order to keep track
of all shapes and their commands in the animation and the field that the shapes are being animated on.

AViewOnlyShape:
ViewOnlyOval:
ViewOnlyRectangle:
IViewOnlyShape:
AViewOnlyShape:
All of the view classes are the same exact methods as the classes they are views of but excludes any mutating methods. Meaning that the only methods available to the user are methods
that retrieve the shapes instance variables.

For Assignment 6:

Changes:

Added getLeft(), getTop(), getWidth(), getHeight(), getScreenSize(), and getShape(String id) to IAnimation model. These methods were made because they are extremely useful
when trying to implement a Swing visual view. Additionally, an ArrayList of Strings was added as a field to the model which stores the IDs of all of the shapes. Consequently,
a method named getIds() was implemented into the model and returns the list of IDs. This method was useful when trying to find which shapes were created before others because
each ID is stored in the order the shapes are made. Therefore, older shapes appear first in the list.

Next, I added public constructors to my Oval and Rectangle classes that take in no fields. This was done because the input files don't specify parameters for shapes when shapes
are created. As a result, shapes can be created with arbitrary values as fields in order to comply with input files. Consequently, the constructors for the old shapes that took
in all parameters were made protected in the abstract classes and public in the subclasses in order to allow for object copying and easier testing. This also led to removing the
requirements for shapes and initial commands to have the same parameters as shapes could be created with no parameters. This did not cause much of an issue as our design does not
depend on the shape being at its initial state in the model.

Lastly, the left and top fields were added to the model. These were added to act as the top-left corner of the animation viewing boundary. These fields are essential when
plotting the shapes to the visual view.

Added:

Builder:
A public static class within the AnimationModel that builds a model based on given parameters from a document. This class implements the AnimationBuilder interface
which is given for this assignment. All methods for this interface were implementable except addKeyFrame().

AnimatorViewFactory:
A public factory class that has a single method which takes in a String and produces a view object based on the inputted String. The possible inputs for
this method are "text", "svg", and "visual".

IAnimationView:
A public interface to represent a view for an animation. This interface has two methods, view(int speed) and toString(). The view method produces the specified
view and stores it for the text and SVG views and outputs it for the visual view. In order to access the final view, the toString() method can be called on the view and will return
a String representing the view. Since there is no String representation of the visual view, toString() returns null in that class.

SVGView:
A public class representing the view that produces an SVG formatted representation of the animation. This class produces an SVG representation of an animation according to
the official SVG documentation.

TextView:
A public class representing the view that produces a text description of the animation. This class produces a view in accordance with the text output from Assignment 5.
The view method in this class will produce the text description of the animation. The speed parameter for this method is not used.

AnimationPanel:
A public class that represents the panel where shapes will be drawn. This class extends JPanel and implements ActionListener. Extending JPanel allows this panel to
be used as a drawing panel for the visual view. Implementing ActionListener allows the panel to be a listener for the Timer object in the visual view. This timer ticks at a user-specified
speed (default is 1 tick per second). Every time the timer ticks, a method inside AnimationPanel named actionPerformed is called. In this method is where I refresh the drawing board and
redraw the shapes as well as increase the current tick by 1. This is done so every time the timer ticks, the timeline of the animation is maintained. This class draws the shapes by finding
the command at the current tick for each shape. Then, it mutates the shape to be at the correct position at that time. Lastly, it draws the shape to the panel.

VisualView:
A public class that represents a visual view of an animation. This class extends JFrame outputs a frame where an animation can be viewed. The shapes of an animation are drawn
using the drawing panel object AnimationPanel, which implements JPanel. A Timer object is stored in this class and pushes the animation timeline. The view method in this class will start the
animation at the given speed (in ticks per second).

Excellence:
This is a public class that holds the main() method for the animator. This class takes in command line arguments that access files to build models and then outputs a command-line-specified
animation view to either a file or to System.out (visual views are outputted, by default).

I also received feedback for Assignment 5 to make all of my fields private when appropriate because I had an accessible field in one of my classes. I simply just made that field private to fix
this issue.

Assignment 7:

New changes:

There were many changes from this assignment to the last. Here are all of the classes with change.

VisualView:
The visual view no longer has its own timer. All of the timing is done by the corresponding controller object.

AnimationPanel:
The animation panel is no longer working as an ActionListener. Additionally, the animation panel does not do the tweening processes and
instead receives shape information from the model.

IAnimationModel / IViewOnlyAnimationModel:
My original model object has now been split up into two separate interfaces, a mutable and immutable one. The IViewOnlyAnimationModel
contains all methods needed to retrieve any fields. The IAnimationModel contains methods that mutate the fields of the animation. New methods
added to this class include addKeyFrame and deleteKeyFrame. Additionally, all added commands and keyframes will be automatically
updated to their proper states when KeyFrames are added or removed.

New classes:

IKeyFrame / KeyFrame:
This is the KeyFrame object for my animation. This KeyFrame keeps track of a single endpoint of an animation. The IKeyFrame interface
has various methods that retrieve the fields of the keyframe along with other essential methods used during the tweening processes.

IAnimationController:
This is the overall interface for all controllers. This interface holds methods used by controllers to produce an animation.

IAnimationEditorController:
This is the interface for the animation editor. This interface holds methods that can be used to change or edit the animation.

AnimationEditorController:
This is the controller for the animation editor. This class handles all of the commanding and delegation of the animation
during the editing process. This class also holds the timer controls of the animation. This class
implements IAnimationEditorController.

VisualViewController:
This is the controller for the visual view. This class contains all of the methods needed to properly
produce a visual view. This class implements IAnimationController.

SVGViewController:
This is the controller for the SVG view. This class contains all of the methods needed to properly
produce an SVG view. This class implements IAnimationController.

TextViewController:
This is the controller for the text view. This class contains all of the methods needed to properly
produce a text view. This class implements IAnimationController.

TimerListener:
This is a listener class for the timer object made by the visual views. This class tells the
controller to handle a tick whenever the timer object ticks. This class implements ActionListener.

MouseClickHandler:
This is the MouseAdapter class that handles a mouse click and sends the information to the
controller.

AnimationEditorGuiHandler:
This class implements ActionListener and sends the controller commands from the GUI to handle.