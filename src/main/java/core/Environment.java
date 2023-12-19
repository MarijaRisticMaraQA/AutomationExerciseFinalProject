package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.Constants;

import static utils.Utils.dotEnv;

public class Environment {

	private String homeUrl;
	private WebDriver driver;

	private static final Logger log = LogManager.getLogger(Environment.class.getName());

	public Environment(WebDriver driver) {

		this.driver = driver;
	}

	public void goToEnvironment() {

		String env = dotEnv().get("ENV");

		if (env.equalsIgnoreCase("dev")) {
			homeUrl = Constants.DEV_URL;
		} else {
			homeUrl = Constants.QA_URL;
		}
		log.info("Opening browser: " + dotEnv().get("BROWSER") + " navigating to " + homeUrl);
		driver.get(homeUrl);
	}
}
