Design Document 
Kei Yoshikoshi, John Gilhuly, Georgia Tse, Catherine Zhang
=======
###Introduction (Kei)
**This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should be approximately 200-300 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).**

Our team is trying to implement an abstract engine that is able to simulate a simple Logo program. This engine consists of a GUI that gives users a variety of options on how to control the turtle on the screen, and allows the user to input commands given a certain set of syntax. These commands are not only limited to those that affect the turtle on the screen, but can also calculate different things. The primary design goals of this project is to separate the front-end (the GUI) and the back-end (the model) so that group members can work on these parts separately without affecting each other's code. We will also design the project in such a way that it is easy to implement new features, such as new commands, new turtles, etc. As stated, modification of the back-end from the front-end side will be closed, and vice versa. Each side will only know what is completely necessary, and all other processes are hidden. This makes extensions easier - if we were to change the front-end to some other type of display, this should be rather easy since the back-end would not be affected (open for extension, closed for modification). We intend to implement the above in a scalable manner that conforms to standard design conventions separating the program into reasonable structures using various classes. We will be using Java8 and JavaFX to do so.

###Overview (Kei)
**This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing specifically on each class's behavior, not its state. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should be approximately 700-1000 words long and discuss specific classes, methods, and data structures, but not individual lines of code.**

![Design OverView Drawing](https://github.com/duke-compsci308-spring2015/slogo_team06/blob/master/Design%20OverView%20Drawing)

###User Interface
**This section describes how the user will interact with your program (keep it simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.**

The user interface would contain a menu bar (to contain personalization of the pen, turtle, background color, access to a help page), a text input area (for commands), a console for return values, read a sidebar to see previous commands, and the physical canvas/grid.

###Design Details 
**This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.**

The relationship between the view and the controller would be one way, any commands, or anything that needed to be passed to be interpreted would go to the controller that would then parse the commands and get the appropriate command class or update the appropriate turtle, etc. Any updates to the back end turtle classes would be observed by the view updater that would update instantly. This way, the model could relay information back to the view in this way. This way, the model needs to contain an observer instance of the view updater.

The *model* would contian a general command super class and have sub command types so that each command could be generalized into a group, whether it is a math type command, or turtle status command, or a move command, and within those subgroups, and each command would have its own class, but extend these super classes. 
The *command parser* would return a specific command object and the controller would determine what type of subcommand that command belonged to, and if it was a view type command, the controller would call on the view to perform the specified command, and if it was a non-view related command, it would call the model classes to reflect that command change. 

###API Example code (this is especially important in helping others understand how to use your API)
**It should be clear from this code which objects are responsible for completing each part of the task, but you do not have to implement the called functions. Show actual "sequence of code" that implements the following use case:  The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail.**
**Note, clearly show the flow of calls to public methods needed to complete this example, indicating which class contains each method called. It is not necessary to understand exactly how parsing works in order to complete this example, just what the result of parsing the command will be.**

 - if user typed in 'PENDOWN', this command would go through the command parser, and it would see if it is a subtype of a view command and either call the scene updater directly to change the value of the pen status and have the view display the return value
 - if the user typed in 'fd 50' this command would be passed to the controller and then it would give it to the command parser to then do the apprpriate move methods

###Design Considerations 
**This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.**

We talked about having a turtle view and a turtle model, where the turtle view is the image and any pen drawing methods, and the turtle model contains the x and y locations and store the heading. 
We considered where the responsability of storing the offset grid view where the back end will send original locations and final locations and the view will store things like if the pen is up/down to interpret whether or not to draw the line. We also considered where the command parser should live / be stored either between the controller and the model, and act as the other middle man between the view and the data.
We disliked the idea of having the view contain a method in the controller, but then couldn't come to a medium between using an observable and having the controller shuttle information back and forth. 

A huge consideration we had was how exactly to use the obeservable in our turtle classes because we ran into complications with the physical drawing of a line which requires a starting and ending point. If you used the observable, the view turtle would need to have the old position, but once the model turtle was changed, the view turtle would have no idea where it used to be. 
We decided to not use the observable and just have the back end turtle send information about where a line needs to be drawn/ where a turtle needs to be moved so that given original positions and new positions. This way, the front end turtle does not need to store its location, but just know have a move method, and based on if the pen is up or not, it will draw a line. <-- we are not sure about the best way mediate between these options.
If we made commmands classes, there was also a problem where it needed to be able to modify the turtle directly, but the command class couldn't have an instance of the turtle so then what is even the purpose of the separate command class? 

###Team Responsibilities
**This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.**
Front end: John, Kei
* GUI
*parser
*view updater class
Back end: Catherine, Georgia