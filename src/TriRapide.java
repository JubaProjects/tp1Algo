import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TriRapide {
    private ArrayList<Integer> array;

    /**Constructeur*/
    public TriRapide(ArrayList<Integer> array) {
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


    /**Tri Rapide*/
    public void tri_Rapide(){
        setArray(quickSort_aux(0, getSize()-1));
    }

    public ArrayList<Integer> quickSort_aux(int debut, int fin){
        if(debut<fin){
            int pivot = partition(debut,fin);
            quickSort_aux(debut,pivot);
            quickSort_aux(pivot+1,fin);
        }
        return array;
    }

    public int partition(int debut, int fin ){
        int pivot = array.get(debut);
        int i = debut;
        int j = fin;
        while (i < j) {
            while (array.get(j) > pivot ){j--;}
            while (array.get(i) < pivot){i++;}
            permutation(i,j);
        }
        return j;
    }

    /**Tests*/
    public static void main(String args[]){
        //averageTime();
        test_Tri_Rapide();
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
    public static float test_Tri_Rapide(){
        TriRapide sequence = new TriRapide(makeArray());
        System.out.println("Tableau initial: \n" + sequence.toString());
        long startTime = System.nanoTime();
        sequence.tri_Rapide();
        long endTime = System.nanoTime();
        long timeElapse = endTime - startTime;
        System.out.println("Tableau trié: \n" + sequence.toString());
        System.out.println("Tri a Rapide: " + sequence.isSorted());
        System.out.println("Temps d'éxécution: " + timeElapse + " nanosecondes");
        return timeElapse;
    }
    public static void averageTime(){
        float s=0;
        int i=1;
        while (i<=100){
            s=s+test_Tri_Rapide();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }
}
