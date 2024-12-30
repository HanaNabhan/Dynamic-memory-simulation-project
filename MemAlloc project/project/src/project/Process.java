package project;
public class Process {
    private final short id;
    private int size;
    private int timeOut;
    private MemBlock allocBlock;
    private static short counter=1;

    public Process(int size, int timeOut) {
    	 this.id = counter++; // Assign and increment nextId
         this.size = size;
         this.timeOut = timeOut;
         this.allocBlock = null;
    }
    public void set (MemBlock allocBlock)
    {
        this.allocBlock=allocBlock;
    }

    public  short getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public MemBlock getMblock() {
        return allocBlock;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", size=" + size + ", timeOut=" + timeOut + '}';
    }
    public void setTimeOut(int time) {
        this.timeOut = time;
    }
    public int getTimeout() {
    	return timeOut;
    }
    public void set_allocBlock (MemBlock allocBlock)
    {
        this.allocBlock=allocBlock;
        }
}