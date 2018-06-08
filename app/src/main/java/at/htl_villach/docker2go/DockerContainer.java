package at.htl_villach.docker2go;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DockerContainer implements DockerObj {

    private String Id;
    private String Image;
    private String ImageID;
    private String Command;
    private int Created;
    private LabelsBean Labels;
    private String State;
    private String Status;
    private HostConfigBean HostConfig;
    private NetworkSettingsBean NetworkSettings;
    private List<String> Names;
    private List<PortsBean> Ports;
    private List<MountsBean> Mounts;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String ImageID) {
        this.ImageID = ImageID;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String Command) {
        this.Command = Command;
    }

    public int getCreated() {
        return Created;
    }

    public void setCreated(int Created) {
        this.Created = Created;
    }

    public LabelsBean getLabels() {
        return Labels;
    }

    public void setLabels(LabelsBean Labels) {
        this.Labels = Labels;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public HostConfigBean getHostConfig() {
        return HostConfig;
    }

    public void setHostConfig(HostConfigBean HostConfig) {
        this.HostConfig = HostConfig;
    }

    public NetworkSettingsBean getNetworkSettings() {
        return NetworkSettings;
    }

    public void setNetworkSettings(NetworkSettingsBean NetworkSettings) {
        this.NetworkSettings = NetworkSettings;
    }

    public List<String> getNames() {
        return Names;
    }

    public void setNames(List<String> Names) {
        this.Names = Names;
    }

    public List<PortsBean> getPorts() {
        return Ports;
    }

    public void setPorts(List<PortsBean> Ports) {
        this.Ports = Ports;
    }

    public List<MountsBean> getMounts() {
        return Mounts;
    }

    public void setMounts(List<MountsBean> Mounts) {
        this.Mounts = Mounts;
    }

    public static class LabelsBean {
        @SerializedName("com.docker.compose.config-hash")
        private String _$ComDockerComposeConfighash65; // FIXME check this code
        @SerializedName("com.docker.compose.container-number")
        private String _$ComDockerComposeContainernumber137; // FIXME check this code
        @SerializedName("com.docker.compose.oneoff")
        private String _$ComDockerComposeOneoff107; // FIXME check this code
        @SerializedName("com.docker.compose.project")
        private String _$ComDockerComposeProject295; // FIXME check this code
        @SerializedName("com.docker.compose.service")
        private String _$ComDockerComposeService12; // FIXME check this code
        @SerializedName("com.docker.compose.version")
        private String _$ComDockerComposeVersion128; // FIXME check this code
        @SerializedName("org.label-schema.vcs-url")
        private String _$OrgLabelschemaVcsurl37; // FIXME check this code

        public String get_$ComDockerComposeConfighash65() {
            return _$ComDockerComposeConfighash65;
        }

        public void set_$ComDockerComposeConfighash65(String _$ComDockerComposeConfighash65) {
            this._$ComDockerComposeConfighash65 = _$ComDockerComposeConfighash65;
        }

        public String get_$ComDockerComposeContainernumber137() {
            return _$ComDockerComposeContainernumber137;
        }

        public void set_$ComDockerComposeContainernumber137(String _$ComDockerComposeContainernumber137) {
            this._$ComDockerComposeContainernumber137 = _$ComDockerComposeContainernumber137;
        }

        public String get_$ComDockerComposeOneoff107() {
            return _$ComDockerComposeOneoff107;
        }

        public void set_$ComDockerComposeOneoff107(String _$ComDockerComposeOneoff107) {
            this._$ComDockerComposeOneoff107 = _$ComDockerComposeOneoff107;
        }

        public String get_$ComDockerComposeProject295() {
            return _$ComDockerComposeProject295;
        }

        public void set_$ComDockerComposeProject295(String _$ComDockerComposeProject295) {
            this._$ComDockerComposeProject295 = _$ComDockerComposeProject295;
        }

        public String get_$ComDockerComposeService12() {
            return _$ComDockerComposeService12;
        }

        public void set_$ComDockerComposeService12(String _$ComDockerComposeService12) {
            this._$ComDockerComposeService12 = _$ComDockerComposeService12;
        }

        public String get_$ComDockerComposeVersion128() {
            return _$ComDockerComposeVersion128;
        }

        public void set_$ComDockerComposeVersion128(String _$ComDockerComposeVersion128) {
            this._$ComDockerComposeVersion128 = _$ComDockerComposeVersion128;
        }

        public String get_$OrgLabelschemaVcsurl37() {
            return _$OrgLabelschemaVcsurl37;
        }

        public void set_$OrgLabelschemaVcsurl37(String _$OrgLabelschemaVcsurl37) {
            this._$OrgLabelschemaVcsurl37 = _$OrgLabelschemaVcsurl37;
        }
    }

    public static class HostConfigBean {
        /**
         * NetworkMode : bridge
         */

        private String NetworkMode;

        public String getNetworkMode() {
            return NetworkMode;
        }

        public void setNetworkMode(String NetworkMode) {
            this.NetworkMode = NetworkMode;
        }
    }

    public static class NetworkSettingsBean {
        /**
         * Networks : {"bridge":{"IPAMConfig":null,"Links":null,"Aliases":null,"NetworkID":"a025278a9c83a40b7bd8a18b2267b8e07913b8b37f5946c9bd0b4ef3fc27ca85","EndpointID":"62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2","Gateway":"172.17.0.1","IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"MacAddress":"02:42:ac:11:00:02","DriverOpts":null}}
         */

        private NetworksBean Networks;

        public NetworksBean getNetworks() {
            return Networks;
        }

        public void setNetworks(NetworksBean Networks) {
            this.Networks = Networks;
        }

        public static class NetworksBean {
            /**
             * bridge : {"IPAMConfig":null,"Links":null,"Aliases":null,"NetworkID":"a025278a9c83a40b7bd8a18b2267b8e07913b8b37f5946c9bd0b4ef3fc27ca85","EndpointID":"62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2","Gateway":"172.17.0.1","IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"MacAddress":"02:42:ac:11:00:02","DriverOpts":null}
             */

            private BridgeBean bridge;

            public BridgeBean getBridge() {
                return bridge;
            }

            public void setBridge(BridgeBean bridge) {
                this.bridge = bridge;
            }

            public static class BridgeBean {
                /**
                 * IPAMConfig : null
                 * Links : null
                 * Aliases : null
                 * NetworkID : a025278a9c83a40b7bd8a18b2267b8e07913b8b37f5946c9bd0b4ef3fc27ca85
                 * EndpointID : 62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2
                 * Gateway : 172.17.0.1
                 * IPAddress : 172.17.0.2
                 * IPPrefixLen : 16
                 * IPv6Gateway :
                 * GlobalIPv6Address :
                 * GlobalIPv6PrefixLen : 0
                 * MacAddress : 02:42:ac:11:00:02
                 * DriverOpts : null
                 */

                private Object IPAMConfig;
                private Object Links;
                private Object Aliases;
                private String NetworkID;
                private String EndpointID;
                private String Gateway;
                private String IPAddress;
                private int IPPrefixLen;
                private String IPv6Gateway;
                private String GlobalIPv6Address;
                private int GlobalIPv6PrefixLen;
                private String MacAddress;
                private Object DriverOpts;

                public Object getIPAMConfig() {
                    return IPAMConfig;
                }

                public void setIPAMConfig(Object IPAMConfig) {
                    this.IPAMConfig = IPAMConfig;
                }

                public Object getLinks() {
                    return Links;
                }

                public void setLinks(Object Links) {
                    this.Links = Links;
                }

                public Object getAliases() {
                    return Aliases;
                }

                public void setAliases(Object Aliases) {
                    this.Aliases = Aliases;
                }

                public String getNetworkID() {
                    return NetworkID;
                }

                public void setNetworkID(String NetworkID) {
                    this.NetworkID = NetworkID;
                }

                public String getEndpointID() {
                    return EndpointID;
                }

                public void setEndpointID(String EndpointID) {
                    this.EndpointID = EndpointID;
                }

                public String getGateway() {
                    return Gateway;
                }

                public void setGateway(String Gateway) {
                    this.Gateway = Gateway;
                }

                public String getIPAddress() {
                    return IPAddress;
                }

                public void setIPAddress(String IPAddress) {
                    this.IPAddress = IPAddress;
                }

                public int getIPPrefixLen() {
                    return IPPrefixLen;
                }

                public void setIPPrefixLen(int IPPrefixLen) {
                    this.IPPrefixLen = IPPrefixLen;
                }

                public String getIPv6Gateway() {
                    return IPv6Gateway;
                }

                public void setIPv6Gateway(String IPv6Gateway) {
                    this.IPv6Gateway = IPv6Gateway;
                }

                public String getGlobalIPv6Address() {
                    return GlobalIPv6Address;
                }

                public void setGlobalIPv6Address(String GlobalIPv6Address) {
                    this.GlobalIPv6Address = GlobalIPv6Address;
                }

                public int getGlobalIPv6PrefixLen() {
                    return GlobalIPv6PrefixLen;
                }

                public void setGlobalIPv6PrefixLen(int GlobalIPv6PrefixLen) {
                    this.GlobalIPv6PrefixLen = GlobalIPv6PrefixLen;
                }

                public String getMacAddress() {
                    return MacAddress;
                }

                public void setMacAddress(String MacAddress) {
                    this.MacAddress = MacAddress;
                }

                public Object getDriverOpts() {
                    return DriverOpts;
                }

                public void setDriverOpts(Object DriverOpts) {
                    this.DriverOpts = DriverOpts;
                }
            }
        }
    }

    public static class PortsBean {
        /**
         * IP : 127.0.0.1
         * PrivatePort : 3000
         * PublicPort : 32001
         * Type : tcp
         */

        private String IP;
        private int PrivatePort;
        private int PublicPort;
        private String Type;

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public int getPrivatePort() {
            return PrivatePort;
        }

        public void setPrivatePort(int PrivatePort) {
            this.PrivatePort = PrivatePort;
        }

        public int getPublicPort() {
            return PublicPort;
        }

        public void setPublicPort(int PublicPort) {
            this.PublicPort = PublicPort;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }
    }

    public static class MountsBean {
        /**
         * Type : bind
         * Source : /home/kroelld/keylog.conf.js
         * Destination : /usr/src/app/config.js
         * Mode : ro
         * RW : false
         * Propagation : rprivate
         */

        private String Type;
        private String Source;
        private String Destination;
        private String Mode;
        private boolean RW;
        private String Propagation;

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public String getDestination() {
            return Destination;
        }

        public void setDestination(String Destination) {
            this.Destination = Destination;
        }

        public String getMode() {
            return Mode;
        }

        public void setMode(String Mode) {
            this.Mode = Mode;
        }

        public boolean isRW() {
            return RW;
        }

        public void setRW(boolean RW) {
            this.RW = RW;
        }

        public String getPropagation() {
            return Propagation;
        }

        public void setPropagation(String Propagation) {
            this.Propagation = Propagation;
        }
    }
}
