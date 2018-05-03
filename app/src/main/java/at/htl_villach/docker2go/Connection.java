package at.htl_villach.docker2go;

import com.orm.SugarRecord;
/**
 * Created by kroel on 23.04.2018.
 */

public class Connection extends SugarRecord {
    private String hostname;
    private String username;
    private String password;
    private Integer sshPort;
    private Integer timesConnected;

    public Connection() {}

    public Connection(String hostname, String username, String password, int sshPort) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.sshPort = sshPort;
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
}
