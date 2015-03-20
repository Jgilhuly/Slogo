// This entire file is part of my masterpiece.
// John Gilhuly

package ui;

import java.util.Set;
import javafx.collections.ObservableList;
import ui_table.TableElements;

public interface FrontEndInterface {
    public void sendCommands (String input, String language);
    public void setOutputText (String outputText);
    public void setListBind (String type, ObservableList<TableElements> l);
    public Set<String> getPrevCommandList ();
    public void createTurtle (TurtleView tView);
    public void createNewWorkspace ();
    public void addCommandHistory (String input);
}
