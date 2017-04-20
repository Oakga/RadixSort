import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by olwin on 3/7/17.
 */
public class RadixSort {
    public int tableSize = 10, currentTable, previousTable,
            longestDataLength, currentDigit, tableIndex, currentQueue;

    PrintWriter output;
    Scanner input;
    File inFile, outFile;

    Hashtable<Integer, linkedListQueue>[] HashTables;

    RadixSort(File inFile, File outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
        currentTable = previousTable = longestDataLength = currentDigit = tableIndex = currentQueue = 0;
        HashTables = new Hashtable[2];
        HashTables[0] = new Hashtable<Integer, linkedListQueue>(tableSize);
        HashTables[1] = new Hashtable<Integer, linkedListQueue>(tableSize);

        Integer data = 0;
        while (data < 10) {
            linkedListQueue newQueue1 = new linkedListQueue();
            linkedListQueue newQueue2 = new linkedListQueue();
            HashTables[0].put(data, newQueue1);
            HashTables[1].put(data, newQueue2);
            data++;
        }//intialized 10 new queue for both hash tables

        sort();
    }

    void sort() {
        try {
            output = new PrintWriter(outFile, "UTF-8");
            input = new Scanner(inFile);

            String data;
            int number;
            linkedListStack Stack = new linkedListStack();

            while (input.hasNext()) {
                data = input.next();
                number = Integer.parseInt(data);
                listNode newNode = new listNode(number);
                Stack.push(newNode);
                if (data.length() > longestDataLength) longestDataLength = data.length();
            }
            output.write(Stack.printStack());

            currentDigit = 0;
            currentTable = 0;
            previousTable = 1;

            int digit;
            while (!Stack.isEmpty()) {
                listNode node = Stack.pop();
                digit = getDigit(node);
                digit = hashIndex(digit);
                addTail(node, digit);
            }

            printTable();
            currentDigit++;

            while (currentDigit < longestDataLength) {

                int temp = currentTable;
                currentTable = previousTable;
                previousTable = temp;

                move();
                printTable();
                currentDigit++;
            }

            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addTail(listNode node, int index) {
        tableIndex = index;
        linkedListQueue queue = HashTables[currentTable].get(tableIndex);
        queue.addTail(node);
    }

    int getDigit(listNode node) {
        int a = currentDigit + 1, b = currentDigit;
        int digit, data = node.Data;

        a = (int) (Math.pow(10, a));
        digit = data % a;

        b = (int) (Math.pow(10, b));
        digit = digit / b;

        return digit;
    }

    int hashIndex(int digit) {
        return (digit % tableSize);}//hash function


    void printTable() {
        output.write("\n");
        Hashtable table = HashTables[currentTable];
        Integer key=0;
        linkedListQueue queue;
        while (key<=tableSize-1) {
            queue = (linkedListQueue) table.get(key);
            if (!queue.isEmpty()) {
                output.write("Table[" + currentTable + "][" + key + "]: " + queue.printQueue() + "\n");
            }
            key++;
        }
    }


    void move() {
        Hashtable Ptable = HashTables[previousTable];

        Integer key=0;
        listNode node;
        linkedListQueue queue;
        int digit, hashIndex;

        currentQueue=0;
        while (currentQueue<=tableSize-1) {
            queue = (linkedListQueue) Ptable.get(key);
            while (!queue.isEmpty()) {
                node = queue.deleteHead();
                digit = getDigit(node);
                hashIndex = hashIndex(digit);
                addTail(node, hashIndex);//add to the current Table
            }
            key++;
            currentQueue++;
        }
    }
}
