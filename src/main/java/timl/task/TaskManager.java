package timl.task;

import timl.exceptions.EmptyException;
import timl.utility.Printer;
import timl.utility.TextParser;
import timl.exceptions.TimException;

import java.util.ArrayList;

public class TaskManager {
    public static ArrayList<Task> list = new ArrayList<>();
    public void addTask(Task t) {
        list.add(t);
    }
    public static String getTaskType(Task t) {
        String words = t.getStatus();
        return words.substring(0, 3);
    }
    public static void printList(){
        Printer.printList();
        for (Task i : list) {
            System.out.println("    " + (list.indexOf(i) + 1) + ": " + i.getStatus());
        }
        Printer.printLine();
    }
    public static void mark(int taskIndex) throws TimException {
        if ((taskIndex < 0) |(list.size() <= taskIndex)){
            throw new TimException();
        }
        list.get(taskIndex).isMarked = true;
        Printer.printMarkedOpening();
        System.out.println("    [X] " + list.get(taskIndex).getDescription());
        Printer.printLine();
    }
    public static void unMark(int taskIndex) throws TimException {
        if ((taskIndex < 0) |(list.size() <= taskIndex)){
            throw new TimException();
        }
        list.get(taskIndex).isMarked = false;
        Printer.printUnMarkedOpening();
        System.out.println("    [ ] " + list.get(taskIndex).getDescription());
        Printer.printLine();
    }
     public static void addToDo(String message) throws EmptyException {
        if(message.isBlank()){
            throw new EmptyException();
        }
        Todo task = new Todo(message);
        list.add(task);
        Printer.printAddedTaskOpening();
        System.out.println("    " + task.getStatus());
        System.out.println("    Now you have " + list.size() + " task in the list.");
        Printer.printLine();
     }
     public static void addDeadline(String message) throws TimException{
         if(message.isEmpty()){
             throw new TimException();
         }
         String taskName = TextParser.extractTaskName(message);
         String finishBy = TextParser.extractDeadlineTime(message);
         Deadline newDeadline = new Deadline(taskName, finishBy);
         list.add(newDeadline);
         Printer.printLine();
         System.out.println("    " + newDeadline.getStatus());
         System.out.println("    Now you have " + list.size() + " task in the list.");
         Printer.printLine();
     }
     public static void addEvent(String message) throws TimException{
         if(message.isEmpty()){
             throw new TimException();
         }
         String[] eventDurations = TextParser.extractEventTime(message);
         String taskName = TextParser.extractTaskName(message);
         Events newEvent = new Events(taskName, eventDurations[0], eventDurations[1]);
         list.add(newEvent);
         Printer.printLine();
         System.out.println("    " + newEvent.getStatus());
         System.out.println("    Now you have " + list.size() + " task in the list.");
         Printer.printLine();
     }
    public static void delete(int taskIndex) throws TimException {
        if ((taskIndex < 0) |(list.size() <= taskIndex)){
            throw new TimException();
        }
        Printer.printDeleteOpening();
        System.out.println("     " + list.get(taskIndex).getStatus());
        list.remove(taskIndex);
        System.out.println("     Now you have " + list.size() + " task in the list.");
        Printer.printLine();
    }
    public static void find(String keyword){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Task i : list){
            if (i.description.contains(keyword)) {
                indexes.add(list.indexOf(i));
            }
        }

        if (indexes.isEmpty()) {
            Printer.printFoundNothingMessage();
            return;
        }
        Printer.printFindOpening();
        int k = 1;
        for (int i : indexes) {
            System.out.println("    " + k + ": " + list.get(i).getStatus());
            k ++;
        }
        Printer.printLine();
    }
}