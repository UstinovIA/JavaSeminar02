package Homework;

// *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

public class Task02 {
    public static void main(String[] args) {
        int[] array = { 9, 10, 9, 4, 5, 6, 6, 3, 9, 9 };
        outputArray(array);
        sortArray(array);
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
}
