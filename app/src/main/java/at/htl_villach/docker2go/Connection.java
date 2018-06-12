package at.htl_villach.docker2go;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;


/**
 * Created by kroel on 23.04.2018.
 */

public class Connection extends SugarRecord {
    private String hostname;
    private String username;
    private String password;
    private Integer sshPort;
    private Integer timesConnected = 0;
    private String operatingSystem = "";
    private String hostKey;

    @Ignore
    private String serverHostKey;
    @Ignore
    private Integer connectionTimeout = 10000;

    @Ignore
    public interface onCommandStatusChangeListener {
        void onCommandFinished(Command command);
        void onAllCommandsFinished(CommandExecutionSummary ces);
    }

    public Connection() {}

    public Connection(String hostname, String username, String password, int sshPort) {
        this(hostname, username, password, sshPort, "");
    }

    public Connection(String hostname, String username, String password, int sshPort, String operatingSystem) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.sshPort = sshPort;
        this.operatingSystem = operatingSystem;
        this.timesConnected = 0;
    }

    @Override
    public String toString() {
        return username + "@" + hostname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSshPort() {
        return sshPort;
    }

    public void setSshPort(Integer sshPort) {
        this.sshPort = sshPort;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void increaseTimesConnected() {
        this.timesConnected++;
    }

    public Integer getTimesConnected() {
        return timesConnected;
    }

    public String getHostKey() {
        return hostKey;
    }

    public void setServerHostKey(String hostKey) {
        this.serverHostKey = hostKey;
    }

    public void storeServerHostKey(){
        this.hostKey = this.serverHostKey;
        this.save();
    }

    public void executeCommand(Connection.onCommandStatusChangeListener listener, Command... commands){
        AsyncTaskCommandExecutor commandExecutor = new AsyncTaskCommandExecutor(listener, this);
        commandExecutor.execute(commands);
    }

    public void checkHostKey(AlertDialog dialog, Resources resources){
        AsyncTaskCheckHostKey task = new AsyncTaskCheckHostKey(this, dialog, resources);
        task.execute();
    }
}
