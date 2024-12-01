package org.testingframework.tools;


import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Utils {


    public String testDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm-ss"));
    }

    public String makefolder(String filepathname ){
        File folder= new File(File.separator+filepathname+File.separator);
        System.out.println("Created folder "+folder.mkdir());
        return folder.getPath();
    }

    public void zipfolder(String filePathTestReport,String file) throws IOException {
        ProgressMonitor progressMonitor;
        try (ZipFile zipFile = new ZipFile(file + ".zip")) {
            progressMonitor = zipFile.getProgressMonitor();
            zipFile.setRunInThread(true);
            zipFile.addFolder(new File(filePathTestReport));
        }

        while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {
            System.out.println("Percentage done: " + progressMonitor.getPercentDone());
            System.out.println("Current file: " + progressMonitor.getFileName());
            System.out.println("Current task: " + progressMonitor.getCurrentTask());
        }

        if (progressMonitor.getResult().equals(ProgressMonitor.Result.SUCCESS)) {
            System.out.println("Successfully added folder to zip");
        } else if (progressMonitor.getResult().equals(ProgressMonitor.Result.ERROR)) {
            System.out.println("Error occurred. Error message: " + progressMonitor.getException().getMessage());
        } else if (progressMonitor.getResult().equals(ProgressMonitor.Result.CANCELLED)) {
            System.out.println("Task cancelled");
        }
    }



  }
