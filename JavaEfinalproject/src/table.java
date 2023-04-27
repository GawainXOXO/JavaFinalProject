package default;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import com.config.ConnectionDb;
import com.utility.Clothes;

public class tableClothes extends JFrame implements ActionListener, MouseInputListener{
	
	public Vector<Clothes> clothesData;
	private ConnectionDb connectDb;
	
	private JPanel topPanel, midPanel, contentPanel, formPanel, tablePanel, botPanel, botTPanel, botBPanel,
		kodeLblPanel, namaLblPanel, hargaLblPanel, stokLblPanel,
		kodeFieldPanel, namaFieldPanel, hargaFieldPanel, stokFieldPanel;
	private JLabel titleLbl, kodeLbl, namaLbl, hargaLbl, stokLbl;
	private JTextField kodeField, namaField, hargaField, stokField;
	private JTable clothesTable;
	private JScrollPane scrollPane;
	private JButton addBtn, updateBtn, deleteBtn, clearBtn, backBtn;
	
	private Vector<Object> column, row;
	private Vector<Vector<Object>> data;
	
	private int kode, harga, stok;
	private String nama;
	
	public tableClothes(Vector<Clothes> clothesData, ConnectionDb connectDb) {
		this.clothesData = clothesData;
		this.connectDb = connectDb;
		
		clothesView();
		clothesFrame();
	}
	
	void clothesView() {
		
		// Top Panel
		topPanel = new JPanel();
		titleLbl = new JLabel("CRUD Clothes");
		titleLbl.setFont(new Font("", Font.BOLD, 48));
		topPanel.add(titleLbl);
		
		// Mid Panel
		midPanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(1, 2));
		
		// Left Panel
		formPanel = new JPanel(new GridLayout(5, 2));
		
		// Clothes kode
		kodeLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		kodeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		kodeLbl = new JLabel("kode :");
		kodeLblPanel.add(kodeLbl);
		kodeField = new JTextField();
		kodeField.setEditable(false);
		kodeField.setPreferredstok(new Dimension(150, 25));
		kodeFieldPanel.add(kodeField);
		
		// Clothes nama
		namaLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		namaFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		namaLbl = new JLabel("nama :");
		namaLblPanel.add(namaLbl);
		namaField = new JTextField();
		namaField.setPreferredstok(new Dimension(150, 25));
		namaFieldPanel.add(namaField);
		
		// Clothes harga
		hargaLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		hargaFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
				
		hargaLbl = new JLabel("harga :");
		hargaLblPanel.add(hargaLbl);
		hargaField = new JTextField();
		hargaField.setPreferredstok(new Dimension(150, 25));
		hargaFieldPanel.add(hargaField);
		
		// Clothes stok
		stokLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		stokFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						
		stokLbl = new JLabel("stok :");
		stokLblPanel.add(stokLbl);
		stokField = new JTextField();
		stokField.setPreferredstok(new Dimension(150, 25));
		stokFieldPanel.add(stokField);
		
		formPanel.add(kodeLblPanel);
		formPanel.add(kodeFieldPanel);
		formPanel.add(namaLblPanel);
		formPanel.add(namaFieldPanel);
		formPanel.add(hargaLblPanel);
		formPanel.add(hargaFieldPanel);
		formPanel.add(stokLblPanel);
		formPanel.add(stokFieldPanel);
		
		// Right Panel
		column = new Vector<Object>();
		column.add("kode");
		column.add("nama");
		column.add("harga");
		column.add("stok");
		
		data = new Vector<Vector<Object>>();
		for (Clothes clothes : clothesData) {
			row = new Vector<Object>();
			row.add(clothes.getkode());
			row.add(clothes.getnama());
			row.add(clothes.getharga());
			row.add(clothes.getstok());
			data.add(row);
		}
		
		clothesTable = new JTable(data, column);
		clothesTable.addMouseListener(this);
		scrollPane = new JScrollPane(clothesTable);
		tablePanel = new JPanel();
		tablePanel.add(scrollPane);
		
		contentPanel.add(formPanel);
		contentPanel.add(tablePanel);
		
		midPanel.add(contentPanel);
		
		// Bot Panel
		botPanel = new JPanel(new GridLayout(2, 1));
		
		// Bottom Top Panel
		botTPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		addBtn = new JButton("Add");
		addBtn.addActionListener(this);
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(this);
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(this);
		
		botTPanel.add(addBtn);
		botTPanel.add(updateBtn);
		botTPanel.add(deleteBtn);
		botTPanel.add(clearBtn);
		
		// Bottom Bottom Panel
		botBPanel = new JPanel();
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		botBPanel.add(backBtn);
		
		botPanel.add(botTPanel);
		botPanel.add(botBPanel);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
	}
	
	void clothesFrame() {
		setTitle("Clothify");
		setstok(1140, 768);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			String idStr = idField.getText();
			nama = namaField.getText();
			harga = hargaField.getText();
			stok = stokField.getText();
			
			// Add Clothes Data to Vector
			Clothes addNewClothes = new Clothes(0, nama, harga, stok);
			clothesData.add(addNewClothes);
			
			// Add Clothes to Database
			connectDb.insertClothesData(nama, harga, stok);
			
			clothesData.clear();
			setVisible(false);
			new Menu(clothesData, connectDb);
		}
		else if (e.getSource() == updateBtn) {
			// coba sendiri :D
		}
		else if (e.getSource() == deleteBtn) {
			// coba sendiri :D
		}
		else if (e.getSource() == clearBtn) {
			idField.setText("");
			namaField.setText("");
			hargaField.setText("");
			stokField.setText("");
		}
		else if (e.getSource() == backBtn) {
			clothesData.clear();
			setVisible(false);
			new Menu(clothesData, connectDb);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == clothesTable) {
			int row = clothesTable.getSelectedRow();
			idField.setText(clothesTable.getValueAt(row, 0).toString());
			namaField.setText(clothesTable.getValueAt(row, 1).toString());
			hargaField.setText(clothesTable.getValueAt(row, 2).toString());
			stokField.setText(clothesTable.getValueAt(row, 3).toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
