package pcBuild;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.aspose.cells.DateTime;
import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.ImageType;
import com.aspose.cells.PageOrientationType;
import com.aspose.cells.SheetRender;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import java.awt.Choice;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JRadioButton;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Color;

public class NewPCBuild {

	private JFrame frame;
	private JTextField MobileNum;
	private JTextField RegMobNum;
	private JTextField RegUserName;
	JComboBox<String> comboBox = new JComboBox<String>();
	private JLabel ProName;
	private JLabel ProUrl;
	ArrayList<String> proId = new ArrayList<String>();
	ArrayList<String> proName = new ArrayList<String>();
	ArrayList<String> proUrl = new ArrayList<String>();
	ArrayList<String> proPrice = new ArrayList<String>();
	ArrayList<String> proImg = new ArrayList<String>();
	ArrayList<String> FilterproName = new ArrayList<String>();
	ArrayList<String> FilterproUrl = new ArrayList<String>();
	ArrayList<String> FilterproPrice = new ArrayList<String>();
	ArrayList<String> FilterproId = new ArrayList<String>();
	ArrayList<String> FilterproImg = new ArrayList<String>();
	ArrayList<String> ViewproName = new ArrayList<String>();
	ArrayList<String> ViewproId = new ArrayList<String>();
	ArrayList<String> ViewproPrice = new ArrayList<String>();
	ArrayList<String> ViewBuilds = new ArrayList<String>();
	ArrayList<String> ViewTimeStamp = new ArrayList<String>();
	ArrayList<String> ViewEstimate = new ArrayList<String>();
	String userName = null;
	String MobileNumber = "";
	ArrayList<String> ViewCabImg = new ArrayList<String>();
	ArrayList<String> ViewDisImg = new ArrayList<String>();
	ArrayList<String> ViewColImg = new ArrayList<String>();
	double estimate = 0;
	double dummyEstimate = 0;
	JPanel motherbrd = new JPanel();
	JLabel est = new JLabel("Rs:");
	JLabel est_mtb = new JLabel("Rs:");
	JComboBox<String> comboBox_mtb = new JComboBox<String>();
	JLabel WelcomeMessage;
	JProgressBar progressBar = new JProgressBar();
	ArrayList<String> products = new ArrayList<String>();
	private JTextField buildName;
	private JTextField viewMobile;
	private JTextField mailText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPCBuild window = new NewPCBuild();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewPCBuild() {
		initialize();
	}

	public void fetchDataFrom(String table, JComboBox comboBox) throws Exception {
		proName = new ArrayList<String>();
		proPrice = new ArrayList<String>();
		proUrl = new ArrayList<String>();
		proId = new ArrayList<String>();
		proImg = new ArrayList<String>();
		Class.forName("oracle.jdbc.OracleDriver");
		// Change Oracle DB Username And Password
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "password");
		Statement st = con.createStatement();
		String selection = "select * from pcbproducts where type='" + table + "'";
		System.out.println(selection);
		ResultSet data = st.executeQuery(selection);
		while (data.next()) {
			proName.add(data.getString("pname"));
			proUrl.add(data.getString("pdesc"));
			proPrice.add(data.getString("price"));
			proId.add(data.getString("prid"));
			proImg.add(data.getString("pimg"));
			comboBox.addItem(data.getString("pname"));
		}

		con.close();
	}

	public boolean addToDataBase(String UserName, String Mobile) throws Exception {
		int flag1 = 0;
		Class.forName("oracle.jdbc.OracleDriver");
		// Change Oracle DB Username And Password
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "password");
		Statement st = con.createStatement();
		MobileNumber = Mobile;
		ResultSet flag = st.executeQuery("select count(*) as res from pcbuserdetails where mobile = '" + Mobile + "'");
		while (flag.next()) {
			flag1 = flag.getInt("res");
		}
		if (flag1 == 0) {
			String addDetails = "insert into pcbuserdetails values('" + Mobile + "',' " + UserName + "')";
			System.out.println(addDetails);
			userName = UserName;
			String temp = WelcomeMessage.getText();
			temp = "<html>Hello " + UserName + ", <html>" + temp;
			WelcomeMessage.setText(temp);
			WelcomeMessage.repaint();
			WelcomeMessage.revalidate();
			st.executeUpdate(addDetails);
			System.out.println("Successfully Created Your Account\n\n");
			con.close();
			return true;
		}
		if (flag1 != 0) {
			return false;
		}
		return false;
	}

	public void filterIn(String filter, String Table, JComboBox Filters) throws Exception {
		FilterproId = new ArrayList<String>();
		FilterproName = new ArrayList<String>();
		FilterproPrice = new ArrayList<String>();
		FilterproUrl = new ArrayList<String>();
		FilterproImg = new ArrayList<String>();
		Filters.removeAll();
		Filters.repaint();
		Filters.revalidate();
		Class.forName("oracle.jdbc.OracleDriver");
		// Change Oracle DB Username And Password
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "password");
		Statement st = con.createStatement();
		String selection = "select * from pcbproducts where type='" + Table + "' and pname like '%"
				+ filter.toUpperCase() + "%'";
		System.out.println(selection);
		ResultSet data = st.executeQuery(selection);
		while (data.next()) {
			FilterproName.add(data.getString("pname"));
			FilterproUrl.add(data.getString("pdesc"));
			FilterproPrice.add(data.getString("price"));
			FilterproId.add(data.getString("prid"));
			FilterproImg.add(data.getString("pimg"));
			Filters.addItem(data.getString("pname"));
		}
		con.close();
	}

	public void highAndLow(String Table, JComboBox Filters, String order) throws Exception {
		FilterproId = new ArrayList<String>();
		FilterproName = new ArrayList<String>();
		FilterproPrice = new ArrayList<String>();
		FilterproUrl = new ArrayList<String>();
		FilterproImg = new ArrayList<String>();
		Filters.removeAll();
		Filters.repaint();
		Filters.revalidate();
		Class.forName("oracle.jdbc.OracleDriver");
		// Change Oracle DB Username And Password
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "password");
		Statement st = con.createStatement();
		String selection = "select * from pcbproducts where type='" + Table + "' order by price " + order + "";
		System.out.println(selection);
		ResultSet data = st.executeQuery(selection);
		while (data.next()) {
			FilterproName.add(data.getString("pname"));
			FilterproUrl.add(data.getString("pdesc"));
			FilterproPrice.add(data.getString("price"));
			FilterproId.add(data.getString("prid"));
			FilterproImg.add(data.getString("pimg"));
			Filters.addItem(data.getString("pname"));
		}
		con.close();
	}

	public String fetchFromDataBase(String Mobile) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		// Change Oracle DB Username And Password
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "password");
		Statement st = con.createStatement();
		String fetchDetails = "select name from pcbuserdetails where mobile='" + Mobile + "'";
		System.out.println(fetchDetails);
		ResultSet userDetails = st.executeQuery(fetchDetails);
		String UserName = null;
		String temp = WelcomeMessage.getText();
		while (userDetails.next()) {
			UserName = userDetails.getString("name");
		}
		MobileNumber = Mobile;
		con.close();
		return UserName;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("PC Build");
		frame.setBackground(Color.BLACK);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setIconImage(
				new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbicon.jpg").getImage());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);
		// frame.setVisible(true);

		frame.setBounds(100, 100, 1579, 882);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 46, 1511, 711);
		frame.getContentPane().add(tabbedPane);

		JPanel Welcome = new JPanel();
		Welcome.setToolTipText("Welcome");
		tabbedPane.addTab("Welcome", null, Welcome, null);
		Welcome.setLayout(null);
		JComboBox<String> ProcessorFilters = new JComboBox<String>();

		JLabel lblNewLabel = new JLabel("Welcome to PC Build");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel.setBounds(570, 90, 381, 46);
		Welcome.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Create New Build");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setEnabledAt(1, true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1.setBounds(616, 244, 306, 55);
		Welcome.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("View Or Edit Build");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(15);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1_1.setBounds(616, 349, 306, 55);
		Welcome.add(btnNewButton_1_1);

		JLabel pcbBg1 = new JLabel("               ");
		pcbBg1.setHorizontalAlignment(SwingConstants.CENTER);
		pcbBg1.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbBg1.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbBg1.setBounds(0, 0, 1555, 719);
		Welcome.add(pcbBg1);

		JPanel Build = new JPanel();
		tabbedPane.addTab("Build", null, Build, null);
		Build.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Enter User Details");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(562, 222, 395, 43);
		Build.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Mobile Number");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_3.setBounds(435, 323, 219, 53);
		Build.add(lblNewLabel_3);

		MobileNum = new JTextField();
		MobileNum.setFont(new Font("Tahoma", Font.PLAIN, 30));
		MobileNum.setBounds(804, 334, 184, 31);
		Build.add(MobileNum);
		JLabel invalidErr = new JLabel("Invalid User Details");
		invalidErr.setVisible(false);
		MobileNum.setColumns(10);

		JButton btnNewButton = new JButton("New user? Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setEnabledAt(1, false);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton.setBounds(619, 519, 271, 53);
		Build.add(btnNewButton);

		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = fetchFromDataBase(MobileNum.getText());
					if (user == null) {
						invalidErr.setVisible(true);
					}
					if (user != null) {
						invalidErr.setVisible(false);
						String temp = WelcomeMessage.getText();
						temp = "<html>Hello " + user + ", <html>" + temp;
						WelcomeMessage.setText(temp);
						WelcomeMessage.repaint();
						tabbedPane.setSelectedIndex(3);
						tabbedPane.setEnabledAt(3, true);
						tabbedPane.setEnabledAt(2, false);
						tabbedPane.setEnabledAt(1, false);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSubmit_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnSubmit_1.setBounds(680, 432, 145, 37);
		Build.add(btnSubmit_1);

		invalidErr.setForeground(Color.RED);
		invalidErr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		invalidErr.setBounds(804, 376, 184, 25);
		Build.add(invalidErr);

		JLabel pcbg2 = new JLabel("  ");
		pcbg2.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg2.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg2.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbg2.setBounds(0, 0, 1555, 719);
		Build.add(pcbg2);
		tabbedPane.setEnabledAt(1, false);

		JPanel Register = new JPanel();
		tabbedPane.addTab("Register User", null, Register, null);
		Register.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Register User");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(654, 179, 248, 37);
		Register.add(lblNewLabel_4);

		JLabel lblNewLabel_3_1 = new JLabel("Mobile Number");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_3_1.setBounds(476, 295, 219, 53);
		Register.add(lblNewLabel_3_1);

		RegMobNum = new JTextField();
		RegMobNum.setFont(new Font("Tahoma", Font.PLAIN, 30));
		RegMobNum.setColumns(10);
		RegMobNum.setBounds(845, 306, 184, 31);
		Register.add(RegMobNum);

		JLabel lblNewLabel_3_2 = new JLabel("Username");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_3_2.setBounds(476, 227, 219, 53);
		Register.add(lblNewLabel_3_2);

		RegUserName = new JTextField();
		RegUserName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		RegUserName.setColumns(10);
		RegUserName.setBounds(845, 238, 184, 31);
		Register.add(RegUserName);
		JLabel userExists = new JLabel("Already a user");
		userExists.setVisible(false);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean res = addToDataBase(RegUserName.getText(), RegMobNum.getText());
					if (res == true) {
						tabbedPane.setSelectedIndex(3);
						tabbedPane.setEnabledAt(3, true);
						tabbedPane.setEnabledAt(2, false);
						tabbedPane.setEnabledAt(1, false);
						tabbedPane.setEnabledAt(0, false);
					}
					if (res == false) {
						userExists.setVisible(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnSubmit.setBounds(705, 347, 145, 37);
		Register.add(btnSubmit);

		JButton btnNewButton_2 = new JButton("Already a user? Login");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				tabbedPane.setEnabledAt(2, false);
				tabbedPane.setEnabledAt(0, false);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_2.setBounds(642, 396, 271, 53);
		Register.add(btnNewButton_2);

		userExists.setForeground(Color.RED);
		userExists.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userExists.setBounds(1063, 306, 156, 31);
		Register.add(userExists);

		JLabel pcbg3 = new JLabel("  ");
		pcbg3.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg3.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg3.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbg3.setBounds(0, 0, 1555, 719);
		Register.add(pcbg3);
		JLabel lblNewLabel_2 = new JLabel("PC Build");
		lblNewLabel_2.setForeground(Color.WHITE);
		tabbedPane.setEnabledAt(2, false);

		JPanel Start = new JPanel();
		tabbedPane.addTab("Start", null, Start, null);
		Start.setLayout(null);

		WelcomeMessage = new JLabel(
				"<html>Welcome you to PC Build are you interested in Building your own PC here we provide the details of the components Start Building Your PC from the Begining<html>");
		WelcomeMessage.setForeground(Color.WHITE);
		WelcomeMessage.setVerticalAlignment(SwingConstants.TOP);
		WelcomeMessage.setHorizontalAlignment(SwingConstants.LEFT);
		WelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		WelcomeMessage.setBounds(179, 186, 1150, 146);
		Start.add(WelcomeMessage);

		JButton btnNewButton_3 = new JButton("Start");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
				tabbedPane.setEnabledAt(4, true);
				tabbedPane.setEnabledAt(3, false);
				tabbedPane.setEnabledAt(2, false);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setEnabledAt(0, false);
				try {
					fetchDataFrom("processor", comboBox);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton_3.setBounds(677, 374, 156, 37);
		Start.add(btnNewButton_3);

		JLabel pcbg4 = new JLabel("   ");
		pcbg4.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg4.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg4.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbg4.setBounds(0, 0, 1555, 719);
		Start.add(pcbg4);

		JPanel Processor = new JPanel();
		tabbedPane.addTab("Processor", null, Processor, null);
		Processor.setLayout(null);
		JLabel ProId = new JLabel("ID");
		ProId.setForeground(Color.GREEN);
		ProId.setBackground(Color.WHITE);

		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setForeground(Color.GREEN);
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6.setBounds(41, 327, 125, 46);
		Processor.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Details");
		lblNewLabel_6_1.setForeground(Color.GREEN);
		lblNewLabel_6_1.setBackground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1.setBounds(41, 384, 125, 46);
		Processor.add(lblNewLabel_6_1);

		JLabel lblNewLabel_6_2 = new JLabel("Price");
		lblNewLabel_6_2.setForeground(Color.GREEN);
		lblNewLabel_6_2.setBackground(Color.WHITE);
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2.setBounds(41, 441, 125, 46);
		Processor.add(lblNewLabel_6_2);

		ProName = new JLabel("Name");
		ProName.setForeground(Color.GREEN);
		ProName.setBackground(Color.WHITE);
		ProName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName.setBounds(218, 327, 1287, 46);
		Processor.add(ProName);

		JLabel ProUrl = new JLabel("https://----");
		ProUrl.setForeground(Color.GREEN);
		ProUrl.setBackground(Color.WHITE);
		ProUrl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl.getText()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		ProUrl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl.setBounds(218, 384, 1287, 46);
		Processor.add(ProUrl);

		JLabel ProPrice = new JLabel("Rs:");
		ProPrice.setForeground(Color.GREEN);
		ProPrice.setBackground(Color.WHITE);
		ProPrice.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice.setBounds(218, 441, 1287, 46);
		Processor.add(ProPrice);

		JButton btnNewButton_4 = new JButton("Next");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(10);
				estimate = dummyEstimate;
				tabbedPane.setSelectedIndex(5);
				tabbedPane.setEnabledAt(4, false);
				tabbedPane.setEnabledAt(5, true);
				products.add(ProId.getText());
				System.out.println(products);
				try {
					fetchDataFrom("motherboard", comboBox_mtb);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				est_mtb.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4.setBounds(1371, 640, 114, 37);
		Processor.add(btnNewButton_4);
		comboBox.setEditable(false);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setMaximumRowCount(200);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName.setText(proName.get(comboBox.getSelectedIndex()));
				ProUrl.setText(proUrl.get(comboBox.getSelectedIndex()));
				ProPrice.setText("Rs: " + proPrice.get(comboBox.getSelectedIndex()));
				ProId.setText(proId.get(comboBox.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox.getSelectedIndex())) + estimate;

				est.setText("Rs: " + dummyEstimate);

			}
		});
		comboBox.setBounds(39, 52, 1446, 46);
		Processor.add(comboBox);
		JRadioButton IntelBtn = new JRadioButton("Intel");
		IntelBtn.setOpaque(false);
		IntelBtn.setForeground(Color.GREEN);
		JRadioButton AmdBtn = new JRadioButton("AMD");
		AmdBtn.setOpaque(false);
		AmdBtn.setForeground(Color.GREEN);
		JLabel lblNewLabel_6_3 = new JLabel("Estimate");
		lblNewLabel_6_3.setForeground(Color.GREEN);
		lblNewLabel_6_3.setBackground(Color.WHITE);
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3.setBounds(41, 497, 125, 46);
		Processor.add(lblNewLabel_6_3);
		est.setForeground(Color.GREEN);
		est.setBackground(Color.WHITE);

		est.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est.setBounds(218, 497, 1287, 46);
		Processor.add(est);
		ProcessorFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					ProId.setText(FilterproId.get(ProcessorFilters.getSelectedIndex()));
					ProName.setText(FilterproName.get(ProcessorFilters.getSelectedIndex()));
					ProUrl.setText(FilterproUrl.get(ProcessorFilters.getSelectedIndex()));
					ProPrice.setText("Rs: " + FilterproPrice.get(ProcessorFilters.getSelectedIndex()));

					dummyEstimate = Double.parseDouble(FilterproPrice.get(ProcessorFilters.getSelectedIndex()))
							+ estimate;

					est.setText("Rs: " + dummyEstimate);
				} catch (Exception e) {
					System.out.println("Invalid");
				}
			}
		});

		ProcessorFilters.setMaximumRowCount(200);
		ProcessorFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ProcessorFilters.setEditable(false);
		ProcessorFilters.setBounds(39, 52, 1446, 46);
		Processor.add(ProcessorFilters);
		JComboBox<String> comboBox_sto = new JComboBox<String>();
		comboBox_sto.setAutoscrolls(true);
		JLabel ProId_mtb = new JLabel("ID");
		ProId_mtb.setForeground(Color.GREEN);

		JLabel est_sto = new JLabel("Rs:");
		est_sto.setForeground(Color.GREEN);

		JLabel lblNewLabel_6_5 = new JLabel("Product ID");
		lblNewLabel_6_5.setForeground(Color.GREEN);
		lblNewLabel_6_5.setBackground(Color.WHITE);
		lblNewLabel_6_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5.setBounds(41, 270, 142, 46);
		Processor.add(lblNewLabel_6_5);

		ProId.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId.setBounds(218, 270, 1287, 46);
		Processor.add(ProId);

		IntelBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					ProcessorFilters.removeAllItems();
					ProcessorFilters.setVisible(true);
					ProcessorFilters.repaint();
					ProcessorFilters.revalidate();
					filterIn("intel", "processor", ProcessorFilters);
					comboBox.setVisible(false);
					comboBox.revalidate();
					comboBox.repaint();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		IntelBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		IntelBtn.setBounds(522, 140, 114, 30);
		Processor.add(IntelBtn);

		AmdBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					ProcessorFilters.removeAllItems();
					ProcessorFilters.setVisible(true);
					ProcessorFilters.repaint();
					ProcessorFilters.revalidate();
					filterIn("amd", "processor", ProcessorFilters);
					comboBox.setVisible(false);
					comboBox.repaint();
					comboBox.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		AmdBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AmdBtn.setBounds(692, 140, 114, 30);
		Processor.add(AmdBtn);

		ButtonGroup proFilter = new ButtonGroup();
		proFilter.add(IntelBtn);
		proFilter.add(AmdBtn);

		JRadioButton proClears = new JRadioButton("Clear");
		proClears.setOpaque(false);
		proClears.setForeground(Color.GREEN);
		proFilter.add(proClears);
		proClears.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox.setVisible(true);
				comboBox.revalidate();
				comboBox.repaint();
				ProcessorFilters.removeAllItems();
				ProcessorFilters.setVisible(false);
				ProcessorFilters.repaint();
				ProcessorFilters.revalidate();
				try {
					fetchDataFrom("processor", comboBox);
				} catch (Exception e) {
					System.out.println("Invalid");
				}
			}
		});
		proClears.setFont(new Font("Tahoma", Font.PLAIN, 24));
		proClears.setBounds(881, 140, 114, 30);
		Processor.add(proClears);

		JRadioButton proL2H = new JRadioButton("Low To High");
		proL2H.setOpaque(false);
		proL2H.setForeground(Color.GREEN);
		proL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					ProcessorFilters.removeAllItems();
					ProcessorFilters.setVisible(true);
					ProcessorFilters.repaint();
					ProcessorFilters.revalidate();
					highAndLow("processor", ProcessorFilters, "asc");
					comboBox.setVisible(false);
					comboBox.repaint();
					comboBox.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		proL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		proL2H.setBounds(522, 188, 181, 30);
		Processor.add(proL2H);

		JRadioButton proH2L = new JRadioButton("High To Low");
		proH2L.setOpaque(false);
		proH2L.setForeground(Color.GREEN);
		proH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					ProcessorFilters.removeAllItems();
					ProcessorFilters.setVisible(true);
					ProcessorFilters.repaint();
					ProcessorFilters.revalidate();
					highAndLow("processor", ProcessorFilters, "desc");
					comboBox.setVisible(false);
					comboBox.repaint();
					comboBox.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		proFilter.add(proH2L);
		proFilter.add(proL2H);

		proH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		proH2L.setBounds(814, 188, 181, 30);
		Processor.add(proH2L);

		JLabel pcbgpro = new JLabel("   ");
		pcbgpro.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgpro.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgpro.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\processor.jpg"));
		pcbgpro.setBounds(0, 0, 1555, 719);
		Processor.add(pcbgpro);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(393, 10, 781, 32);
		frame.getContentPane().add(lblNewLabel_2);

		progressBar.setBounds(10, 769, 1511, 32);
		frame.getContentPane().add(progressBar);
		tabbedPane.setEnabledAt(3, false);
		tabbedPane.setEnabledAt(4, false);

		motherbrd.setLayout(null);
		tabbedPane.addTab("Mother Board", null, motherbrd, null);
		tabbedPane.setEnabledAt(5, false);

		JLabel lblNewLabel_6_4 = new JLabel("Name");
		lblNewLabel_6_4.setForeground(Color.GREEN);
		lblNewLabel_6_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4.setBounds(41, 318, 125, 46);
		motherbrd.add(lblNewLabel_6_4);

		JLabel lblNewLabel_6_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1.setForeground(Color.GREEN);
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1.setBounds(41, 375, 125, 46);
		motherbrd.add(lblNewLabel_6_1_1);

		JLabel lblNewLabel_6_2_1 = new JLabel("Price");
		lblNewLabel_6_2_1.setForeground(Color.GREEN);
		lblNewLabel_6_2_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1.setBounds(41, 432, 125, 46);
		motherbrd.add(lblNewLabel_6_2_1);

		JLabel ProName_mtb = new JLabel("Name");
		ProName_mtb.setForeground(Color.GREEN);
		ProName_mtb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_mtb.setBounds(198, 318, 1287, 46);
		motherbrd.add(ProName_mtb);

		JLabel ProUrl_mtb = new JLabel("https://----");
		ProUrl_mtb.setForeground(Color.GREEN);
		ProUrl_mtb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_mtb.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ProUrl_mtb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_mtb.setBounds(198, 375, 1287, 46);
		motherbrd.add(ProUrl_mtb);

		JLabel ProPrice_mtb = new JLabel("Rs:");
		ProPrice_mtb.setForeground(Color.GREEN);
		ProPrice_mtb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_mtb.setBounds(198, 432, 1287, 46);
		motherbrd.add(ProPrice_mtb);

		JButton btnNewButton_4_2 = new JButton("Next");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(20);
				estimate = dummyEstimate;
				products.add(ProId_mtb.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(6);
				tabbedPane.setEnabledAt(5, false);
				tabbedPane.setEnabledAt(6, true);
				try {
					fetchDataFrom("storage", comboBox_sto);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				est_sto.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2.setBounds(1371, 640, 114, 37);
		motherbrd.add(btnNewButton_4_2);

		comboBox_mtb.setMaximumRowCount(200);
		comboBox_mtb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_mtb.setBounds(41, 52, 1446, 46);
		motherbrd.add(comboBox_mtb);

		comboBox_mtb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_mtb.setText(proName.get(comboBox_mtb.getSelectedIndex()));
				ProUrl_mtb.setText(proUrl.get(comboBox_mtb.getSelectedIndex()));
				ProPrice_mtb.setText("Rs: " + proPrice.get(comboBox_mtb.getSelectedIndex()));
				ProId_mtb.setText(proId.get(comboBox_mtb.getSelectedIndex()));

				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_mtb.getSelectedIndex())) + estimate;

				est_mtb.setText("Rs: " + dummyEstimate);

			}
		});

		JLabel lblNewLabel_6_3_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1.setForeground(Color.GREEN);
		lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1.setBounds(41, 488, 125, 46);
		motherbrd.add(lblNewLabel_6_3_1);
		est_mtb.setForeground(Color.GREEN);

		est_mtb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_mtb.setBounds(198, 488, 1287, 46);
		motherbrd.add(est_mtb);

		JComboBox<String> MtbFilters = new JComboBox<String>();
		MtbFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProName_mtb.setText(FilterproName.get(MtbFilters.getSelectedIndex()));
				ProUrl_mtb.setText(FilterproUrl.get(MtbFilters.getSelectedIndex()));
				ProPrice_mtb.setText("Rs: " + FilterproPrice.get(MtbFilters.getSelectedIndex()));
				ProId_mtb.setText(FilterproId.get(MtbFilters.getSelectedIndex()));

				dummyEstimate = Double.parseDouble(FilterproPrice.get(MtbFilters.getSelectedIndex())) + estimate;

				est_mtb.setText("Rs: " + dummyEstimate);
			}
		});
		MtbFilters.setMaximumRowCount(200);
		MtbFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MtbFilters.setBounds(41, 52, 1446, 46);
		motherbrd.add(MtbFilters);
		JLabel ProId_sto = new JLabel("ID");
		ProId_sto.setForeground(Color.GREEN);

		ProId_mtb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_mtb.setBounds(218, 261, 1287, 46);
		motherbrd.add(ProId_mtb);

		JLabel lblNewLabel_6_5_1 = new JLabel("Product ID");
		lblNewLabel_6_5_1.setForeground(Color.GREEN);
		lblNewLabel_6_5_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_1.setBounds(41, 261, 142, 46);
		motherbrd.add(lblNewLabel_6_5_1);

		JRadioButton AsrockBtn = new JRadioButton("Asrock");
		AsrockBtn.setForeground(Color.GREEN);
		AsrockBtn.setOpaque(false);
		AsrockBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					MtbFilters.removeAllItems();
					MtbFilters.setVisible(true);
					MtbFilters.repaint();
					MtbFilters.revalidate();
					filterIn("asrock", "motherboard", MtbFilters);
					comboBox_mtb.setVisible(false);
					comboBox_mtb.repaint();
					comboBox_mtb.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		AsrockBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AsrockBtn.setBounds(660, 151, 103, 21);
		motherbrd.add(AsrockBtn);

		JRadioButton AsusBtn = new JRadioButton("Asus");
		AsusBtn.setForeground(Color.GREEN);
		AsusBtn.setOpaque(false);
		AsusBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					MtbFilters.removeAllItems();
					MtbFilters.setVisible(true);
					MtbFilters.repaint();
					MtbFilters.revalidate();
					filterIn("asus", "motherboard", MtbFilters);
					comboBox_mtb.setVisible(false);
					comboBox_mtb.repaint();
					comboBox_mtb.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		AsusBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AsusBtn.setBounds(483, 151, 103, 21);
		motherbrd.add(AsusBtn);

		JRadioButton mtbClear = new JRadioButton("Clear");
		mtbClear.setForeground(Color.GREEN);
		mtbClear.setOpaque(false);
		mtbClear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_mtb.setVisible(true);
				comboBox_mtb.revalidate();
				comboBox_mtb.repaint();
				MtbFilters.removeAllItems();
				MtbFilters.setVisible(false);
				MtbFilters.repaint();
				MtbFilters.revalidate();
				try {
					fetchDataFrom("motherboard", comboBox_mtb);
				} catch (Exception e) {
					System.out.println("Invalid");
				}
			}
		});
		mtbClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mtbClear.setBounds(841, 151, 103, 21);
		motherbrd.add(mtbClear);

		ButtonGroup mtbFilter = new ButtonGroup();
		mtbFilter.add(AsusBtn);
		mtbFilter.add(AsrockBtn);
		mtbFilter.add(mtbClear);

		JRadioButton mtbL2H = new JRadioButton("Low To High");
		mtbL2H.setForeground(Color.GREEN);
		mtbL2H.setOpaque(false);
		mtbL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					MtbFilters.removeAllItems();
					MtbFilters.setVisible(true);
					MtbFilters.repaint();
					MtbFilters.revalidate();
					highAndLow("motherboard", MtbFilters, "asc");
					comboBox_mtb.setVisible(false);
					comboBox_mtb.repaint();
					comboBox_mtb.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mtbL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mtbL2H.setBounds(483, 199, 181, 30);
		motherbrd.add(mtbL2H);

		JRadioButton mtbH2L = new JRadioButton("High To Low");
		mtbH2L.setForeground(Color.GREEN);
		mtbH2L.setOpaque(false);
		mtbH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					MtbFilters.removeAllItems();
					MtbFilters.setVisible(true);
					MtbFilters.repaint();
					MtbFilters.revalidate();
					highAndLow("motherboard", MtbFilters, "desc");
					comboBox_mtb.setVisible(false);
					comboBox_mtb.repaint();
					comboBox_mtb.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mtbH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mtbH2L.setBounds(763, 199, 181, 30);
		motherbrd.add(mtbH2L);
		mtbFilter.add(mtbH2L);
		mtbFilter.add(mtbL2H);

		JLabel pcbgmtb = new JLabel("  ");
		pcbgmtb.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgmtb.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgmtb.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\processor.jpg"));
		pcbgmtb.setBounds(0, 0, 1555, 719);
		motherbrd.add(pcbgmtb);

		JPanel storage = new JPanel();
		storage.setLayout(null);
		tabbedPane.addTab("Storage", null, storage, null);

		JComboBox<String> StoFilters = new JComboBox<String>();
		JLabel ProName_sto = new JLabel("Name");
		ProName_sto.setForeground(Color.GREEN);
		JLabel ProUrl_sto = new JLabel("https://----");
		ProUrl_sto.setForeground(Color.GREEN);
		ProUrl_sto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_sto.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProPrice_sto = new JLabel("Rs:");
		ProPrice_sto.setForeground(Color.GREEN);
		StoFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProName_sto.setText(FilterproName.get(StoFilters.getSelectedIndex()));
				ProUrl_sto.setText(FilterproUrl.get(StoFilters.getSelectedIndex()));
				ProPrice_sto.setText("Rs: " + FilterproPrice.get(StoFilters.getSelectedIndex()));
				ProId_sto.setText(FilterproId.get(StoFilters.getSelectedIndex()));

				dummyEstimate = Double.parseDouble(FilterproPrice.get(StoFilters.getSelectedIndex())) + estimate;

				est_sto.setText("Rs: " + dummyEstimate);

			}
		});

		JLabel lblNewLabel_6_4_1 = new JLabel("Name");
		lblNewLabel_6_4_1.setForeground(Color.GREEN);
		lblNewLabel_6_4_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1.setBounds(41, 318, 125, 46);
		storage.add(lblNewLabel_6_4_1);

		JLabel lblNewLabel_6_1_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1_1.setForeground(Color.GREEN);
		lblNewLabel_6_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1.setBounds(41, 375, 125, 46);
		storage.add(lblNewLabel_6_1_1_1);

		JLabel lblNewLabel_6_2_1_1 = new JLabel("Price");
		lblNewLabel_6_2_1_1.setForeground(Color.GREEN);
		lblNewLabel_6_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1.setBounds(41, 432, 125, 46);
		storage.add(lblNewLabel_6_2_1_1);

		ProName_sto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_sto.setBounds(198, 318, 1287, 46);
		storage.add(ProName_sto);

		ProUrl_sto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_sto.setBounds(198, 375, 1287, 46);
		storage.add(ProUrl_sto);

		ProPrice_sto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_sto.setBounds(198, 432, 1287, 46);
		storage.add(ProPrice_sto);
		JComboBox<String> comboBox_ram = new JComboBox<String>();
		JLabel est_ram = new JLabel("Rs:");
		est_ram.setForeground(Color.WHITE);

		JButton btnNewButton_4_2_1 = new JButton("Next");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(30);
				estimate = dummyEstimate;
				products.add(ProId_sto.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(7);
				tabbedPane.setEnabledAt(6, false);
				tabbedPane.setEnabledAt(7, true);
				try {
					fetchDataFrom("ram", comboBox_ram);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				est_ram.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1.setBounds(1371, 640, 114, 37);
		storage.add(btnNewButton_4_2_1);

		comboBox_sto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProName_sto.setText(proName.get(comboBox_sto.getSelectedIndex()));
				ProUrl_sto.setText(proUrl.get(comboBox_sto.getSelectedIndex()));
				ProPrice_sto.setText("Rs: " + proPrice.get(comboBox_sto.getSelectedIndex()));
				ProId_sto.setText(proId.get(comboBox_sto.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_sto.getSelectedIndex())) + estimate;

				est_sto.setText("Rs: " + dummyEstimate);

			}
		});
		comboBox_sto.setMaximumRowCount(200);
		comboBox_sto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_sto.setBounds(41, 52, 1446, 46);
		storage.add(comboBox_sto);

		JLabel lblNewLabel_6_3_1_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1.setForeground(Color.GREEN);
		lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1.setBounds(41, 488, 125, 46);
		storage.add(lblNewLabel_6_3_1_1);

		est_sto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_sto.setBounds(198, 488, 1287, 46);
		storage.add(est_sto);

		StoFilters.setMaximumRowCount(200);
		StoFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		StoFilters.setBounds(41, 52, 1446, 46);
		storage.add(StoFilters);

		ProId_sto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_sto.setBounds(218, 261, 1287, 46);
		storage.add(ProId_sto);

		JLabel lblNewLabel_6_5_2 = new JLabel("Product ID");
		lblNewLabel_6_5_2.setForeground(Color.GREEN);
		lblNewLabel_6_5_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2.setBounds(41, 261, 159, 46);
		storage.add(lblNewLabel_6_5_2);

		JRadioButton SsdBtn = new JRadioButton("SSD");
		SsdBtn.setBackground(Color.WHITE);
		SsdBtn.setForeground(Color.BLACK);
		SsdBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					filterIn("ssd", "storage", StoFilters);
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		SsdBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SsdBtn.setBounds(338, 178, 103, 21);
		storage.add(SsdBtn);

		JRadioButton Tb1Btn = new JRadioButton("1TB");
		Tb1Btn.setBackground(Color.WHITE);
		Tb1Btn.setForeground(Color.BLACK);
		Tb1Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					filterIn("1tb", "storage", StoFilters);
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Tb1Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Tb1Btn.setBounds(477, 178, 103, 21);
		storage.add(Tb1Btn);

		JRadioButton Tb2Btn = new JRadioButton("2TB");
		Tb2Btn.setBackground(Color.WHITE);
		Tb2Btn.setForeground(Color.BLACK);
		Tb2Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					filterIn("2tb", "storage", StoFilters);
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Tb2Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Tb2Btn.setBounds(592, 178, 103, 21);
		storage.add(Tb2Btn);

		JRadioButton Gb500Btn = new JRadioButton("500GB");
		Gb500Btn.setBackground(Color.WHITE);
		Gb500Btn.setForeground(Color.BLACK);
		Gb500Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					filterIn("500gb", "storage", StoFilters);
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Gb500Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Gb500Btn.setBounds(739, 178, 103, 21);
		storage.add(Gb500Btn);

		JRadioButton Gb200Btn = new JRadioButton("240GB");
		Gb200Btn.setBackground(Color.WHITE);
		Gb200Btn.setForeground(Color.BLACK);
		Gb200Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					filterIn("240gb", "storage", StoFilters);
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Gb200Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Gb200Btn.setBounds(890, 178, 103, 21);
		storage.add(Gb200Btn);

		JRadioButton stoClear = new JRadioButton("Clear");
		stoClear.setBackground(Color.WHITE);
		stoClear.setForeground(Color.BLACK);
		stoClear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_sto.setVisible(true);
				comboBox_sto.revalidate();
				comboBox_sto.repaint();
				StoFilters.removeAllItems();
				StoFilters.setVisible(false);
				StoFilters.repaint();
				StoFilters.revalidate();
				try {
					fetchDataFrom("storage", comboBox_sto);
				} catch (Exception e1) {
					System.out.println("Invalid");
				}
			}
		});
		ButtonGroup stoFilter = new ButtonGroup();
		stoFilter.add(SsdBtn);
		stoFilter.add(Tb1Btn);
		stoFilter.add(Tb2Btn);
		stoFilter.add(Gb500Btn);
		stoFilter.add(Gb200Btn);
		stoFilter.add(stoClear);

		stoClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		stoClear.setBounds(1036, 178, 103, 21);
		storage.add(stoClear);

		JRadioButton stoL2H = new JRadioButton("Low To High");
		stoL2H.setBackground(Color.WHITE);
		stoL2H.setForeground(Color.BLACK);
		stoL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					highAndLow("storage", StoFilters, "asc");
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		stoL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		stoL2H.setBounds(477, 215, 181, 30);
		storage.add(stoL2H);

		JRadioButton stoH2L = new JRadioButton("High To Low");
		stoH2L.setBackground(Color.WHITE);
		stoH2L.setForeground(Color.BLACK);
		stoH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					StoFilters.removeAllItems();
					StoFilters.setVisible(true);
					StoFilters.repaint();
					StoFilters.revalidate();
					highAndLow("storage", StoFilters, "desc");
					comboBox_sto.setVisible(false);
					comboBox_sto.repaint();
					comboBox_sto.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		stoH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		stoH2L.setBounds(739, 215, 181, 30);
		storage.add(stoH2L);
		stoFilter.add(stoL2H);
		stoFilter.add(stoH2L);

		JLabel pcbgsto = new JLabel("  ");
		pcbgsto.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgsto.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgsto.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\storage.jpg"));
		pcbgsto.setBounds(0, 0, 1555, 719);
		storage.add(pcbgsto);
		JPanel RAM = new JPanel();
		JLabel ProId_ram = new JLabel("ID");
		ProId_ram.setForeground(Color.WHITE);
		JLabel ProName_ram = new JLabel("Name");
		ProName_ram.setForeground(Color.WHITE);
		JLabel ProUrl_ram = new JLabel("https://----");
		ProUrl_ram.setForeground(Color.WHITE);
		ProUrl_ram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_ram.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProPrice_ram = new JLabel("Rs:");
		ProPrice_ram.setForeground(Color.WHITE);
		RAM.setLayout(null);
		tabbedPane.addTab("RAM", null, RAM, null);
		JComboBox<String> RamFilters = new JComboBox<String>();
		RamFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProName_ram.setText(FilterproName.get(RamFilters.getSelectedIndex()));
				ProUrl_ram.setText(FilterproUrl.get(RamFilters.getSelectedIndex()));
				ProPrice_ram.setText("Rs: " + FilterproPrice.get(RamFilters.getSelectedIndex()));
				ProId_ram.setText(FilterproId.get(RamFilters.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(FilterproPrice.get(RamFilters.getSelectedIndex())) + estimate;

				est_ram.setText("Rs: " + dummyEstimate);
			}
		});

		JLabel lblNewLabel_6_4_1_1 = new JLabel("Name");
		lblNewLabel_6_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1_1.setBounds(41, 318, 125, 46);
		RAM.add(lblNewLabel_6_4_1_1);

		JLabel lblNewLabel_6_1_1_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1_1.setBounds(41, 375, 125, 46);
		RAM.add(lblNewLabel_6_1_1_1_1);

		JLabel lblNewLabel_6_2_1_1_1 = new JLabel("Price");
		lblNewLabel_6_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1_1.setBounds(41, 432, 125, 46);
		RAM.add(lblNewLabel_6_2_1_1_1);

		ProName_ram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_ram.setBounds(198, 318, 1287, 46);
		RAM.add(ProName_ram);

		ProUrl_ram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_ram.setBounds(198, 375, 1287, 46);
		RAM.add(ProUrl_ram);

		JLabel ProUrl_cab = new JLabel("https://----");
		ProUrl_cab.setForeground(Color.WHITE);
		ProUrl_cab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_cab.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProName_cab = new JLabel("Name");
		ProName_cab.setForeground(Color.WHITE);
		JLabel est_cab = new JLabel("Rs:");
		est_cab.setForeground(Color.WHITE);
		JLabel ProPrice_cab = new JLabel("Rs:");
		ProPrice_cab.setForeground(Color.WHITE);
		JLabel ProId_cab = new JLabel("ID");
		ProId_cab.setForeground(Color.WHITE);
		JLabel imageDisplay = new JLabel("Loading");

		ProPrice_ram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_ram.setBounds(198, 432, 1287, 46);
		RAM.add(ProPrice_ram);
		JComboBox<String> comboBox_cab = new JComboBox<String>();
		comboBox_cab.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_cab.setText(proName.get(comboBox_cab.getSelectedIndex()));
				ProUrl_cab.setText(proUrl.get(comboBox_cab.getSelectedIndex()));
				ProPrice_cab.setText("Rs: " + proPrice.get(comboBox_cab.getSelectedIndex()));
				ProId_cab.setText(proId.get(comboBox_cab.getSelectedIndex()));
				URL url;
				try {
					url = new URL(proImg.get(comboBox_cab.getSelectedIndex()));
					BufferedImage image = ImageIO.read(url);
					imageDisplay.setIcon(new ImageIcon(
							new ImageIcon(image).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_cab.getSelectedIndex())) + estimate;

				est_cab.setText("Rs: " + dummyEstimate);
			}
		});

		JButton btnNewButton_4_2_1_1 = new JButton("Next");
		btnNewButton_4_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(40);
				estimate = dummyEstimate;
				products.add(ProId_ram.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(8);
				tabbedPane.setEnabledAt(7, false);
				tabbedPane.setEnabledAt(8, true);
				try {
					fetchDataFrom("cabinet", comboBox_cab);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				est_cab.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1_1.setBounds(1371, 640, 114, 37);
		RAM.add(btnNewButton_4_2_1_1);

		comboBox_ram.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProName_ram.setText(proName.get(comboBox_ram.getSelectedIndex()));
				ProUrl_ram.setText(proUrl.get(comboBox_ram.getSelectedIndex()));
				ProPrice_ram.setText("Rs: " + proPrice.get(comboBox_ram.getSelectedIndex()));
				ProId_ram.setText(proId.get(comboBox_ram.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_ram.getSelectedIndex())) + estimate;

				est_ram.setText("Rs: " + dummyEstimate);
			}
		});
		comboBox_ram.setMaximumRowCount(200);
		comboBox_ram.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_ram.setAutoscrolls(true);
		comboBox_ram.setBounds(41, 52, 1446, 46);
		RAM.add(comboBox_ram);

		JLabel lblNewLabel_6_3_1_1_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1_1.setBounds(41, 488, 125, 46);
		RAM.add(lblNewLabel_6_3_1_1_1);

		est_ram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_ram.setBounds(198, 488, 1287, 46);
		RAM.add(est_ram);

		RamFilters.setMaximumRowCount(200);
		RamFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		RamFilters.setBounds(41, 52, 1446, 46);
		RAM.add(RamFilters);

		ProId_ram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_ram.setBounds(218, 261, 1287, 46);
		RAM.add(ProId_ram);

		JLabel lblNewLabel_6_5_2_1 = new JLabel("Product ID");
		lblNewLabel_6_5_2_1.setForeground(Color.WHITE);
		lblNewLabel_6_5_2_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2_1.setBounds(41, 261, 156, 46);
		RAM.add(lblNewLabel_6_5_2_1);

		JRadioButton Gb8Btn = new JRadioButton("8GB");
		Gb8Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					RamFilters.removeAllItems();
					RamFilters.setVisible(true);
					RamFilters.repaint();
					RamFilters.revalidate();
					filterIn("8GB", "ram", RamFilters);
					comboBox_ram.setVisible(false);
					comboBox_ram.repaint();
					comboBox_ram.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Gb8Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Gb8Btn.setBounds(335, 157, 103, 21);
		RAM.add(Gb8Btn);

		JRadioButton Gb16Btn = new JRadioButton("16GB");
		Gb16Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					RamFilters.removeAllItems();
					RamFilters.setVisible(true);
					RamFilters.repaint();
					RamFilters.revalidate();
					filterIn("16gb", "ram", RamFilters);
					comboBox_ram.setVisible(false);
					comboBox_ram.repaint();
					comboBox_ram.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		Gb16Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Gb16Btn.setBounds(472, 157, 103, 21);
		RAM.add(Gb16Btn);

		JRadioButton Gb32Btn = new JRadioButton("32GB");
		Gb32Btn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					RamFilters.removeAllItems();
					RamFilters.setVisible(true);
					RamFilters.repaint();
					RamFilters.revalidate();
					filterIn("32GB", "ram", RamFilters);
					comboBox_ram.setVisible(false);
					comboBox_ram.repaint();
					comboBox_ram.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Gb32Btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Gb32Btn.setBounds(596, 157, 103, 21);
		RAM.add(Gb32Btn);

		JRadioButton Gb64BtnR = new JRadioButton("64GB");
		Gb64BtnR.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					RamFilters.removeAllItems();
					RamFilters.setVisible(true);
					RamFilters.repaint();
					RamFilters.revalidate();
					filterIn("64gb", "ram", RamFilters);
					comboBox_ram.setVisible(false);
					comboBox_ram.repaint();
					comboBox_ram.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Gb64BtnR.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Gb64BtnR.setBounds(740, 157, 103, 21);
		RAM.add(Gb64BtnR);

		JRadioButton ramClear = new JRadioButton("Clear");
		ramClear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_ram.setVisible(true);
				comboBox_ram.revalidate();
				comboBox_ram.repaint();
				RamFilters.removeAllItems();
				RamFilters.setVisible(false);
				RamFilters.repaint();
				RamFilters.revalidate();
				try {
					fetchDataFrom("ram", comboBox_ram);
				} catch (Exception e1) {
					System.out.println("Invalid");
				}
			}
		});
		ramClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ramClear.setBounds(884, 157, 103, 21);
		RAM.add(ramClear);
		ButtonGroup ramFilter = new ButtonGroup();
		ramFilter.add(ramClear);
		ramFilter.add(Gb8Btn);
		ramFilter.add(Gb16Btn);
		ramFilter.add(Gb32Btn);
		ramFilter.add(Gb64BtnR);

		JRadioButton ramL2H = new JRadioButton("Low To High");
		ramL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					RamFilters.removeAllItems();
					RamFilters.setVisible(true);
					RamFilters.repaint();
					RamFilters.revalidate();
					highAndLow("ram", RamFilters, "asc");
					comboBox.setVisible(false);
					comboBox.repaint();
					comboBox.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ramL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ramL2H.setBounds(472, 199, 181, 30);
		RAM.add(ramL2H);

		JRadioButton ramH2L = new JRadioButton("High To Low");
		ramH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					RamFilters.removeAllItems();
					RamFilters.setVisible(true);
					RamFilters.repaint();
					RamFilters.revalidate();
					highAndLow("ram", RamFilters, "desc");
					comboBox_ram.setVisible(false);
					comboBox_ram.repaint();
					comboBox_ram.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		ramH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ramH2L.setBounds(740, 199, 181, 30);
		RAM.add(ramH2L);
		ramFilter.add(ramH2L);
		ramFilter.add(ramL2H);

		JLabel pcbgram = new JLabel("  ");
		pcbgram.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgram.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgram.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\ram.jpg"));
		pcbgram.setBounds(0, 0, 1555, 719);
		RAM.add(pcbgram);
		JPanel Cabinet = new JPanel();
		Cabinet.setLayout(null);
		tabbedPane.addTab("Cabinet", null, Cabinet, null);

		JLabel lblNewLabel_6_4_1_1_1 = new JLabel("Name");
		lblNewLabel_6_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1_1_1.setBounds(41, 318, 125, 46);
		lblNewLabel_6_4_1_1_1.setForeground(Color.WHITE);
		Cabinet.add(lblNewLabel_6_4_1_1_1);

		JLabel lblNewLabel_6_1_1_1_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1_1_1.setBounds(41, 375, 125, 46);
		lblNewLabel_6_1_1_1_1_1.setForeground(Color.WHITE);
		Cabinet.add(lblNewLabel_6_1_1_1_1_1);

		JLabel lblNewLabel_6_2_1_1_1_1 = new JLabel("Price");
		lblNewLabel_6_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1_1_1.setBounds(41, 432, 125, 46);
		lblNewLabel_6_2_1_1_1_1.setForeground(Color.WHITE);
		Cabinet.add(lblNewLabel_6_2_1_1_1_1);

		ProName_cab.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_cab.setBounds(198, 318, 1287, 46);
		Cabinet.add(ProName_cab);

		ProUrl_cab.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_cab.setBounds(198, 375, 1287, 46);
		Cabinet.add(ProUrl_cab);

		ProPrice_cab.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_cab.setBounds(198, 432, 1287, 46);
		Cabinet.add(ProPrice_cab);
		JLabel est_col = new JLabel("Rs:");
		est_col.setForeground(Color.WHITE);
		JLabel ProName_col = new JLabel("Name");
		ProName_col.setForeground(Color.WHITE);
		JLabel ProId_col = new JLabel("ID");
		ProId_col.setForeground(Color.WHITE);
		JLabel ProUrl_col = new JLabel("https://----");
		ProUrl_col.setForeground(Color.WHITE);
		ProUrl_col.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_col.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProPrice_col = new JLabel("Rs:");
		ProPrice_col.setForeground(Color.WHITE);
		JLabel lblNewLabel_6_3_1_1_1_1_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1_1_1_1.setForeground(Color.WHITE);
		JLabel imageDisplay_col = new JLabel("Loading");
		JLabel lblNewLabel_6_5_2_1_1_1 = new JLabel("Product ID");
		lblNewLabel_6_5_2_1_1_1.setForeground(Color.WHITE);
		ProPrice_col.setForeground(Color.WHITE);
		lblNewLabel_6_3_1_1_1_1_1.setForeground(Color.WHITE);

		JComboBox<String> comboBox_col = new JComboBox<String>();
		comboBox_col.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_col.setText(proName.get(comboBox_col.getSelectedIndex()));
				ProUrl_col.setText(proUrl.get(comboBox_col.getSelectedIndex()));
				ProPrice_col.setText("Rs: " + proPrice.get(comboBox_col.getSelectedIndex()));
				ProId_col.setText(proId.get(comboBox_col.getSelectedIndex()));
				URL url;
				try {
					url = new URL(proImg.get(comboBox_col.getSelectedIndex()));
					BufferedImage image = ImageIO.read(url);
					imageDisplay_col.setIcon(new ImageIcon(
							new ImageIcon(image).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_col.getSelectedIndex())) + estimate;

				est_col.setText("Rs: " + dummyEstimate);
			}
		});

		JButton btnNewButton_4_2_1_1_1 = new JButton("Next");
		btnNewButton_4_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(50);
				estimate = dummyEstimate;
				products.add(ProId_cab.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(9);
				tabbedPane.setEnabledAt(8, false);
				tabbedPane.setEnabledAt(9, true);
				try {
					fetchDataFrom("cooler", comboBox_col);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				est_col.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1_1_1.setBounds(1371, 640, 114, 37);
		Cabinet.add(btnNewButton_4_2_1_1_1);

		comboBox_cab.setMaximumRowCount(200);
		comboBox_cab.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_cab.setAutoscrolls(true);
		comboBox_cab.setBounds(41, 52, 1446, 46);
		Cabinet.add(comboBox_cab);

		JLabel lblNewLabel_6_3_1_1_1_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1_1_1.setBounds(41, 488, 125, 46);
		lblNewLabel_6_3_1_1_1_1.setForeground(Color.WHITE);
		Cabinet.add(lblNewLabel_6_3_1_1_1_1);

		est_cab.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_cab.setBounds(198, 488, 1287, 46);
		Cabinet.add(est_cab);

		ProId_cab.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_cab.setBounds(218, 261, 1287, 46);
		Cabinet.add(ProId_cab);

		JLabel lblNewLabel_6_5_2_1_1 = new JLabel("Product ID");
		lblNewLabel_6_5_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2_1_1.setBounds(41, 261, 167, 46);
		lblNewLabel_6_5_2_1_1.setForeground(Color.WHITE);
		Cabinet.add(lblNewLabel_6_5_2_1_1);

		imageDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		imageDisplay.setBounds(1285, 147, 200, 200);
		Cabinet.add(imageDisplay);

		JLabel pcbgcab = new JLabel("  ");
		pcbgcab.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgcab.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgcab.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\cabinet.jpg"));
		pcbgcab.setBounds(0, 0, 1555, 719);
		Cabinet.add(pcbgcab);

		JPanel Cooler = new JPanel();
		Cooler.setLayout(null);
		tabbedPane.addTab("Cooler", null, Cooler, null);

		JLabel lblNewLabel_6_4_1_1_1_1 = new JLabel("Name");
		lblNewLabel_6_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1_1_1_1.setBounds(41, 318, 125, 46);
		lblNewLabel_6_4_1_1_1_1.setForeground(Color.WHITE);
		Cooler.add(lblNewLabel_6_4_1_1_1_1);

		JLabel lblNewLabel_6_1_1_1_1_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1_1_1_1.setBounds(41, 375, 125, 46);
		lblNewLabel_6_1_1_1_1_1_1.setForeground(Color.WHITE);
		Cooler.add(lblNewLabel_6_1_1_1_1_1_1);

		JLabel lblNewLabel_6_2_1_1_1_1_1 = new JLabel("Price");
		lblNewLabel_6_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1_1_1_1.setBounds(41, 432, 125, 46);
		lblNewLabel_6_2_1_1_1_1_1.setForeground(Color.WHITE);
		Cooler.add(lblNewLabel_6_2_1_1_1_1_1);

		ProName_col.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_col.setBounds(198, 318, 1287, 46);
		Cooler.add(ProName_col);

		ProUrl_col.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_col.setBounds(198, 375, 1287, 46);
		Cooler.add(ProUrl_col);

		ProPrice_col.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_col.setBounds(198, 432, 1287, 46);
		Cooler.add(ProPrice_col);
		JLabel est_gpu = new JLabel("Rs:");
		est_gpu.setForeground(Color.WHITE);
		JLabel lblNewLabel_6_2_1_1_1_1_1_1 = new JLabel("Price");
		lblNewLabel_6_2_1_1_1_1_1_1.setForeground(Color.WHITE);
		JLabel ProName_gpu = new JLabel("Name");
		ProName_gpu.setForeground(Color.WHITE);
		JLabel ProUrl_gpu = new JLabel("https://----");
		ProUrl_gpu.setForeground(Color.WHITE);
		ProUrl_gpu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_gpu.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProPrice_gpu = new JLabel("Rs:");
		ProPrice_gpu.setForeground(Color.WHITE);
		JLabel ProId_gpu = new JLabel("ID");
		ProId_gpu.setForeground(Color.WHITE);
		JComboBox<String> comboBox_gpu = new JComboBox<String>();
		comboBox_gpu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_gpu.setText(proName.get(comboBox_gpu.getSelectedIndex()));
				ProUrl_gpu.setText(proUrl.get(comboBox_gpu.getSelectedIndex()));
				ProPrice_gpu.setText("Rs: " + proPrice.get(comboBox_gpu.getSelectedIndex()));
				ProId_gpu.setText(proId.get(comboBox_gpu.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_gpu.getSelectedIndex())) + estimate;

				est_gpu.setText("Rs: " + dummyEstimate);
			}
		});

		JButton btnNewButton_4_2_1_1_1_1 = new JButton("Next");
		btnNewButton_4_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(60);
				estimate = dummyEstimate;
				products.add(ProId_col.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(10);
				tabbedPane.setEnabledAt(9, false);
				tabbedPane.setEnabledAt(10, true);
				try {
					fetchDataFrom("gpu", comboBox_gpu);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				est_gpu.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1_1_1_1.setBounds(1371, 640, 114, 37);
		Cooler.add(btnNewButton_4_2_1_1_1_1);

		comboBox_col.setMaximumRowCount(200);
		comboBox_col.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_col.setAutoscrolls(true);
		comboBox_col.setBounds(41, 52, 1446, 46);
		Cooler.add(comboBox_col);

		lblNewLabel_6_3_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1_1_1_1.setBounds(41, 488, 125, 46);
		Cooler.add(lblNewLabel_6_3_1_1_1_1_1);

		est_col.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_col.setBounds(198, 488, 1287, 46);
		Cooler.add(est_col);

		ProId_col.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_col.setBounds(218, 261, 1287, 46);
		Cooler.add(ProId_col);

		lblNewLabel_6_5_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2_1_1_1.setBounds(41, 261, 167, 46);
		Cooler.add(lblNewLabel_6_5_2_1_1_1);

		imageDisplay_col.setHorizontalAlignment(SwingConstants.CENTER);
		imageDisplay_col.setBounds(1285, 147, 200, 200);
		Cooler.add(imageDisplay_col);

		JLabel pcbgcol = new JLabel("  ");
		pcbgcol.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgcol.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgcol.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\cabinet.jpg"));
		pcbgcol.setBounds(0, 0, 1555, 719);
		Cooler.add(pcbgcol);

		JPanel GPU = new JPanel();
		GPU.setLayout(null);
		tabbedPane.addTab("GPU", null, GPU, null);

		JLabel lblNewLabel_6_4_1_1_1_1_1 = new JLabel("Name");
		lblNewLabel_6_4_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_4_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1_1_1_1_1.setBounds(41, 318, 125, 46);
		GPU.add(lblNewLabel_6_4_1_1_1_1_1);

		JLabel lblNewLabel_6_1_1_1_1_1_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1_1_1_1_1.setBounds(41, 375, 125, 46);
		GPU.add(lblNewLabel_6_1_1_1_1_1_1_1);

		lblNewLabel_6_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1_1_1_1_1.setBounds(41, 432, 125, 46);
		GPU.add(lblNewLabel_6_2_1_1_1_1_1_1);

		ProName_gpu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_gpu.setBounds(198, 318, 1287, 46);
		GPU.add(ProName_gpu);

		ProUrl_gpu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_gpu.setBounds(198, 375, 1287, 46);
		GPU.add(ProUrl_gpu);

		ProPrice_gpu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_gpu.setBounds(198, 432, 1287, 46);
		GPU.add(ProPrice_gpu);
		JLabel ProName_pws = new JLabel("Name");
		ProName_pws.setForeground(Color.WHITE);
		JLabel ProUrl_pws = new JLabel("https://----");
		ProUrl_pws.setForeground(Color.WHITE);
		ProUrl_pws.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_pws.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProPrice_pws = new JLabel("Rs:");
		ProPrice_pws.setForeground(Color.WHITE);
		JLabel ProId_pws = new JLabel("ID");
		ProId_pws.setForeground(Color.WHITE);
		JLabel est_pws = new JLabel("Rs:");
		est_pws.setForeground(Color.WHITE);

		JComboBox<String> comboBox_pws = new JComboBox<String>();
		comboBox_pws.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_pws.setText(proName.get(comboBox_pws.getSelectedIndex()));
				ProUrl_pws.setText(proUrl.get(comboBox_pws.getSelectedIndex()));
				ProPrice_pws.setText("Rs: " + proPrice.get(comboBox_pws.getSelectedIndex()));
				ProId_pws.setText(proId.get(comboBox_pws.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_pws.getSelectedIndex())) + estimate;

				est_pws.setText("Rs: " + dummyEstimate);
			}
		});

		JButton btnNewButton_4_2_1_1_1_1_1 = new JButton("Next");
		btnNewButton_4_2_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(70);
				estimate = dummyEstimate;
				products.add(ProId_gpu.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(11);
				tabbedPane.setEnabledAt(10, false);
				tabbedPane.setEnabledAt(11, true);
				try {
					fetchDataFrom("PowerSupply", comboBox_pws);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				est_gpu.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1_1_1_1_1.setBounds(1371, 640, 114, 37);
		GPU.add(btnNewButton_4_2_1_1_1_1_1);

		comboBox_gpu.setMaximumRowCount(200);
		comboBox_gpu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_gpu.setAutoscrolls(true);
		comboBox_gpu.setBounds(41, 52, 1446, 46);
		GPU.add(comboBox_gpu);

		JLabel lblNewLabel_6_3_1_1_1_1_1_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_3_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1_1_1_1_1.setBounds(41, 488, 125, 46);
		GPU.add(lblNewLabel_6_3_1_1_1_1_1_1);

		est_gpu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_gpu.setBounds(198, 488, 1287, 46);
		GPU.add(est_gpu);

		ProId_gpu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_gpu.setBounds(218, 261, 1287, 46);
		GPU.add(ProId_gpu);

		JLabel lblNewLabel_6_5_2_1_1_1_1 = new JLabel("Product ID");
		lblNewLabel_6_5_2_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_5_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2_1_1_1_1.setBounds(41, 261, 167, 46);
		GPU.add(lblNewLabel_6_5_2_1_1_1_1);
		JComboBox<String> GpuFilters = new JComboBox<String>();

		JRadioButton gpuClear = new JRadioButton("CLEAR");
		gpuClear.setForeground(Color.WHITE);
		gpuClear.setOpaque(false);
		gpuClear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_gpu.setVisible(true);
				comboBox_gpu.revalidate();
				comboBox_gpu.repaint();
				GpuFilters.removeAllItems();
				GpuFilters.setVisible(false);
				GpuFilters.repaint();
				GpuFilters.revalidate();
				try {
					fetchDataFrom("gpu", comboBox_gpu);
				} catch (Exception e1) {
					System.out.println("Invalid");
				}
			}
		});
		GpuFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_gpu.setText(FilterproName.get(GpuFilters.getSelectedIndex()));
				ProUrl_gpu.setText(FilterproUrl.get(GpuFilters.getSelectedIndex()));
				ProPrice_gpu.setText("Rs: " + FilterproPrice.get(GpuFilters.getSelectedIndex()));
				ProId_gpu.setText(FilterproId.get(GpuFilters.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(FilterproPrice.get(GpuFilters.getSelectedIndex())) + estimate;
				est_gpu.setText("Rs: " + dummyEstimate);
			}
		});
		GpuFilters.setMaximumRowCount(200);
		GpuFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GpuFilters.setAutoscrolls(true);
		GpuFilters.setBounds(41, 52, 1446, 46);
		GPU.add(GpuFilters);

		JRadioButton GigabyteBtn = new JRadioButton("GIGABYTE");
		GigabyteBtn.setForeground(Color.WHITE);
		GigabyteBtn.setOpaque(false);
		GigabyteBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					filterIn("gigabyte", "gpu", GpuFilters);
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GigabyteBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GigabyteBtn.setBounds(304, 177, 137, 21);
		GPU.add(GigabyteBtn);

		JRadioButton ZotacBtn = new JRadioButton("ZOTAC");
		ZotacBtn.setForeground(Color.WHITE);
		ZotacBtn.setOpaque(false);
		ZotacBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					filterIn("zotac", "gpu", GpuFilters);
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		ZotacBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ZotacBtn.setBounds(472, 177, 103, 21);
		GPU.add(ZotacBtn);

		JRadioButton MsiBtn = new JRadioButton("MSI");
		MsiBtn.setForeground(Color.WHITE);
		MsiBtn.setOpaque(false);
		MsiBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					filterIn("msi", "gpu", GpuFilters);
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		MsiBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		MsiBtn.setBounds(625, 177, 103, 21);
		GPU.add(MsiBtn);

		JRadioButton GtxBtn = new JRadioButton("GTX");
		GtxBtn.setForeground(Color.WHITE);
		GtxBtn.setOpaque(false);
		GtxBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					filterIn("gtx", "gpu", GpuFilters);
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GtxBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GtxBtn.setBounds(763, 177, 103, 21);
		GPU.add(GtxBtn);

		JRadioButton RtxBtn = new JRadioButton("RTX");
		RtxBtn.setForeground(Color.WHITE);
		RtxBtn.setOpaque(false);
		RtxBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					filterIn("rtx", "gpu", GpuFilters);
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		RtxBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		RtxBtn.setBounds(875, 177, 103, 21);
		GPU.add(RtxBtn);

		JRadioButton AsusGpuBtn = new JRadioButton("ASUS");
		AsusGpuBtn.setForeground(Color.WHITE);
		AsusGpuBtn.setOpaque(false);
		AsusGpuBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					filterIn("asus", "gpu", GpuFilters);
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		AsusGpuBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AsusGpuBtn.setBounds(995, 177, 103, 21);
		GPU.add(AsusGpuBtn);
		ButtonGroup gpuFilter = new ButtonGroup();
		gpuFilter.add(AsusGpuBtn);
		gpuFilter.add(RtxBtn);
		gpuFilter.add(GtxBtn);
		gpuFilter.add(ZotacBtn);
		gpuFilter.add(GigabyteBtn);
		gpuFilter.add(MsiBtn);
		gpuFilter.add(gpuClear);

		gpuClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gpuClear.setBounds(1122, 177, 103, 21);
		GPU.add(gpuClear);

		JRadioButton gpuL2H = new JRadioButton("Low To High");
		gpuL2H.setForeground(Color.WHITE);
		gpuL2H.setOpaque(false);
		gpuL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					highAndLow("gpu", GpuFilters, "asc");
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		gpuL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gpuL2H.setBounds(452, 224, 181, 30);
		GPU.add(gpuL2H);

		JRadioButton gpuH2L = new JRadioButton("High To Low");
		gpuH2L.setForeground(Color.WHITE);
		gpuH2L.setOpaque(false);
		gpuH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					GpuFilters.removeAllItems();
					GpuFilters.setVisible(true);
					GpuFilters.repaint();
					GpuFilters.revalidate();
					highAndLow("gpu", GpuFilters, "desc");
					comboBox_gpu.setVisible(false);
					comboBox_gpu.repaint();
					comboBox_gpu.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		gpuH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gpuH2L.setBounds(720, 224, 181, 30);
		GPU.add(gpuH2L);
		gpuFilter.add(gpuH2L);
		gpuFilter.add(gpuL2H);

		JLabel pcbggpu = new JLabel("  ");
		pcbggpu.setHorizontalAlignment(SwingConstants.CENTER);
		pcbggpu.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbggpu.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\gpu.jpg"));
		pcbggpu.setBounds(0, 0, 1555, 719);
		GPU.add(pcbggpu);

		JPanel PowerSupply = new JPanel();
		PowerSupply.setLayout(null);
		tabbedPane.addTab("Power Supply", null, PowerSupply, null);

		JLabel lblNewLabel_6_4_1_1_1_1_1_1 = new JLabel("Name");
		lblNewLabel_6_4_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_4_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1_1_1_1_1_1.setBounds(41, 318, 125, 46);
		PowerSupply.add(lblNewLabel_6_4_1_1_1_1_1_1);

		JLabel lblNewLabel_6_1_1_1_1_1_1_1_1 = new JLabel("Details");
		lblNewLabel_6_1_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1_1_1_1_1_1.setBounds(41, 375, 125, 46);
		PowerSupply.add(lblNewLabel_6_1_1_1_1_1_1_1_1);

		JLabel lblNewLabel_6_2_1_1_1_1_1_1_1 = new JLabel("Price");
		lblNewLabel_6_2_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_2_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1_1_1_1_1_1.setBounds(41, 432, 125, 46);
		PowerSupply.add(lblNewLabel_6_2_1_1_1_1_1_1_1);

		ProName_pws.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_pws.setBounds(198, 318, 1287, 46);
		PowerSupply.add(ProName_pws);

		ProUrl_pws.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_pws.setBounds(198, 375, 1287, 46);
		PowerSupply.add(ProUrl_pws);

		ProPrice_pws.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_pws.setBounds(198, 432, 1287, 46);
		PowerSupply.add(ProPrice_pws);
		JLabel ProId_dis = new JLabel("ID");
		ProId_dis.setForeground(Color.WHITE);
		JLabel est_dis = new JLabel("Rs:");
		est_dis.setForeground(Color.WHITE);
		JLabel imageDisplay_dis = new JLabel("Loading");
		imageDisplay_dis.setForeground(Color.WHITE);
		JLabel ProUrl_dis = new JLabel("https://----");
		ProUrl_dis.setForeground(Color.WHITE);
		ProUrl_dis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(ProUrl_dis.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel ProName_dis = new JLabel("Name");
		ProName_dis.setForeground(Color.WHITE);
		JLabel ProPrice_dis = new JLabel("Rs:");
		ProPrice_dis.setForeground(Color.WHITE);

		JComboBox<String> DisFilters = new JComboBox<String>();
		DisFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_dis.setText(FilterproName.get(DisFilters.getSelectedIndex()));
				ProUrl_dis.setText(FilterproUrl.get(DisFilters.getSelectedIndex()));
				ProPrice_dis.setText("Rs: " + FilterproPrice.get(DisFilters.getSelectedIndex()));
				ProId_dis.setText(FilterproId.get(DisFilters.getSelectedIndex()));
				URL url;
				try {
					url = new URL(FilterproImg.get(DisFilters.getSelectedIndex()));
					BufferedImage image = ImageIO.read(url);
					imageDisplay_dis.setIcon(new ImageIcon(
							new ImageIcon(image).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dummyEstimate = Double.parseDouble(FilterproPrice.get(DisFilters.getSelectedIndex())) + estimate;

				est_dis.setText("Rs: " + dummyEstimate);
			}
		});
		JComboBox<String> comboBox_dis = new JComboBox<String>();
		comboBox_dis.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_dis.setText(proName.get(comboBox_dis.getSelectedIndex()));
				ProUrl_dis.setText(proUrl.get(comboBox_dis.getSelectedIndex()));
				ProPrice_dis.setText("Rs: " + proPrice.get(comboBox_dis.getSelectedIndex()));
				ProId_dis.setText(proId.get(comboBox_dis.getSelectedIndex()));
				URL url;
				try {
					url = new URL(proImg.get(comboBox_dis.getSelectedIndex()));
					BufferedImage image = ImageIO.read(url);
					imageDisplay_dis.setIcon(new ImageIcon(
							new ImageIcon(image).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dummyEstimate = Double.parseDouble(proPrice.get(comboBox_dis.getSelectedIndex())) + estimate;

				est_dis.setText("Rs: " + dummyEstimate);
			}
		});

		JButton btnNewButton_4_2_1_1_1_1_1_1 = new JButton("Next");
		btnNewButton_4_2_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(80);
				estimate = dummyEstimate;
				products.add(ProId_pws.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(12);
				tabbedPane.setEnabledAt(11, false);
				tabbedPane.setEnabledAt(12, true);
				try {
					fetchDataFrom("display", comboBox_dis);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				est_gpu.setText("Rs: " + estimate);
			}
		});
		btnNewButton_4_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1_1_1_1_1_1.setBounds(1371, 640, 114, 37);
		PowerSupply.add(btnNewButton_4_2_1_1_1_1_1_1);

		comboBox_pws.setMaximumRowCount(200);
		comboBox_pws.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_pws.setAutoscrolls(true);
		comboBox_pws.setBounds(39, 44, 1446, 46);
		PowerSupply.add(comboBox_pws);

		JLabel lblNewLabel_6_3_1_1_1_1_1_1_1 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_3_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1_1_1_1_1_1.setBounds(41, 488, 125, 46);
		PowerSupply.add(lblNewLabel_6_3_1_1_1_1_1_1_1);

		est_pws.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_pws.setBounds(198, 488, 1287, 46);
		PowerSupply.add(est_pws);

		ProId_pws.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_pws.setBounds(218, 261, 1287, 46);
		PowerSupply.add(ProId_pws);

		JLabel lblNewLabel_6_5_2_1_1_1_1_1 = new JLabel("Product ID");
		lblNewLabel_6_5_2_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_5_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2_1_1_1_1_1.setBounds(41, 261, 167, 46);
		PowerSupply.add(lblNewLabel_6_5_2_1_1_1_1_1);
		JComboBox<String> PwsFilters = new JComboBox<String>();

		JRadioButton AntecBtn = new JRadioButton("ANTEC");
		AntecBtn.setForeground(Color.WHITE);
		AntecBtn.setOpaque(false);
		AntecBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					PwsFilters.removeAllItems();
					PwsFilters.setVisible(true);
					PwsFilters.repaint();
					PwsFilters.revalidate();
					filterIn("antec", "PowerSupply", PwsFilters);
					comboBox_pws.setVisible(false);
					comboBox_pws.repaint();
					comboBox_pws.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		AntecBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AntecBtn.setBounds(304, 177, 137, 21);
		PowerSupply.add(AntecBtn);

		JRadioButton AsusPwsBtn = new JRadioButton("ASUS");
		AsusPwsBtn.setForeground(Color.WHITE);
		AsusPwsBtn.setOpaque(false);
		AsusPwsBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					PwsFilters.removeAllItems();
					PwsFilters.setVisible(true);
					PwsFilters.repaint();
					PwsFilters.revalidate();
					filterIn("asus", "PowerSupply", PwsFilters);
					comboBox_pws.setVisible(false);
					comboBox_pws.repaint();
					comboBox_pws.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		AsusPwsBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AsusPwsBtn.setBounds(472, 177, 103, 21);
		PowerSupply.add(AsusPwsBtn);

		JRadioButton CoolerBtn = new JRadioButton("COOLER");
		CoolerBtn.setForeground(Color.WHITE);
		CoolerBtn.setOpaque(false);
		CoolerBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					PwsFilters.removeAllItems();
					PwsFilters.setVisible(true);
					PwsFilters.repaint();
					PwsFilters.revalidate();
					filterIn("cooler", "PowerSupply", PwsFilters);
					comboBox_pws.setVisible(false);
					comboBox_pws.repaint();
					comboBox_pws.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		CoolerBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CoolerBtn.setBounds(647, 177, 125, 21);
		PowerSupply.add(CoolerBtn);

		JRadioButton CorsairPwsBtn = new JRadioButton("CORSAIR");
		CorsairPwsBtn.setForeground(Color.WHITE);
		CorsairPwsBtn.setOpaque(false);
		CorsairPwsBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					PwsFilters.removeAllItems();
					PwsFilters.setVisible(true);
					PwsFilters.repaint();
					PwsFilters.revalidate();
					filterIn("corsair", "PowerSupply", PwsFilters);
					comboBox_pws.setVisible(false);
					comboBox_pws.repaint();
					comboBox_pws.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		CorsairPwsBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CorsairPwsBtn.setBounds(855, 177, 137, 21);
		PowerSupply.add(CorsairPwsBtn);

		JRadioButton pwsClear = new JRadioButton("CLEAR");
		pwsClear.setForeground(Color.WHITE);
		pwsClear.setOpaque(false);
		pwsClear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_pws.setVisible(true);
				comboBox_pws.revalidate();
				comboBox_pws.repaint();
				PwsFilters.removeAllItems();
				PwsFilters.setVisible(false);
				PwsFilters.repaint();
				PwsFilters.revalidate();
				try {
					fetchDataFrom("PowerSupply", comboBox_pws);
				} catch (Exception e1) {
					System.out.println("Invalid");
				}
			}
		});
		pwsClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pwsClear.setBounds(1071, 177, 125, 21);
		PowerSupply.add(pwsClear);

		PwsFilters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_pws.setText(FilterproName.get(PwsFilters.getSelectedIndex()));
				ProUrl_pws.setText(FilterproUrl.get(PwsFilters.getSelectedIndex()));
				ProPrice_pws.setText("Rs: " + FilterproPrice.get(PwsFilters.getSelectedIndex()));
				ProId_pws.setText(FilterproId.get(PwsFilters.getSelectedIndex()));
				dummyEstimate = Double.parseDouble(FilterproPrice.get(PwsFilters.getSelectedIndex())) + estimate;
				est_pws.setText("Rs: " + dummyEstimate);
			}
		});
		PwsFilters.setMaximumRowCount(200);
		PwsFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PwsFilters.setAutoscrolls(true);
		PwsFilters.setBounds(39, 44, 1446, 46);
		PowerSupply.add(PwsFilters);
		ButtonGroup pwsFilter = new ButtonGroup();
		pwsFilter.add(AntecBtn);
		pwsFilter.add(AsusPwsBtn);
		pwsFilter.add(CoolerBtn);
		pwsFilter.add(CorsairPwsBtn);
		pwsFilter.add(pwsClear);

		JRadioButton pwsL2H = new JRadioButton("Low To High");
		pwsL2H.setForeground(Color.WHITE);
		pwsL2H.setOpaque(false);
		pwsL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					PwsFilters.removeAllItems();
					PwsFilters.setVisible(true);
					PwsFilters.repaint();
					PwsFilters.revalidate();
					highAndLow("PowerSupply", PwsFilters, "asc");
					comboBox_pws.setVisible(false);
					comboBox_pws.repaint();
					comboBox_pws.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		pwsL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pwsL2H.setBounds(454, 224, 181, 30);
		PowerSupply.add(pwsL2H);

		JRadioButton pwsH2L = new JRadioButton("High To Low");
		pwsH2L.setForeground(Color.WHITE);
		pwsH2L.setOpaque(false);
		pwsH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					PwsFilters.removeAllItems();
					PwsFilters.setVisible(true);
					PwsFilters.repaint();
					PwsFilters.revalidate();
					highAndLow("PowerSupply", PwsFilters, "desc");
					comboBox_pws.setVisible(false);
					comboBox_pws.repaint();
					comboBox_pws.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		pwsH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pwsH2L.setBounds(722, 224, 181, 30);
		PowerSupply.add(pwsH2L);
		pwsFilter.add(pwsH2L);
		pwsFilter.add(pwsL2H);

		JLabel pcbgpws = new JLabel("  ");
		pcbgpws.setHorizontalAlignment(SwingConstants.CENTER);
		pcbgpws.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbgpws.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pws.jpg"));
		pcbgpws.setBounds(0, 0, 1555, 719);
		PowerSupply.add(pcbgpws);
		JPanel Display = new JPanel();
		Display.setBackground(Color.BLACK);
		Display.setForeground(Color.WHITE);
		Display.setLayout(null);
		tabbedPane.addTab("Display", null, Display, null);

		JLabel lblNewLabel_6_4_1_1_1_1_2 = new JLabel("Name");
		lblNewLabel_6_4_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_6_4_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_4_1_1_1_1_2.setBounds(41, 318, 125, 46);
		Display.add(lblNewLabel_6_4_1_1_1_1_2);

		JLabel lblNewLabel_6_1_1_1_1_1_1_2 = new JLabel("Details");
		lblNewLabel_6_1_1_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_6_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_1_1_1_1_1_1_2.setBounds(41, 375, 125, 46);
		Display.add(lblNewLabel_6_1_1_1_1_1_1_2);

		JLabel lblNewLabel_6_2_1_1_1_1_1_2 = new JLabel("Price");
		lblNewLabel_6_2_1_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_6_2_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_2_1_1_1_1_1_2.setBounds(41, 432, 125, 46);
		Display.add(lblNewLabel_6_2_1_1_1_1_1_2);

		ProName_dis.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_dis.setBounds(198, 318, 1287, 46);
		Display.add(ProName_dis);

		ProUrl_dis.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProUrl_dis.setBounds(198, 375, 1287, 46);
		Display.add(ProUrl_dis);

		ProPrice_dis.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_dis.setBounds(198, 432, 1287, 46);
		Display.add(ProPrice_dis);

		JButton btnNewButton_4_2_1_1_1_1_2 = new JButton("Next");
		btnNewButton_4_2_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(90);
				estimate = dummyEstimate;
				products.add(ProId_dis.getText());
				System.out.println(products);
				tabbedPane.setSelectedIndex(13);
				tabbedPane.setEnabledAt(12, false);
				tabbedPane.setEnabledAt(13, true);
			}
		});
		btnNewButton_4_2_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_4_2_1_1_1_1_2.setBounds(1371, 640, 114, 37);
		Display.add(btnNewButton_4_2_1_1_1_1_2);

		comboBox_dis.setMaximumRowCount(200);
		comboBox_dis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_dis.setAutoscrolls(true);
		comboBox_dis.setBounds(39, 52, 1446, 46);
		Display.add(comboBox_dis);

		JLabel lblNewLabel_6_3_1_1_1_1_1_2 = new JLabel("Estimate");
		lblNewLabel_6_3_1_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_6_3_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_3_1_1_1_1_1_2.setBounds(41, 488, 125, 46);
		Display.add(lblNewLabel_6_3_1_1_1_1_1_2);

		est_dis.setFont(new Font("Tahoma", Font.PLAIN, 30));
		est_dis.setBounds(198, 488, 1287, 46);
		Display.add(est_dis);

		ProId_dis.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProId_dis.setBounds(218, 261, 1287, 46);
		Display.add(ProId_dis);

		JLabel lblNewLabel_6_5_2_1_1_1_2 = new JLabel("Product ID");
		lblNewLabel_6_5_2_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_6_5_2_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_6_5_2_1_1_1_2.setBounds(41, 261, 167, 46);
		Display.add(lblNewLabel_6_5_2_1_1_1_2);

		imageDisplay_dis.setHorizontalAlignment(SwingConstants.CENTER);
		imageDisplay_dis.setBounds(1285, 147, 200, 200);
		Display.add(imageDisplay_dis);

		DisFilters.setMaximumRowCount(200);
		DisFilters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DisFilters.setAutoscrolls(true);
		DisFilters.setBounds(39, 52, 1446, 46);
		Display.add(DisFilters);

		JRadioButton AsusDisBtn = new JRadioButton("ASUS");
		AsusDisBtn.setBackground(Color.BLACK);
		AsusDisBtn.setForeground(Color.WHITE);
		AsusDisBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DisFilters.removeAllItems();
					DisFilters.setVisible(true);
					DisFilters.repaint();
					DisFilters.revalidate();
					filterIn("asus", "display", DisFilters);
					comboBox_dis.setVisible(false);
					comboBox_dis.repaint();
					comboBox_dis.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		AsusDisBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AsusDisBtn.setBounds(332, 160, 137, 21);
		Display.add(AsusDisBtn);

		JRadioButton LgBtn = new JRadioButton("LG");
		LgBtn.setBackground(Color.BLACK);
		LgBtn.setForeground(Color.WHITE);
		LgBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DisFilters.removeAllItems();
					DisFilters.setVisible(true);
					DisFilters.repaint();
					DisFilters.revalidate();
					filterIn("lg", "display", DisFilters);
					comboBox_dis.setVisible(false);
					comboBox_dis.repaint();
					comboBox_dis.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		LgBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		LgBtn.setBounds(497, 160, 72, 21);
		Display.add(LgBtn);

		JRadioButton SamsungDisBtn = new JRadioButton("SAMSUNG");
		SamsungDisBtn.setBackground(Color.BLACK);
		SamsungDisBtn.setForeground(Color.WHITE);
		SamsungDisBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DisFilters.removeAllItems();
					DisFilters.setVisible(true);
					DisFilters.repaint();
					DisFilters.revalidate();
					filterIn("samsung", "display", DisFilters);
					comboBox_dis.setVisible(false);
					comboBox_dis.repaint();
					comboBox_dis.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		SamsungDisBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SamsungDisBtn.setBounds(624, 160, 151, 21);
		Display.add(SamsungDisBtn);

		JRadioButton disClear = new JRadioButton("CLEAR");
		disClear.setBackground(Color.BLACK);
		disClear.setForeground(Color.WHITE);
		disClear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_dis.setVisible(true);
				comboBox_dis.revalidate();
				comboBox_dis.repaint();
				DisFilters.removeAllItems();
				DisFilters.setVisible(false);
				DisFilters.repaint();
				DisFilters.revalidate();
				try {
					fetchDataFrom("display", comboBox_dis);
				} catch (Exception e1) {
					System.out.println("Invalid");
				}
			}
		});
		disClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		disClear.setBounds(805, 160, 151, 21);
		Display.add(disClear);
		ButtonGroup disFilter = new ButtonGroup();
		disFilter.add(SamsungDisBtn);
		disFilter.add(LgBtn);
		disFilter.add(AsusDisBtn);
		disFilter.add(disClear);

		JRadioButton disL2H = new JRadioButton("Low To High");
		disL2H.setBackground(Color.BLACK);
		disL2H.setForeground(Color.WHITE);
		disL2H.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DisFilters.removeAllItems();
					DisFilters.setVisible(true);
					DisFilters.repaint();
					DisFilters.revalidate();
					highAndLow("display", DisFilters, "asc");
					comboBox_dis.setVisible(false);
					comboBox_dis.repaint();
					comboBox_dis.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		disL2H.setFont(new Font("Tahoma", Font.PLAIN, 24));
		disL2H.setBounds(417, 209, 181, 30);
		Display.add(disL2H);

		JRadioButton disH2L = new JRadioButton("High To Low");
		disH2L.setBackground(Color.BLACK);
		disH2L.setForeground(Color.WHITE);
		disH2L.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DisFilters.removeAllItems();
					DisFilters.setVisible(true);
					DisFilters.repaint();
					DisFilters.revalidate();
					highAndLow("display", DisFilters, "desc");
					comboBox_dis.setVisible(false);
					comboBox_dis.repaint();
					comboBox_dis.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		disH2L.setFont(new Font("Tahoma", Font.PLAIN, 24));
		disH2L.setBounds(685, 209, 181, 30);
		Display.add(disH2L);
		disFilter.add(disH2L);
		disFilter.add(disL2H);
		JPanel FinishBuild = new JPanel();
		FinishBuild.setLayout(null);
		tabbedPane.addTab("Finish Build", null, FinishBuild, null);

		JLabel lblNewLabel_5 = new JLabel("Finish Your Build");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(680, 69, 212, 44);
		FinishBuild.add(lblNewLabel_5);

		JLabel lblNewLabel_7 = new JLabel("What's Your Build Name?");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_7.setBounds(355, 280, 309, 31);
		FinishBuild.add(lblNewLabel_7);

		buildName = new JTextField();
		buildName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buildName.setBounds(772, 277, 412, 37);
		FinishBuild.add(buildName);
		buildName.setColumns(10);
		JLabel buildExists = new JLabel("You have this buildname already");
		buildExists.setVisible(false);

		JButton saveBuild = new JButton("Save Build");
		saveBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int count = 0;
					Class.forName("oracle.jdbc.OracleDriver");
					// Change Oracle DB Username And Password
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM",
							"password");
					Statement st = con.createStatement();
					ResultSet r = st.executeQuery("select count(*) as res from pcbuilds where mobile = '" + MobileNumber
							+ "' and buildname = '" + buildName.getText() + "'");
					while (r.next()) {
						count = r.getInt("res");
					}
					if (count == 0) {
						String dateTime = LocalDate.now().toString() + "  " + LocalTime.now().getHour() + ":"
								+ LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
						dateTime = dateTime.replace('-', '.');
						String addDetails = "insert into pcbuilds values('" + buildName.getText() + "','" + MobileNumber
								+ "','" + products.get(0) + "','" + products.get(1) + "','" + products.get(2) + "','"
								+ products.get(3) + "','" + products.get(4) + "','" + products.get(5) + "','"
								+ products.get(6) + "','" + products.get(7) + "','" + products.get(8) + "'," + estimate
								+ ", TO_TIMESTAMP ('" + dateTime + "','YYYY.MM.DD  HH24:MI:SS'))";
						System.out.println(addDetails);
						st.executeUpdate(addDetails);
						System.out.println("Success");
						tabbedPane.setSelectedIndex(14);
						tabbedPane.setEnabledAt(13, false);
						tabbedPane.setEnabledAt(14, true);
					}
					if (count != 0) {
						buildExists.setVisible(true);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		saveBuild.setFont(new Font("Tahoma", Font.PLAIN, 28));
		saveBuild.setBounds(680, 440, 212, 58);
		FinishBuild.add(saveBuild);

		buildExists.setHorizontalAlignment(SwingConstants.CENTER);
		buildExists.setForeground(Color.RED);
		buildExists.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buildExists.setBounds(772, 331, 412, 31);
		FinishBuild.add(buildExists);

		JLabel pcbg10 = new JLabel("  ");
		pcbg10.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg10.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg10.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbg10.setBounds(0, 0, 1555, 719);
		FinishBuild.add(pcbg10);

		JPanel ThankYou = new JPanel();
		ThankYou.setLayout(null);
		tabbedPane.addTab("Thank You", null, ThankYou, null);

		JLabel lblNewLabel_5_1 = new JLabel("Thank You For using PC Build");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_5_1.setBounds(534, 247, 541, 65);
		ThankYou.add(lblNewLabel_5_1);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
				tabbedPane.setEnabledAt(12, false);
				tabbedPane.setEnabledAt(0, true);
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnGoBack.setBounds(706, 387, 212, 58);
		ThankYou.add(btnGoBack);

		JLabel pcbg11 = new JLabel("  ");
		pcbg11.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg11.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg11.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbg11.setBounds(0, 0, 1555, 719);
		ThankYou.add(pcbg11);

		JPanel View = new JPanel();
		View.setLayout(null);
		tabbedPane.addTab("View", null, View, null);

		JLabel lblNewLabel_1_1 = new JLabel("Enter User Details");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1_1.setBounds(572, 168, 395, 43);
		View.add(lblNewLabel_1_1);

		JLabel lblNewLabel_3_3 = new JLabel("Mobile Number");
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_3_3.setBounds(371, 266, 219, 53);
		View.add(lblNewLabel_3_3);

		viewMobile = new JTextField();
		viewMobile.setFont(new Font("Tahoma", Font.PLAIN, 30));
		viewMobile.setColumns(10);
		viewMobile.setBounds(855, 278, 184, 31);
		View.add(viewMobile);

		JLabel noBuilds = new JLabel("You have No Builds");
		JLabel invalidErrView = new JLabel("Invalid user Details");

		JLabel ProName_disView = new JLabel("Name");
		ProName_disView.setForeground(Color.WHITE);
		JLabel ProName_pwsView = new JLabel("Name");
		ProName_pwsView.setForeground(Color.WHITE);
		JLabel ProName_colView = new JLabel("Name");
		ProName_colView.setForeground(Color.WHITE);
		JLabel ProName_cabView = new JLabel("Name");
		ProName_cabView.setForeground(Color.WHITE);
		JLabel ProName_gpuView = new JLabel("Name");
		ProName_gpuView.setForeground(Color.WHITE);
		JLabel ProName_stoView = new JLabel("Name");
		ProName_stoView.setForeground(Color.WHITE);
		JLabel ProName_ramView = new JLabel("Name");
		ProName_ramView.setForeground(Color.WHITE);
		JLabel ProName_mtbView = new JLabel("Name");
		ProName_mtbView.setForeground(Color.WHITE);
		JLabel ProName_proView = new JLabel("Name");
		ProName_proView.setForeground(Color.WHITE);
		JLabel ProPrice_disView = new JLabel("Price");
		ProPrice_disView.setForeground(Color.WHITE);
		JLabel ProPrice_pwsView = new JLabel("Price");
		ProPrice_pwsView.setForeground(Color.WHITE);
		JLabel ProPrice_colView = new JLabel("Price");
		ProPrice_colView.setForeground(Color.WHITE);
		JLabel ProPrice_cabView = new JLabel("Price");
		ProPrice_cabView.setForeground(Color.WHITE);
		JLabel ProPrice_gpuView = new JLabel("Price");
		ProPrice_gpuView.setForeground(Color.WHITE);
		JLabel ProPrice_stoView = new JLabel("Price");
		ProPrice_stoView.setForeground(Color.WHITE);
		JLabel ProPrice_ramView = new JLabel("Price");
		ProPrice_ramView.setForeground(Color.WHITE);
		JLabel ProPrice_mtbView = new JLabel("Price");
		ProPrice_mtbView.setForeground(Color.WHITE);
		JLabel ProPrice_proView = new JLabel("Price");
		ProPrice_proView.setForeground(Color.WHITE);
		JLabel buildEstimate = new JLabel("Price");
		buildEstimate.setForeground(Color.WHITE);

		JComboBox<String> buildNames = new JComboBox<String>();
		buildNames.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buildNames.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ProName_proView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 0));
				ProName_mtbView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 1));
				ProName_ramView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 2));
				ProName_stoView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 3));
				ProName_gpuView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 4));
				ProName_cabView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 5));
				ProName_colView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 6));
				ProName_pwsView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 7));
				ProName_disView.setText(ViewproName.get(buildNames.getSelectedIndex() * 9 + 8));
				ProPrice_proView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (0)));
				ProPrice_mtbView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (1)));
				ProPrice_ramView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (2)));
				ProPrice_stoView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (3)));
				ProPrice_gpuView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (4)));
				ProPrice_cabView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (5)));
				ProPrice_colView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (6)));
				ProPrice_pwsView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (7)));
				ProPrice_disView.setText(ViewproPrice.get(buildNames.getSelectedIndex() * 9 + (8)));
				buildEstimate.setText(ViewEstimate.get(buildNames.getSelectedIndex()));
			}
		});

		invalidErrView.setHorizontalAlignment(SwingConstants.CENTER);
		invalidErrView.setForeground(Color.RED);
		invalidErrView.setFont(new Font("Tahoma", Font.PLAIN, 20));
		invalidErrView.setBounds(855, 320, 184, 25);
		View.add(invalidErrView);
		noBuilds.setVisible(false);
		invalidErrView.setVisible(false);
		JButton btnSubmit_1_1 = new JButton("Submit");
		btnSubmit_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int flags = 0;
					int builds = 0;
					Class.forName("oracle.jdbc.OracleDriver");
					// Change Oracle DB Username And Password
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM",
							"password");
					Statement st = con.createStatement();
					ResultSet r = st.executeQuery(
							"select count(*) as res from pcbuserdetails where mobile = '" + viewMobile.getText() + "'");
					while (r.next()) {
						flags = r.getInt("res");
					}
					if (flags == 0) {
						invalidErrView.setVisible(true);
						noBuilds.setVisible(false);
					}
					if (flags != 0) {
						ResultSet fetchname = st.executeQuery(
								"select * from pcbuserdetails where mobile = '" + viewMobile.getText() + "'");
						while (fetchname.next()) {
							userName = fetchname.getString("name");
						}
						ResultSet res = st.executeQuery(
								"select count(*) as nos from pcbuilds where mobile = '" + viewMobile.getText() + "'");
						while (res.next()) {
							builds = res.getInt("nos");
						}
						if (builds == 0) {
							invalidErrView.setVisible(false);
							noBuilds.setVisible(true);
						}
						if (builds != 0) {
							ArrayList<String> processor = new ArrayList<String>();
							ArrayList<String> motherbrd = new ArrayList<String>();
							ArrayList<String> storage = new ArrayList<String>();
							ArrayList<String> display = new ArrayList<String>();
							ArrayList<String> powersupply = new ArrayList<String>();
							ArrayList<String> gpu = new ArrayList<String>();
							ArrayList<String> cooler = new ArrayList<String>();
							ArrayList<String> cabinet = new ArrayList<String>();
							ArrayList<String> ram = new ArrayList<String>();
							ArrayList<String> BuildName = new ArrayList<String>();
							noBuilds.setVisible(false);
							invalidErrView.setVisible(false);
							ResultSet result = st.executeQuery(
									"select * from pcbuilds where mobile = '" + viewMobile.getText() + "'");
							while (result.next()) {
								BuildName.add(result.getString("buildname"));
								ViewBuilds.add(result.getString("buildname"));
								ViewTimeStamp.add(result.getTimestamp("createdat").toString());
								ViewEstimate.add(Double.toString(result.getDouble("estimate")));
								processor.add(result.getString("processor"));
								motherbrd.add(result.getString("motherboard"));
								storage.add(result.getString("storage"));
								ram.add(result.getString("ram"));
								cabinet.add(result.getString("cabinet"));
								cooler.add(result.getString("cooler"));
								gpu.add(result.getString("gpu"));
								powersupply.add(result.getString("powersupply"));
								display.add(result.getString("display"));
							}
							for (int i = 0; i < processor.size(); i++) {
								ResultSet pro = st
										.executeQuery("select * from pcbproducts where type='processor' and prid = '"
												+ processor.get(i) + "'");
								while (pro.next()) {
									ViewproId.add(pro.getString("prid"));
									ViewproName.add(pro.getString("pname"));
									ViewproPrice.add(Double.toString(pro.getDouble("price")));
								}
								ResultSet mtb = st
										.executeQuery("select * from pcbproducts where type='motherboard' and prid = '"
												+ motherbrd.get(i) + "'");
								while (mtb.next()) {
									ViewproId.add(mtb.getString("prid"));
									ViewproName.add(mtb.getString("pname"));
									ViewproPrice.add(Double.toString(mtb.getDouble("price")));
								}
								ResultSet rams = st.executeQuery(
										"select * from pcbproducts where type='ram' and prid = '" + ram.get(i) + "'");
								while (rams.next()) {
									ViewproId.add(rams.getString("prid"));
									ViewproName.add(rams.getString("pname"));
									ViewproPrice.add(Double.toString(rams.getDouble("price")));
								}
								ResultSet sto = st
										.executeQuery("select * from pcbproducts where type='storage' and prid = '"
												+ storage.get(i) + "'");
								while (sto.next()) {
									ViewproId.add(sto.getString("prid"));
									ViewproName.add(sto.getString("pname"));
									ViewproPrice.add(Double.toString(sto.getDouble("price")));
								}
								ResultSet gpus = st.executeQuery(
										"select * from pcbproducts where type='gpu' and prid = '" + gpu.get(i) + "'");
								while (gpus.next()) {
									ViewproId.add(gpus.getString("prid"));
									ViewproName.add(gpus.getString("pname"));
									ViewproPrice.add(Double.toString(gpus.getDouble("price")));
								}
								ResultSet cab = st
										.executeQuery("select * from pcbproducts where type='cabinet' and prid = '"
												+ cabinet.get(i) + "'");
								while (cab.next()) {
									ViewproId.add(cab.getString("prid"));
									ViewproName.add(cab.getString("pname"));
									ViewproPrice.add(Double.toString(cab.getDouble("price")));
									ViewCabImg.add(cab.getString("pimg"));
								}
								ResultSet col = st
										.executeQuery("select * from pcbproducts where type='cooler' and prid = '"
												+ cooler.get(i) + "'");
								while (col.next()) {
									ViewproId.add(col.getString("prid"));
									ViewproName.add(col.getString("pname"));
									ViewproPrice.add(Double.toString(col.getDouble("price")));
									ViewColImg.add(col.getString("pimg"));
								}
								ResultSet pws = st
										.executeQuery("select * from pcbproducts where type='PowerSupply' and prid = '"
												+ powersupply.get(i) + "'");
								while (pws.next()) {
									ViewproId.add(pws.getString("prid"));
									ViewproName.add(pws.getString("pname"));
									ViewproPrice.add(Double.toString(pws.getDouble("price")));
								}
								ResultSet dis = st
										.executeQuery("select * from pcbproducts where type='display' and prid = '"
												+ display.get(i) + "'");
								while (dis.next()) {
									ViewproId.add(dis.getString("prid"));
									ViewproName.add(dis.getString("pname"));
									ViewproPrice.add(Double.toString(dis.getDouble("price")));
									ViewDisImg.add(dis.getString("pimg"));
								}
							}
							System.out.println(ViewproName.size());
							for (int i = 0; i < ViewBuilds.size(); i++) {
								buildNames.addItem(ViewBuilds.get(i));
							}
							tabbedPane.setSelectedIndex(16);
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubmit_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnSubmit_1_1.setBounds(710, 421, 145, 37);
		View.add(btnSubmit_1_1);

		noBuilds.setHorizontalAlignment(SwingConstants.CENTER);
		noBuilds.setForeground(Color.RED);
		noBuilds.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noBuilds.setBounds(855, 320, 184, 25);
		View.add(noBuilds);

		JLabel pcbg12 = new JLabel("  ");
		pcbg12.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg12.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg12.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\pcbg1.jpg"));
		pcbg12.setBounds(0, 0, 1555, 719);
		View.add(pcbg12);

		JPanel ViewDetails = new JPanel();
		ViewDetails.setLayout(null);
		tabbedPane.addTab("Details", null, ViewDetails, null);

		buildNames.setBounds(91, 26, 835, 43);
		ViewDetails.add(buildNames);

		JLabel lblNewLabel_8 = new JLabel("Processor");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8.setBounds(91, 117, 122, 29);
		ViewDetails.add(lblNewLabel_8);

		JLabel lblNewLabel_8_1 = new JLabel("MotherBoard");
		lblNewLabel_8_1.setForeground(Color.WHITE);
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_1.setBounds(91, 183, 151, 29);
		ViewDetails.add(lblNewLabel_8_1);

		JLabel lblNewLabel_8_2 = new JLabel("RAM");
		lblNewLabel_8_2.setForeground(Color.WHITE);
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_2.setBounds(91, 249, 122, 29);
		ViewDetails.add(lblNewLabel_8_2);

		JLabel lblNewLabel_8_3 = new JLabel("Storage");
		lblNewLabel_8_3.setForeground(Color.WHITE);
		lblNewLabel_8_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_3.setBounds(91, 313, 122, 29);
		ViewDetails.add(lblNewLabel_8_3);

		JLabel lblNewLabel_8_4 = new JLabel("GPU");
		lblNewLabel_8_4.setForeground(Color.WHITE);
		lblNewLabel_8_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_4.setBounds(91, 380, 122, 29);
		ViewDetails.add(lblNewLabel_8_4);

		JLabel lblNewLabel_8_5 = new JLabel("Cabinet");
		lblNewLabel_8_5.setForeground(Color.WHITE);
		lblNewLabel_8_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_5.setBounds(91, 446, 122, 29);
		ViewDetails.add(lblNewLabel_8_5);

		JLabel lblNewLabel_8_6 = new JLabel("Cooler");
		lblNewLabel_8_6.setForeground(Color.WHITE);
		lblNewLabel_8_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_6.setBounds(91, 513, 122, 29);
		ViewDetails.add(lblNewLabel_8_6);

		JLabel lblNewLabel_8_7 = new JLabel("PowerSupply");
		lblNewLabel_8_7.setForeground(Color.WHITE);
		lblNewLabel_8_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_7.setBounds(91, 579, 151, 29);
		ViewDetails.add(lblNewLabel_8_7);

		JLabel lblNewLabel_8_8 = new JLabel("Display");
		lblNewLabel_8_8.setForeground(Color.WHITE);
		lblNewLabel_8_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8_8.setBounds(91, 639, 122, 29);
		ViewDetails.add(lblNewLabel_8_8);

		ProName_proView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_proView.setBounds(306, 102, 860, 55);
		ViewDetails.add(ProName_proView);

		ProName_mtbView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_mtbView.setBounds(306, 168, 860, 55);
		ViewDetails.add(ProName_mtbView);

		ProName_ramView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_ramView.setBounds(306, 234, 860, 55);
		ViewDetails.add(ProName_ramView);

		ProName_stoView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_stoView.setBounds(306, 298, 860, 55);
		ViewDetails.add(ProName_stoView);

		ProName_gpuView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_gpuView.setBounds(306, 365, 860, 55);
		ViewDetails.add(ProName_gpuView);

		ProName_cabView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_cabView.setBounds(306, 431, 860, 55);
		ViewDetails.add(ProName_cabView);

		ProName_colView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_colView.setBounds(306, 498, 860, 55);
		ViewDetails.add(ProName_colView);

		ProName_pwsView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_pwsView.setBounds(306, 564, 860, 55);
		ViewDetails.add(ProName_pwsView);

		ProName_disView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProName_disView.setBounds(306, 624, 860, 55);
		ViewDetails.add(ProName_disView);

		ProPrice_proView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_proView.setBounds(1194, 102, 295, 55);
		ViewDetails.add(ProPrice_proView);

		ProPrice_mtbView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_mtbView.setBounds(1194, 168, 295, 55);
		ViewDetails.add(ProPrice_mtbView);

		ProPrice_ramView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_ramView.setBounds(1194, 234, 295, 55);
		ViewDetails.add(ProPrice_ramView);

		ProPrice_stoView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_stoView.setBounds(1194, 298, 295, 55);
		ViewDetails.add(ProPrice_stoView);

		ProPrice_gpuView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_gpuView.setBounds(1194, 365, 295, 55);
		ViewDetails.add(ProPrice_gpuView);

		ProPrice_cabView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_cabView.setBounds(1194, 431, 295, 55);
		ViewDetails.add(ProPrice_cabView);

		ProPrice_colView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_colView.setBounds(1194, 498, 295, 55);
		ViewDetails.add(ProPrice_colView);

		ProPrice_pwsView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_pwsView.setBounds(1194, 564, 295, 55);
		ViewDetails.add(ProPrice_pwsView);

		ProPrice_disView.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ProPrice_disView.setBounds(1194, 624, 295, 55);
		ViewDetails.add(ProPrice_disView);

		buildEstimate.setFont(new Font("Tahoma", Font.PLAIN, 30));
		buildEstimate.setBounds(988, 26, 182, 43);
		ViewDetails.add(buildEstimate);

		JButton ExportView = new JButton("Export");
		ExportView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(17);
				tabbedPane.setEnabledAt(16, false);
			}
		});
		ExportView.setBounds(1317, 36, 89, 23);
		ViewDetails.add(ExportView);

		JLabel pcbg13 = new JLabel("  ");
		pcbg13.setHorizontalAlignment(SwingConstants.CENTER);
		pcbg13.setHorizontalTextPosition(SwingConstants.CENTER);
		pcbg13.setIcon(new ImageIcon(Paths.get("").toAbsolutePath().toString() + "\\lib\\images\\view.jpg"));
		pcbg13.setBounds(0, 0, 1555, 719);
		ViewDetails.add(pcbg13);
		tabbedPane.setEnabledAt(15, false);
		tabbedPane.setEnabledAt(16, false);
		JPanel Export = new JPanel();
		Export.setBackground(Color.BLACK);
		Export.setLayout(null);
		tabbedPane.addTab("Export", null, Export, null);

		JLabel lblNewLabel_5_1_1 = new JLabel("Thank You For using PC Build");
		lblNewLabel_5_1_1.setForeground(Color.WHITE);
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_5_1_1.setBounds(530, 84, 541, 65);
		Export.add(lblNewLabel_5_1_1);

		JLabel mailSuccess = new JLabel("E-Mail Sent Successfully");
		mailText = new JTextField();
		mailText.setVisible(false);
		mailSuccess.setVisible(false);
		JButton submitMail = new JButton("Send");
		submitMail.setVisible(false);
		submitMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File(
						Paths.get("").toAbsolutePath().toString() + "\\lib\\template\\PCBuild Invoice template.xlsx");
				FileInputStream fIP;
				// Change this (yourSMTP@mail.id SMTPpassword) with Your Mail ID And Password in
				// below line
				System.out.println("\"" + Paths.get("").toAbsolutePath().toString()
						+ "\\lib\\script\\mailer\\dist\\mailer.exe\" yourSMTP@mail.id SMTPpassword \""
						+ mailText.getText() + "\" \"" + userName + "\" \""
						+ ViewBuilds.get(buildNames.getSelectedIndex()) + "\" \"" + System.getProperty("user.home")
						+ "\\Desktop\\" + userName + " " + ViewBuilds.get(buildNames.getSelectedIndex()) + ".pdf\"");
				try {
					FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\"
							+ userName + " " + ViewBuilds.get(buildNames.getSelectedIndex()) + ".xlsx");
					fIP = new FileInputStream(file);
					XSSFWorkbook workbookinput = new XSSFWorkbook(fIP);
					XSSFWorkbook workbookoutput = workbookinput;
					System.out.println("Copied");
					workbookoutput.getSheetAt(0).getRow(7).createCell(8).setCellValue(userName);
					workbookoutput.getSheetAt(0).getRow(8).createCell(8)
							.setCellValue(ViewTimeStamp.get(buildNames.getSelectedIndex()));
					workbookoutput.getSheetAt(0).getRow(9).createCell(8)
							.setCellValue(ViewBuilds.get(buildNames.getSelectedIndex()));
					for (int i = 0; i < 9; i++) {
						workbookoutput.getSheetAt(0).getRow(i + 13).createCell(1)
								.setCellValue(ViewproId.get(buildNames.getSelectedIndex() * 9 + i));
					}
					for (int i = 0; i < 9; i++) {
						workbookoutput.getSheetAt(0).getRow(i + 13).createCell(3)
								.setCellValue(ViewproName.get(buildNames.getSelectedIndex() * 9 + i));
					}
					for (int i = 0; i < 9; i++) {
						workbookoutput.getSheetAt(0).getRow(i + 13).createCell(8)
								.setCellValue("Rs: " + ViewproPrice.get(buildNames.getSelectedIndex() * 9 + i));
					}
					workbookoutput.getSheetAt(0).getRow(24).createCell(8)
							.setCellValue("Rs: " + ViewEstimate.get(buildNames.getSelectedIndex()));
					workbookoutput.write(out);
					out.close();
					fIP.close();
					Workbook xls = new Workbook(System.getProperty("user.home") + "\\Desktop\\" + userName + " "
							+ ViewBuilds.get(buildNames.getSelectedIndex()) + ".xlsx");
					xls.getWorksheets().get(0).getPageSetup().setOrientation(PageOrientationType.LANDSCAPE);
					xls.save(System.getProperty("user.home") + "\\Desktop\\" + userName + " "
							+ ViewBuilds.get(buildNames.getSelectedIndex()) + ".pdf");
					// Change Mail ID And Password
					Process p = Runtime.getRuntime().exec("\"" + Paths.get("").toAbsolutePath().toString()
							+ "\\lib\\script\\mailer\\dist\\mailer.exe\" \"yourSMTP@mail.id\" \"SMTPPassowrd\" \""
							+ mailText.getText() + "\" \"" + userName + "\" \""
							+ ViewBuilds.get(buildNames.getSelectedIndex()) + "\" \"" + System.getProperty("user.home")
							+ "\\Desktop\\" + userName + " " + ViewBuilds.get(buildNames.getSelectedIndex())
							+ ".pdf\"");
					System.out.println("Working");
					mailSuccess.setVisible(true);
					mailText.setVisible(false);
					submitMail.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton btnGoBack_1_1 = new JButton("Send E-mail");
		btnGoBack_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mailText.setVisible(true);
				submitMail.setVisible(true);
				mailSuccess.setVisible(false);
			}
		});
		btnGoBack_1_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnGoBack_1_1.setBounds(683, 475, 307, 58);
		Export.add(btnGoBack_1_1);

		JButton btnExportXls = new JButton("Export PDF");
		btnExportXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File(
						Paths.get("").toAbsolutePath().toString() + "\\lib\\template\\PCBuild Invoice template.xlsx");
				FileInputStream fIP;
				try {
					FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\"
							+ userName + " " + ViewBuilds.get(buildNames.getSelectedIndex()) + ".xlsx");
					fIP = new FileInputStream(file);
					XSSFWorkbook workbookinput = new XSSFWorkbook(fIP);
					XSSFWorkbook workbookoutput = workbookinput;
					System.out.println("Copied");
					workbookoutput.getSheetAt(0).getRow(7).createCell(8).setCellValue(userName);
					workbookoutput.getSheetAt(0).getRow(8).createCell(8)
							.setCellValue(ViewTimeStamp.get(buildNames.getSelectedIndex()));
					workbookoutput.getSheetAt(0).getRow(9).createCell(8)
							.setCellValue(ViewBuilds.get(buildNames.getSelectedIndex()));
					for (int i = 0; i < 9; i++) {
						workbookoutput.getSheetAt(0).getRow(i + 13).createCell(1)
								.setCellValue(ViewproId.get(buildNames.getSelectedIndex() * 9 + i));
					}
					for (int i = 0; i < 9; i++) {
						workbookoutput.getSheetAt(0).getRow(i + 13).createCell(3)
								.setCellValue(ViewproName.get(buildNames.getSelectedIndex() * 9 + i));
					}
					for (int i = 0; i < 9; i++) {
						workbookoutput.getSheetAt(0).getRow(i + 13).createCell(8)
								.setCellValue("Rs: " + ViewproPrice.get(buildNames.getSelectedIndex() * 9 + i));
					}
					workbookoutput.getSheetAt(0).getRow(24).createCell(8)
							.setCellValue("Rs: " + ViewEstimate.get(buildNames.getSelectedIndex()));
					workbookoutput.write(out);
					out.close();
					fIP.close();
					Workbook xls = new Workbook(System.getProperty("user.home") + "\\Desktop\\" + userName + " "
							+ ViewBuilds.get(buildNames.getSelectedIndex()) + ".xlsx");
					xls.getWorksheets().get(0).getPageSetup().setOrientation(PageOrientationType.LANDSCAPE);
					xls.save(System.getProperty("user.home") + "\\Desktop\\" + userName + " "
							+ ViewBuilds.get(buildNames.getSelectedIndex()) + ".pdf");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (file.isFile() && file.exists()) {
					System.out.println("openworkbook.xlsx file open successfully.");
				} else {
					System.out.println("Error to open openworkbook.xlsx file.");
				}
			}
		});
		btnExportXls.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnExportXls.setBounds(683, 337, 307, 58);
		Export.add(btnExportXls);

		mailText.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mailText.setBounds(683, 544, 460, 40);
		Export.add(mailText);
		mailText.setColumns(10);

		submitMail.setFont(new Font("Tahoma", Font.PLAIN, 23));
		submitMail.setBounds(1172, 544, 137, 40);
		Export.add(submitMail);

		mailSuccess.setForeground(new Color(0, 100, 0));
		mailSuccess.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mailSuccess.setBounds(1028, 495, 232, 25);
		Export.add(mailSuccess);

		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		tabbedPane.setEnabledAt(4, false);
		tabbedPane.setEnabledAt(5, false);
		tabbedPane.setEnabledAt(6, false);
		tabbedPane.setEnabledAt(7, false);
		tabbedPane.setEnabledAt(8, false);
		tabbedPane.setEnabledAt(9, false);
		tabbedPane.setEnabledAt(10, false);
		tabbedPane.setEnabledAt(11, false);
		tabbedPane.setEnabledAt(12, false);
		tabbedPane.setEnabledAt(13, false);
		tabbedPane.setEnabledAt(14, false);
		tabbedPane.setEnabledAt(15, false);
		tabbedPane.setEnabledAt(16, false);
		tabbedPane.setEnabledAt(17, false);

	}
}