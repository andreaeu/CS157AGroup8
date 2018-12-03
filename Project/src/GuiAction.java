import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuiAction {
	
	public List<ProductPojo> getProducts() {

		ArrayList<ProductPojo> ret = new ArrayList<>();
		DataBaseConnection.openConnection();

		ResultSet rs = DataBaseConnection.sqlStatement("SELECT * FROM product");

		try {
			while (rs.next()) {
				int model = rs.getInt("model");
				String brand = rs.getString("brand");
				String type = rs.getString("type");
				int views = rs.getInt("views");
				int bought = rs.getInt("bought");

				ret.add(new ProductPojo(brand, model, type, bought, views));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DataBaseConnection.closeConnection();
		return ret;

	}
	
	public List<SalePojo> getSales() {

		ArrayList<SalePojo> ret = new ArrayList<>();
		DataBaseConnection.openConnection();

		ResultSet rs = DataBaseConnection.sqlStatement("SELECT * FROM sales");

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int user = rs.getInt("user");
				int item = rs.getInt("item");
				int amount = rs.getInt("amount");

				ret.add(new SalePojo(id, user, item, amount));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DataBaseConnection.closeConnection();
		return ret;

	}
	
	public List<UserPojo> getUsers() {

		ArrayList<UserPojo> ret = new ArrayList<>();
		DataBaseConnection.openConnection();

		ResultSet rs = DataBaseConnection.sqlStatement("SELECT * FROM user");

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				float browseTime = rs.getFloat("browseTime");

				ret.add(new UserPojo(id, gender, age, browseTime));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DataBaseConnection.closeConnection();
		return ret;

	}

	public String getProductDetails(ProductPojo p) {

		String type = p.getType().toLowerCase();
		int model = p.getModel();

		DataBaseConnection.openConnection();

		PreparedStatement stmt = DataBaseConnection
				.createPreparedStatement("SELECT * FROM " + type + " WHERE model = ?");
		try {
			stmt.setInt(1, model);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {

				if (type.equals("guitar")) {
					String ret = "Price: " + res.getFloat("price") + "\n" + "Type: " + res.getString("type") + "\n"
							+ "Size: " + res.getString("size") + "\n";

					DataBaseConnection.closeConnection();
					return ret;
				}

				else if (type.equals("bass")) {
					String ret = "Price: " + res.getFloat("price") + "\n" + "Type: " + res.getString("type") + "\n";

					DataBaseConnection.closeConnection();
					return ret;
				}

				else if (type.equals("percussion")){

					String ret = "Price: " + res.getFloat("price") + "\n" + "Type: " + res.getString("type") + "\n"
							+ "Pieces: " + res.getString("pieces") + "\n";

					DataBaseConnection.closeConnection();
					return ret;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Error";
	}
	
	

}
