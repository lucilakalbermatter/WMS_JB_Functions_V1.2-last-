package main.java.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Repository
 * 
 * @author riteshp
 *
 */
public class PersonnelRepository {

	private static List<Person> PERSON_LIST = new ArrayList<Person>();

	/**
	 * Load item records from the stock.json file
	 */
	static {
		// System.out.println("Loading items");
		BufferedReader reader = null;
		try {
			PERSON_LIST.clear();

			reader = new BufferedReader(new FileReader("/Users/temporaryadmin/Documents/Java/WMS_JB_Functions_V1.2/src/main/resources/personnel.json"));
			Object data = JSONValue.parse(reader);
			if (data instanceof JSONArray) {
				JSONArray dataArray = (JSONArray) data;
				for (Object obj : dataArray) {
					if (obj instanceof JSONObject) {
						JSONObject jsonData = (JSONObject) obj;
						Person person = new Person();
						person.setUserName(jsonData.get("user_name").toString());
						person.setPassword(jsonData.get("password").toString());
						//person.setHeadOf((Person) jsonData.getOrDefault("head_of", null));
						PERSON_LIST.add(person);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Get All persons
	 *
	 * @return
	 */
	public static List<Person> getAllPersons() {
		return PERSON_LIST;
	}


	public static boolean isUserValid(String userName, String password) {
		List<Person> person = getAllPersons();

		for (Person persons : person) {
			if (userName.equals(persons.getUserName())) {
				if (password.equals(persons.getPassword())) {
					return true;
				}
			} else {
				System.out.println("No employee with that name and password found. /n Please change the name and password");
			} // incomplete
		}
		return false;

	}
}









