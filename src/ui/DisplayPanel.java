package ui;


import model.Variable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//will return one display panel
public class DisplayPanel {

	public Node makeTable(String title) {
		VBox labelAndTable = new VBox();
		labelAndTable.setSpacing(5);
		
		Label label = new Label(title);
		label.setFont(new Font("Arial", 14));
		
		TableView table = new TableView();
		ObservableList<Variable> variables = setBindList();
		table.setItems(variables);
	
		TableColumn<Variable,String> nameCol = new TableColumn<Variable,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<Variable,String> valueCol = new TableColumn<Variable,String>("Value");
		valueCol.setCellValueFactory(new PropertyValueFactory("value"));
		table.getColumns().setAll( nameCol, valueCol);
		
//		ArrayList<TableColumn> cols = new ArrayList<TableColumn>();
//		
//		for (String s : columnNames) {
//			cols.add(new TableColumn(s));
//		}
//		
//		table.getColumns().addAll(cols);
		
		labelAndTable.getChildren().addAll(label, table);
		return labelAndTable;
	}
	public ObservableList<Variable> setBindList() {
		ObservableList<Variable> var = FXCollections.observableArrayList();
		var.add(new Variable(":g", 6.0));
		return var;
	}
}
