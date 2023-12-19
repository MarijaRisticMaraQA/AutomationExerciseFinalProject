package core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static core.OptionsManager.getChromeOptions;
import static core.OptionsManager.getEdgeOptions;
import static core.OptionsManager.getFirefoxOptions;
import static utils.Utils.dotEnv;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverManager {

	private static String browser = dotEnv().get("BROWSER");
	private static final DriverManager instance = new DriverManager();

	public static DriverManager getInstance() {

		return instance;
	}

	public WebDriver setDriver() {

		if (browser.equalsIgnoreCase("chrome")) {
			return new ChromeDriver(getChromeOptions());
		} else if (browser.equalsIgnoreCase("firefox")) {
			return new FirefoxDriver(getFirefoxOptions());
		} else if (browser.equalsIgnoreCase("edge")) {
			return new EdgeDriver(getEdgeOptions());
		}

		return null;
	}
}