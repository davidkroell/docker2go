package at.htl_villach.docker2go;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class DockerContainerDetail {

    /**
     * Id : 97d7298d3004fcada1df72c6c91326de8084bde0a644fef7f25bc372d9da981c
     * Created : 2018-05-16T17:23:56.78076987Z
     * Path : /usr/bin/env
     * Args : ["node","/usr/src/app/bin/www"]
     * State : {"Status":"running","Running":true,"Paused":false,"Restarting":false,"OOMKilled":false,"Dead":false,"Pid":22453,"ExitCode":0,"Error":"","StartedAt":"2018-05-16T17:41:08.644289589Z","FinishedAt":"2018-05-16T17:41:04.428559341Z"}
     * Image : sha256:a4cf9fdbb3ced6716ca6c943f4b53077209775ec888fd9247a7b2535e070a739
     * ResolvConfPath : /var/lib/docker/containers/97d7298d3004fcada1df72c6c91326de8084bde0a644fef7f25bc372d9da981c/resolv.conf
     * HostnamePath : /var/lib/docker/containers/97d7298d3004fcada1df72c6c91326de8084bde0a644fef7f25bc372d9da981c/hostname
     * HostsPath : /var/lib/docker/containers/97d7298d3004fcada1df72c6c91326de8084bde0a644fef7f25bc372d9da981c/hosts
     * LogPath : /var/lib/docker/containers/97d7298d3004fcada1df72c6c91326de8084bde0a644fef7f25bc372d9da981c/97d7298d3004fcada1df72c6c91326de8084bde0a644fef7f25bc372d9da981c-json.log
     * Name : /keylog.rest-api
     * RestartCount : 0
     * Driver : overlay2
     * Platform : linux
     * MountLabel :
     * ProcessLabel :
     * AppArmorProfile :
     * ExecIDs : null
     * HostConfig : {"ContainerIDFile":"","LogConfig":{"Type":"json-file","Config":{}},"NetworkMode":"bridge","RestartPolicy":{"Name":"always","MaximumRetryCount":0},"AutoRemove":false,"VolumeDriver":"","VolumesFrom":[],"CapAdd":null,"CapDrop":null,"Dns":null,"DnsOptions":null,"DnsSearch":null,"ExtraHosts":null,"GroupAdd":null,"IpcMode":"shareable","Cgroup":"","Links":null,"OomScoreAdj":0,"PidMode":"","Privileged":false,"PublishAllPorts":false,"ReadonlyRootfs":false,"SecurityOpt":null,"UTSMode":"","UsernsMode":"","ShmSize":67108864,"Runtime":"runc","ConsoleSize":[0,0],"Isolation":"","CpuShares":0,"Memory":0,"NanoCpus":0,"CgroupParent":"","BlkioWeight":0,"BlkioWeightDevice":null,"BlkioDeviceReadBps":null,"BlkioDeviceWriteBps":null,"BlkioDeviceReadIOps":null,"BlkioDeviceWriteIOps":null,"CpuPeriod":0,"CpuQuota":0,"CpuRealtimePeriod":0,"CpuRealtimeRuntime":0,"CpusetCpus":"","CpusetMems":"","Devices":null,"DeviceCgroupRules":null,"DiskQuota":0,"KernelMemory":0,"MemoryReservation":0,"MemorySwap":0,"MemorySwappiness":null,"OomKillDisable":false,"PidsLimit":0,"Ulimits":null,"CpuCount":0,"CpuPercent":0,"IOMaximumIOps":0,"IOMaximumBandwidth":0}
     * Mounts : [{"Type":"bind","Source":"/home/kroelld/keylog.conf.js","Destination":"/usr/src/app/config.js","Mode":"ro","RW":false,"Propagation":"rprivate"}]
     * Config : {"Hostname":"97d7298d3004","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["NODE_ENV=production","PROXY_HEADER_REAL_IP_KEY=X-Forwarded-For","PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin","NODE_VERSION=6.13.1","YARN_VERSION=1.5.1"],"Cmd":null,"ArgsEscaped":true,"Image":"davidkroell/keylog.rest","WorkingDir":"/usr/src/app","Entrypoint":["/usr/bin/env","node","/usr/src/app/bin/www"],"OnBuild":null}
     * NetworkSettings : {"Bridge":"","SandboxID":"1e70915ecc1de33caa3f37b63bc2ac84a3330a3ecc04910100f5b6baeb6103aa","HairpinMode":false,"LinkLocalIPv6Address":"","LinkLocalIPv6PrefixLen":0,"SandboxKey":"/var/run/docker/netns/1e70915ecc1d","SecondaryIPAddresses":null,"SecondaryIPv6Addresses":null,"EndpointID":"62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2","Gateway":"172.17.0.1","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","MacAddress":"02:42:ac:11:00:02","Networks":{"bridge":{"IPAMConfig":null,"Links":null,"Aliases":null,"NetworkID":"a025278a9c83a40b7bd8a18b2267b8e07913b8b37f5946c9bd0b4ef3fc27ca85","EndpointID":"62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2","Gateway":"172.17.0.1","IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"MacAddress":"02:42:ac:11:00:02","DriverOpts":null}}}
     */

    private String Id;
    private String Created;
    private String Path;
    private StateBean State;
    private String Image;
    private String ResolvConfPath;
    private String HostnamePath;
    private String HostsPath;
    private String LogPath;
    private String Name;
    private int RestartCount;
    private String Driver;
    private String Platform;
    private String MountLabel;
    private String ProcessLabel;
    private String AppArmorProfile;
    private Object ExecIDs;
    private HostConfigBean HostConfig;
    private ConfigBeanX Config;
    private NetworkSettingsBean NetworkSettings;
    private List<String> Args;
    private List<MountsBean> Mounts;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public StateBean getState() {
        return State;
    }

    public void setState(StateBean State) {
        this.State = State;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getResolvConfPath() {
        return ResolvConfPath;
    }

    public void setResolvConfPath(String ResolvConfPath) {
        this.ResolvConfPath = ResolvConfPath;
    }

    public String getHostnamePath() {
        return HostnamePath;
    }

    public void setHostnamePath(String HostnamePath) {
        this.HostnamePath = HostnamePath;
    }

    public String getHostsPath() {
        return HostsPath;
    }

    public void setHostsPath(String HostsPath) {
        this.HostsPath = HostsPath;
    }

    public String getLogPath() {
        return LogPath;
    }

    public void setLogPath(String LogPath) {
        this.LogPath = LogPath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getRestartCount() {
        return RestartCount;
    }

    public void setRestartCount(int RestartCount) {
        this.RestartCount = RestartCount;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String Driver) {
        this.Driver = Driver;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String Platform) {
        this.Platform = Platform;
    }

    public String getMountLabel() {
        return MountLabel;
    }

    public void setMountLabel(String MountLabel) {
        this.MountLabel = MountLabel;
    }

    public String getProcessLabel() {
        return ProcessLabel;
    }

    public void setProcessLabel(String ProcessLabel) {
        this.ProcessLabel = ProcessLabel;
    }

    public String getAppArmorProfile() {
        return AppArmorProfile;
    }

    public void setAppArmorProfile(String AppArmorProfile) {
        this.AppArmorProfile = AppArmorProfile;
    }

    public Object getExecIDs() {
        return ExecIDs;
    }

    public void setExecIDs(Object ExecIDs) {
        this.ExecIDs = ExecIDs;
    }

    public HostConfigBean getHostConfig() {
        return HostConfig;
    }

    public void setHostConfig(HostConfigBean HostConfig) {
        this.HostConfig = HostConfig;
    }

    public ConfigBeanX getConfig() {
        return Config;
    }

    public void setConfig(ConfigBeanX Config) {
        this.Config = Config;
    }

    public NetworkSettingsBean getNetworkSettings() {
        return NetworkSettings;
    }

    public void setNetworkSettings(NetworkSettingsBean NetworkSettings) {
        this.NetworkSettings = NetworkSettings;
    }

    public List<String> getArgs() {
        return Args;
    }

    public void setArgs(List<String> Args) {
        this.Args = Args;
    }

    public List<MountsBean> getMounts() {
        return Mounts;
    }

    public void setMounts(List<MountsBean> Mounts) {
        this.Mounts = Mounts;
    }

    public static class StateBean {
        /**
         * Status : running
         * Running : true
         * Paused : false
         * Restarting : false
         * OOMKilled : false
         * Dead : false
         * Pid : 22453
         * ExitCode : 0
         * Error :
         * StartedAt : 2018-05-16T17:41:08.644289589Z
         * FinishedAt : 2018-05-16T17:41:04.428559341Z
         */

        private String Status;
        private boolean Running;
        private boolean Paused;
        private boolean Restarting;
        private boolean OOMKilled;
        private boolean Dead;
        private int Pid;
        private int ExitCode;
        private String Error;
        private String StartedAt;
        private String FinishedAt;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public boolean isRunning() {
            return Running;
        }

        public void setRunning(boolean Running) {
            this.Running = Running;
        }

        public boolean isPaused() {
            return Paused;
        }

        public void setPaused(boolean Paused) {
            this.Paused = Paused;
        }

        public boolean isRestarting() {
            return Restarting;
        }

        public void setRestarting(boolean Restarting) {
            this.Restarting = Restarting;
        }

        public boolean isOOMKilled() {
            return OOMKilled;
        }

        public void setOOMKilled(boolean OOMKilled) {
            this.OOMKilled = OOMKilled;
        }

        public boolean isDead() {
            return Dead;
        }

        public void setDead(boolean Dead) {
            this.Dead = Dead;
        }

        public int getPid() {
            return Pid;
        }

        public void setPid(int Pid) {
            this.Pid = Pid;
        }

        public int getExitCode() {
            return ExitCode;
        }

        public void setExitCode(int ExitCode) {
            this.ExitCode = ExitCode;
        }

        public String getError() {
            return Error;
        }

        public void setError(String Error) {
            this.Error = Error;
        }

        public String getStartedAt() {
            return StartedAt;
        }

        public void setStartedAt(String StartedAt) {
            this.StartedAt = StartedAt;
        }

        public String getFinishedAt() {
            return FinishedAt;
        }

        public void setFinishedAt(String FinishedAt) {
            this.FinishedAt = FinishedAt;
        }
    }

    public static class HostConfigBean {
        /**
         * ContainerIDFile :
         * LogConfig : {"Type":"json-file","Config":{}}
         * NetworkMode : bridge
         * RestartPolicy : {"Name":"always","MaximumRetryCount":0}
         * AutoRemove : false
         * VolumeDriver :
         * VolumesFrom : []
         * CapAdd : null
         * CapDrop : null
         * Dns : null
         * DnsOptions : null
         * DnsSearch : null
         * ExtraHosts : null
         * GroupAdd : null
         * IpcMode : shareable
         * Cgroup :
         * Links : null
         * OomScoreAdj : 0
         * PidMode :
         * Privileged : false
         * PublishAllPorts : false
         * ReadonlyRootfs : false
         * SecurityOpt : null
         * UTSMode :
         * UsernsMode :
         * ShmSize : 67108864
         * Runtime : runc
         * ConsoleSize : [0,0]
         * Isolation :
         * CpuShares : 0
         * Memory : 0
         * NanoCpus : 0
         * CgroupParent :
         * BlkioWeight : 0
         * BlkioWeightDevice : null
         * BlkioDeviceReadBps : null
         * BlkioDeviceWriteBps : null
         * BlkioDeviceReadIOps : null
         * BlkioDeviceWriteIOps : null
         * CpuPeriod : 0
         * CpuQuota : 0
         * CpuRealtimePeriod : 0
         * CpuRealtimeRuntime : 0
         * CpusetCpus :
         * CpusetMems :
         * Devices : null
         * DeviceCgroupRules : null
         * DiskQuota : 0
         * KernelMemory : 0
         * MemoryReservation : 0
         * MemorySwap : 0
         * MemorySwappiness : null
         * OomKillDisable : false
         * PidsLimit : 0
         * Ulimits : null
         * CpuCount : 0
         * CpuPercent : 0
         * IOMaximumIOps : 0
         * IOMaximumBandwidth : 0
         */

        private String ContainerIDFile;
        private LogConfigBean LogConfig;
        private String NetworkMode;
        private RestartPolicyBean RestartPolicy;
        private boolean AutoRemove;
        private String VolumeDriver;
        private Object CapAdd;
        private Object CapDrop;
        private Object Dns;
        private Object DnsOptions;
        private Object DnsSearch;
        private Object ExtraHosts;
        private Object GroupAdd;
        private String IpcMode;
        private String Cgroup;
        private Object Links;
        private int OomScoreAdj;
        private String PidMode;
        private boolean Privileged;
        private boolean PublishAllPorts;
        private boolean ReadonlyRootfs;
        private Object SecurityOpt;
        private String UTSMode;
        private String UsernsMode;
        private int ShmSize;
        private String Runtime;
        private String Isolation;
        private int CpuShares;
        private int Memory;
        private int NanoCpus;
        private String CgroupParent;
        private int BlkioWeight;
        private Object BlkioWeightDevice;
        private Object BlkioDeviceReadBps;
        private Object BlkioDeviceWriteBps;
        private Object BlkioDeviceReadIOps;
        private Object BlkioDeviceWriteIOps;
        private int CpuPeriod;
        private int CpuQuota;
        private int CpuRealtimePeriod;
        private int CpuRealtimeRuntime;
        private String CpusetCpus;
        private String CpusetMems;
        private Object Devices;
        private Object DeviceCgroupRules;
        private int DiskQuota;
        private int KernelMemory;
        private int MemoryReservation;
        private int MemorySwap;
        private Object MemorySwappiness;
        private boolean OomKillDisable;
        private int PidsLimit;
        private Object Ulimits;
        private int CpuCount;
        private int CpuPercent;
        private int IOMaximumIOps;
        private int IOMaximumBandwidth;
        private List<?> VolumesFrom;
        private List<Integer> ConsoleSize;

        public String getContainerIDFile() {
            return ContainerIDFile;
        }

        public void setContainerIDFile(String ContainerIDFile) {
            this.ContainerIDFile = ContainerIDFile;
        }

        public LogConfigBean getLogConfig() {
            return LogConfig;
        }

        public void setLogConfig(LogConfigBean LogConfig) {
            this.LogConfig = LogConfig;
        }

        public String getNetworkMode() {
            return NetworkMode;
        }

        public void setNetworkMode(String NetworkMode) {
            this.NetworkMode = NetworkMode;
        }

        public RestartPolicyBean getRestartPolicy() {
            return RestartPolicy;
        }

        public void setRestartPolicy(RestartPolicyBean RestartPolicy) {
            this.RestartPolicy = RestartPolicy;
        }

        public boolean isAutoRemove() {
            return AutoRemove;
        }

        public void setAutoRemove(boolean AutoRemove) {
            this.AutoRemove = AutoRemove;
        }

        public String getVolumeDriver() {
            return VolumeDriver;
        }

        public void setVolumeDriver(String VolumeDriver) {
            this.VolumeDriver = VolumeDriver;
        }

        public Object getCapAdd() {
            return CapAdd;
        }

        public void setCapAdd(Object CapAdd) {
            this.CapAdd = CapAdd;
        }

        public Object getCapDrop() {
            return CapDrop;
        }

        public void setCapDrop(Object CapDrop) {
            this.CapDrop = CapDrop;
        }

        public Object getDns() {
            return Dns;
        }

        public void setDns(Object Dns) {
            this.Dns = Dns;
        }

        public Object getDnsOptions() {
            return DnsOptions;
        }

        public void setDnsOptions(Object DnsOptions) {
            this.DnsOptions = DnsOptions;
        }

        public Object getDnsSearch() {
            return DnsSearch;
        }

        public void setDnsSearch(Object DnsSearch) {
            this.DnsSearch = DnsSearch;
        }

        public Object getExtraHosts() {
            return ExtraHosts;
        }

        public void setExtraHosts(Object ExtraHosts) {
            this.ExtraHosts = ExtraHosts;
        }

        public Object getGroupAdd() {
            return GroupAdd;
        }

        public void setGroupAdd(Object GroupAdd) {
            this.GroupAdd = GroupAdd;
        }

        public String getIpcMode() {
            return IpcMode;
        }

        public void setIpcMode(String IpcMode) {
            this.IpcMode = IpcMode;
        }

        public String getCgroup() {
            return Cgroup;
        }

        public void setCgroup(String Cgroup) {
            this.Cgroup = Cgroup;
        }

        public Object getLinks() {
            return Links;
        }

        public void setLinks(Object Links) {
            this.Links = Links;
        }

        public int getOomScoreAdj() {
            return OomScoreAdj;
        }

        public void setOomScoreAdj(int OomScoreAdj) {
            this.OomScoreAdj = OomScoreAdj;
        }

        public String getPidMode() {
            return PidMode;
        }

        public void setPidMode(String PidMode) {
            this.PidMode = PidMode;
        }

        public boolean isPrivileged() {
            return Privileged;
        }

        public void setPrivileged(boolean Privileged) {
            this.Privileged = Privileged;
        }

        public boolean isPublishAllPorts() {
            return PublishAllPorts;
        }

        public void setPublishAllPorts(boolean PublishAllPorts) {
            this.PublishAllPorts = PublishAllPorts;
        }

        public boolean isReadonlyRootfs() {
            return ReadonlyRootfs;
        }

        public void setReadonlyRootfs(boolean ReadonlyRootfs) {
            this.ReadonlyRootfs = ReadonlyRootfs;
        }

        public Object getSecurityOpt() {
            return SecurityOpt;
        }

        public void setSecurityOpt(Object SecurityOpt) {
            this.SecurityOpt = SecurityOpt;
        }

        public String getUTSMode() {
            return UTSMode;
        }

        public void setUTSMode(String UTSMode) {
            this.UTSMode = UTSMode;
        }

        public String getUsernsMode() {
            return UsernsMode;
        }

        public void setUsernsMode(String UsernsMode) {
            this.UsernsMode = UsernsMode;
        }

        public int getShmSize() {
            return ShmSize;
        }

        public void setShmSize(int ShmSize) {
            this.ShmSize = ShmSize;
        }

        public String getRuntime() {
            return Runtime;
        }

        public void setRuntime(String Runtime) {
            this.Runtime = Runtime;
        }

        public String getIsolation() {
            return Isolation;
        }

        public void setIsolation(String Isolation) {
            this.Isolation = Isolation;
        }

        public int getCpuShares() {
            return CpuShares;
        }

        public void setCpuShares(int CpuShares) {
            this.CpuShares = CpuShares;
        }

        public int getMemory() {
            return Memory;
        }

        public void setMemory(int Memory) {
            this.Memory = Memory;
        }

        public int getNanoCpus() {
            return NanoCpus;
        }

        public void setNanoCpus(int NanoCpus) {
            this.NanoCpus = NanoCpus;
        }

        public String getCgroupParent() {
            return CgroupParent;
        }

        public void setCgroupParent(String CgroupParent) {
            this.CgroupParent = CgroupParent;
        }

        public int getBlkioWeight() {
            return BlkioWeight;
        }

        public void setBlkioWeight(int BlkioWeight) {
            this.BlkioWeight = BlkioWeight;
        }

        public Object getBlkioWeightDevice() {
            return BlkioWeightDevice;
        }

        public void setBlkioWeightDevice(Object BlkioWeightDevice) {
            this.BlkioWeightDevice = BlkioWeightDevice;
        }

        public Object getBlkioDeviceReadBps() {
            return BlkioDeviceReadBps;
        }

        public void setBlkioDeviceReadBps(Object BlkioDeviceReadBps) {
            this.BlkioDeviceReadBps = BlkioDeviceReadBps;
        }

        public Object getBlkioDeviceWriteBps() {
            return BlkioDeviceWriteBps;
        }

        public void setBlkioDeviceWriteBps(Object BlkioDeviceWriteBps) {
            this.BlkioDeviceWriteBps = BlkioDeviceWriteBps;
        }

        public Object getBlkioDeviceReadIOps() {
            return BlkioDeviceReadIOps;
        }

        public void setBlkioDeviceReadIOps(Object BlkioDeviceReadIOps) {
            this.BlkioDeviceReadIOps = BlkioDeviceReadIOps;
        }

        public Object getBlkioDeviceWriteIOps() {
            return BlkioDeviceWriteIOps;
        }

        public void setBlkioDeviceWriteIOps(Object BlkioDeviceWriteIOps) {
            this.BlkioDeviceWriteIOps = BlkioDeviceWriteIOps;
        }

        public int getCpuPeriod() {
            return CpuPeriod;
        }

        public void setCpuPeriod(int CpuPeriod) {
            this.CpuPeriod = CpuPeriod;
        }

        public int getCpuQuota() {
            return CpuQuota;
        }

        public void setCpuQuota(int CpuQuota) {
            this.CpuQuota = CpuQuota;
        }

        public int getCpuRealtimePeriod() {
            return CpuRealtimePeriod;
        }

        public void setCpuRealtimePeriod(int CpuRealtimePeriod) {
            this.CpuRealtimePeriod = CpuRealtimePeriod;
        }

        public int getCpuRealtimeRuntime() {
            return CpuRealtimeRuntime;
        }

        public void setCpuRealtimeRuntime(int CpuRealtimeRuntime) {
            this.CpuRealtimeRuntime = CpuRealtimeRuntime;
        }

        public String getCpusetCpus() {
            return CpusetCpus;
        }

        public void setCpusetCpus(String CpusetCpus) {
            this.CpusetCpus = CpusetCpus;
        }

        public String getCpusetMems() {
            return CpusetMems;
        }

        public void setCpusetMems(String CpusetMems) {
            this.CpusetMems = CpusetMems;
        }

        public Object getDevices() {
            return Devices;
        }

        public void setDevices(Object Devices) {
            this.Devices = Devices;
        }

        public Object getDeviceCgroupRules() {
            return DeviceCgroupRules;
        }

        public void setDeviceCgroupRules(Object DeviceCgroupRules) {
            this.DeviceCgroupRules = DeviceCgroupRules;
        }

        public int getDiskQuota() {
            return DiskQuota;
        }

        public void setDiskQuota(int DiskQuota) {
            this.DiskQuota = DiskQuota;
        }

        public int getKernelMemory() {
            return KernelMemory;
        }

        public void setKernelMemory(int KernelMemory) {
            this.KernelMemory = KernelMemory;
        }

        public int getMemoryReservation() {
            return MemoryReservation;
        }

        public void setMemoryReservation(int MemoryReservation) {
            this.MemoryReservation = MemoryReservation;
        }

        public int getMemorySwap() {
            return MemorySwap;
        }

        public void setMemorySwap(int MemorySwap) {
            this.MemorySwap = MemorySwap;
        }

        public Object getMemorySwappiness() {
            return MemorySwappiness;
        }

        public void setMemorySwappiness(Object MemorySwappiness) {
            this.MemorySwappiness = MemorySwappiness;
        }

        public boolean isOomKillDisable() {
            return OomKillDisable;
        }

        public void setOomKillDisable(boolean OomKillDisable) {
            this.OomKillDisable = OomKillDisable;
        }

        public int getPidsLimit() {
            return PidsLimit;
        }

        public void setPidsLimit(int PidsLimit) {
            this.PidsLimit = PidsLimit;
        }

        public Object getUlimits() {
            return Ulimits;
        }

        public void setUlimits(Object Ulimits) {
            this.Ulimits = Ulimits;
        }

        public int getCpuCount() {
            return CpuCount;
        }

        public void setCpuCount(int CpuCount) {
            this.CpuCount = CpuCount;
        }

        public int getCpuPercent() {
            return CpuPercent;
        }

        public void setCpuPercent(int CpuPercent) {
            this.CpuPercent = CpuPercent;
        }

        public int getIOMaximumIOps() {
            return IOMaximumIOps;
        }

        public void setIOMaximumIOps(int IOMaximumIOps) {
            this.IOMaximumIOps = IOMaximumIOps;
        }

        public int getIOMaximumBandwidth() {
            return IOMaximumBandwidth;
        }

        public void setIOMaximumBandwidth(int IOMaximumBandwidth) {
            this.IOMaximumBandwidth = IOMaximumBandwidth;
        }

        public List<?> getVolumesFrom() {
            return VolumesFrom;
        }

        public void setVolumesFrom(List<?> VolumesFrom) {
            this.VolumesFrom = VolumesFrom;
        }

        public List<Integer> getConsoleSize() {
            return ConsoleSize;
        }

        public void setConsoleSize(List<Integer> ConsoleSize) {
            this.ConsoleSize = ConsoleSize;
        }

        public static class LogConfigBean {
            /**
             * Type : json-file
             * Config : {}
             */

            private String Type;
            private ConfigBean Config;

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public ConfigBean getConfig() {
                return Config;
            }

            public void setConfig(ConfigBean Config) {
                this.Config = Config;
            }

            public static class ConfigBean {
            }
        }


        public Map<String, List<PortBindingsBean>> getPortBindings() {
            return PortBindings;
        }

        public void setPortBindings(Map<String, List<PortBindingsBean>> portBindings) {
            PortBindings = portBindings;
        }

        private Map<String, List<PortBindingsBean>> PortBindings;

        public static class PortBindingsBean {
            /**
             * HostIp : 127.0.0.1
             * HostPort : 6379
             */

            private String HostIp;
            private String HostPort;

            public String getHostIp() {
                return HostIp;
            }

            public void setHostIp(String HostIp) {
                this.HostIp = HostIp;
            }

            public String getHostPort() {
                return HostPort;
            }

            public void setHostPort(String HostPort) {
                this.HostPort = HostPort;
            }
        }

        public static class RestartPolicyBean {
            /**
             * Name : always
             * MaximumRetryCount : 0
             */

            private String Name;
            private int MaximumRetryCount;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getMaximumRetryCount() {
                return MaximumRetryCount;
            }

            public void setMaximumRetryCount(int MaximumRetryCount) {
                this.MaximumRetryCount = MaximumRetryCount;
            }
        }
    }

    public static class ConfigBeanX {
        /**
         * Hostname : 97d7298d3004
         * Domainname :
         * User :
         * AttachStdin : false
         * AttachStdout : false
         * AttachStderr : false
         * Tty : false
         * OpenStdin : false
         * StdinOnce : false
         * Env : ["NODE_ENV=production","PROXY_HEADER_REAL_IP_KEY=X-Forwarded-For","PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin","NODE_VERSION=6.13.1","YARN_VERSION=1.5.1"]
         * Cmd : null
         * ArgsEscaped : true
         * Image : davidkroell/keylog.rest
         * WorkingDir : /usr/src/app
         * Entrypoint : ["/usr/bin/env","node","/usr/src/app/bin/www"]
         * OnBuild : null
         */

        private String Hostname;
        private String Domainname;
        private String User;
        private boolean AttachStdin;
        private boolean AttachStdout;
        private boolean AttachStderr;
        private boolean Tty;
        private boolean OpenStdin;
        private boolean StdinOnce;
        private Object Cmd;
        private boolean ArgsEscaped;
        private String Image;
        private String WorkingDir;
        private Object OnBuild;
        private List<String> Env;
        private List<String> Entrypoint;

        public String getHostname() {
            return Hostname;
        }

        public void setHostname(String Hostname) {
            this.Hostname = Hostname;
        }

        public String getDomainname() {
            return Domainname;
        }

        public void setDomainname(String Domainname) {
            this.Domainname = Domainname;
        }

        public String getUser() {
            return User;
        }

        public void setUser(String User) {
            this.User = User;
        }

        public boolean isAttachStdin() {
            return AttachStdin;
        }

        public void setAttachStdin(boolean AttachStdin) {
            this.AttachStdin = AttachStdin;
        }

        public boolean isAttachStdout() {
            return AttachStdout;
        }

        public void setAttachStdout(boolean AttachStdout) {
            this.AttachStdout = AttachStdout;
        }

        public boolean isAttachStderr() {
            return AttachStderr;
        }

        public void setAttachStderr(boolean AttachStderr) {
            this.AttachStderr = AttachStderr;
        }

        public boolean isTty() {
            return Tty;
        }

        public void setTty(boolean Tty) {
            this.Tty = Tty;
        }

        public boolean isOpenStdin() {
            return OpenStdin;
        }

        public void setOpenStdin(boolean OpenStdin) {
            this.OpenStdin = OpenStdin;
        }

        public boolean isStdinOnce() {
            return StdinOnce;
        }

        public void setStdinOnce(boolean StdinOnce) {
            this.StdinOnce = StdinOnce;
        }

        public Object getCmd() {
            return Cmd;
        }

        public void setCmd(Object Cmd) {
            this.Cmd = Cmd;
        }

        public boolean isArgsEscaped() {
            return ArgsEscaped;
        }

        public void setArgsEscaped(boolean ArgsEscaped) {
            this.ArgsEscaped = ArgsEscaped;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getWorkingDir() {
            return WorkingDir;
        }

        public void setWorkingDir(String WorkingDir) {
            this.WorkingDir = WorkingDir;
        }

        public Object getOnBuild() {
            return OnBuild;
        }

        public void setOnBuild(Object OnBuild) {
            this.OnBuild = OnBuild;
        }

        public List<String> getEnv() {
            return Env;
        }

        public void setEnv(List<String> Env) {
            this.Env = Env;
        }

        public List<String> getEntrypoint() {
            return Entrypoint;
        }

        public void setEntrypoint(List<String> Entrypoint) {
            this.Entrypoint = Entrypoint;
        }
    }

    public static class NetworkSettingsBean {
        /**
         * Bridge :
         * SandboxID : 1e70915ecc1de33caa3f37b63bc2ac84a3330a3ecc04910100f5b6baeb6103aa
         * HairpinMode : false
         * LinkLocalIPv6Address :
         * LinkLocalIPv6PrefixLen : 0
         * SandboxKey : /var/run/docker/netns/1e70915ecc1d
         * SecondaryIPAddresses : null
         * SecondaryIPv6Addresses : null
         * EndpointID : 62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2
         * Gateway : 172.17.0.1
         * GlobalIPv6Address :
         * GlobalIPv6PrefixLen : 0
         * IPAddress : 172.17.0.2
         * IPPrefixLen : 16
         * IPv6Gateway :
         * MacAddress : 02:42:ac:11:00:02
         * Networks : {"bridge":{"IPAMConfig":null,"Links":null,"Aliases":null,"NetworkID":"a025278a9c83a40b7bd8a18b2267b8e07913b8b37f5946c9bd0b4ef3fc27ca85","EndpointID":"62d58b7728dc4b0e4037d7eac682934544c71b9e9e2dd2a62fed94badb630df2","Gateway":"172.17.0.1","IPAddress":"172.17.0.2","IPPrefixLen":16,"IPv6Gateway":"","GlobalIPv6Address":"","GlobalIPv6PrefixLen":0,"MacAddress":"02:42:ac:11:00:02","DriverOpts":null}}
         */

        private String Bridge;
        private String SandboxID;
        private boolean HairpinMode;
        private String LinkLocalIPv6Address;
        private int LinkLocalIPv6PrefixLen;
        private String SandboxKey;
        private Object SecondaryIPAddresses;
        private Object SecondaryIPv6Addresses;
        private String EndpointID;
        private String Gateway;
        private String GlobalIPv6Address;
        private int GlobalIPv6PrefixLen;
        private String IPAddress;
        private int IPPrefixLen;
        private String IPv6Gateway;
        private String MacAddress;
        private NetworksBean Networks;

        public String getBridge() {
            return Bridge;
        }

        public void setBridge(String Bridge) {
            this.Bridge = Bridge;
        }

        public String getSandboxID() {
            return SandboxID;
        }

        public void setSandboxID(String SandboxID) {
            this.SandboxID = SandboxID;
        }

        public boolean isHairpinMode() {
            return HairpinMode;
        }

        public void setHairpinMode(boolean HairpinMode) {
            this.HairpinMode = HairpinMode;
        }

        public String getLinkLocalIPv6Address() {
            return LinkLocalIPv6Address;
        }

        public void setLinkLocalIPv6Address(String LinkLocalIPv6Address) {
            this.LinkLocalIPv6Address = LinkLocalIPv6Address;
        }

        public int getLinkLocalIPv6PrefixLen() {
            return LinkLocalIPv6PrefixLen;
        }

        public void setLinkLocalIPv6PrefixLen(int LinkLocalIPv6PrefixLen) {
            this.LinkLocalIPv6PrefixLen = LinkLocalIPv6PrefixLen;
        }

        public String getSandboxKey() {
            return SandboxKey;
        }

        public void setSandboxKey(String SandboxKey) {
            this.SandboxKey = SandboxKey;
        }

        public Object getSecondaryIPAddresses() {
            return SecondaryIPAddresses;
        }

        public void setSecondaryIPAddresses(Object SecondaryIPAddresses) {
            this.SecondaryIPAddresses = SecondaryIPAddresses;
        }

        public Object getSecondaryIPv6Addresses() {
            return SecondaryIPv6Addresses;
        }

        public void setSecondaryIPv6Addresses(Object SecondaryIPv6Addresses) {
            this.SecondaryIPv6Addresses = SecondaryIPv6Addresses;
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

        public String getMacAddress() {
            return MacAddress;
        }

        public void setMacAddress(String MacAddress) {
            this.MacAddress = MacAddress;
        }

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
