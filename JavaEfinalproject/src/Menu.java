package default;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.config.ConnectionDb;
import com.utility.Clothes;

public class Menu extends JFrame implements ActionListener{
	
	public Vector<Clothes> clothesData;
	private ConnectionDb connectDb;
	
	private JPanel topPanel, botPanel;
	private JLabel titleLbl;
	private JButton enterBtn;
	
	void menuView() {
		// Top Panel
		topPanel = new JPanel();
		titleLbl = new JLabel("CLOTHIFY");
		titleLbl.setFont(new Font("", Font.BOLD, 64));
		topPanel.add(titleLbl);
		
		// Bot Panel
		botPanel = new JPanel();
		enterBtn = new JButton("Enter");
		enterBtn.addActionListener(this);
		botPanel.add(enterBtn);
		
		add(topPanel, BorderLayout.NORTH);
		add(botPanel, BorderLayout.SOUTH);
	}
	
	void menuFrame() {
		setTitle("Clothify");
		setSize(350, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Menu(Vector<Clothes> clothesData, ConnectionDb connectDb) {
		this.clothesData = clothesData;
		this.connectDb = connectDb;
		
		fillDataVector();
		menuView();
		menuFrame();
	}
	
	private void fillDataVector() {
		connectDb.rs = connectDb.getClothesData();
		try {
			while (connectDb.rs.next()) {
				int kode = Integer.valueOf(String.valueOf(connectDb.rs.getObject(1)));
				String nama = String.valueOf(connectDb.rs.getObject(2));
				int harga =  String.valueOf(connectDb.rs.getObject(3));
				int stok = String.valueOf(connectDb.rs.getObject(4));
				
				clothesData.add(new Clothes(kode, nama, harga, stok));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterBtn) {
			new tableClothes(clothesData, connectDb);
			setVisible(false);
		}
	}
	
}
