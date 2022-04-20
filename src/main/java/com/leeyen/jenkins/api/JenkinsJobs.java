package com.leeyen.jenkins.api;

import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.job.Workflow;

import java.util.HashMap;
import java.util.List;

public interface JenkinsJobs {
    JobList getJobList(String fileName);

    JobInfo getJobInfo(String jobName);

    String getJobConfig(String jobName);

    boolean updateJobConfig(String jobName, String configFileName);

    String getJobsDescriptions(String jobName);

    Boolean JobsDescriptions(String jobName, String jobDescription);

    RequestStatus deleteJob(String jobName);

    RequestStatus createJob(String configFileName);

    boolean enableJob(String jobName);

    boolean disableJob(String jobName);

    BuildInfo getJobBuildInfo(String jobName, int buildId);

    IntegerResponse buildJob(String jobName);

    IntegerResponse buldParamJob(String jobName, HashMap<String, List<String>> params);

    Integer getLastBuildNumber(String jobName);

    String getLastBuildTimetamp(String jobName);

    boolean renameJob(String jobName, String newName);

    Workflow getWorkFlow(String jobName, int indexId);

}
