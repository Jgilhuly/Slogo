package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class InfoElement {
	private VBox myBaseNode;
	private List<TableView<String>> myTables;

	public InfoElement() {
		init();
	}

	private void init() {
		myBaseNode = new VBox();
		myBaseNode.setSpacing(5);

		ArrayList<String> cols = new ArrayList<String>();

		cols.add("Commands");
		TableView<String> prevCommandsTable = makeTable("Previous Commands", cols);
		myBaseNode.getChildren().add(prevCommandsTable);

		cols.clear();
		cols.add("Names");
		cols.add("Values");
		TableView<String> variablesTable = makeTable("Variables", cols);
		myBaseNode.getChildren().add(variablesTable);

		cols.clear();
		cols.add("Commands");
		TableView<String> userCommandsTable = makeTable("User Commands", cols);
		myBaseNode.getChildren().add(userCommandsTable);

		
		myTables = new ArrayList<TableView<String>>();
		myTables.add(prevCommandsTable);
		myTables.add(variablesTable);
		myTables.add(userCommandsTable);
	}

	private TableView<String> makeTable(String title, List<String> columnNames) {
		TableView<String> table = new TableView<String>();

		for (String s : columnNames) {
			table.getColumns().add((new TableColumn<String, String>(s)));
		}

		return table;
	}

	public Node getBaseNode() {
		return myBaseNode;
	}

	public List<TableView<String>> getTables() {
		return myTables;
	}
}
