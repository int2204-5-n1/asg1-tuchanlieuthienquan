/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.net.SocketOption;
import java.util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
public class dictionary {
    public static dictionary dic = new dictionary();
    //public ArrayList<Word> words = new ArrayList<>();
    public Map<String,String> word_= new HashMap<>();
    public Map<String,String> word_2= new HashMap<>();
//    DictionaryCommandLine goi = new DictionaryCommandLine();
     public void insertFormCommandline() {
        Scanner ai = new Scanner(System.in);
        int n;
        n = ai.nextInt();
        for (int i = 0; i < n; i++) {
            String Tagret = ai.nextLine();
            String Explain = ai.nextLine();
           // Word word = new Word(Tagret, Explain);
            word_.put(Tagret, Explain);
        }
    }
    public void insertFromFile() {
        File file = new File("dictionaries.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            int i = 0;
            String line = "";
            for (i = 0; (line = br.readLine()) != null; i++){
                
                String[] a = line.split("\\s", 2);
                
                word_.put(a[0],a[1]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

       public String dictionaryLookup(String xt) {
           //Scanner sca = new Scanner(System.in);
          // System.out.println("Tu can tim: ");
           //String xt = sca.nextLine();
           if (word_.containsKey((xt))) {
               System.out.println("nghia cua tu");
               System.out.println(word_.get(xt));
               return word_.get(xt);
           }
           //System.out.println("Khong co tu nay");
           return "khong tim thay";
       }
       public void showAllWords() {
         for(Map.Entry<String,String> m :word_.entrySet()){
             System.out.println(m.getKey()+"         "+m.getValue());
         }
       }
     public int suatu(String xt, String nghia) {
         //Scanner sca = new Scanner(System.in);         
             //System.out.println("nhạp tu can xua:");
             //String xt = sca.nextLine();
             if (word_.containsKey(xt)) {
                 //System.out.println("nhap nghia cua tu:");
                 //String nghia = sca.nextLine();
                 word_.replace(xt, nghia);
                 dictionaryExportToFile();
                 return 1;
                 
             }
             System.out.println("không co tu nay");
             return 0;
         
     }
     public int themtu(String tu, String nghia){
         //Scanner sac = new Scanner(System.in);
         //System.out.println("Từ cần them: ");
         //System.out.println("Nghĩa của từ: ");
         //String nghia = sac.nextLine();
         if(dictionaryLookup(tu)=="khong tim thay"){
            word_.put(tu, nghia);
            dictionaryExportToFile();
            return 1;
         }
         else return 0;
     }
     public int xoatu(String tx){
         //Scanner sca = new Scanner(System.in);
        // System.out.println("nhap tu can xoa");
         //String tx = sca.nextLine();
         if(word_.containsKey((tx))){
             word_.remove(tx);
             dictionaryExportToFile();
             return 1;
         }
         System.out.println("không co tu nay");
         return 0;

     }
     public int dictionarySearcher(String w){             
        Set<String> keySet= word_.keySet();
        int kt=0;
        for(String i: keySet) {
            if(w.length()<=i.length()){
                String a=i.toLowerCase().substring(0,w.length());
                if(a.equals(w)) {
                    word_2.put(i, word_.get(i));
                    //System.out.println(i + " \t|" + word_.get(i));
                    kt=1;
                }
            }
        }
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            File f = new File("newdic.txt");
      
           Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f), "UTF-8"));
           
            FileWriter fw = new FileWriter(f);
            Set<String> keySet1= word_2.keySet();
            fw.write("\r\n");
            for(String i: keySet1) {
                //Bước 2: Ghi dữ liệu
                String s= i+" "+ word_2.get(i)+"\r\n";
                fw.write(s);
            }
           
            //Bước 3: Đóng luồng
            fw.close();
        } catch (IOException ex) {
            System.out.println("Can't write to file " + ex);
        }
        if(kt==0) return 0;
        else return 1;
    }
     public void dictionaryExportToFile(){
         File file = new File("dictionaries.txt");
         try(PrintWriter pw = new PrintWriter(file)){
             for(Map.Entry<String,String> m :word_.entrySet()){
                 pw.println(m.getKey()+" "+m.getValue());
             }
         }
       catch (Exception e){
             System.out.println(" loi ghi file");
       }
     }
    

    public static void main(String[] args){
        Scanner cin= new Scanner(System.in);
        String word= cin.nextLine();
        dic.insertFromFile();
        dic.dictionaryLookup(word);
       dic.dictionarySearcher(word);
       
    }
}


