package model;

public class User {

    private String user;
    private String pass;
    private String TenNv;
    private int idNv;
    private boolean role;

    public User() {
    }

    public User(String user, String pass, String TenNv, int idNv, boolean role) {
        this.user = user;
        this.pass = pass;
        this.TenNv = TenNv;
        this.idNv = idNv;
        this.role = role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isRole() {
        return role;
    }


    public int getIdNv() {
        return idNv;
    }

    public void setIdNv(int idNv) {
        this.idNv = idNv;
    }

    public String getTenNv() {
        return TenNv;
    }

    public void setTenNv(String TenNv) {
        this.TenNv = TenNv;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" + "user=" + user + ", pass=" + pass + '}';
    }
}
