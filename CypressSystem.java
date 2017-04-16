/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CypressSys;

import CypressSys.Database.PriorityStructure;
import java.util.*;
import java.lang.*;

/**
 *
 * @author Philip Lam
 */
public class CypressSystem {
    private Database database;
    
    public CypressSystem(){
        database = new Database();
    }
    
    public boolean createUserAccount(String fullname, String username, 
            String password, String telephone, String address, String email){
        if (database.checkValidName(username)){
            database.addUser(new UserAccount(
                    fullname,username,password,telephone,address,email 
            ));
            return true;
        }
        return false;
    }
    
    public boolean loginUser(String[] data){
        UserAccount user = database.getUser(data[0]);
        if(user == null){
            return false;
        }
        return user.userAuthenticate(data[0], data[1]);
    }
    public boolean logoutUser(UserAccount user){
        user.logoutUser();
        return true;
    }
    
    public UserAccount getUser(String username){
        return database.getUser(username);
    }
    
    public boolean submitReport(String problem, String address, String owner){
        Report report = new Report(problem, address, owner);
        database.addReport(report);
        return true;
    }
    
    public ArrayList<String> getUserReports(String username){
        ArrayList<Report> history = database.getUsersReport(username);
        ArrayList<String> list = new ArrayList<>();
        if (history != null){
            for (Report item : history){
                String str = item.getAddress()+": "+item.getConcern();
                list.add(str);
            }
        }
        return list;
    }
    
    public void modifyUser(
        String username, 
        String fullname, String telephone, String address, String email
    ){
        UserAccount userAccount = database.getUser(username);
        userAccount.editUser(fullname, telephone, address, email);
    }
    public void modifyUserPassword(String user, String pass){
        UserAccount userAccount = database.getUser(user);
        userAccount.editPassword(pass);
    }
    
    public ArrayList<PriorityStructure> getReports(){
        return database.rankReports();
    }
}
