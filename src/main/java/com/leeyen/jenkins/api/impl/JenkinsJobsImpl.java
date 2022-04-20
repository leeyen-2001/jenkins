package com.leeyen.jenkins.api.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.job.Workflow;
import com.cdancy.jenkins.rest.features.JobsApi;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.leeyen.jenkins.utils.JenkinsConnect;
import com.leeyen.jenkins.api.JenkinsJobs;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.jclouds.util.Strings2.toStringAndClose;

@Data
public class JenkinsJobsImpl implements JenkinsJobs {
    private JenkinsClient jenkinsClient;
    private JobsApi jobsApi;

    public JenkinsJobsImpl(){
        jenkinsClient = JenkinsConnect.getClient();
        jobsApi = jenkinsClient.api().jobsApi();
    }

    /**
     * 用于读取resource资源
     * @param resource 资源名
     * @return 资源内容
     */
    public String payloadFromResource(String resource) {
        try {
            return new String(toStringAndClose(getClass().getResourceAsStream(resource)).getBytes(Charsets.UTF_8));
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 获取job列表
     * @param fileName 文件夹路径为空则取的是最外层的任务，若要查看任务文件夹中的任务，入参则为文件名名称
     * @return jobList列表
     */
    @Override
    public JobList getJobList(String fileName){
        return jobsApi.jobList(fileName);
    }

    /**
     * 根据任务名称获取任务信息
     * @param jobName 任务名称
     * @return JobInfo
     */
    @Override
    public JobInfo getJobInfo(String jobName){
        // 文件夹路径null
        return jobsApi.jobInfo(null, jobName);
    }

    /**
     * 根据任务名获取任务配置文件内容
     * @param jobName 任务名称
     * @return String
     */
    @Override
    public String getJobConfig(String jobName){
        // 文件夹路径null
        return jobsApi.config(null, jobName);
    }

    /**
     * 更新任务的配置文件
     * @param jobName 任务名称
     * @param configFileName 任务配置文件名
     * @return boolean
     */
    @Override
    public boolean updateJobConfig(String jobName, String configFileName){
        String xmlStr = payloadFromResource(configFileName);
        // 文件夹路径null
        return jobsApi.config(null, jobName, xmlStr);
    }

    /**
     * 获取任务描述
     * @param jobName 任务名称
     * @return 内容
     */
    @Override
    public String getJobsDescriptions(String jobName) {
        // 文件夹路径null
        String description= jobsApi.description(null, jobName);
        return description;
    }

    /**
     * 更新任务的描述
     * @param jobName 任务名称
     * @param jobDescription 新的任务描述
     * @return
     */
    @Override
    public Boolean JobsDescriptions(String jobName, String jobDescription) {
        // 文件夹路径null
        return jobsApi.description(null, jobName, jobDescription);
    }

    /**
     * 删除任务
     * @param jobName 任务名称
     * @return RequestStatus
     */
    @Override
    public RequestStatus deleteJob(String jobName) {
        // 文件夹路径null
        return jobsApi.delete(null,jobName);
    }

    /**
     * 创建一个流水线job
     * @param configFileName 任务配置文件名
     * @return 创建状态
     */
    @Override
    public RequestStatus createJob(String configFileName){
        String xmlStr = payloadFromResource(configFileName);
        RequestStatus status = jobsApi.create(null, "SomeKey", xmlStr);
        return status;
    }

    /**
     * 启用任务
     * @param jobName 任务名称
     * @return
     */
    @Override
    public boolean enableJob(String jobName) {
        // 文件夹路径null
        return jobsApi.enable(null,jobName);
    }

    /**
     * 禁用任务
     * @param jobName 任务名称
     * @return
     */
    @Override
    public boolean disableJob(String jobName) {
        // 文件夹路径null
        return jobsApi.disable(null,jobName);
    }

    /**
     * 根据任务名称和任务构建id，获取构建信息
     * @param jobName 任务名称
     * @param buildId 构建id
     * @return
     */
    @Override
    public BuildInfo getJobBuildInfo(String jobName, int buildId) {
        // 文件夹路径null
        return jobsApi.buildInfo(null, jobName, buildId);
    }

    /**
     * 任务构建
     * @param jobName 任务名称
     * @return
     */
    @Override
    public IntegerResponse buildJob(String jobName) {
        // 文件夹路径null
        return jobsApi.build(null,jobName);
    }

    /**
     * 带参数构建任务
     * @param jobName 任务名称
     * @param params 任务参数
     * @return
     */
    @Override
    public IntegerResponse buldParamJob(String jobName, HashMap<String, List<String>> params) {
        // 文件夹路径null
        return jobsApi.buildWithParameters(null, jobName, params);
    }

    /**
     * 上次构建的序号
     * @param jobName 任务名称
     * @return
     */
    @Override
    public Integer getLastBuildNumber(String jobName) {
        // 文件夹路径null
        return jobsApi.lastBuildNumber(null, jobName);
    }

    /**
     * 上次任务构建时间
     * @param jobName 任务名称
     * @return
     */
    @Override
    public String getLastBuildTimetamp(String jobName) {
        // 文件夹路径null
        return jobsApi.lastBuildTimestamp(null, jobName);
    }

    /**
     * 重命名任务
     * @param jobName
     * @param newName
     * @return
     */
    @Override
    public boolean renameJob(String jobName, String newName) {
        // 文件夹路径null
        return jobsApi.rename(null, jobName, newName);
    }

    /**
     * 获取任务构建index对应的构建步骤内容
     * @param jobName 任务名称
     * @param indexId 构建id
     * @return
     */
    @Override
    public Workflow getWorkFlow(String jobName, int indexId) {
        return jobsApi.workflow(null, jobName, indexId);
    }


}
