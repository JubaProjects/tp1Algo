import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TriParInsertion {
    private ArrayList<Integer> array;

    /**Constructeur*/
    public TriParInsertion(ArrayList<Integer> array) {
        this.array = array;
    }

    /**Getters & Setters*/
    public void setArray(ArrayList array) {
        this.array = array;
    }
    public int getSize() {
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

    /**Tri Par Insertion */

    public void Tri_Par_Insertion(){
        int i,j,res;
        for( i = 1 ; i < array.size() ;i++) {
            res = array.get(i);
            j = i;
            while( j > 0 && array.get(j-1) > res ){
                int a = array.get(j-1);
                array.set(j,a);
                j--;
            }
            array.set( j, res);
        }
    }

    /**Tests*/
    public static void main(String args[]){
        //averageTime();
        test_Tri_Par_Insertion();
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
    public static float test_Tri_Par_Insertion(){
        TriParInsertion sequence = new TriParInsertion(makeArray());
        System.out.println("Tableau initial: \n" + sequence.toString());
        long startTime = System.nanoTime();
        sequence.Tri_Par_Insertion();
        long endTime = System.nanoTime();
        long timeElapse = endTime - startTime;
        System.out.println("Tableau trié: \n" + sequence.toString());
        System.out.println("Tri Par Insertion: " + sequence.isSorted());
        System.out.println("Temps d'éxécution: " + timeElapse + " nanosecondes");
        return timeElapse;
    }
    public static void averageTime(){
        float s=0;
        int i=1;
        while (i<=100){
            s=s+test_Tri_Par_Insertion();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }
}
