SLogo Addition (Kei Yoshikoshi - ky52)
======
####Estimation: before looking at the old code:
**How long do you think it will take you to complete this new feature?**
As I worked with both the front and the back-end for this project, I chose to do the front-end feature for this extension. I believe this will take me about one to two hours to complete.

**How many files will you need to add or update? Why?**
I believe I will have to change and add about 4 to 5 classes. The front-end GUI classes are relatively well-structured and those edits will probably be minor changes. To implement the new feature, I would have to make a new class or two.

####Review: after completing the feature:
 **How long did it take you to complete this new feature?**
It took me about 30 minutes to complete this new feature.

**Did you get it completely right on the first try?**
No - I had issues with setting the path for the ImageView as it kept giving me an InvalidResourceException. After some searching on Google, I was able to get this right. I then had to adjust some parts of the implementation as the ComboBox was not updating as I was adding new turtles, etc.

 **How many files did you need to add or update? Why?**
 In total, I added two new classes and edited three classes. I edited TurtleView to add a toString() method so that the ComboBox can show its name. I edited the TPropertiesElement class to add a new button to the left pane of the main screen, and I edited the GUI class to make the list of TurtleViews an observable list rather than an ArrayList. This made it easier for me to implement the ComboBox.
 
####Analysis: what do you feel this exercise reveals about your project's design and documentation?
 **Was it as good (or bad) as you remembered?**
The overall layout was decently done but the documentation was close to non-existent. I was surprised at how quickly I was able to implement this new feature, aside from taking the time to go through and track the method calls. My partners and I had done some refactoring before we submitted the code, resulting in a cleaner design.
 **What could be improved?**
There are a lot of element classes (classes with the name Element in them) without an interface. I believe that there are similarities among all these classes and the instantiations can be generalized using an interface. However, in regards to adding a new element, it was very easy to do as only one centralized class contained all of these elements. Another thing that could be improved is to comment the code so I can easily navigate, rather than having to use Eclipse's Reference tool.
 **What would it have been like if you were not familiar with the code at all?**
 It would have been hard to understand which methods were calling which class or which method, as the code was poorly commented. It was because of a rather clean structure and the familiarity of the code that I was able to navigate through relatively quickly. I understand that this task is to help us understand the importance of good design and documentation as even we forget our own code and must re-familiarize ourselves.
