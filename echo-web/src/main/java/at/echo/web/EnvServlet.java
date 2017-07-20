package at.echo.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://127.0.0.1:8080/echo-web/evn
 */
@WebServlet("/env")
public class EnvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(EnvServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		Map<String, String[]> map = request.getParameterMap();

		String join = map.entrySet().stream().map(m -> m.getKey() + "=" + String.join(",", m.getValue()))
				.collect(Collectors.joining("\n"));

		builder.append("<p>");
		builder.append("Version 1:");
		builder.append("</p>");

		builder.append("<p>");
		builder.append("Parameters: " + join);

		builder.append("<br/><hr>");
		builder.append("<br/>");

		String[] envs = map.get("env");
		if (envs != null) {

			String envString = Arrays.stream(envs).map(s -> "ENV: " + s + "=" + System.getenv(s))
					.collect(Collectors.joining("<br/>"));
			builder.append("EnvString: " + envString);
			builder.append("<br/><hr>");
			builder.append("<br/>");
		}

		String allEnvs = System.getenv().entrySet().stream().map(m -> m.getKey() + "=" + String.join(",", m.getValue()))
				.collect(Collectors.joining("<br/>"));

		builder.append("EnvString: " + allEnvs);
		builder.append("<br/><hr>");
		builder.append("</p>");
		log.info(builder.toString());
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(builder.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

}
