package ua.lviv.lgs.jdbc_app;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {

	public static void main(String[] args) {

		try (Connection openConnection = ConnectionUtils.openConnection()) {
			/*==================================Book block==========================================*/
			BookDao bookDao = new BookDao(openConnection);
			bookDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");
			// Book Insert new row
			bookDao.insert(new Book("Last Castle", "200500701", 1));
			bookDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");
			// Book Read by ID
			Book bookFromDB = bookDao.read(10);
			System.out.println(bookFromDB.toString());
			System.out.println("============================================================");
			// Book update
			Book bookNew = new Book("Victory in the Hell", "1256565666", 2);
			bookDao.update(bookNew, 11);
			bookDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");

			// Book Delete
			bookDao.delete(1);
			bookDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");
			
			
			/*==================================Genre block==========================================*/
			
			GenreDao genreDao = new GenreDao(openConnection);
			genreDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");
			// Genre Insert new row
			genreDao.insert(new Genre("Mistic story"));
			genreDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");
			// Genre Read by ID
			Genre genreFromDB = genreDao.read(3);
			System.out.println(genreFromDB.toString());
			System.out.println("============================================================");
			// Genre update
			Genre genreNew = new Genre("Sarcasm stories");
			genreDao.update(genreNew, 4);
			genreDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");

			// Genre Delete
			genreDao.delete(8);
			genreDao.readAll().forEach(System.out::println);
			System.out.println("============================================================");
			

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
