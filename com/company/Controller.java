package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private String consoleValue;
    private String[] parsingArray;
    private String[] parsingPath;
    private final String INCORRECTVALUE = "\\.{3,}|\\/{2,}|\\.{2,}\\w{1,}";
    Pattern pattern = Pattern.compile(INCORRECTVALUE);
    Matcher matcher;
    RouterController routerController = new RouterController();
    Scanner scanner = new Scanner(System.in);

    public Controller(){

    }

    public void start(){
        System.out.println("help");
        while (routerController.isCondition()) {
            System.out.print(routerController.getName());
            consoleValue = scanner.nextLine();

            parsingConsoleValue();
        }
    }

    public void parsingConsoleValue() {
        String replaceNames = consoleValue.replaceAll("\\\\\\s{1}", "\\\\");
        String removeExtraSpaces = replaceNames.replaceAll("\\s+", " ").trim();

        matcher = pattern.matcher(removeExtraSpaces);

        if (removeExtraSpaces.equals("") || removeExtraSpaces.equals(" ")) {
            start();
        }else if(matcher.find()){
            routerController.wrongValue(consoleValue);
            start();
        }else{
            parsingArray = removeExtraSpaces.split(" ");

            if(parsingArray.length > 1) {
                for(int i = 0; i < parsingArray.length; i++){
                    String replaceSpaceNames = parsingArray[i].replaceAll("\\\\", " ");
                    parsingArray[i] = replaceSpaceNames;
                }
                parsingPath = parsingArray[1].split("/");
            }
            commandValidator();
        }
    }

    public void commandValidator() {
        for(int i = 0; i < routerController.getCOMMANDS().length; i++){
                if(parsingArray[0].equals("help") && parsingArray.length == 1 ){
                    routerController.help();
                    return;
                }else if(parsingArray[0].equals("ls") && parsingArray.length == 1){
                    routerController.ls();
                    return;
                }else if(parsingArray[0].equals("cd") && parsingArray.length == 2){
                    routerController.cd(parsingPath);
                    return;
                }else if(parsingArray[0].equals("exit") && parsingArray.length == 1){
                    routerController.exit();
                    return;
                }else if (i == routerController.getCOMMANDS().length - 1){
                    routerController.wrongValue(consoleValue);
                    start();
            }
        }
    }
}

