package ui.elements;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import ui.GUI;
import ui_table.PreviousCommands;
import ui_table.TableElements;
import ui_table.Variable;


public class InfoElement {
    private VBox myBaseNode;
    private List<TableView<TableElements>> myTables;
    private ObservableList<TableElements> var = FXCollections
            .observableArrayList();
    private ObservableList<TableElements> commands = FXCollections
            .observableArrayList();
    private TableView<TableElements> prevCommandsTable;
    private TableView<TableElements> variablesTable;
    private GUI myGUI;

    public InfoElement (GUI gui) {
        init();
        myGUI = gui;
    }

    private void init () {
        myBaseNode = new VBox();
        myBaseNode.setSpacing(5);

        ArrayList<String> cols = new ArrayList<String>();

        cols.add("Commands");

        prevCommandsTable = makeTable(cols);
        prevCommandsTable.setItems(commands);
        myBaseNode.getChildren().add(prevCommandsTable);

        cols.clear();
        cols.add("Names");
        cols.add("Values");

        variablesTable = makeTable(cols);
        variablesTable.setItems(var);
        myBaseNode.getChildren().add(variablesTable);

        // TODO: implement userCommandsTable
        cols.clear();
        cols.add("Commands");
        TableView<TableElements> userCommandsTable = makeTable(cols);
        myBaseNode.getChildren().add(userCommandsTable);

        myTables = new ArrayList<TableView<TableElements>>();
        myTables.add(prevCommandsTable);
        myTables.add(variablesTable);
        myTables.add(userCommandsTable);
    }

    private <T> TableView<TableElements> makeTable (List<String> columnNames) {
        TableView<TableElements> table = new TableView<>();

        TableColumn<TableElements, String> string = new TableColumn<>(
                                                                      columnNames.get(0));
        table.getColumns().add(string);
        string.setCellValueFactory(cellData -> cellData.getValue()
                .nameProperty());
        table.setRowFactory(tv -> {
            TableRow<TableElements> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1 && (!row.isEmpty())) {
                    myGUI.setInputText(row.getItem().nameProperty().get());
                }
            });
            return row;
        });

        if (columnNames.size() > 1) {
            TableColumn<TableElements, String> value = new TableColumn<>(
                                                                         columnNames.get(1));
            table.getColumns().add(value);
            value.setCellValueFactory(cellData -> cellData.getValue()
                    .valueProperty());
        }

        return table;
    }

    public void setBindVariableList (String command, double value) {
        var.add(new Variable(command, value));
        update();
    }

    public void setBindCommandList (String command) {
        commands.add(new PreviousCommands(command));
        update();
    }

    private void update () {
        prevCommandsTable.setItems(commands);
    }

    public Node getBaseNode () {
        return myBaseNode;
    }

    public List<TableView<TableElements>> getTables () {
        return myTables;
    }
}
