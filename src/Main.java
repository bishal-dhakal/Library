import java.io.*;
import java.util.Scanner;

public class Main {

    static String fileName =null;

    static Library lib = new Library();
    static Scanner in = new Scanner(System.in);
    static Boolean running = true;
    public static void main(String[] args){
        while(running){
            System.out.println("\n Enter 0 to load library" +
                    "\n Enter 1 to save and quit"+
                    "\n Enter 2 to list all books in Library"+
                    "\n Enter 3 to add book to library");
            int answer = in.nextInt();
            switch(answer){

                case 0:
                    System.out.println("Enter the file name to load");
                    loadScript(in.next());
                    break;

                case 1:
                    saveAndQuit();
                    break;

                case 2:
                    System.out.println(lib.toString());
                    break;
                case 3:
                    addBook();
                    break;
            }
        }
        System.exit(0);
    }
    private static void addBook() {
        int isbn;
        String title,author;
        double price;

        System.out.println("\n Enter title : ");
        title = in.next();

        System.out.println("\n Enter Author : ");
        author = in.next();

        System.out.println("\n Enter ISBN : ");
        isbn = in.nextInt();

        System.out.println("\n Enter price : ");
        price = in.nextInt();

        Book b = new Book(isbn,title,author,price);
        lib.addBook(b);

    }
    private static void saveAndQuit() {
        System.out.println("Enter file name:");
        fileName =in.next();
        running = false;
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(lib);
            fos.close();
            out.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadScript(String fileName) {

        FileInputStream f = null;
        ObjectInputStream in = null;

        File file = new File(fileName+".ser");
        if(file.exists()) {
            try {
                f = new FileInputStream(file);
                in = new ObjectInputStream(f);

                lib = (Library)in.readObject();
                f.close();
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("\n The file doesnot exist!");
        }
    }
}
