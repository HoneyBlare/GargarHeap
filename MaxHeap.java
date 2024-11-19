import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int heapSize;

    public MaxHeap(int capacity) {
        heap = new int[capacity];
        heapSize = 0;
    }

    // Insert a new element into the heap
    public void insert(int key) {
        if (heapSize == heap.length) {
            System.out.println("Heap Overflow!");
            return;
        }

        heap[heapSize] = key;
        heapSize++;
        heapifyUp(heapSize - 1);
    }

    // Remove the maximum element from the heap
    public int deleteMax() {
        if (heapSize == 0) {
            System.out.println("Heap is empty!");
            return -1; // Or throw an exception
        }

        int max = heap[0];
        heap[0] = heap[heapSize - 1]; // Replace the root with the last element
        heapSize--; // Decrease the heap size
        heapifyDown(0); // Heapify down from the root to maintain the heap property
        return max;
    }

    // Heapify up from a given index (for insertion)
    private void heapifyUp(int index) {
        while (index > 0 && heap[index] > heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // Heapify down from a given index (for deletion)
    private void heapifyDown(int index) {
        while (leftChild(index) < heapSize) {
            int largest = leftChild(index);
            if (rightChild(index) < heapSize && heap[rightChild(index)] > heap[largest]) {
                largest = rightChild(index);
            }
            if (heap[index] < heap[largest]) {
                swap(index, largest);
                index = largest;
            } else {
                break; // Heap property is satisfied
            }
        }
    }

    // Helper functions for heap operations
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Other methods for accessing and etc
    public int peek() {
        if (heapSize == 0) {
            return -1;
        }
        return heap[0];
    }

    public int size() {
        return heapSize;
    }

    public void printHeap() {
        // Implement your own heap printing logic
        System.out.println("Heap: " + Arrays.toString(heap));
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(1);
        maxHeap.insert(10);
        maxHeap.insert(3);
        maxHeap.insert(5);
        maxHeap.insert(4);
        maxHeap.insert(8);
        maxHeap.insert(2);
        maxHeap.insert(7);
        maxHeap.insert(6);
        maxHeap.insert(9);
        maxHeap.printHeap();
        System.out.println("Max: " + maxHeap.deleteMax());
        maxHeap.printHeap();
    }
}