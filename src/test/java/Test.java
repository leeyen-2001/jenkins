import com.leeyen.jenkins.api.JenkinsJobs;
import com.leeyen.jenkins.api.impl.JenkinsJobsImpl;

public class Test {
    public static void main(String[] args) {
        JenkinsJobs jenkinsJobs = new JenkinsJobsImpl();

        System.out.println(jenkinsJobs.getJobBuildInfo("leeyen", 1));
    }
}
