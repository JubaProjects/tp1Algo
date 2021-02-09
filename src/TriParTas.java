import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TriParTas {
    private ArrayList<Integer> array;

    /**Constructeur*/
    public TriParTas(ArrayList<Integer> array) {
        this.array = array;
    }

    /**Getter*/
    public int getSize(){
        return array.size();
    }

    /**Autres méthodes*/
    public void permutation(int index1, int index2){
        int temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
    public boolean isSorted(){
        for (int i=0; i<getSize()-1; i++){
            if (array.get(i)>array.get(i+1)) return false;
        }
        return true;
    }
    public String toString(){
        return array.toString();
    }

    /**Tri par tas*/
    public void Tri_par_Tas(){
        constructHeap();
        int heapSize = getSize();
        for (int i=getSize()-1; i>=1; i--){
            permutation(0, i);
            heapSize--;
            pileUp(0, heapSize);
        }
    }
    public void constructHeap(){
        int heapSize = getSize();
        int initialIndex =Math.abs( heapSize/2 )-1; //indices des éléments autres que les feuilles.
        for(int i = initialIndex; i>=0; i--){
            pileUp(i, heapSize);
        }
    }
    public void pileUp(int i, int heapSize){
        int left = 2*i+1;
        int right = 2*i+2;
        int maxValueIndex;
        if (left < heapSize && array.get(left)>array.get(i)){
            maxValueIndex = left;
        }
        else {maxValueIndex = i;}
        if (right< heapSize && array.get(right) > array.get(maxValueIndex)){
            maxValueIndex = right;
        }
        if (maxValueIndex != i){ //si la racine n'est pas le plus grand des trois
            permutation(i, maxValueIndex);
            pileUp(maxValueIndex, heapSize);
        }
    }

    /**Test*/
    public static void main(String args[]){
        //averageTime();
        test_Tri_par_Tas();
    }
    public static ArrayList<Integer> makeArray(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre d'éléments: ");
        int size = input.nextInt();
        //int size=1000;
        ArrayList<Integer> array = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i<size; i++){
            array.add(rand.nextInt(100000));
        }
        return array;
    }
    public static float test_Tri_par_Tas(){
        TriParTas sequence = new TriParTas(makeArray());
        System.out.println("Tableau initial: \n" + sequence.toString());
        long startTime = System.nanoTime();
        sequence.Tri_par_Tas();
        long endTime = System.nanoTime();
        long timeElapse = endTime - startTime;
        System.out.println("Tableau trié: \n" + sequence.toString());
        System.out.println("Tri par tas: " + sequence.isSorted());
        System.out.println("Temps d'éxécution: " + timeElapse + " nanosecondes");
        return timeElapse;
    }
    public static void averageTime(){
        float s=0;
        int i=1;
        while (i<=100){
            s=s+test_Tri_par_Tas();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }
}
