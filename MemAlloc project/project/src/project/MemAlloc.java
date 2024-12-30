package project;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class MemAlloc {

     LinkedList<MemBlock> memoryBlocks = new LinkedList<>();
     Queue<Process> startingList = new LinkedList<>();
     LinkedList<Process> runningList = new LinkedList<>();
     PriorityQueue waitingList = new PriorityQueue(20); // Using custom PriorityQueue
     Queue<Process> completed = new LinkedList<>();
     int time = 1 ;


    public MemAlloc() {
        memoryBlocks.add(new MemBlock(0, 1024)); // Initialize memory block with size 1024
    }

    public void addProcessToStartList(Process p) {
        startingList.add(p);
    }

    public void addRunningState(Process p) {
        runningList.add(p);
    }

    public void addProcessToWaitingList(Process p) {
        waitingList.enqueue(p);
    }

    public void removeProcessFromStartList() {
        startingList.remove();
    }

    public void allocateMem(Process p) {
        for (MemBlock block : memoryBlocks) {
            if (p.getSize() <= block.getSize()) {
                int endAddress = block.getStart_address() + p.getSize() - 1;
                if (endAddress > block.getEnd_address()) {
                    System.err.printf("Error: Process{id=%d, size=%d} exceeds block limit!\n", p.getId(), p.getSize());
                    addProcessToWaitingList(p);
                    return;
                }

                // Allocate memory
                p.set_allocBlock(new MemBlock(block.getStart_address(), p.getSize()));
                addRunningState(p);
                block.setStart_address(block.getStart_address() + p.getSize());
                block.setSize(block.getSize() - p.getSize());
                if (block.getSize() == 0) {
                    memoryBlocks.remove(block);
                }
                return;
            }
        }
        addProcessToWaitingList(p); // Process is too large, added to waiting list
    }

    public void transformation() {

        while (!startingList.isEmpty() || !runningList.isEmpty() || !waitingList.IsEmpty()) {
            if (!startingList.isEmpty()) {
                allocateMem(startingList.poll()); // Allocate memory for the next process
            }

            for (Process ps : runningList) {
                ps.setTimeOut((int) (ps.getTimeOut() - 1000));
                if (ps.getTimeOut() <= 0) {
                    completed.add(ps);
                }
            }

            for (Process completedProcess : completed) {
                runningList.remove(completedProcess);
                releaseMemory(completedProcess);
            }

           
            int sizeBefore = waitingList.size(); 
            int x=0;// 
            while (!waitingList.IsEmpty()) { 
                Process waitingProcess = waitingList.dequeue(); 
                allocateMem(waitingProcess); 
                    x++;
                    if(x>sizeBefore)
                        break;
               
            }


            displayState();

            // Simulate a 1-second delay
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted: " + e.getMessage());
            }
        }
    }

    public void displayState() {
        System.out.println("______");
        System.out.printf("At time %d\n",time++); // Simulated time

        // Running processes
        System.out.println("RUNNING:");
        for (Process p : runningList) {
            MemBlock allocBlock = p.getMblock();
            if (allocBlock != null) {
                System.out.printf("process %d with size: %d, timeout: %d\n", p.getId(), p.getSize(), p.getTimeout());
            }
        }
        if (runningList.isEmpty()) {
            System.out.println(); 
        }

        System.out.println("______");

        // Waiting processes
        System.out.println("waiting:");
        for (int i = 0; i < waitingList.size(); i++) {
            Process waitingProcess = waitingList.peekatIndex(i);
            if (waitingProcess != null) {
                System.out.printf("process %d with size: %d\n", waitingProcess.getId(), waitingProcess.getSize());
            }
        }
        if (waitingList.IsEmpty()) {
            System.out.println(); 
        }

        System.out.println("______________________");
    }




    private void releaseMemory(Process completed) {
        if (completed.getMblock() != null) {
            memoryBlocks.add(completed.getMblock());
            mergeFreeBlocks();
            completed.set_allocBlock(null);
        }
    }

    private void mergeFreeBlocks() {
        memoryBlocks.sort(Comparator.comparingInt(MemBlock::getStart_address));
        ListIterator<MemBlock> it = memoryBlocks.listIterator();

        while (it.hasNext()) {
            MemBlock current = it.next();

            if (!it.hasNext()) break;

            MemBlock next = it.next();

            if (current.getEnd_address() + 1 == next.getStart_address()) {
                current.setSize(current.getSize() + next.getSize());
                current.setEnd_address(next.getEnd_address());
                it.remove();
            } else {
                it.previous();
            }
        }
    }
}
