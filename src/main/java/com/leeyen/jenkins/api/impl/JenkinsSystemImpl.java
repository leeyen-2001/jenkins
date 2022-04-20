package com.leeyen.jenkins.api.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.plugins.Plugins;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.cdancy.jenkins.rest.features.PluginManagerApi;
import com.cdancy.jenkins.rest.features.SystemApi;
import com.leeyen.jenkins.api.JenkinsSystem;
import com.leeyen.jenkins.utils.JenkinsConnect;

public class JenkinsSystemImpl implements JenkinsSystem {

    private JenkinsClient jenkinsClient;
    private SystemApi systemsApi;
    private PluginManagerApi pluginManagerApi;
    //client.api().pluginManagerApi();
            //=client.api().systemApi();


    public JenkinsSystemImpl() {
        jenkinsClient = JenkinsConnect.getClient();
        systemsApi = jenkinsClient.api().systemApi();
        pluginManagerApi = jenkinsClient.api().pluginManagerApi();
    }

    /**
     * 查看jenkins系统环境信息
     * @return
     */
    @Override
    public SystemInfo getSystemInfo() {
        return systemsApi.systemInfo();
    }

    /**
     * 准备关闭jenkins,对应Prepare for Shutdown功能
     * @return
     */
    @Override
    public RequestStatus quietDownJenkins() {
        return systemsApi.quietDown();
    }

    /**
     * 取消准备关闭jenkins
     * @return
     */
    @Override
    public RequestStatus cancleQuietDown() {
        return systemsApi.cancelQuietDown();
    }

    /**
     * 查看插件所有信息
     * @return
     */
    @Override
    public Plugins getAllPluginsInfo() {
        return pluginManagerApi.plugins(3,null);
    }

    /**
     * 安装插件
     * @param pluginsVersion 插件ID@版本号
     * @return
     */
    @Override
    public RequestStatus installNecessaryPlugins(String pluginsVersion) {
        return pluginManagerApi.installNecessaryPlugins(pluginsVersion);
    }
}
