package com.company;

import java.io.File;

public class RouterController {
    File path = new File("/Users");
    File tempPath;
    private boolean condition = true;
    private static final String[] COMMANDS = {"help",
            "ls  (list files and directories)",
            "cd <path>  (use \"\\\" with names containing spaces, for example \"cd test\\ 1\")",
            "exit"};

    public RouterController(){

    }

    public void help(){
        for (int i = 1; i < COMMANDS.length; i ++){
            System.out.println(" > " + COMMANDS[i]);
        }
    }

    public void ls(){
        File[] files = path.listFiles();

        try {
            System.out.printf(" %-50s %s%n","<---Name--->", "<---Bytes--->");
            for(File f : files){
                System.out.printf(" %-50s %s%n", f.getName(), f.length());
            }
        }catch (NullPointerException exception){
            System.out.println("Problem with the permissions of the directory"); //при вызове метода ls в директориях по типу Downloads возвращает NullPointerException
        }
    }

    public void cd(String[] cdPath){
        try {
            tempPath = new File(path.getPath());

            for (int i = 0; i < cdPath.length; i++) {
                if (cdPath[i].equals("..")) {
                    tempPath = new File(tempPath.getParent() + "/");
                } else {
                    tempPath = new File(tempPath.getPath() + "/" + cdPath[i] + "/");
                }
            }

            if(tempPath.isDirectory()){
                path = new File(tempPath.getPath());
            }else {
                System.out.print("Incorrect directory path \"");
                for(String incorrectValue : cdPath){
                    System.out.print(incorrectValue + "/");
                }
                System.out.println("\"");
            }
        }catch (NullPointerException exception){
            System.out.println("Incorrect directory path");
        }
    }

    public void exit() {
       System.out.println("See you next time");
       condition = false;
    }

    public String getName() {
        if(path.getPath().equals("/")){
            return path.getPath() + " > ";
        }else {
            return path.getName() + " > ";
        }
    }

    public void wrongValue(String value){
        System.out.println("command not found: " + value);
    }

    public String[] getCOMMANDS() {
        return COMMANDS;
    }

    public boolean isCondition() {
        return condition;
    }
}
