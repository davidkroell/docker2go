package at.htl_villach.docker2go;

/**
 * Created by kroel on 14.05.2018.
 */

public class DockerCommandBuilder implements Command {

    // booleans
    private boolean useSudo = false;

    // strings
    private String baseCommand = "curl";

    private String baseEndpoint = "--unix-socket /var/run/docker.sock";

    private String apiEndpointPrefix = "http::";

    private String apiEndpoint;

    private String requestMethodCommandPrefix = "-X";

    private String requestMethod = "GET";

    private String result;

    // integers
    private Integer expectedExitCode = 0;

    private Integer exitCode = null;

    private Integer refreshTimeOut = 80; // milliseconds


    private Exception exception;

    @Override
    public Integer getExpectedExitCode() {
        return expectedExitCode;
    }

    @Override
    public Integer getRefreshTimeOut() {
        return refreshTimeOut;
    }

    // fluent and chainable methods
    public DockerCommandBuilder useSudo() {
        this.useSudo = true;
        return this;
    }

    public DockerCommandBuilder apiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        return this;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public DockerCommandBuilder requestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }


    @Override
    public String parseString() {
        return this.toString();
    }

    // outputs the string, which is sent to the docker api
    // "curl --unix-socket /var/run/docker.sock http::/containers/json"
    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder();

        if (useSudo) strBuilder.append("sudo");

        strBuilder.append(baseCommand).append(" ")
                .append(baseEndpoint).append(" ")
                .append(apiEndpointPrefix)
                .append(apiEndpoint).append(" ")
                .append(requestMethodCommandPrefix).append(" ")
                .append(requestMethod);

        return strBuilder.toString();
    }

    @Override
    public String getResult() {
        return this.result;
    }

    @Override
    public void setResult(String value) {
        this.result = value;
    }

    @Override
    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }

    @Override
    public Integer getExitCode() {
        return exitCode;
    }

    @Override
    public boolean exitedAsExpected() {
        return getExpectedExitCode().equals(getExitCode());
    }

    @Override
    public Exception getException() {
        return this.exception;
    }

    @Override
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
