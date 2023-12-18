package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.LoginUserModel;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {

	public static List<LoginUserModel> getDataFromJson() {

		Reader reader = null;

		try {
			reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir") + "/src/test/resources/user.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Gson().fromJson(reader, new TypeToken<List<LoginUserModel>>(){}.getType());
	}
}
