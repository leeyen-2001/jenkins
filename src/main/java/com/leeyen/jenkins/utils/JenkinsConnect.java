package com.leeyen.jenkins.utils;

import com.cdancy.jenkins.rest.JenkinsClient;


public class JenkinsConnect {
    public JenkinsConnect(){
    }

    static final String JENKINS_URL = "http://192.168.239.133:8888";
    static final String JENKINS_USERNAME_PASSWORD = "leeyen:123456";

    public static JenkinsClient getClient(){
        JenkinsClient jenkinsClient = null;
        try {
            jenkinsClient = JenkinsClient.builder()
                    .endPoint(JENKINS_URL)
                    .credentials(JENKINS_USERNAME_PASSWORD)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jenkinsClient;
    }

}
