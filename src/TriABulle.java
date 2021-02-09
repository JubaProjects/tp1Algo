import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TriABulle {
    private ArrayList<Integer> array;

    /**Constructeur*/
    public TriABulle(ArrayList<Integer> array) {
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

    /**Tri à Bulle*/
    public void tri_a_Bulle(){
        int n = this.getSize();
        for (int i= n-1; i>=1; i--){
            for (int j = 0; j<= i-1; j++){
                if (array.get(j) > array.get(j+1)){
                    permutation(j, j+1);
                }
            }
        }
    }

    /**Tests*/
    public static void main(String args[]){
        test_Tri_A_Bulle();
        //averageTime();
    }
    public static ArrayList<Integer> makeArray(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre d'éléments: ");
        int size = input.nextInt();
        //int size = 1000;
        ArrayList<Integer> array = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i<size; i++){
            array.add(rand.nextInt(1000));
        }
        return array;
    }
    public static float test_Tri_A_Bulle(){
        TriABulle sequence = new TriABulle(makeArray());
        System.out.println("Tableau initial: \n" +sequence.toString());
        long startTime = System.nanoTime();
        sequence.tri_a_Bulle();
        long endTime = System.nanoTime();
        long timeElapse = endTime - startTime;
        System.out.println("Tableau trié: \n" +sequence.toString());
        System.out.println("Tri a bulle: " + sequence.isSorted());
        System.out.println("Temps d'éxécution: " + timeElapse + " nanosecondes");
        return timeElapse;
    }
    public static void averageTime(){
        float s=0;
        int i=1;
        while (i<=10){
            s=s+test_Tri_A_Bulle();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }
}


