# Memory Allocation Simulation

This project is a simulation of a memory allocation system written in Java. It demonstrates how memory blocks are dynamically allocated and managed for various processes, using different states such as `Starting`, `Running`, `Waiting`, and `Completed`.

## Features

- Dynamic memory allocation for processes based on size and priority.
- Custom implementation of a priority queue to manage waiting processes.
- Real-time simulation with memory release and reallocation.
- Processes transition between states (`Starting`, `Running`, `Waiting`, and `Completed`).
- Free memory blocks are merged for efficient memory usage.

## Classes Overview

### 1. **MemAlloc**
The main memory allocator class responsible for:
- Managing memory blocks and process states.
- Allocating and releasing memory.
- Handling transitions of processes between different states.
- Displaying the current system state at each time step.

### 2. **MemBlock**
Represents a block of memory with:
- Start and end addresses.
- Size of the block.
- Methods to modify and access memory block properties.

### 3. **PriorityQueue**
A custom priority queue for managing waiting processes based on:
- Memory size (smaller sizes get higher priority).
- Timeout (shorter timeouts get higher priority if sizes are equal).

### 4. **Process**
Represents a process with:
- Unique ID.
- Memory size requirement.
- Timeout (execution time).
- Allocated memory block.

### 5. **Main**
The entry point of the application. It:
- Initializes the memory allocator.
- Creates a set of random processes.
- Adds processes to the starting list.
- Runs the memory allocation simulation.

## How It Works

1. **Initialization**:
   - The system starts with a single memory block of size 1024 units.
   - Random processes are generated with varying memory size requirements and timeout values.

2. **Memory Allocation**:
   - Processes are moved from the `Starting` list to the `Running` list if there is enough free memory.
   - If insufficient memory is available, processes are added to the `Waiting` list.

3. **Process Execution**:
   - The system simulates process execution by reducing the timeout of `Running` processes.
   - Completed processes are removed from the `Running` list, and their memory is released.

4. **Memory Management**:
   - Free memory blocks are merged to optimize memory usage.
   - Processes in the `Waiting` list are reattempted for allocation.

5. **Simulation**:
   - The system runs in real-time, updating the state every second.
   - The state of `Running` and `Waiting` processes is displayed at each time step.


## Example Output

```
______
At time 1
RUNNING:
Process 1 with size: 120, timeout: 19000
Process 2 with size: 200, timeout: 18000
______
WAITING:
Process 3 with size: 300
Process 4 with size: 150
______________________
```

## Customization

- **Memory Block Size**: Change the initial memory block size in the `MemAlloc` constructor.
- **Process Count**: Modify the loop in `Main` to generate a different number of processes.
- **Priority Criteria**: Adjust the priority logic in the `PriorityQueue` class.


