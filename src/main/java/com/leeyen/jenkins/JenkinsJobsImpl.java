package com.leeyen.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.features.JobsApi;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import lombok.Data;

import java.io.IOException;

import static org.jclouds.util.Strings2.toStringAndClose;

@Data
public class JenkinsJobsImpl {
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
    public JobList getJobList(String fileName){
        return jobsApi.jobList(fileName);
    }

    /**
     * 根据任务名称获取任务信息
     * @param jobName 任务名称
     * @return JobInfo
     */
    public JobInfo getJobInfo(String jobName){
        return jobsApi.jobInfo(null, jobName);
    }

    /**
     * 根据任务名获取任务配置文件内容
     * @param jobName 任务名称
     * @return String
     */
    public String getJobConfig(String jobName){
        return jobsApi.config(null, jobName);
    }

    /**
     * 更新任务的配置文件
     * @param jobName 任务名称
     * @param configFileName 任务配置文件名
     * @return boolean
     */
    public boolean updateJobConfig(String jobName, String configFileName){
        String xmlStr = payloadFromResource(configFileName);
        return jobsApi.config(null, jobName, xmlStr);
    }



    /**
     * 创建一个流水线job
     * @param configFileName 任务配置文件名
     * @return 创建状态
     */
    public RequestStatus createJob(String configFileName){
        String xmlStr = payloadFromResource(configFileName);
        RequestStatus status = jobsApi.create(null, "SomeKey", xmlStr);
        return status;
    }



}
