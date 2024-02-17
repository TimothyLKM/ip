package timl;

import timl.Exeptions.EmptyException;
import timl.Exeptions.TimException;
import timl.task.Task;
import timl.task.TaskManager;
import timl.utility.Printer;
import timl.utility.TextParser;

import java.util.Scanner;
import java.lang.String;

public class TimL {
    public static void respondToCommand(String response, Task[] list){
        String command = TextParser.extractCommand(response);
        String message = TextParser.extractMessage(response);
        int emptyIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                emptyIndex = i;
                break;
            }
        }
        int taskIndex;
        switch (command) {
            case "list":
                printList(list, emptyIndex);
                break;
            case "mark":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.mark(taskIndex, list, emptyIndex);
                }catch (NumberFormatException | TimException e){
                    Printer.printInvalidNumber();
                }
                break;
            case "unmark":
                try {
                    taskIndex = Integer.parseInt(message) - 1;
                    TaskManager.unMark(taskIndex, list, emptyIndex);
                }catch (NumberFormatException | TimException e){
                    Printer.printInvalidNumber();
                }
                break;
            case "todo":
                try {
                    TaskManager.addToDo(message, list, emptyIndex);
                }catch (ArrayIndexOutOfBoundsException e){
                    Printer.printTaskOverflow();
                }catch (EmptyException e){
                    Printer.printEmptyContent();
                }
                break;
            case "deadline":
                try{
                    TaskManager.addDeadline(message, list, emptyIndex);
                }catch (ArrayIndexOutOfBoundsException e){
                    Printer.printTaskOverflow();
                }catch (TimException | IndexOutOfBoundsException e) {
                    Printer.printInvalidDeadline();
                }
                break;
            case "event":
                try {
                    TaskManager.addEvent(message, list, emptyIndex);
                }catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printTaskOverflow();
                }catch (TimException | IndexOutOfBoundsException e) {
                    Printer.printInvalidEvent();
                }
                break;
            default:
                Printer.printDefaultError();
                break;
        }
    }



    public static void printList(Task[] list, int index){
        Printer.printList();
        int i = 1;
        for (int j = 0; j < index; j++) {
            System.out.println("    " + i + ": " + list[j].getStatus());
            i++;
        }
        Printer.printLine();
    }


    public static void main(String[] args) {
        Task[] todolist = new Task[100];
        Printer.printGreeting();
        String response;
        Scanner in = new Scanner(System.in);
        response = in.nextLine();
        while (!response.equals("bye")){
            respondToCommand(response, todolist);
            in = new Scanner(System.in);
            response = in.nextLine();
        }
        Printer.printGoodbye();
    }
}
