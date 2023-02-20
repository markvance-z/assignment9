import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static <E extends Comparable<E>> boolean isSorted(ArrayList<E> a) {
        for (int i = 0; i < a.size() -1; i++) {
            if(a.get(i).compareTo(a.get(i +1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> a) {
        for (int i = a.size(); i > 0; i--) {
            for( int j = 0; j < i - 1; j++) {
                if (a.get(j).compareTo(a.get(j+1)) > 0) {
                    E temp = a.get(j);
                    a.set(j, a.get(j));
                    a.set(j+1, temp);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void mergeArray(ArrayList<E> a, int start, int middle, int end) {
        int i = start;
        int j = middle;
        ArrayList<E> c = new ArrayList<>();
        //int[] c = new int[end - start];
        int k = 0;

        while (i < middle && j < end) {
            if (a.get(i).compareTo(a.get(j)) <= 0) {
                c.add(a.get(i));
                i++;
            } else {
                c.add(a.get(j));
                j++;
            }
        }

        while(i < middle) {
            c.add(a.get(i));
            i++;
        }
        
        while (j < end) {
            c.add(a.get(j));
            j++;
        }

        for (i = start; i < end; i++) {
            a.set(i, c.get(i - start));
        }
    }

    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> a, int start, int end) {
        if(end - start <= 1){
            return;
        }

        int middle = (start + end) / 2;
        mergeSort(a, start, middle);
        mergeSort(a, middle, end);
        mergeArray(a, start, middle, end);
    }

    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> a) {
        mergeSort(a, 0, a.size());
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (int i = 0; i < array.length; i++) {
                fileWriter.write(array[i] + "\n");
            }
        } catch (IOException e) {
            
        }
    }

    public static int[] readFileToArray(String filename){
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            File file = new File("temp.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                arrayList.add(Integer.parseInt(s));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
        }

        int[] array = new int[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }
        return array; 

    }

    public static void main(String[] args) throws Exception {
        
        //get array length from user and create random array
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease input the array length: ");
        int arrayLength = scanner.nextInt();
        for (int i = 0; i < arrayLength; i++) {
            arrayList.add(random.nextInt(100));
        }

        //write array to file
        //writeArrayToFile(array, "temp.txt");
        //readFileToArray("temp.txt");

        //print initial array and check if sorted
        System.out.println(arrayList.toString());
        System.out.println("Before sorting, isSorted(array): " + isSorted(arrayList) + "\n");
        
        //call bubbleSort
        //bubbleSort(arrayList);
        
        //call mergeSort
        mergeSort(arrayList);

        //writeArrayToFile(array, "sortedTemp.txt");
        //readFileToArray("sortedTemp.txt");
        
        //print array after sort and check if sorted
        System.out.println(arrayList.toString());
        System.out.println("After sorting, isSorted(array): " + isSorted(arrayList) + "\n");

        scanner.close();
    }

}
