package CypressSys;
import java.util.ArrayList;

/**
 * A UserAccount represents a User (a citizen) that is utilizing
 * the CYPRESS system to submit Reports
 * *********************************************************************************
 * NOTE:
 * Since the majority of these methods pass around packages of data, the most
 * convenient data structure to contain these packages would be a string array.
 * However, to keep the structure of these arrays consistent across all functions,
 * we will use the following format:
 *   Each array will be a 5-tuple, of which the indexes are as follows:
 *      1. username
 *      2. password
 *      3. telephone number
 *      4. address of user
 *      5. email of user
 * When a value for a certain index is not required/not given, it will remain null.
 * *********************************************************************************
 */
public class UserAccount {

    private String username;
    private String password;
    private String fullname;
    private String telenum;
    private String address;
    private String email;
    private boolean loggedIn;
    private ArrayList<Report> reports;
    
    public UserAccount(
        String fullname, String username, String password, 
        String telephone, String address, String email
    ){
        setUsername(username);
        setPassword(password);
        setFullname(fullname);
        setTeleNum(telephone);
        setAddress(address);
        setEmail(email);
        loginUser();
    }
    
    private void setUsername(String name){
        this.username = name;
    }
    public String getUserName(){
        return username;
    }

    private void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return password;
    }
    
    private void setFullname(String name){
        this.fullname = name;
    }
    public String getFullname(){
        return fullname;
    } 
            
    private void setTeleNum(String phoneNumber){
        this.telenum = phoneNumber;
    }
    public String getTeleNum(){
        return telenum;
    }  

    private void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    
    private void setEmail(String mail){
        this.email = mail;
    }
    public String getEmail(){
        return email;
    }

    private void loginUser(){
        loggedIn = true;
    }
    public void logoutUser(){
        loggedIn = false;
    }

    public boolean userAuthenticate(String user, String pass){
        boolean valid = this.username.equals(user) && this.password.equals(pass);
        if (valid) loginUser();
        return valid;
    }

    public void editUser(
        String fullname, 
        String telephone, String address, String email
    ){
        if (!fullname.equals("")){
            this.fullname = fullname;
        }
        if (!telephone.equals("")){
            this.telenum = telephone;
        }
        if (!address.equals("")){
            this.address = address;
        }
        if (!email.equals("")){
            this.email = email;
        }
    }  
    public void editPassword(String pass){
        this.password = pass;
    }
}