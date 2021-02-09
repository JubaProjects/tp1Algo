import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class TriFusion {
    private LinkedList<Integer> list;

    /**Constructeur*/
    public TriFusion(LinkedList<Integer> array) {
        this.list = array;
    }

    /**Setter*/
    public void setList(LinkedList<Integer> list) {
        this.list = list;
    }

    /**Autres méthodes*/
    public boolean isSorted(){
        for (int i=0; i<list.size()-1; i++){
            if (list.get(i)>list.get(i+1)) return false;
        }
        return true;
    }
    public String toString(){
        return list.toString();
    }

    /**Tri Fusion*/
    public void tri_Fusion(){
        setList(sortArray_Fusion(list));
    }
    public LinkedList<Integer> sortArray_Fusion(LinkedList<Integer> seq){
        if (seq.size() > 1){
            LinkedList<Integer> seq1 = new LinkedList<>();
            for (int i=0; i<seq.size()/2; i++){
                seq1.add(seq.get(i));
            }
            LinkedList<Integer> seq2 = new LinkedList<>();
            for (int i=seq.size()/2; i<seq.size(); i++){
                seq2.add(seq.get(i));
            }
            seq1 = sortArray_Fusion(seq1);
            seq2= sortArray_Fusion(seq2);
            seq = fusion(seq1, seq2);
        }
        return seq;
    }
    public LinkedList<Integer> fusion(LinkedList<Integer> s1, LinkedList<Integer> s2){
        LinkedList<Integer> fusion = new LinkedList<>();
        int index1 =0;
        int index2 =0;
        while (index1 < s1.size() && index2 < s2.size()){
            if(s1.get(index1) <= s2.get(index2)){
                fusion.add(s1.get(index1));
                index1++;
            }
            else {
                fusion.add(s2.get(index2));
                index2++;
            }
        }
        if(index1<s1.size()) {
            fusion.addAll(s1.subList(index1, s1.size()));
        }
        else{
            fusion.addAll(s2.subList(index2, s2.size()));
        }
        return fusion;
    }

    /**Tests*/
    public static void main(String args[]){
        //test_Tri_Fusion();
        averageTime();
    }
    public static LinkedList<Integer> makeList(){
        /*Scanner input = new Scanner(System.in);
        System.out.println("Nombre d'éléments: ");
        int size = input.nextInt();*/
        int size=1000;
        LinkedList<Integer> array = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i<size; i++){
            array.add(rand.nextInt(100));
        }
        return array;
    }
    public static float test_Tri_Fusion(){
        TriFusion sequence = new TriFusion(makeList());
        System.out.println("Tableau initial: \n" + sequence.toString());
        long startTime = System.nanoTime();
        sequence.tri_Fusion();
        long endTime = System.nanoTime();
        long timeElapse = endTime - startTime;
        System.out.println("Tableau trié: \n" + sequence.toString());
        System.out.println("Tri Fusion: " + sequence.isSorted());
        System.out.println("Temps d'éxécution: " + timeElapse + " nanosecondes");
        return timeElapse;
    }
    public static void averageTime(){
        float s=0;
        int i=1;
        while (i<=10){
            s=s+test_Tri_Fusion();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }
}
