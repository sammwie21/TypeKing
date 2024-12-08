import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScanFile {
    private static Scanner s = new Scanner(System.in);
    public static ArrayList<String> words = new ArrayList<>();
    public static ArrayList<String> fonts = new ArrayList<>();

    public ScanFile(Scanner s){
        this.s = s;
    }

    // get words from a txt file and put into an array list
    public static void createWords() throws FileNotFoundException {
        File file = new File("src\\words.txt");
        s = new Scanner(file);
        while (s.hasNextLine()) {
            words.add(s.nextLine().toLowerCase().trim()); // Trim extra spaces
        }
        s.close();
    }

    // get fonts from a txt file and put into an array list
    public static void createFonts() throws FileNotFoundException{
        File file = new File("src\\fonts.txt");
        s = new Scanner(file);
        while (s.hasNextLine()) {
            fonts.add(s.nextLine());
        }
        s.close();
    }
}