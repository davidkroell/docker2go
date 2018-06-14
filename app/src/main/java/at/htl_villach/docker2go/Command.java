package at.htl_villach.docker2go;

/**
 * Created by kroel on 13.05.2018.
 */

public interface Command {

    /**
     * returns the string to be executed by the remote shell
     *
     * @deprecated use {@link #toString()} instead.
     */
    @Deprecated
    String parseString();

    // returns the expected exit code
    Integer getExpectedExitCode();

    // returns the real exit code
    Integer getExitCode();

    // set the real exit code
    void setExitCode(Integer exitCode);

    // returns the time in milliseconds to sleep between exit code check
    Integer getRefreshTimeOut();

    // returns the result of the command, if already set
    String getResult();

    // sets the result
    void setResult(String result);

    String getApiEndpoint();

    // returns true, if the command exited as expect, otherwise false
    // should be a alias for
    // getExpectedExitCode() == getExitCode()
    boolean exitedAsExpected();

    // returns the exception if occurred, if not, return null
    Exception getException();

    void setException(Exception exception);
}
