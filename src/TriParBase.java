import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TriParBase {
    private ArrayList<Integer> array;

    /**Constructeur*/
    public TriParBase(ArrayList<Integer> array) {
        this.array = array;
    }

    /**Getters & Setters*/
    public int getSize(){return array.size();}
    public void setArray(ArrayList<Integer> array) {
        this.array = array;
    }

    /**Autres méthodes*/
    public boolean isSorted(){
        for (int i=0; i<getSize()-1; i++){
            if (array.get(i)>array.get(i+1)) return false;
        }
        return true;
    }
    public String toString(){
        return array.toString();
    }
    public int maxValue(){
        int max=array.get(0);
        for (int i=1; i<getSize(); i++){
            if (array.get(i)>max){
                max = array.get(i);
            }
        }
        return max;
    }
    public ArrayList<Integer> initEmpty(int size){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=0; i<size; i++){
            arrayList.add(0);
        }
        return arrayList;
    }

    /**Tri par dénombrement*/
    public void Tri_Denombrement(int poids){
        int k = maxValue();
        int  nb[] = new int[k+1];
        int  pos[] = new int[k+1];
        ArrayList result = initEmpty(getSize());
        for (int i =0; i<=k; i++){
            nb[i] = 0;
        }
        for (int i=0; i<getSize(); i++){
            //System.out.println(getDigit(array.get(i), poids));
            nb[getDigit(array.get(i), poids)]= nb[getDigit(array.get(i), poids)] + 1;
        }
        pos[0]=0;
        for (int i=1; i<=k; i++){
            pos[i] = pos[i-1] + nb[i-1];
        }
        for (int i=0; i<this.getSize(); i++){
            result.set(pos[getDigit(array.get(i), poids)], array.get(i));
            //result.set(array.get(i), array.get(i));
            pos[getDigit(array.get(i), poids)] = pos[getDigit(array.get(i), poids)] + 1;
            //pos[array.get(i)] = pos[array.get(i)] + 1;
        }
        setArray(result);
    }
    public int getDigit(int number, int poids){
        int digit = number % (int)Math.pow(10, poids);
        return digit;
    }

    /**Tri par Base*/
    public void Tri_Par_Base(){
        int numberMaxOfDigits = String.valueOf(maxValue()).length();
        for (int i=1; i<=numberMaxOfDigits; i++){
            Tri_Denombrement(i);
        }
    }

    /**Tests*/
    public static void main(String args[]){
        averageTime();
        //test_Tri_Denombrement();
    }
    public static ArrayList<Integer> makeArray(){
        /*Scanner input = new Scanner(System.in);
        System.out.println("Nombre d'éléments: ");
        int size = input.nextInt();*/
        int size =1000;
        ArrayList<Integer> array = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i<size; i++){
            array.add(rand.nextInt(1000));
        }
        return array;
    }
    public static float test_Tri_Denombrement(){
        TriParBase sequence = new TriParBase(makeArray());
        System.out.println("Tableau initial: \n" + sequence.toString());
        long startTime = System.nanoTime();
        sequence.Tri_Par_Base();
        long endTime = System.nanoTime();
        long timeElapse = endTime - startTime;
        System.out.println("Tableau trié: \n" + sequence.toString());
        System.out.println("Tri par base: " + sequence.isSorted());
        System.out.println("Temps d'éxécution: " + timeElapse + " nanosecondes");
        return timeElapse;
    }
    public static void averageTime(){
        float s=0;
        int i=1;
        while (i<=10){
            s=s+test_Tri_Denombrement();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }

}
