/**
 * Created by olwin on 3/5/17.
 */

public class listNode {
    int Data;
    listNode next;

    public listNode(int data) {
        this.Data = data;
        next = null;
    }

    public void delete() {
        this.Data = 0;
        this.next = null;
    }
}
