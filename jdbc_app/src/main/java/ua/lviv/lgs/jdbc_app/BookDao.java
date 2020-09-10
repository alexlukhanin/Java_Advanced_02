package ua.lviv.lgs.jdbc_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private static String READ_ALL = "select * from book";
	private static String CREATE_NEW_ROW = "insert into book(`name`, `isbn`, `genre_id`) values (?,?,?)";
	private static String READ_BY_ID = "select * from book where id =?";
	private static String UPDATE_BY_ID = "update book set name=?, isbn=?, genre_id=? where id = ?";
	private static String DELETE_BY_ID = "delete from book where id=?";

	private Connection connection;

	public BookDao(Connection connection) {
		this.connection = connection;
	}

	public List<Book> readAll() {
		List<Book> listOfBooks = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				listOfBooks.add(BookMapper.map(result));
			}
			return listOfBooks;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public void insert(Book book) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_ROW)) {
			// Try With Resources give opportunity automatically close connection
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getIsbn());
			preparedStatement.setInt(3, book.getGenre_id());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Book read(int id) throws SQLException {
		ResultSet result = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			result.next();
			return BookMapper.map(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(Book book, int id) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getIsbn());
			preparedStatement.setInt(3, book.getGenre_id());
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
