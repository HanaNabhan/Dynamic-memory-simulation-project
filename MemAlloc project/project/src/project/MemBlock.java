package project;

public class MemBlock {
    private int size;
    private int start_address;
    private int end_address;

    public MemBlock(int start, int size) {
        this.start_address = start;
        this.end_address = size + start - 1;
        this.size = size;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int s) {
        size = s;
    }

    public int getStart_address() {
        return start_address;
    }
    public void setStart_address(int s) {
         start_address = s;
    }
    public void setEnd_address(int s) {
        end_address = s;
   }

    public int getEnd_address() {
        return end_address;
    }
    
}
