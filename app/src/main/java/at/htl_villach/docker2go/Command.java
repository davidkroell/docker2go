package at.htl_villach.docker2go;

/**
 * Created by kroel on 13.05.2018.
 */

public interface Command {
    // returns the string to be executed by the remote shell
    String parseString();
    Integer getExpectedExitCode();
    Integer getRefreshTimeOut();
}
