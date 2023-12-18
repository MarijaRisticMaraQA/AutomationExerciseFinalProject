package dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	@DataProvider(parallel = false,
			name = "loginProvider")
	public Object[][] loginProvider() {

		return new Object[][] {
				{ "", "" },
				{ "user.user", "" },
				{ "USER.USER@USER.COM", "" },
				{ ";@user.com", "" },
				{ "user@.com", "" },
				{ "1@1.1", "" },
				{ "@user@user", "" },
				{ "user.user@user.com", "" },
				{ "user.user@user.com", "1" },
				{ "user.user@user.com", "a" },
				{ "user.user@user.com", "password"}
		};
	}
}
