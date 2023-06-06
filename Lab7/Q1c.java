import java.util.*;
import java.util.stream.Collectors;
class Analyzer{
    String reserved,text;
    List <String> b;
    List<String> tmb;
    String r2="";
    String res[];
    Analyzer(String reserved,String text) {
        this.reserved=reserved.toLowerCase();
        this.text=text.toLowerCase();
        this.reserved = this.reserved.replaceAll("\\p{Punct}","");
        this.text = this.text.replaceAll("\\p{Punct}","");
        res=this.reserved.split(" ");
        String tmp1[]= this.text.split("");
        tmb = Arrays.asList(tmp1);
        b = tmb.stream().distinct().collect(Collectors.toList());
    }

    String Unique() {
        Iterator itr1 = b.iterator();
        while (itr1.hasNext()) {
            String i = (String)itr1.next();
            r2=r2+i+" ";
        }
        return r2;
    }
    String[] Unique_sorted(){
        String[] tmpb= r2.split(" ");
        Arrays.sort(tmpb);
        return tmpb;
    }
    String[] Unique_size(){
        String[] tmpb= r2.split(" ");
        for (int i=1 ;i<tmpb.length; i++) {
            String temp=tmpb[i];
            int j=i-1;
            while(j>=0 && temp.length()< tmpb[j].length()) {
                tmpb[j+1]=tmpb[j];
                j--;
            }
            tmpb[j+1]=temp;
        }
        for (int i=1 ;i<tmpb.length; i++) {
            String temp=tmpb[i];
            int j=i-1;
            while(j>=0 && temp.length()==tmpb[j].length() && temp.compareTo(tmpb[j])<0 ) {
                tmpb[j+1]=tmpb[j];
                j--;
            }
            tmpb[j+1]=temp;
        }
        return tmpb;
    }

    String Unique_freq(){
        HashMap<String, Integer> map1= new HashMap<String, Integer>();
        String tmp3[]= text.split(" ");

        for (String s:tmp3) {
            if (map1.containsKey(s)) map1.put(s,map1.get(s)+1);
            else map1.put(s,1);
        }
        List<Map.Entry<String, Integer> > list =new LinkedList<Map.Entry<String, Integer> >(map1.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            hm.put(aa.getKey(), aa.getValue());
        }

        Iterator hm_iter = hm.entrySet().iterator();
        int max=1;
        while (hm_iter.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hm_iter.next();
            if((int)mapElement.getValue()>max)
                max=(int)mapElement.getValue();
        }

        String fin="";
        for(int wordfreq=1;wordfreq<=max;wordfreq++) {
            List<String> tmp=b;
            Iterator itr2=tmp.iterator();
            while (itr2.hasNext()) {

                String i = (String)itr2.next();
                Iterator hm_it = hm.entrySet().iterator();
                while (hm_it.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hm_it.next();
                    if((mapElement.getKey()).equals(i) && (int)mapElement.getValue()==wordfreq)
                        fin=fin+mapElement.getKey()+" ";
                }
            }
        }
        return fin;
    }

    int[] count() {
        Iterator itr2 = tmb.iterator();
        int[] freq=new int[26];
        while (itr2.hasNext()) {
            String i=(String)itr2.next();
            for(int k=0;k<26;k++) {
                char c1=(char)(k+97);
                String c2="";
                c2=c2+c1;
                if(i.startsWith(c2)) freq[k]=freq[k]+1;
            }
        }
        return freq;
    }
    String reserved_appear() {
        Iterator itr3 = b.iterator();
        String app="";
        while (itr3.hasNext()) {
            String i=(String)itr3.next();
            for(int j=0;j<res.length;j++) if(i.equals(res[j])) app=app+res[j]+" ";
        }
        return app;
    }
    String reserved_freq(){
        HashMap<String, Integer> map2= new HashMap<String, Integer>();
        String tmp4[]= text.split(" ");
        for(String s:res)  map2.put(s,0);
        for (String s:tmp4) if (map2.containsKey(s)) map2.put(s,map2.get(s)+1);

        List<Map.Entry<String, Integer> > list =new LinkedList<Map.Entry<String, Integer> >(map2.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) hm.put(aa.getKey(), aa.getValue());

        Iterator hm_iter = hm.entrySet().iterator();
        int max=1;
        while (hm_iter.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hm_iter.next();
            if((int)mapElement.getValue()>max)
                max=(int)mapElement.getValue();
        }

        String fin="";
        for(int wordfreq=max;wordfreq>=0;wordfreq--) {
            List<String> tmp=b;
            Iterator itr2=tmp.iterator();
            while (itr2.hasNext()) {

                String i = (String)itr2.next();
                Iterator hm_it = hm.entrySet().iterator();
                while (hm_it.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hm_it.next();
                    if((mapElement.getKey()).equals(i) && (int)mapElement.getValue()==wordfreq)
                        fin=fin+mapElement.getKey()+" ";
                }
            }
        }
        return fin;
    }
}
public class Q1c {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the reserved words in first line and enter the text string in the next line");
        String reserved=input.nextLine();
        String text=input.nextLine();
        Analyzer an=new Analyzer(reserved,text);
        System.out.println("");
        System.out.println("1a: "+an.Unique());
        String[] tmp1=an.Unique_sorted();
        System.out.print("1b:");
        for(String s: tmp1) System.out.print(s+" ");

        String[] tmp2=an.Unique_size();
        System.out.println("");
        System.out.print("1c:");
        for(String s: tmp2) System.out.print(s+" ");

        System.out.println("");
        System.out.print("1d: "+an.Unique_freq());

        int[] freq=an.count();
        System.out.println("");
        System.out.println("");
        System.out.println("2:");
        for(int i=0;i<26;i++) if(freq[i]!=0) System.out.println((char)(i+97)+" "+freq[i]);
        System.out.println("");
        System.out.print("3a: ");
        System.out.println(an.reserved_appear());
        System.out.print("3b: ");
        System.out.println(an.reserved_freq());
    }
}