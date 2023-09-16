package data.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private int id;
    private String password;
    private String userName;
    private boolean isLocked;
    private List<Entry> entries = new ArrayList<>();
    private LocalDateTime registeredOn;

    public String getUsername() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.userName = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEntries(List<Entry> entries){
        this.entries = entries;
    }
    public List<Entry> getEntries(){
        return entries;
    }
}
