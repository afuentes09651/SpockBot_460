package com.company;

public class Login {

    private String [] ids = {"Captain", "Commander", "Ensign"}; //just some users. The names indicate rank for easr
    private String [] pws = {"llap","spock","beans"}; //passwords correspond to the indexes of ids
    private String id;
    private String pw;

    public Login(String id, String pw){
        this.pw = pw;
        this.id = id;
    }

    public int authenticate(){

        for(int i = 0; i < ids.length; i++){
            if(ids[i].equals(id)){
                if(pws[i].equals(id));{
                    return i; //we are going to use the index as a permission
                }
            }
        }
        return -1; //access denied
    }
}
