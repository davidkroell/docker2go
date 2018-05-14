package at.htl_villach.docker2go;

/**
 * Created by kroel on 14.05.2018.
 */

public class DockerCommandBuilder implements Command {

    // the fields below should build up in something like:
    // "curl --unix-socket /var/run/docker.sock http::/containers/json"
    private boolean useSudo = false;
    private String baseCommand = "curl";
    private String baseEndpoint = "--unix-socket /var/run/docker.sock";
    private String apiEndpointPrefix = "http::";
    private String apiEndpoint;
    private String requestMethodCommandPrefix = "-X";
    private String requestMethod = "GET";


    private Integer expectedExitCode = 0;
    private Integer refreshTimeOut = 80; // milliseconds

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

    public DockerCommandBuilder requestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    @Override
    public String parseString() {
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
}
