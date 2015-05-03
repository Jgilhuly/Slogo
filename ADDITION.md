Addition
=====

I chose to implement this additional feature: "Add a new view that allows users to see the images of all of the turtles (active or not). Clicking on an image should let the user change that turtle's image. If your team did not implement multiple turtles, then show the image of just the single turtle."


####Estimation:

I think this feature will take me 3-4 full hours of coding and figuring out some javaFX features in order to implement the view of this. I will need to update the GUI class and also create a new class that encapulates this view of multiple turtles (aka another part of the window in the view). I would estimate an adding of like 2-3 classes. I believe that one class will contain the scene, the GUI class needs to be updated to handle the view of the turtles, and maybe the turtle class to handle an active selection.

####Review

This part took me around three hours to implement. I did not get it completely right on the first try because we had not used Observable lists for our list of turtles and in turn it was very hard to figure out why the turtles were not changing immediately. I ended up adding one file really, and this file was to handle the new stage of the new turtle view, and I modified a few classes that had to deal with the observable turtles such as all the GUI elements and the GUI controller. There were some nuances with file opening and also List views that I had to look up or look at other projects for. 

####Analysis

Working on adding this feature was not too hard, however I had to slightly work around pre-exisiting code so that I didn't have to modify and immense amount of code. The biggest change was getting an observable list of turtles which we didn't have before, but would also have been useful for the previous sprints. The code could have been cleaner and there could have been more division of elements of the GUI. Instead of passing around a list of turtles, there should have just been an observable list in the first place. I think it wouldn't have been too hard if you were not familiar with the code: you would mainly have to figure out where the turtle images were stored and then manipulate that. That might be hard to find because we passed it through the controller to the GUI.

