package fi.eis.applications.robot;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

@RobotKeywords
public class TestRobotKeywords {

    // this needs to be created when current keywords file is created so it inherits the same scope
    // -> re-created only whenever current file is created
    // important so it retains variables such as auth token
    private final ActualTester tester = new ActualTester();

    @RobotKeyword
    public boolean testRequest (
            String url, String basePath, String acceptHeader,
            Integer returnCode
            ) throws Exception {
        return testRequestVerifyResponse (
                url, basePath, acceptHeader,
                returnCode,
                null
                );
    }

    @RobotKeyword
    public boolean testRequestVerifyResponse (
            String url, String basePath, String acceptHeader,
            Integer returnCode, String responseMessage
            ) throws Exception {
        tester.setBaseURI(url);
        tester.setAcceptHeader(acceptHeader);
        tester.setBasePath(basePath);
        tester.authenticationTest(returnCode, responseMessage);
        return true;
    }
}
