package at.htl_villach.docker2go;

import java.util.List;

public class DockerInfo implements DockerObj {

    private String Architecture;
    private boolean BridgeNfIp6tables;
    private boolean BridgeNfIptables;
    private boolean CPUSet;
    private boolean CPUShares;
    private String CgroupDriver;
    private String ClusterAdvertise;
    private String ClusterStore;
    private ContainerdCommitBean ContainerdCommit;
    private int Containers;
    private int ContainersPaused;
    private int ContainersRunning;
    private int ContainersStopped;
    private boolean CpuCfsPeriod;
    private boolean CpuCfsQuota;
    private boolean Debug;
    private String DefaultRuntime;
    private String DockerRootDir;
    private String Driver;
    private boolean ExperimentalBuild;
    private Object GenericResources;
    private String HttpProxy;
    private String HttpsProxy;
    private String ID;
    private boolean IPv4Forwarding;
    private int Images;
    private String IndexServerAddress;
    private String InitBinary;
    private InitCommitBean InitCommit;
    private String Isolation;
    private boolean KernelMemory;
    private String KernelVersion;
    private boolean LiveRestoreEnabled;
    private String LoggingDriver;
    private long MemTotal;
    private boolean MemoryLimit;
    private int NCPU;
    private int NEventsListener;
    private int NFd;
    private int NGoroutines;
    private String Name;
    private String NoProxy;
    private String OSType;
    private boolean OomKillDisable;
    private String OperatingSystem;
    private PluginsBean Plugins;
    private RuncCommitBean RuncCommit;
    private RuntimesBean Runtimes;
    private String ServerVersion;
    private boolean SwapLimit;
    private SwarmBean Swarm;
    private Object SystemStatus;
    private String SystemTime;
    private List<List<String>> DriverStatus;
    private List<?> Labels;
    private List<String> SecurityOptions;

    public String getArchitecture() {
        return Architecture;
    }

    public void setArchitecture(String Architecture) {
        this.Architecture = Architecture;
    }

    public boolean isBridgeNfIp6tables() {
        return BridgeNfIp6tables;
    }

    public void setBridgeNfIp6tables(boolean BridgeNfIp6tables) {
        this.BridgeNfIp6tables = BridgeNfIp6tables;
    }

    public boolean isBridgeNfIptables() {
        return BridgeNfIptables;
    }

    public void setBridgeNfIptables(boolean BridgeNfIptables) {
        this.BridgeNfIptables = BridgeNfIptables;
    }

    public boolean isCPUSet() {
        return CPUSet;
    }

    public void setCPUSet(boolean CPUSet) {
        this.CPUSet = CPUSet;
    }

    public boolean isCPUShares() {
        return CPUShares;
    }

    public void setCPUShares(boolean CPUShares) {
        this.CPUShares = CPUShares;
    }

    public String getCgroupDriver() {
        return CgroupDriver;
    }

    public void setCgroupDriver(String CgroupDriver) {
        this.CgroupDriver = CgroupDriver;
    }

    public String getClusterAdvertise() {
        return ClusterAdvertise;
    }

    public void setClusterAdvertise(String ClusterAdvertise) {
        this.ClusterAdvertise = ClusterAdvertise;
    }

    public String getClusterStore() {
        return ClusterStore;
    }

    public void setClusterStore(String ClusterStore) {
        this.ClusterStore = ClusterStore;
    }

    public ContainerdCommitBean getContainerdCommit() {
        return ContainerdCommit;
    }

    public void setContainerdCommit(ContainerdCommitBean ContainerdCommit) {
        this.ContainerdCommit = ContainerdCommit;
    }

    public int getContainers() {
        return Containers;
    }

    public void setContainers(int Containers) {
        this.Containers = Containers;
    }

    public int getContainersPaused() {
        return ContainersPaused;
    }

    public void setContainersPaused(int ContainersPaused) {
        this.ContainersPaused = ContainersPaused;
    }

    public int getContainersRunning() {
        return ContainersRunning;
    }

    public void setContainersRunning(int ContainersRunning) {
        this.ContainersRunning = ContainersRunning;
    }

    public int getContainersStopped() {
        return ContainersStopped;
    }

    public void setContainersStopped(int ContainersStopped) {
        this.ContainersStopped = ContainersStopped;
    }

    public boolean isCpuCfsPeriod() {
        return CpuCfsPeriod;
    }

    public void setCpuCfsPeriod(boolean CpuCfsPeriod) {
        this.CpuCfsPeriod = CpuCfsPeriod;
    }

    public boolean isCpuCfsQuota() {
        return CpuCfsQuota;
    }

    public void setCpuCfsQuota(boolean CpuCfsQuota) {
        this.CpuCfsQuota = CpuCfsQuota;
    }

    public boolean isDebug() {
        return Debug;
    }

    public void setDebug(boolean Debug) {
        this.Debug = Debug;
    }

    public String getDefaultRuntime() {
        return DefaultRuntime;
    }

    public void setDefaultRuntime(String DefaultRuntime) {
        this.DefaultRuntime = DefaultRuntime;
    }

    public String getDockerRootDir() {
        return DockerRootDir;
    }

    public void setDockerRootDir(String DockerRootDir) {
        this.DockerRootDir = DockerRootDir;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String Driver) {
        this.Driver = Driver;
    }

    public boolean isExperimentalBuild() {
        return ExperimentalBuild;
    }

    public void setExperimentalBuild(boolean ExperimentalBuild) {
        this.ExperimentalBuild = ExperimentalBuild;
    }

    public Object getGenericResources() {
        return GenericResources;
    }

    public void setGenericResources(Object GenericResources) {
        this.GenericResources = GenericResources;
    }

    public String getHttpProxy() {
        return HttpProxy;
    }

    public void setHttpProxy(String HttpProxy) {
        this.HttpProxy = HttpProxy;
    }

    public String getHttpsProxy() {
        return HttpsProxy;
    }

    public void setHttpsProxy(String HttpsProxy) {
        this.HttpsProxy = HttpsProxy;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isIPv4Forwarding() {
        return IPv4Forwarding;
    }

    public void setIPv4Forwarding(boolean IPv4Forwarding) {
        this.IPv4Forwarding = IPv4Forwarding;
    }

    public int getImages() {
        return Images;
    }

    public void setImages(int Images) {
        this.Images = Images;
    }

    public String getIndexServerAddress() {
        return IndexServerAddress;
    }

    public void setIndexServerAddress(String IndexServerAddress) {
        this.IndexServerAddress = IndexServerAddress;
    }

    public String getInitBinary() {
        return InitBinary;
    }

    public void setInitBinary(String InitBinary) {
        this.InitBinary = InitBinary;
    }

    public InitCommitBean getInitCommit() {
        return InitCommit;
    }

    public void setInitCommit(InitCommitBean InitCommit) {
        this.InitCommit = InitCommit;
    }

    public String getIsolation() {
        return Isolation;
    }

    public void setIsolation(String Isolation) {
        this.Isolation = Isolation;
    }

    public boolean isKernelMemory() {
        return KernelMemory;
    }

    public void setKernelMemory(boolean KernelMemory) {
        this.KernelMemory = KernelMemory;
    }

    public String getKernelVersion() {
        return KernelVersion;
    }

    public void setKernelVersion(String KernelVersion) {
        this.KernelVersion = KernelVersion;
    }

    public boolean isLiveRestoreEnabled() {
        return LiveRestoreEnabled;
    }

    public void setLiveRestoreEnabled(boolean LiveRestoreEnabled) {
        this.LiveRestoreEnabled = LiveRestoreEnabled;
    }

    public String getLoggingDriver() {
        return LoggingDriver;
    }

    public void setLoggingDriver(String LoggingDriver) {
        this.LoggingDriver = LoggingDriver;
    }

    public long getMemTotal() {
        return MemTotal;
    }

    public void setMemTotal(long MemTotal) {
        this.MemTotal = MemTotal;
    }

    public boolean isMemoryLimit() {
        return MemoryLimit;
    }

    public void setMemoryLimit(boolean MemoryLimit) {
        this.MemoryLimit = MemoryLimit;
    }

    public int getNCPU() {
        return NCPU;
    }

    public void setNCPU(int NCPU) {
        this.NCPU = NCPU;
    }

    public int getNEventsListener() {
        return NEventsListener;
    }

    public void setNEventsListener(int NEventsListener) {
        this.NEventsListener = NEventsListener;
    }

    public int getNFd() {
        return NFd;
    }

    public void setNFd(int NFd) {
        this.NFd = NFd;
    }

    public int getNGoroutines() {
        return NGoroutines;
    }

    public void setNGoroutines(int NGoroutines) {
        this.NGoroutines = NGoroutines;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNoProxy() {
        return NoProxy;
    }

    public void setNoProxy(String NoProxy) {
        this.NoProxy = NoProxy;
    }

    public String getOSType() {
        return OSType;
    }

    public void setOSType(String OSType) {
        this.OSType = OSType;
    }

    public boolean isOomKillDisable() {
        return OomKillDisable;
    }

    public void setOomKillDisable(boolean OomKillDisable) {
        this.OomKillDisable = OomKillDisable;
    }

    public String getOperatingSystem() {
        return OperatingSystem;
    }

    public void setOperatingSystem(String OperatingSystem) {
        this.OperatingSystem = OperatingSystem;
    }

    public PluginsBean getPlugins() {
        return Plugins;
    }

    public void setPlugins(PluginsBean Plugins) {
        this.Plugins = Plugins;
    }

    public RuncCommitBean getRuncCommit() {
        return RuncCommit;
    }

    public void setRuncCommit(RuncCommitBean RuncCommit) {
        this.RuncCommit = RuncCommit;
    }

    public RuntimesBean getRuntimes() {
        return Runtimes;
    }

    public void setRuntimes(RuntimesBean Runtimes) {
        this.Runtimes = Runtimes;
    }

    public String getServerVersion() {
        return ServerVersion;
    }

    public void setServerVersion(String ServerVersion) {
        this.ServerVersion = ServerVersion;
    }

    public boolean isSwapLimit() {
        return SwapLimit;
    }

    public void setSwapLimit(boolean SwapLimit) {
        this.SwapLimit = SwapLimit;
    }

    public SwarmBean getSwarm() {
        return Swarm;
    }

    public void setSwarm(SwarmBean Swarm) {
        this.Swarm = Swarm;
    }

    public Object getSystemStatus() {
        return SystemStatus;
    }

    public void setSystemStatus(Object SystemStatus) {
        this.SystemStatus = SystemStatus;
    }

    public String getSystemTime() {
        return SystemTime;
    }

    public void setSystemTime(String SystemTime) {
        this.SystemTime = SystemTime;
    }

    public List<List<String>> getDriverStatus() {
        return DriverStatus;
    }

    public void setDriverStatus(List<List<String>> DriverStatus) {
        this.DriverStatus = DriverStatus;
    }

    public List<?> getLabels() {
        return Labels;
    }

    public void setLabels(List<?> Labels) {
        this.Labels = Labels;
    }

    public List<String> getSecurityOptions() {
        return SecurityOptions;
    }

    public void setSecurityOptions(List<String> SecurityOptions) {
        this.SecurityOptions = SecurityOptions;
    }

    public static class ContainerdCommitBean {
        /**
         * Expected : cfd04396dc68220d1cecbe686a6cc3aa5ce3667c
         * ID : cfd04396dc68220d1cecbe686a6cc3aa5ce3667c
         */

        private String Expected;
        private String ID;

        public String getExpected() {
            return Expected;
        }

        public void setExpected(String Expected) {
            this.Expected = Expected;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public static class InitCommitBean {
        /**
         * Expected : 949e6fa
         * ID : 949e6fa
         */

        private String Expected;
        private String ID;

        public String getExpected() {
            return Expected;
        }

        public void setExpected(String Expected) {
            this.Expected = Expected;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public static class PluginsBean {
        /**
         * Authorization : null
         * Log : ["awslogs","fluentd","gcplogs","gelf","journald","json-file","logentries","splunk","syslog"]
         * Network : ["bridge","host","macvlan","null","overlay"]
         * Volume : ["local"]
         */

        private Object Authorization;
        private List<String> Log;
        private List<String> Network;
        private List<String> Volume;

        public Object getAuthorization() {
            return Authorization;
        }

        public void setAuthorization(Object Authorization) {
            this.Authorization = Authorization;
        }

        public List<String> getLog() {
            return Log;
        }

        public void setLog(List<String> Log) {
            this.Log = Log;
        }

        public List<String> getNetwork() {
            return Network;
        }

        public void setNetwork(List<String> Network) {
            this.Network = Network;
        }

        public List<String> getVolume() {
            return Volume;
        }

        public void setVolume(List<String> Volume) {
            this.Volume = Volume;
        }
    }

    public static class RuncCommitBean {
        /**
         * Expected : 4fc53a81fb7c994640722ac585fa9ca548971871
         * ID : 4fc53a81fb7c994640722ac585fa9ca548971871
         */

        private String Expected;
        private String ID;

        public String getExpected() {
            return Expected;
        }

        public void setExpected(String Expected) {
            this.Expected = Expected;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public static class RuntimesBean {
        /**
         * runc : {"path":"docker-runc"}
         */

        private RuncBean runc;

        public RuncBean getRunc() {
            return runc;
        }

        public void setRunc(RuncBean runc) {
            this.runc = runc;
        }

        public static class RuncBean {
            /**
             * path : docker-runc
             */

            private String path;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

    public static class SwarmBean {
        /**
         * ControlAvailable : false
         * Error :
         * LocalNodeState : inactive
         * NodeAddr :
         * NodeID :
         * RemoteManagers : null
         */

        private boolean ControlAvailable;
        private String Error;
        private String LocalNodeState;
        private String NodeAddr;
        private String NodeID;
        private Object RemoteManagers;

        public boolean isControlAvailable() {
            return ControlAvailable;
        }

        public void setControlAvailable(boolean ControlAvailable) {
            this.ControlAvailable = ControlAvailable;
        }

        public String getError() {
            return Error;
        }

        public void setError(String Error) {
            this.Error = Error;
        }

        public String getLocalNodeState() {
            return LocalNodeState;
        }

        public void setLocalNodeState(String LocalNodeState) {
            this.LocalNodeState = LocalNodeState;
        }

        public String getNodeAddr() {
            return NodeAddr;
        }

        public void setNodeAddr(String NodeAddr) {
            this.NodeAddr = NodeAddr;
        }

        public String getNodeID() {
            return NodeID;
        }

        public void setNodeID(String NodeID) {
            this.NodeID = NodeID;
        }

        public Object getRemoteManagers() {
            return RemoteManagers;
        }

        public void setRemoteManagers(Object RemoteManagers) {
            this.RemoteManagers = RemoteManagers;
        }
    }
}
