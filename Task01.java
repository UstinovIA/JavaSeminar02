package Homework;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * *Получить исходную json строку из файла, используя FileReader или Scanner
 * Дана json строка вида:
 * String json =
 * "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
 * "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
 * "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
 * 
 * Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
 * Студент Иванов получил 5 по предмету Математика.
 * Студент Петрова получил 4 по предмету Информатика.
 * Студент Краснов получил 5 по предмету Физика.
 * 
 * Используйте StringBuilder для подготовки ответа. Далее создайте метод,
 * который запишет
 * результат работы в файл. Обработайте исключения и запишите ошибки в лог файл
 * с помощью Logger.
 */
public class Task01 {

    static Logger logger;

    public static void main(String[] args) {
        String path = "Seminar02\\Homework\\json.txt";
        createLogger();
        String json = readInFile(path);
        String[] inputData = convertString(json);
        String outputString = prepareOutput(inputData);
        String pathOutput = "Seminar02\\Homework\\output.txt";
        writeToFile(outputString, pathOutput);
        closeLogger();
    }

    static String readInFile(String filePath) {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
            logger.info("Успешное открытие файла");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Ошибка при открытии файла");
        }
        return stringBuilder.toString();
    }

    static String[] convertString(String data) {
        String[] temp = data.split(",");
        String[] result = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            String[] t = temp[i].split(":");
            result[i] = t[t.length - 1];
            result[i] = result[i].replace("\\", "");
            result[i] = result[i].replace("\"", "");
            if ((i + 1) % 3 == 0) {
                result[i] = result[i].replace("}", "");
                result[i] = result[i].replace("]", "");
            }
        }
        return result;
    }

    static String prepareOutput(String[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            switch ((i + 1) % 3) {
                case 1:
                    sb.append("Студент ");
                    sb.append(data[i]);
                    break;
                case 2:
                    sb.append(" получил ");
                    sb.append(data[i]);
                    break;
                case 0:
                    sb.append(" по предмету ");
                    sb.append(data[i]);
                    sb.append("\n");
                    break;
            }
        }
        return (sb.toString());
    }

    static void writeToFile(String s1, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(s1);
            writer.flush();
            logger.info("Успешная запись в файл");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Ошибка при записи в файл");
        }
    }

    private static void createLogger() {
        logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("Seminar02//Homework//log.txt", true);
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