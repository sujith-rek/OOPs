import java.util.*;
class Analyser{
    ArrayList<String> reserved_words;
    ArrayList<String> fullSentence;
    ArrayList<String> words;

    Analyser(){
        this.reserved_words = new ArrayList<String>();
        this.words = new ArrayList<String>();
        this.fullSentence = new ArrayList<>();
    }
    Analyser(String input_reserved_words){
        String[] processed_input_reserved_words = input_reserved_words.split(" ");
        ArrayList<String > dup_res_words = new ArrayList<>();
        for(String e:processed_input_reserved_words){
            if(e.equals(""))continue;
            else dup_res_words.add(e);
        }
        this.reserved_words=dup_res_words;
    }
    ArrayList<String> processInput(String input_sentence){
        input_sentence = input_sentence.toLowerCase();
        input_sentence=input_sentence.replaceAll("\\p{Punct}","");
        String[] split_input = input_sentence.split(" ");
        ArrayList<String> refinedList = new ArrayList<>();
        ArrayList<String> word_list = new ArrayList<>();
        for(String e:split_input){
            if(e.equals("")) ;
            else if(refinedList.contains(e)) word_list.add(e);
            else{
                word_list.add(e);
                refinedList.add(e);
            }
        }
        this.words= refinedList;
        this.fullSentence = word_list;
        return refinedList;
    }

    ArrayList<String> sortList(ArrayList<String> input_wordByWord){
        ArrayList<String> dup_words = (ArrayList<String>) input_wordByWord.clone();
        Collections.sort(dup_words);
        return input_wordByWord;
    }

    ArrayList sortOnSize(ArrayList<String> words){
        TreeMap<String, Integer> Lol = new TreeMap<String, Integer>();
        for(String e: words) Lol.put(e,e.length());
        return new ArrayList<>(compareTree(Lol).keySet());
    }

    <K, V extends Comparable<V> >TreeMap compareTree(TreeMap<K,V> Words){
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int comp = Words.get(k1).compareTo(Words.get(k2));
                if (comp == 0) return 1;
                else return comp;
            }
        };
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);
        sorted.putAll(Words);
        return (TreeMap) sorted;
    }

    ArrayList<String> sortOnFrequency(){
        TreeMap<String,Integer> word_freq = new TreeMap<>();
        for(String e:fullSentence){
            if(word_freq.containsKey(e)) word_freq.put(e,(word_freq.get(e)+1));
            else word_freq.put(e,1);
        }
        return new ArrayList<>(compareTree(word_freq).keySet());
    }

    TreeMap<Character,Integer> charFrequency(){
        TreeMap<Character,Integer> charFreq = new TreeMap<>();
        for(String e:fullSentence){
            if(charFreq.containsKey(e.charAt(0))) charFreq.put(e.charAt(0),(charFreq.get(e.charAt(0))+1));
            else charFreq.put(e.charAt(0),1);
        }
        return charFreq;
    }

    ArrayList<String> printReservedWords(){
        ArrayList<String> reservedWords = new ArrayList<>();
        for(int i=0;i<words.size();i++)
            if(reserved_words.contains(words.get(i))) reservedWords.add(words.get(i));
        return reservedWords;
    }

    ArrayList<String> printResFreqOrder(){
        TreeMap<String,Integer> resWorFreq = new TreeMap<>();
        ArrayList<String> resWoFreq = new ArrayList<>();
        for(String e:fullSentence){
            if(reserved_words.contains(e)){
                if(resWorFreq.containsKey(e)) resWorFreq.put(e,resWorFreq.get(e)+1);
                else resWorFreq.put(e,1);
            }
        }
        ArrayList<String>sorted= new ArrayList<> (compareTree(resWorFreq).keySet());
        Collections.reverse(sorted);
        return  sorted;
    }
}

public class Word_Analyser {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the reserved words in first line and enter the text string in the next line");
        String reservedWords = input.nextLine();
        String sen = input.nextLine();
        Analyser sentence = new Analyser(reservedWords);
        ArrayList<String> clean_input=sentence.processInput(sen);
        //1a
        System.out.println("1A\n"+clean_input);
        //1b
        System.out.println("\n1B\n"+sentence.sortList(clean_input));
        //1c
        System.out.println("\n1C\n"+sentence.sortOnSize(clean_input));
        //1d
        System.out.println("\n1D\n"+sentence.sortOnFrequency());
        //Q2
        System.out.println("\n2\n"+sentence.charFrequency());
        //Q3a
        System.out.println("\n3A\n"+sentence.printReservedWords());
        //Q3b
        System.out.println("\n3B\n"+sentence.printResFreqOrder());
    }
}
