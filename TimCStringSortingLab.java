/*
Timothy Cartmel
This simulation reads in words from Undictionary.txt 
and sorts them alphabetically using different sort methods
*/
package timcstringsortinglab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author timca
 */
public class TimCStringSortingLab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] scrambledArray = new String[50000];  
        BufferedReader br;
        
        int[] intOgArray  = new int[50000];
        int[] intUseArray = new int[50000];
        
        for(int i = 0; i < 50000; i++){
            intOgArray[i] = i;
            intUseArray[i] = i;
        }
        
        try{
            br =  new BufferedReader(new FileReader("undictionary.txt"));  
            String line = br.readLine();
            int counter = 0;
         
            while (line != null){
                scrambledArray[counter] = line;
                line = br.readLine();
                counter++;
            } 
        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
            System.exit(1);
        }
        catch(IOException ex){
            System.out.println(ex);
            System.exit(1);
        }
        
        System.out.println( "Bubblesort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = bubbleSort(numOfWords, scrambledArray, intUseArray);
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime );
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
        
        System.out.println( "Quicksort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = quickSort(0, numOfWords-1, scrambledArray, intUseArray);
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime);
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
        
        System.out.println( "Selectionsort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = selectionSort(numOfWords, scrambledArray, intUseArray);  
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime);
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
        
        System.out.println( "Insertionsort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = insertionSort(numOfWords, scrambledArray, intUseArray);  
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime);
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
        
        System.out.println("Mergesort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = mergeSort(0, numOfWords-1, scrambledArray, intUseArray);
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime);
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
        
        System.out.println("Heapsort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = heapSort(numOfWords, scrambledArray, intUseArray);
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime);
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
        
        System.out.println("Shellsort times: ");
        
        for(int j = 1; j <= 9; j++){ // num of words in each sorting method
            int numOfWords = 5000 * j;
            long runtime = shellSort(numOfWords, scrambledArray, intUseArray);
            if(verify(numOfWords, scrambledArray, intUseArray)==false)
                System.out.println("Sorting method wasn't successful.");
            else
                System.out.printf("%d: %d%n", numOfWords, runtime);
            System.arraycopy( intOgArray, 0, intUseArray, 0, numOfWords);
        }
    }
    
    static long bubbleSort(int numOfWords, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
        int n = numOfWords; 
        
        for (int i= 0; i < n-1; i++){ 
            for (int j= 0; j < n-i-1; j++){ 
                int idx1= intUseArray[j  ];
                int idx2= intUseArray[j+1];
                
                if (scrambledArray[idx1].compareTo(scrambledArray[idx2]) > 0){  
                    int temp = intUseArray[j]; 
                    intUseArray[j] = intUseArray[j+1]; 
                    intUseArray[j+1] = temp; 
                } 
            }
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static int quickSortPartition(int low, int high, String scrambledArray[], int []intUseArray){ 
        int iPivot = intUseArray[high];
        String pivot = scrambledArray[iPivot];
        
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            int idx1 = intUseArray[j];
            String compare1 = scrambledArray[idx1];
            // If current element is smaller than the pivot 
            if (compare1.compareTo(pivot) < 0) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = intUseArray[i]; 
                intUseArray[i] = intUseArray[j]; 
                intUseArray[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = intUseArray[i+1]; 
        intUseArray[i+1] = intUseArray[high]; 
        intUseArray[high] = temp; 
  
        return i+1; 
    }
    
    
    static long quickSort(int low, int high, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
    
        if (low < high){ 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = quickSortPartition(low, high, scrambledArray, intUseArray); 
  
            // Recursively sort elements before 
            // partition and after partition 
            quickSort(low, pi-1, scrambledArray, intUseArray); 
            quickSort(pi+1, high, scrambledArray, intUseArray); 
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static long selectionSort(int numOfWords, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
        int n = numOfWords;
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int int_idx = i;
            int min_idx = intUseArray[i];
            for (int j = i+1; j < n; j++){ 
                int idx2 = intUseArray[j];
                
                if (scrambledArray[idx2].compareTo(scrambledArray[min_idx]) < 0){
                    min_idx = idx2;
                    int_idx = j;
                } 
            }
            // Swap the found minimum element with the first element 
            int temp = intUseArray[int_idx]; 
            intUseArray[int_idx] = intUseArray[i]; 
            intUseArray[i] = temp; 
        }
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static long insertionSort(int numOfWords, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
        int n = numOfWords; 
        for (int i = 1; i < n; i++) { 
            int key = intUseArray[i]; 
            int j; 
            
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            for(j = i-1; j >= 0; j--){ 
                int idx2= intUseArray[j];
                if(scrambledArray[idx2].compareTo(scrambledArray[key]) <= 0)
                    break;
                intUseArray[j + 1] = intUseArray[j];    
            } 
            intUseArray[j + 1] = key; 
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static void mergeSortHelper(int l, int m, int r, String scrambledArray[], int[] intUseArray){ 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = intUseArray[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = intUseArray[m + 1+ j]; 
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2){ 
            int idx1= L[i];
            int idx2= R[j];
            
            if(scrambledArray[idx1].compareTo(scrambledArray[idx2]) <= 0){ 
                intUseArray[k] = L[i]; 
                i++; 
            } 
            else{ 
                intUseArray[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1){ 
            intUseArray[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2){ 
            intUseArray[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
    
    static long mergeSort(int l, int r, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
  
        if (l < r){ 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            mergeSort(l, m, scrambledArray, intUseArray); 
            mergeSort(m+1, r, scrambledArray, intUseArray); 
  
            // Merge the sorted halves 
            mergeSortHelper(l, m, r, scrambledArray, intUseArray); 
        }  
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static void heapify(int[] intUseArray, String scrambledArray[], int n, int i){ 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n){
            int idx1= intUseArray[l];
            int idx2= intUseArray[largest];
            if(scrambledArray[idx1].compareTo(scrambledArray[idx2]) > 0)
                largest = l; 
        }
        // If right child is larger than largest so far 
        if (r < n){ 
            int idx1= intUseArray[r];
            int idx2= intUseArray[largest];
            if(scrambledArray[idx1].compareTo(scrambledArray[idx2]) > 0)    
                largest = r; 
        }
        // If largest is not root 
        if (largest != i){ 
            int swap = intUseArray[i]; 
            intUseArray[i] = intUseArray[largest]; 
            intUseArray[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(intUseArray, scrambledArray, n, largest); 
        } 
    }
    static long heapSort(int numOfWords, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
        int n = numOfWords;
        
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(intUseArray, scrambledArray, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
            int temp = intUseArray[0]; 
            intUseArray[0] = intUseArray[i]; 
            intUseArray[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(intUseArray, scrambledArray, i, 0); 
        } 
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static long shellSort(int numOfWords, String scrambledArray[], int[] intUseArray){
        long startTime = System.nanoTime();
        int n = numOfWords;
        
        for (int gap = n/2; gap > 0; gap /= 2){ 
            // Do a gapped insertion sort for this gap size. 
            // The first gap elements a[0..gap-1] are already 
            // in gapped order keep adding one more element 
            // until the entire array is gap sorted 
            for (int i = gap; i < n; i += 1){ 
                // add a[i] to the elements that have been gap 
                // sorted save a[i] in temp and make a hole at 
                // position i 
                int temp = intUseArray[i]; 
                // shift earlier gap-sorted elements up until 
                // the correct location for a[i] is found 
                int j; 
                
                for (j = i; j >= gap && 
                    scrambledArray[intUseArray[j - gap]].compareTo(scrambledArray[temp]) > 0; j -= gap){
                    intUseArray[j] = intUseArray[j - gap];   
                }  
                
                //for (j = i; j >= gap && intUseArray[j - gap] > temp; j -= gap) 
                //    intUseArray[j] = intUseArray[j - gap];
                // put temp (the original a[i]) in its correct location 
                intUseArray[j] = temp; 
            } 
        } 
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    static boolean verify(int numOfWords, String scrambledArray[], int[] intUseArray){
        int n = numOfWords;
        for(int i =0; i < n-1; n++){
            int idx1= intUseArray[i  ];
            int idx2= intUseArray[i+1];
            
            if(scrambledArray[idx1].compareTo(scrambledArray[idx2]) > 0)
                return false; 
        }
        return true;
    }
}
