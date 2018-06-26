package com.junicorn.conf;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Yuefei
 * @date Created in 2018/6/24 21:30
 * @description
 */
public class XmlConf {

    private Depend[] dependency;

    public Depend[] getDependency() {
        return dependency;
    }

    public void setDependency(Depend[] dependency) {
        this.dependency = dependency;
    }

    @Override
    public String toString() {
        return "XmlConf{" +
                "dependency=" + Arrays.toString(dependency) +
                '}';
    }

    public static class Depend {
        private String groupId;
        private String artifactId;
        private String version;
        private String scope;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getArtifactId() {
            return artifactId;
        }

        public void setArtifactId(String artifactId) {
            this.artifactId = artifactId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        @Override
        public String toString() {
            return "Depend{" +
                    "groupId='" + groupId + '\'' +
                    ", artifactId='" + artifactId + '\'' +
                    ", version='" + version + '\'' +
                    ", scope='" + scope + '\'' +
                    '}';
        }
    }

}
