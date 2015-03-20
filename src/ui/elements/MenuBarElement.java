package ui.elements;

import java.util.ResourceBundle;
import ui.GUI;
import ui.Pen;
import util.HTMLHelpPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;


public class MenuBarElement {
    private final String helpPageURL =
            "http://www.cs.duke.edu/courses/compsci308/spring15/assign/03_slogo/commands.php";
    private ToolBar tb;
    private ResourceBundle myResources;
    private CanvasElement canvasPane;
    private IOElement ioPane;
    private SVGPath svg;
    private ColorPicker backgroundColorPicker;
    private ColorPicker penColorPicker;
    private Pen myPen;
    private String languages[];
    private String selectedLanguage;
    private GUI myGui;

    public MenuBarElement (ResourceBundle resourcesIn,
                           CanvasElement canvasPaneIn, IOElement ioPaneIn,
                           Color backgroundColor, String[] languagesIn,
                           String defaultLanguageIn, Pen penIn, GUI myGuiIn) {
        myResources = resourcesIn;
        canvasPane = canvasPaneIn;
        myPen = penIn;
        languages = languagesIn;
        ioPane = ioPaneIn;
        selectedLanguage = defaultLanguageIn;
        myGui = myGuiIn;

        init(backgroundColor);
    }

    private void init (Color backgroundColor) {
        tb = new ToolBar();
        backgroundColorPicker = makeColorPicker(backgroundColor,
                                                e -> changeBackgroundColor());
        penColorPicker = makeColorPicker(myPen.getColor(),
                                         e -> changePenColor());

        tb.getItems().addAll(makeMenuBar(),
                             new Label(myResources.getString("BackgroundColor")),
                             backgroundColorPicker,
                             new Label(myResources.getString("PenColor")), penColorPicker);
    }

    /**
     * Creates the top menu bar
     * 
     * @return
     */
    private Node makeMenuBar () {
        Menu fileMenu = new Menu(myResources.getString("File"));

        MenuItem fileOp1 = new MenuItem(
                                        myResources.getString("MakeNewWorkspace"));
        fileOp1.setOnAction(e -> myGui.createNewWorkspace());
        fileMenu.getItems().add(fileOp1);

        Menu optionsMenu = new Menu(myResources.getString("Options"));
        MenuItem htmlHelp = new MenuItem(myResources.getString("Help"));
        htmlHelp.setOnAction(e -> showHTMLHelp());
        optionsMenu.getItems().add(htmlHelp);

        Menu languageMenu = makeMenu("Languages", languages);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, optionsMenu, languageMenu);

        return menuBar;
    }

    /**
     * Makes a color picker with the given default color and handler
     * 
     * @param defaultColor
     * @param handler
     * @return
     */
    private ColorPicker makeColorPicker (Color defaultColor,
                                         EventHandler<ActionEvent> handler) {
        ColorPicker colorPicker = new ColorPicker(defaultColor);
        svg = new SVGPath();
        svg.setContent("M70,50 L90,50 L120,90 L150,50 L170,50"
                       + "L210,90 L180,120 L170,110 L170,200 L70,200 L70,110 L60,120 L30,90"
                       + "L70,50");
        svg.setStroke(Color.DARKGREY);
        svg.setStrokeWidth(2);
        svg.setEffect(new DropShadow());
        svg.setFill(colorPicker.getValue());

        colorPicker.setOnAction(handler);
        return colorPicker;
    }

    /**
     * Handler for the background color picker
     */
    private void changeBackgroundColor () {
        canvasPane
                .setBackground(new Background(new BackgroundFill(
                                                                 backgroundColorPicker.getValue(),
                                                                 null, null)));
    }

    /**
     * Handler for the pen color picker
     */
    private void changePenColor () {
        myPen.setColor(penColorPicker.getValue());
    }

    /**
     * Creates Menu - temporary method before inheritance hierarchy
     */
    private Menu makeMenu (String name, String[] list) {
        Menu menu = new Menu(myResources.getString(name));
        for (String string : list) {
            CheckMenuItem cmi = new CheckMenuItem(string);
            menu.getItems().add(cmi);

            cmi.selectedProperty()
                    .addListener(
                                 e -> checkLanguageMenuItems(string,
                                                             cmi.isSelected(), menu));
        }
        return menu;
    }

    /**
     * Checks a single menu item within a given menu
     * 
     * @param language
     * @param selected
     * @param menu
     */
    private void checkLanguageMenuItems (String language, boolean selected,
                                         Menu menu) {
        ioPane.getButton().setDisable(false);
        if (selected) {
            selectedLanguage = language;
            // myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
            // + selectedLanguage);
            System.out.println(language);
            toggleMenuItems(menu, language, true);
            ioPane.getInputField().setEditable(true);
        }
        else {
            ioPane.getButton().setDisable(true);
            toggleMenuItems(menu, language, false);
            ioPane.getInputField().setEditable(true);
        }
    }

    /**
     * Toggles a menuItem
     * 
     * @param menu
     * @param input
     * @param state
     */
    private void toggleMenuItems (Menu menu, String input, boolean state) {
        for (int i = 0; i < languages.length; i++) {
            MenuItem temp = menu.getItems().get(i);
            if (!temp.getText().equals(input)) {
                temp.setDisable(state);
            }
        }
    }

    /**
     * Shows the HTML Help window
     */
    private void showHTMLHelp () {
        HTMLHelpPage helpPage = new HTMLHelpPage(myResources, helpPageURL);
        helpPage.show();
    }

    public String getSelectedLanguage () {
        return selectedLanguage;
    }

    public Node getBaseNode () {
        return tb;
    }
}
