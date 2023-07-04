package Homework;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

public class Task02 {
    static Logger logger;

    public static void main(String[] args) {
        int[] array = { 9, 10, 9, 4, 5, 6, 6, 3, 9, 9 };
        outputArray(array);
        createLogger();
        sortArray(array);
        closeLogger();
        outputArray(array);
    }

    static void sortArray(int[] arr) {
        Boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                    logger.info("Число " + arr[i+1] + " на позиции "+ i + " поменялось с числом "+arr[i]+" на позиции "+ (i+1));
                }
            }
        }
    }

    static void outputArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void createLogger() {
        logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("Seminar02//Homework//logTask02.txt", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleFormatter formatter = new SimpleFormatter();
        if (fileHandler != null) {
            fileHandler.setFormatter(formatter);
        }
    }

    private static void closeLogger() {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
    }
}
