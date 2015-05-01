


**Slogo Addition**

*Estimation*

Without looking at the slogo code, I think implementing this new front end feature will take a conservative 3 hours. I anticipate taking about 30 minutes or so to look over the code and remember how everything fits together, and more importantly the syntax of the internal program. After that, I should be able to code the feature in about an hour. I’ll leave the other hour and a half to debug the code when I inevitably break the entire front end (although I hope to be entirely done in under 2 hours). I will need to update the main GUI class to include the button to open this view, or display it. I will be adding a class to handle the internals of the new view, however changing the Turtle image shouldn’t be too hard, because if I remember correctly, the GUI holds an array of TurtleView classes which are observed by the back end.

*Review*

It took me a little over 2 hours to implement this new Turtle Image View Selector. I definitely did not get it completely right on my first try. I lost a good bit of time trying to figure out why the list of turtles I had in the GUI was not updating when a new Turtle was added, which turned out to be because I never added the new Turtle to the list. It’s easy to see how you completely forget what state of mind you were in when you last worked on a project. In order to implement this feature, I had to slightly modify the GUI and TurtleView classes, modify the TPropertiesElement class, and create a new class which I called TurtleImageSelectorView.

*Analysis*
	
Dear god some of my code was awful. I fortunately had a good amount of comments that helped me understand the code, but every method did far too much by itself. I had to plod through a good deal of archaic code because I did not separate jobs well enough. The code could’ve been hugely improved by distributing labor between methods and classes more intelligently, and also by making more use of the Consumer/Supplier/Predicate interfaces. I passed the entire GUI class to smaller elements within the GUI a number of times, which I would not call very good design. Other than that, it would’ve been better if I had standardized the various GUI elements and their creation a bit more. The initialize method in the GUI is a mess, and it is mainly because of this. 

If I was an outsider looking at this code for the first time, I think i could follow it, apart from the the Turtle back end / front end interaction, which was accomplished through observables and never really explained in the code. The comments and documentation is good enough that things would make general sense. That being said, I don’t think I would have a very high opinion of the author of this code, simply because it is inelegant and a bit too brute-forcey.