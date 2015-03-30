package ui;

//THIS ENTIRE FILE IS A PART OF MY MASTERPIECE
//GEORGIA TSE

public interface iGUI {
    public static final String DEFAULT_TURTLE_IMAGE_PATH = "/resources/images/turtleImage.png";
    public void initialize();
    public TurtleView makeTurtleView (String imagePath);
    public void parseCommand();
    public void setOutputText(String output);
    public void setInputText(String input);
    public void addCommandHistory (String input);
    public void createNewWorkspace();

}