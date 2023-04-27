
package default;

import java.util.Vector;

import com.config.ConnectionDb;
import com.utility.Clothes;

public class Main {
	
	public Vector<Clothes> clothesData = new Vector<Clothes>();
	private ConnectionDB connectDb;
	
	public Main() {
		connectDb = new ConnectionDB();
		new Menu(clothesData, connectDb);
	}

	public static void main(String[] args) {
		new Main();
	}

}
