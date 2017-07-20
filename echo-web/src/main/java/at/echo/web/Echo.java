package at.echo.web;

import javax.ejb.Stateless;

@Stateless
public class Echo {

	public static final String ENV_PREFIX = "ENV_PREFIX";
	public static final String ENV_POSTFIX = "ENV_POSTFIX";

	public String echo(String string) {
		return appendPrePostfix(string);
	}

	protected String appendPrePostfix(String string) {
		return getEnv(ENV_PREFIX) + string + getEnv(ENV_POSTFIX);
	}

	protected String getEnv(String env) {
		String envVal = System.getenv(env);
		envVal = envVal == null ? "" : envVal;
		return envVal;
	}

}
