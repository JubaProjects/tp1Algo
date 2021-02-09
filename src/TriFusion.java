import java.util.*;

public class TriFusion {
    private Queue<Integer> list;

    /**Constructeur*/
    public TriFusion() {
        list = new LinkedList<>();
    }

    /**Setter*/
    public void setList(Queue<Integer> list) {
        this.list = list;
    }

    /**Autres méthodes*/
    public boolean isSorted(){
        int val;
        for (int i=0; i<list.size()-1; i++){
            val = list.poll();
            if (val>list.peek()) return false;
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
    public Queue<Integer> sortArray_Fusion(Queue<Integer> seq){
        int initialSize = seq.size();
        if (seq.size() > 1){
            Queue<Integer> seq1 = new LinkedList<>();
            Queue<Integer> seq2 = new LinkedList<>();
            for (int i=0; i<initialSize; i++){
                if (i%2 == 0){seq1.add(seq.poll());}
                else         {seq2.add(seq.poll());}
            }
            seq1 = sortArray_Fusion(seq1);
            seq2= sortArray_Fusion(seq2);
            seq = fusion(seq1, seq2);
        }
        return seq;
    }
    public Queue<Integer> fusion(Queue<Integer> s1, Queue<Integer> s2){
        Queue<Integer> fusion = new LinkedList<>();

        while (!s1.isEmpty() && !s2.isEmpty()){
            if(s1.peek() <= s2.peek()){
                fusion.add(s1.poll());
            }
            else {
                fusion.add(s2.poll());
            }
        }
        if(!s1.isEmpty()) {
            fusion.addAll(s1);
        }
        else{
            fusion.addAll(s2);
        }
        return fusion;
    }

    /**Tests*/
    public static void main(String args[]){
        test_Tri_Fusion();
        //averageTime();
    }
    public static Queue<Integer> makeList(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre d'éléments: ");
        int size = input.nextInt();
        //int size=1000;
        Queue<Integer> array = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i<size; i++){
            array.add(rand.nextInt(100000));
        }
        return array;
    }
    public static float test_Tri_Fusion(){
        TriFusion sequence = new TriFusion();
        sequence.setList(makeList());
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
        while (i<=100){
            s=s+test_Tri_Fusion();
            i++;
        }
        System.out.println("Temps moyen d'éxécution: " + s/i + " nanosecondes");
    }
}
