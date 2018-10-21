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
        int n;//nhâp so lương tu;
        n = ai.nextInt();
        for (int i = 0; i < n; i++) {
            String Tagret = ai.nextLine();//nhap 2 string của từng n từ bàn phím;
            String Explain = ai.nextLine();
           // Word word = new Word(Tagret, Explain);
            word_.put(Tagret, Explain);//them tư vào bảng map;
        }
    }
    public void insertFromFile() {
        File file = new File("dictionaries.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();// đọc từng dòng;
            int i = 0;
            String line = "";
            for (i = 0; (line = br.readLine()) != null; i++){//vòng lặp để chuyển đến dòng tiếp theo
                
                String[] a = line.split("\\s", 2);//gặp dấu " " tách thành 2 xâu;
                
                word_.put(a[0],a[1]);//them vao mang word_ 2 xâu vừa tách là key và value
            }
            br.close();// đóng luồng;
        } catch (IOException e) {
            e.printStackTrace();//các lỗi khi  không đọc đc file v.v;
        }
        }

       public String dictionaryLookup(String xt) {

           if (word_.containsKey((xt))) {//kiểm tra các string key trong bảng map có chứa xt không;
               System.out.println("nghia cua tu");
               // in ra value của xt
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
         //Scanner sca = new Scanner(System.in)
             if (word_.containsKey(xt)) {//kiểm tra key có chứa xt không
                 word_.replace(xt, nghia);//cập nhập lại  velue là nghĩa
                 dictionaryExportToFile();//ghi ra file đè lên file cũ
                 return 1;
                 
             }
             System.out.println("không co tu nay");
             return 0;
         
     }
     public int themtu(String tu, String nghia){

         if(dictionaryLookup(tu)=="khong tim thay"){//gọi hàm dictionaryLoohup để tìm;
            word_.put(tu, nghia);//thêm vào bảng map nếu bảng chưa có;
            dictionaryExportToFile();//ghi ra file đè lên file cũ;
            return 1;
         }
         else return 0;
     }
     public int xoatu(String tx){

         if(word_.containsKey((tx))){// kiểm tra key có chứa tx không
             word_.remove(tx);//xóa key và value tx;
             dictionaryExportToFile();//ghi ra file đè lên file cu;
             return 1;
         }
         System.out.println("không co tu nay");
         return 0;

     }
     public int dictionarySearcher(String w){             
        Set<String> keySet= word_.keySet();// trỏ dến thành phần key của bảng map;
        int kt=0;
        for(String i: keySet) {//duyệt key của mảng;
            if(w.length()<=i.length()){// tìm từ có độ dài lớn hơn w;
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
                 pw.println(m.getKey()+" "+m.getValue());// ghi ra file;
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


