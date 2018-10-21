/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

/**
 *
 * @author Hoai
 */
public class Word {

    static int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String Tagret;
    private String Explain;
    public Word ( String Tagret, String Explain){
        this.Tagret = Tagret;
        this.Explain = Explain;
    }
    public Word(){
    }
    public void setTagret (String Tagret){
        this.Tagret = Tagret;
    }
    public String getTagret(){
        return Tagret;
    }
    public void setExplain(String Explain){
        this.Explain = Explain;
    }
    public String getExplain(){
        return Explain;
    }
}
