package at.htl_villach.docker2go;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DockerImage implements DockerObj {

    private int Containers;
    private int Created;
    private String Id;
    private LabelsBean Labels;
    private String ParentId;
    private List<String> RepoDigests;
    private int SharedSize;
    private int Size;
    private int VirtualSize;
    private List<String> RepoTags;

    public int getContainers() {
        return Containers;
    }

    public void setContainers(int Containers) {
        this.Containers = Containers;
    }

    public int getCreated() {
        return Created;
    }

    public void setCreated(int Created) {
        this.Created = Created;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public LabelsBean getLabels() {
        return Labels;
    }

    public void setLabels(LabelsBean Labels) {
        this.Labels = Labels;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public List<String> getRepoDigests() {
        return RepoDigests;
    }

    public void setRepoDigests(List<String> RepoDigests) {
        this.RepoDigests = RepoDigests;
    }

    public int getSharedSize() {
        return SharedSize;
    }

    public void setSharedSize(int SharedSize) {
        this.SharedSize = SharedSize;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public int getVirtualSize() {
        return VirtualSize;
    }

    public void setVirtualSize(int VirtualSize) {
        this.VirtualSize = VirtualSize;
    }

    public List<String> getRepoTags() {
        return RepoTags;
    }

    public void setRepoTags(List<String> RepoTags) {
        this.RepoTags = RepoTags;
    }

    public static class LabelsBean {
        @SerializedName("com.docker.compose.config-hash")
        private String _$ComDockerComposeConfighash228; // FIXME check this code
        @SerializedName("com.docker.compose.container-number")
        private String _$ComDockerComposeContainernumber86; // FIXME check this code
        @SerializedName("com.docker.compose.oneoff")
        private String _$ComDockerComposeOneoff332; // FIXME check this code
        @SerializedName("com.docker.compose.project")
        private String _$ComDockerComposeProject319; // FIXME check this code
        @SerializedName("com.docker.compose.service")
        private String _$ComDockerComposeService208; // FIXME check this code
        @SerializedName("com.docker.compose.version")
        private String _$ComDockerComposeVersion65; // FIXME check this code

        public String get_$ComDockerComposeConfighash228() {
            return _$ComDockerComposeConfighash228;
        }

        public void set_$ComDockerComposeConfighash228(String _$ComDockerComposeConfighash228) {
            this._$ComDockerComposeConfighash228 = _$ComDockerComposeConfighash228;
        }

        public String get_$ComDockerComposeContainernumber86() {
            return _$ComDockerComposeContainernumber86;
        }

        public void set_$ComDockerComposeContainernumber86(String _$ComDockerComposeContainernumber86) {
            this._$ComDockerComposeContainernumber86 = _$ComDockerComposeContainernumber86;
        }

        public String get_$ComDockerComposeOneoff332() {
            return _$ComDockerComposeOneoff332;
        }

        public void set_$ComDockerComposeOneoff332(String _$ComDockerComposeOneoff332) {
            this._$ComDockerComposeOneoff332 = _$ComDockerComposeOneoff332;
        }

        public String get_$ComDockerComposeProject319() {
            return _$ComDockerComposeProject319;
        }

        public void set_$ComDockerComposeProject319(String _$ComDockerComposeProject319) {
            this._$ComDockerComposeProject319 = _$ComDockerComposeProject319;
        }

        public String get_$ComDockerComposeService208() {
            return _$ComDockerComposeService208;
        }

        public void set_$ComDockerComposeService208(String _$ComDockerComposeService208) {
            this._$ComDockerComposeService208 = _$ComDockerComposeService208;
        }

        public String get_$ComDockerComposeVersion65() {
            return _$ComDockerComposeVersion65;
        }

        public void set_$ComDockerComposeVersion65(String _$ComDockerComposeVersion65) {
            this._$ComDockerComposeVersion65 = _$ComDockerComposeVersion65;
        }
    }
}