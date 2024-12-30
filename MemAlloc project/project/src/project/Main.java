package project;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MemAlloc memAlloc = new MemAlloc(); 
        Random random = new Random();
       
        for (int i = 1; i <= 20; i++) {
            int size = random.nextInt(300) + 1;
            int timeout = random.nextInt(20000) + 1; 
            Process process = new Process(size, timeout);
            memAlloc.addProcessToStartList(process); 
        }
       
        // Run the simulation
        memAlloc.transformation();
        System.out.println("finsihed");
    }
}
