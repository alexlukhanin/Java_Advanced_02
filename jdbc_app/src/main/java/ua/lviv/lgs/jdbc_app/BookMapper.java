package ua.lviv.lgs.jdbc_app;

import java.sql.ResultSet;
import java.sql.SQLException;



public class BookMapper {
	
	public static Book map(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		int genre_id = result.getInt("genre_id");
		String name = result.getString("name");
		String isbn = result.getString("isbn");
		return new Book(id, name, isbn, genre_id);
	}
	

}
