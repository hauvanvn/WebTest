import java.util.*;
import java.io.*;

public class post {
    public static void main(String args[]) throws IOException {
        BufferedReader cin = new BufferedReader(new FileReader("post.inp"));
        PrintWriter cout = new PrintWriter(new FileWriter("post.out"));
        String[] line = cin.readLine().split(" ");
        cout.println(Integer.parseInt(line[0]) + Integer.parseInt(line[1]));
        cin.close();
        cout.close();
    }
}
