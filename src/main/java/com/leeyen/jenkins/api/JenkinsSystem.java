package com.leeyen.jenkins.api;

import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.plugins.Plugins;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;

public interface JenkinsSystem {
    SystemInfo getSystemInfo();

    RequestStatus quietDownJenkins();

    RequestStatus cancleQuietDown();

    Plugins getAllPluginsInfo();

    RequestStatus installNecessaryPlugins(String pluginsVersion);
}
