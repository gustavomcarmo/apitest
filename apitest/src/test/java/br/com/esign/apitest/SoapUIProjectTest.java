package br.com.esign.apitest;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

@RunWith(Parameterized.class)
public class SoapUIProjectTest {

	/**
	 * Just to place the log files inside the target folder. More logging
	 * configuration changes can be done programmatically or directly in
	 * soapui-4.5.1.jar\com\eviware\soapui\resources\conf\soapui-log4j.xml.
	 */
	@BeforeClass
	public static void beforeClass() {
		System.setProperty("soapui.logroot", "target/");
	}

	/**
	 * Identify the files whose names are ended with soapui-project.xml inside
	 * the target/test-classes folder (the folder into which the SoapUI project
	 * files are copied, by Maven, from the resources folder). These files are
	 * used one by one by the JUnit test execution.
	 * 
	 * @return The SoapUI projects' filenames as parameters to the JUnit test
	 *         execution.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		final String pathname = "target/test-classes/";
		File resourcesDir = new File(pathname);
		String[] projectFiles = resourcesDir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith("soapui-project.xml");
			}
		});
		List<Object[]> data = new ArrayList<Object[]>(projectFiles.length);
		for (String projectFile : projectFiles) {
			data.add(new Object[] { pathname.concat(projectFile) });
		}
		return data;
	}

	/**
	 * The SoapUI project filename to be used as parameter in the JUnit test
	 * execution instance.
	 */
	@Parameter
	public String projectFile;

	/**
	 * Execute the test using the SoapUI project filename as parameter. The test
	 * is done by the SoapUI class <a href=
	 * "http://www.soapui.org/apidocs/com/eviware/soapui/tools/SoapUITestCaseRunner.html"
	 * >SoapUITestCaseRunner</a>. More detail can be set in order to get more
	 * control on the test execution.
	 * 
	 * @throws Exception If the test fails.
	 */
	@Test
	public void test() throws Exception {
		SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
		runner.setProjectFile(projectFile);
		runner.run();
	}

}