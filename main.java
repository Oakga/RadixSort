import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by olwin on 3/7/17.
 */
public class main {
    public static void main(String args[]) throws FileNotFoundException {
        File inFile = new File(args[0]);
        File outFile = new File(args[1]);
        RadixSort tables = new RadixSort(inFile,outFile);//create 2 hashtable with all 10 linkedlist queue intialized
    }
}
