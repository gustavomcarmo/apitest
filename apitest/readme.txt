# apitest
API Test using SoapUI 4.5 + Maven 3 + JUnit 4

The apitest project aims to automate the test of APIs. Several SoapUI projects, each one
containing at least one TestCase, can take part on a single JUnit test execution. Just
put the SoapUI project files, ending with soapui-project.xml, in the test/resources
folder of the Eclipse Maven project, which can runs either as "Maven test" or as "JUnit
Test".

Be sure your Eclipse IDE has installed a Maven plugin, such as m2e, and a Git plugin,
such as EGit, before cloning the apitest GitHub repository. Pay also attention if you
need to configure a proxy in the Maven settings.xml file. Once created the Maven project,
the Eclipse IDE will download all the dependencies defined in the Maven project pom.xml
file.

The test execution output is shown in the Eclipse Console View, as well as generated
in the log files soapui.log and soapui-errors.log, placed in the target folder. More
control over logging can be gotten either programmatically or changing the SoapUI Log4J
configuration file soapui-4.5.1.jar\com\eviware\soapui\resources\conf\soapui-log4j.xml.

SoapUI 5 projects can also be used in the test, but their XML files must be modified.
Replace all the occurrences of the <con:authType> tag value by "Global HTTP Settings".