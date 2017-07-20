package at.echo.web;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Echo.class })
public class EchoTest {

	private Echo cut;
	private String prefix = "Pre--";
	private String postfix = "--Post";

	@Before
	public void before() {
		cut = new Echo();
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getenv(Echo.ENV_PREFIX)).thenReturn(prefix);
		PowerMockito.when(System.getenv(Echo.ENV_POSTFIX)).thenReturn(postfix);
	}

	public void testAppendPrePostfix() {
		String expected = "String";
		Assert.assertThat(cut.appendPrePostfix(expected), equalTo(expected));
	}

	@Test
	public void testAppendPrePostfixEnv() {
		String input = "ECHO";
		String expected = prefix + input + postfix;
		Assert.assertThat(cut.appendPrePostfix(input), equalTo(expected));
	}

	@Test
	public void testGetEnvNulll() {
		String expected = "";
		Assert.assertThat(cut.getEnv(""), equalTo(expected));
	}

	@Test
	public void testGetEnvlString() {
		String expected = postfix;
		Assert.assertThat(cut.getEnv(Echo.ENV_POSTFIX), equalTo(expected));
	}

}
