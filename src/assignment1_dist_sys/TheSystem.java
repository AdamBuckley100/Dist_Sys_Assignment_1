package assignment1_dist_sys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Adam Buckley
 * @date 02/10/2017
 * Distributed Systems Assignment 1
 */

public class TheSystem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	Connection conn;
	Statement theStatement;
	ResultSet resultSet;

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "data_structures_db";

	/** The name of the table we are testing with */
	private final String tableName = "employee";

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException { // for initial use only! establishes new connection.
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}
	
//	public void reloadConnection() throws SQLException
//	{
//		Connection conn = null;
//		Properties connectionProps = new Properties();
//		connectionProps.put("user", this.userName);
//		connectionProps.put("password", this.password);
//
//		conn = DriverManager.getConnection("jdbc:mysql://"
//				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
//				connectionProps);
//	}

	/**
	 * Run a SQL command which does NOT return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) { stmt.close(); }
		}
	}

	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void runn() {

		// Connect to MySQL for first and only time needed
		//Connection conn = null;
		try {
			System.out.println("got here 1");
			conn = this.getConnection();
			System.out.println("Connected to database");

			theStatement = conn.createStatement(); // let this happen ONE time.

			resultSet = theStatement.executeQuery("select * from employee"); // ONE time.

		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		try {
			resultSet.absolute(1);
		} catch (SQLException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {

		TheSystem app = new TheSystem();
		app.runn(); // db set up (should only have to happen once).

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MyGui frame = new MyGui();
					app.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String sendBackSsn() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Ssn from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		String ssnInString = "";

		int theSsn = 0;

		if( resultSet.next() )
		{
			//id = resultSet.getString("id"); String.valueOf(i)
			theSsn = resultSet.getInt("Ssn");
		}

		ssnInString = String.valueOf(theSsn);

		System.out.println("Ssn is " + ssnInString);
		return ssnInString;
	}

	private String sendBackBdate() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Bdate from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		Date bDate = null;

		if( resultSet.next() )
		{
			bDate = resultSet.getDate("BDate");
		}

		//convert the Date to String
		String theDateInString = String.valueOf(bDate);

		//int rowImCurrentlyAt = resultSet.getRow();

		//Date bDate = resultSet.getDate(rowImCurrentlyAt);

		//System.out.println("B Date is " + bDate);

		return theDateInString;
	}

	private String sendBackName() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Name from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		String name = "";

		if( resultSet.next() )
		{
			name = resultSet.getString("name");
		}

		System.out.println("Name is " + name);
		return name;
	}

	private String sendBackAddress() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Address from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		String address = "";

		if( resultSet.next() )
		{
			address = resultSet.getString("address");
		}

		System.out.println("Address is " + address);
		return address;
	}

	private String sendBackSalary() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Salary from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		String theSalaryInString = "";

		if( resultSet.next() )
		{
			theSalaryInString = String.valueOf( resultSet.getBigDecimal("Salary") );
		}

		System.out.println("Salary is " + theSalaryInString);
		return theSalaryInString;
	}

	private String sendBackGender() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Gender from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		String genderInString = "";

		if( resultSet.next() )
		{
			genderInString = resultSet.getString("Gender"); //Gender is char not varchar
		}

		System.out.println("Gender is " + genderInString);

		return genderInString;
	}

	private String sendBackWorks_For() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Works_For from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		int works_ForInInt = 0;
		String works_ForInString = "";

		if( resultSet.next() )
		{
			works_ForInInt = resultSet.getInt("Works_For");
		}

		works_ForInString = String.valueOf( works_ForInInt );

		System.out.println("Works_For is " + works_ForInString);

		return works_ForInString;
	}

	private String sendBackManages() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Manages from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		int managesInInt = 0;
		String managesInString = "";

		if( resultSet.next() )
		{
			managesInInt = resultSet.getInt("Manages");
		}

		managesInString = String.valueOf( managesInInt );

		System.out.println("Manages is " + managesInString);

		return managesInString;
	}

	private String sendBackSupervises() throws SQLException
	{
		Connection conn = getConnection();

		System.out.println(conn); //you can take this out if you want

		Statement theStatement = conn.createStatement();

		ResultSet resultSet = theStatement.executeQuery("select Supervises from employee");
		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.

		int supervisesInInt = 0;
		String supervisesInString = "";

		if( resultSet.next() )
		{
			supervisesInInt = resultSet.getInt("Supervises");
		}

		supervisesInString = String.valueOf( supervisesInInt );

		System.out.println("Manages is " + supervisesInString);

		System.out.println("Supervises is " + supervisesInString);

		return supervisesInString;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public TheSystem() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblEmployeeDetails = new JLabel("Employee Details");
		GridBagConstraints gbc_lblEmployeeDetails = new GridBagConstraints();
		gbc_lblEmployeeDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployeeDetails.gridx = 4;
		gbc_lblEmployeeDetails.gridy = 0;
		contentPane.add(lblEmployeeDetails, gbc_lblEmployeeDetails);

		JLabel lblSsn = new JLabel("SSn (No Update/Change Allowed)");
		GridBagConstraints gbc_lblSsn = new GridBagConstraints();
		gbc_lblSsn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSsn.gridx = 2;
		gbc_lblSsn.gridy = 1;
		contentPane.add(lblSsn, gbc_lblSsn);

		textField = new JTextField();
		String theSsn = sendBackSsn();
		textField.setText(theSsn);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblDob = new JLabel("DOB (Input: YYYY-MM-DD)");
		GridBagConstraints gbc_lblDob = new GridBagConstraints();
		gbc_lblDob.insets = new Insets(0, 0, 5, 5);
		gbc_lblDob.gridx = 2;
		gbc_lblDob.gridy = 2;
		contentPane.add(lblDob, gbc_lblDob);

		textField_1 = new JTextField();
		String theBdate = sendBackBdate();
		textField_1.setText(theBdate);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 2;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// previous start
				try {

					boolean whatIsPrevious = true;

					if ( (whatIsPrevious = resultSet.previous() ) == false ) // if theres nothing previous
					{
						if (whatIsPrevious == false) // no prev call made
						{
							//dont allow it to go past at all
							// show the next on screen (next IS the first one)

							resultSet.next();
							
							String ssnVal = resultSet.getString("Ssn");
							String bDateVal = resultSet.getString("Bdate");
							String nameVal = resultSet.getString("Name");
							String addressVal = resultSet.getString("Address");
							String salaryVal = resultSet.getString("Salary");
							String genderVal = resultSet.getString("Gender");
							String works_ForVal = resultSet.getString("Works_For");
							String managesVal = resultSet.getString("Manages");
							String supervisesVal = resultSet.getString("Supervises");
							
							textField.setText(ssnVal);
							textField_1.setText(bDateVal);
							textField_2.setText(nameVal);
							textField_3.setText(addressVal);
							textField_4.setText(salaryVal);
							textField_5.setText(genderVal);
							textField_6.setText(works_ForVal);
							textField_7.setText(managesVal);
							textField_8.setText(supervisesVal);
						}
					}

					String ssnVal = resultSet.getString("Ssn");
					String bDateVal = resultSet.getString("Bdate");
					String nameVal = resultSet.getString("Name");
					String addressVal = resultSet.getString("Address");
					String salaryVal = resultSet.getString("Salary");
					String genderVal = resultSet.getString("Gender");
					String works_ForVal = resultSet.getString("Works_For");
					String managesVal = resultSet.getString("Manages");
					String supervisesVal = resultSet.getString("Supervises");

					textField.setText(ssnVal);
					textField_1.setText(bDateVal);
					textField_2.setText(nameVal);
					textField_3.setText(addressVal);
					textField_4.setText(salaryVal);
					textField_5.setText(genderVal);
					textField_6.setText(works_ForVal);
					textField_7.setText(managesVal);
					textField_8.setText(supervisesVal);

				} catch (SQLException e1) {
					System.out.println("error 4 OK?");
					e1.printStackTrace();
				}

				// previous end
			}
		});
		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevious.gridx = 5;
		gbc_btnPrevious.gridy = 2;
		contentPane.add(btnPrevious, gbc_btnPrevious);

		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 3;
		contentPane.add(lblName, gbc_lblName);

		textField_2 = new JTextField();
		String theName = sendBackName();
		textField_2.setText(theName);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 3;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JButton btnNext = new JButton("Next");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//here st

				try {
					boolean whatIsNext = true;

					if ( (whatIsNext = resultSet.next() ) == false ) // if theres nothing next
					{

						if (whatIsNext == false) // no next call made
						{
							//dont allow it to go past at all
							// show the previous on screen (previous IS the last one)

							resultSet.previous();

							String ssnVal = resultSet.getString("Ssn");
							String bDateVal = resultSet.getString("Bdate");
							String nameVal = resultSet.getString("Name");
							String addressVal = resultSet.getString("Address");
							String salaryVal = resultSet.getString("Salary");
							String genderVal = resultSet.getString("Gender");
							String works_ForVal = resultSet.getString("Works_For");
							String managesVal = resultSet.getString("Manages");
							String supervisesVal = resultSet.getString("Supervises");

							textField.setText(ssnVal);
							textField_1.setText(bDateVal);
							textField_2.setText(nameVal);
							textField_3.setText(addressVal);
							textField_4.setText(salaryVal);
							textField_5.setText(genderVal);
							textField_6.setText(works_ForVal);
							textField_7.setText(managesVal);
							textField_8.setText(supervisesVal);

						}
					}
					//end of new

					// resultSet.next (); <-- not needed the next was done in the above if statment.
					String ssnVal = resultSet.getString("Ssn");
					String bDateVal = resultSet.getString("Bdate");
					String nameVal = resultSet.getString("Name");
					String addressVal = resultSet.getString("Address");
					String salaryVal = resultSet.getString("Salary");
					String genderVal = resultSet.getString("Gender");
					String works_ForVal = resultSet.getString("Works_For");
					String managesVal = resultSet.getString("Manages");
					String supervisesVal = resultSet.getString("Supervises");

					textField.setText(ssnVal);
					textField_1.setText(bDateVal);
					textField_2.setText(nameVal);
					textField_3.setText(addressVal);
					textField_4.setText(salaryVal);
					textField_5.setText(genderVal);
					textField_6.setText(works_ForVal);
					textField_7.setText(managesVal);
					textField_8.setText(supervisesVal);

				} catch (SQLException e1) {
					System.out.println("error 4 OK?");
					e1.printStackTrace();
				}


				//here end

			}
		});
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 5);
		gbc_btnNext.gridx = 5;
		gbc_btnNext.gridy = 3;
		contentPane.add(btnNext, gbc_btnNext);

		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 2;
		gbc_lblAddress.gridy = 4;
		contentPane.add(lblAddress, gbc_lblAddress);

		textField_3 = new JTextField();
		String theAddress = sendBackAddress();
		textField_3.setText(theAddress);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 4;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblSalary = new JLabel("Salary");
		GridBagConstraints gbc_lblSalary = new GridBagConstraints();
		gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalary.gridx = 2;
		gbc_lblSalary.gridy = 5;
		contentPane.add(lblSalary, gbc_lblSalary);

		textField_4 = new JTextField();
		String theSalary = sendBackSalary();
		textField_4.setText(theSalary);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 4;
		gbc_textField_4.gridy = 5;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JButton btnClear = new JButton("Clear all (except Ssn)");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// clear all of the fields 

				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 5;
		gbc_btnClear.gridy = 5;
		contentPane.add(btnClear, gbc_btnClear);

		JLabel lblGender = new JLabel("Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 2;
		gbc_lblGender.gridy = 6;
		contentPane.add(lblGender, gbc_lblGender);

		textField_5 = new JTextField();
		String theGender = sendBackGender();
		textField_5.setText(theGender);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.anchor = GridBagConstraints.WEST;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 4;
		gbc_textField_5.gridy = 6;
		contentPane.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JButton btnClearAllincluding = new JButton("Clear all (incl. Ssn)");
		btnClearAllincluding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Start
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				
				// End
			}
		});
		GridBagConstraints gbc_btnClearAllincluding = new GridBagConstraints();
		gbc_btnClearAllincluding.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearAllincluding.gridx = 5;
		gbc_btnClearAllincluding.gridy = 6;
		contentPane.add(btnClearAllincluding, gbc_btnClearAllincluding);

		JLabel lblNoOfPeople = new JLabel("No. of people working for");
		GridBagConstraints gbc_lblNoOfPeople = new GridBagConstraints();
		gbc_lblNoOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfPeople.gridx = 2;
		gbc_lblNoOfPeople.gridy = 7;
		contentPane.add(lblNoOfPeople, gbc_lblNoOfPeople);

		textField_6 = new JTextField();
		String theWorks_For = sendBackWorks_For();
		textField_6.setText(theWorks_For);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.anchor = GridBagConstraints.WEST;
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.gridx = 4;
		gbc_textField_6.gridy = 7;
		contentPane.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		JLabel lblNoOfPeople_1 = new JLabel("No. of people managing");
		GridBagConstraints gbc_lblNoOfPeople_1 = new GridBagConstraints();
		gbc_lblNoOfPeople_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfPeople_1.gridx = 2;
		gbc_lblNoOfPeople_1.gridy = 8;
		contentPane.add(lblNoOfPeople_1, gbc_lblNoOfPeople_1);

		textField_7 = new JTextField();
		String theManages = sendBackManages();
		textField_7.setText(theManages);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.anchor = GridBagConstraints.WEST;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 4;
		gbc_textField_7.gridy = 8;
		contentPane.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JLabel lblNoOfPeople_2 = new JLabel("No. of people supervising");
		GridBagConstraints gbc_lblNoOfPeople_2 = new GridBagConstraints();
		gbc_lblNoOfPeople_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfPeople_2.gridx = 2;
		gbc_lblNoOfPeople_2.gridy = 9;
		contentPane.add(lblNoOfPeople_2, gbc_lblNoOfPeople_2);

		textField_8 = new JTextField();
		String theSupervises = sendBackSupervises();
		textField_8.setText(theSupervises);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.anchor = GridBagConstraints.WEST;
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.gridx = 4;
		gbc_textField_8.gridy = 9;
		contentPane.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);

		// The Add button
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				//start add
				String theSsn = textField.getText();
				String theBdate = textField_1.getText();
				String theName = textField_2.getText();
				String theAddress = textField_3.getText();
				String theSalary = textField_4.getText();
				String theGender = textField_5.getText();
				String theWorks_For = textField_6.getText();
				String theManages = textField_7.getText();
				String theSupervises = textField_8.getText();	

				int theSsnInInt = Integer.valueOf(theSsn);

				BigDecimal theSalaryInDecimal = new BigDecimal(theSalary.replaceAll(",", ""));

				int theWorks_ForInInt = Integer.parseInt(theWorks_For);
				int theManagesInInt = Integer.parseInt(theManages);
				int theSupervisesInInt = Integer.parseInt(theSupervises);

				try {
					String createString =
							"INSERT INTO " + "employee" +
									" VALUES ('" + theSsnInInt + "', '" + theBdate + "', '" + theName + "', '" + theAddress + "', '" + theSalaryInDecimal + "', '" + theGender + "', '" + theWorks_ForInInt + "', '" + theManagesInInt + "','" + theSupervisesInInt + "');";
					System.out.println("Inset String:" + createString);
					System.out.println("Conn of the new class: " + conn);
					executeUpdate(conn, createString);
					System.out.println("Updated the table of ID: employee");
				} catch (SQLException e) {
					System.out.println("ERROR: Could not update the table");
					e.printStackTrace();
					return;
				}
				
				// reload the conn so that the new addition can be iterated through in next and prev.
					runn();
				
				//end add
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 11;
		contentPane.add(btnAdd, gbc_btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//delete start

				String theSsn = textField.getText();

				try {
					String createString =
							"DELETE FROM " + "employee" +
									" WHERE Ssn='" + theSsn + "';";
					System.out.println("Delete string:" + createString);
					System.out.println("Conn of the new class: " + conn);
					executeUpdate(conn, createString);
					System.out.println("Updated the table of ID: employee");
				} catch (SQLException ee) {
					System.out.println("ERROR: Could not update the table");
					ee.printStackTrace();
					return;
				}
				
				runn();

				//delete end
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 11;
		contentPane.add(btnDelete, gbc_btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// update start

				String whatIsInTheSsnField = textField.getText();
				String theBdateChange = textField_1.getText();
				String theNameChange = textField_2.getText();
				String theAddressChange = textField_3.getText();
				String theSalaryChange = textField_4.getText();
				String theGenderChange = textField_5.getText();
				String theWorks_ForChange = textField_6.getText();
				String theManagesChange = textField_7.getText();
				String theSupervisesChange = textField_8.getText();		

				try {
					String createString =
							"UPDATE " + "employee" +
									" SET " + "Bdate = '" + theBdateChange + "', " + "Name = '" + theNameChange + "',Address = '" + theAddressChange + "',Salary = '" + theSalaryChange + "',Gender = '" + theGenderChange + "',Works_For = '" + theWorks_ForChange + "',Manages = '" + theManagesChange + "',Supervises = '" + theSupervisesChange + "'" +
									" WHERE Ssn = " + whatIsInTheSsnField + ";";
					System.out.println("Update String:" + createString);
					executeUpdate(conn, createString);
					System.out.println("Updated the table of ID: " + whatIsInTheSsnField);
					runn();
				} catch (SQLException ee) {
					System.out.println("ERROR: Could not update the table");
					ee.printStackTrace();
					return;
				}
				
			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.WEST;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 4;
		gbc_btnUpdate.gridy = 11;
		contentPane.add(btnUpdate, gbc_btnUpdate);
		
		JLabel lblWhenDeletingJust = new JLabel("When Deleting, you can input only the Ssn or all fields.");
		GridBagConstraints gbc_lblWhenDeletingJust = new GridBagConstraints();
		gbc_lblWhenDeletingJust.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhenDeletingJust.gridx = 2;
		gbc_lblWhenDeletingJust.gridy = 12;
		contentPane.add(lblWhenDeletingJust, gbc_lblWhenDeletingJust);
		
		JLabel lblNewLabel = new JLabel("Note: Ssn is not permitted to be changed.");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 13;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNoteIfA = new JLabel("If a button seems to not work, try pressing again.");
		GridBagConstraints gbc_lblNoteIfA = new GridBagConstraints();
		gbc_lblNoteIfA.anchor = GridBagConstraints.WEST;
		gbc_lblNoteIfA.insets = new Insets(0, 0, 0, 5);
		gbc_lblNoteIfA.gridx = 2;
		gbc_lblNoteIfA.gridy = 14;
		contentPane.add(lblNoteIfA, gbc_lblNoteIfA);
	}
}