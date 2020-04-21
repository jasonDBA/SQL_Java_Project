package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MainController {

	@FXML Button _btnShip;
	@FXML TextArea _txtShow;
	@FXML private ComboBox<String> _cmbID;
	@FXML TextField _orderID;
	@FXML TextField _inventory;
	@FXML TextField _productID;
	@FXML TextField _customerID;
	@FXML Button _btnOrderAmt;
	@FXML Button _btnOrderNum;
	@FXML Button _btnInvent;


	@FXML private void initialize(){
		String[] IDList = {"Customer", "Category", "Product", "Orders", "Shipping"};//set combo box values
		ObservableList<String> IDOList = FXCollections.observableArrayList(IDList);
		// initialize the combo box
		_cmbID.setItems(IDOList);
	}
//on click event to show the result
	@FXML private void onClickedShow()
	{
		_txtShow.clear();//clear text area before showing result

		//loads the driver in memory
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD", "comp214_f18_004_5_61","password");//connection details
			String selected= _cmbID.getSelectionModel().getSelectedItem();
				Statement retrieveStatement = connection.createStatement();
				ResultSet resultSet = retrieveStatement.executeQuery("select * from "+selected);

				// fetch all rows from the table
				// get ResultSet's meta data

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();


				// using table data

				// display the names of the columns in the ResultSet
				for (int i = 1; i <= numberOfColumns; i++){

					_txtShow.appendText(String.format("%-10s\t",metaData.getColumnName(i)));

				}
				_txtShow.appendText("\n");

				// display query results
				while (resultSet.next())
				{
					for (int i = 1; i <= numberOfColumns; i++)
					{
						_txtShow.appendText(String.format("%-15s\t", resultSet.getObject(i)));
					}
					_txtShow.appendText("\n");
				}
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	@FXML private void onClickedOrderNumber()
	{
		_txtShow.clear();//clear text area before showing result

		//loads the driver in memory
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD", "COMP214_F18_004_5_56","password");//connection details
			String customerID = (_customerID.getText());
				Statement retrieveStatement = connection.createStatement();
				ResultSet resultSet = retrieveStatement.executeQuery("SELECT COUNT(*)\"Total Orders\"FROM orders where status = 'Y' and CUSTOMER_ID ="+customerID+" group by customer_id");


				// fetch all rows from the table
				// get ResultSet's meta data

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();


				// using table data

				// display the names of the columns in the ResultSet
				for (int i = 1; i <= numberOfColumns; i++){

					_txtShow.appendText(String.format("%-10s\t",metaData.getColumnName(i)));

				}
				_txtShow.appendText("\n");

				// display query results
				while (resultSet.next())
				{
					for (int i = 1; i <= numberOfColumns; i++)
					{
						_txtShow.appendText(String.format("%-15s\t", resultSet.getObject(i)));
					}
					_txtShow.appendText("\n");
				}
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	@FXML private void onClickedOrderAmount()
	{
		_txtShow.clear();//clear text area before showing result

		//loads the driver in memory
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD", "COMP214_F18_004_5_56","password");//connection details
			String orderID = (_orderID.getText());
				Statement retrieveStatement = connection.createStatement();
				ResultSet resultSet = retrieveStatement.executeQuery("SELECT SUM(order_amt_cal(60023,prices,quantity))\"Order amount\" from product JOIN orderdetail "
						+ "USING(product_id)GROUP BY order_id HAVING order_id="+orderID);



				// fetch all rows from the table
				// get ResultSet's meta data

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();


				// using table data

				// display the names of the columns in the ResultSet
				for (int i = 1; i <= numberOfColumns; i++){

					_txtShow.appendText(String.format("%-10s\t",metaData.getColumnName(i)));

				}
				_txtShow.appendText("\n");

				// display query results
				while (resultSet.next())
				{
					for (int i = 1; i <= numberOfColumns; i++)
					{
						_txtShow.appendText(String.format("%-15s\t", resultSet.getObject(i)));
					}
					_txtShow.appendText("\n");
				}
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML private void onClickedShipped()
	{
		_txtShow.clear();//clear text area before showing result

		//loads the driver in memory
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD", "COMP214_F18_004_5_56","password");//connection details
			String orderID = (_orderID.getText());
				Statement retrieveStatement = connection.createStatement();
				ResultSet resultSet = retrieveStatement.executeQuery("select status\"Shipping Status\" from orders where order_id="+orderID);



				// fetch all rows from the table
				// get ResultSet's meta data

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();


				// using table data

				// display the names of the columns in the ResultSet
				for (int i = 1; i <= numberOfColumns; i++){

					_txtShow.appendText(String.format("%-10s\t",metaData.getColumnName(i)));

				}
				_txtShow.appendText("\n");

				// display query results
				while (resultSet.next())
				{
					for (int i = 1; i <= numberOfColumns; i++)
					{
						_txtShow.appendText(String.format("%-15s\t", resultSet.getObject(i)));
					}
					_txtShow.appendText("\n");
				}
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML private void onClickedInventory()
	{
		_txtShow.clear();//clear text area before showing result

		//loads the driver in memory
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD", "COMP214_F18_004_5_56","password");//connection details
			String productID = (_productID.getText());
				Statement retrieveStatement = connection.createStatement();
				ResultSet resultSet = retrieveStatement.executeQuery("select inventory from product where product_id="+productID);



				// fetch all rows from the table
				// get ResultSet's meta data

				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();


				// using table data

				// display the names of the columns in the ResultSet
				for (int i = 1; i <= numberOfColumns; i++){

					_txtShow.appendText(String.format("%-10s\t",metaData.getColumnName(i)));

				}
				_txtShow.appendText("\n");

				// display query results
				while (resultSet.next())
				{
					for (int i = 1; i <= numberOfColumns; i++)
					{
						_txtShow.appendText(String.format("%-15s\t", resultSet.getObject(i)));
					}
					_txtShow.appendText("\n");
				}
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}





}
