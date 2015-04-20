package neu.mapreduce.io.sockets;

import api.JobConf;
import neu.mapreduce.core.mapper.MapRun;

/**
 * Created by srikar on 4/19/15.
 */
public class SlaveMapRunThread implements Runnable{

    private String inputFilePath;
    private String mapOutputFilePath;
    private String shuffleOutputFolder;
    private String clientJarPath;
    private JobConf jobConf;

    public SlaveMapRunThread(String inputFilePath, String mapOutputFilePath, String shuffleOutputFolder, String clientJarPath, JobConf jobConf) {
        this.inputFilePath = inputFilePath;
        this.mapOutputFilePath = mapOutputFilePath;
        this.clientJarPath = clientJarPath;
        this.shuffleOutputFolder = shuffleOutputFolder;
        this.jobConf = jobConf;
    }

    @Override
    public void run() {
        // run maprun
        new MapRun().mapRun(inputFilePath, mapOutputFilePath, shuffleOutputFolder, clientJarPath, jobConf.getMapperClassName(), jobConf.getMapKeyInputClassName(), jobConf.getMapValueInputClassName(), jobConf.getMapKeyOutputClassName(), jobConf.getMapValueOutputClassName(), jobConf.isIsCombinerSet());
        SlaveListener.status = ConnectionTypes.JOB_COMPLETE;
    }
}