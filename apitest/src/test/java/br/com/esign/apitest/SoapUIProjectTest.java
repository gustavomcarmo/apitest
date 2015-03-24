package br.com.esign.apitest;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

@RunWith(Parameterized.class)
public class SoapUIProjectTest {
	
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
    		data.add(new Object[] {pathname.concat(projectFile)});
    	}
    	return data;
    }
    
    @Parameter
    public String projectFile;
	
	@Test
	public void test() throws Exception {
		SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
		runner.setProjectFile(projectFile);
		runner.run();
	}
	
}