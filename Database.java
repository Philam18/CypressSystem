package CypressSys;

import java.util.*;

/**
 * Database that keeps track of Users as well as pending reports
 */
public class Database{

    private HashMap<String, UserAccount> users;
    private HashMap<String, ArrayList<Report>> reportList;

    public Database(){
        users = new HashMap<>();
        reportList = new HashMap<>();
    }

    public boolean addUser(UserAccount user){
        users.put(user.getUserName(), user);
        return true;
    }
    public boolean modifyUser(
        String username, 
        String fullname, String telephone, String address, String email
    ){
        if (!username.equals("")){
            UserAccount profile = users.get(username);
            if (profile != null){
                profile.editUser(
                    fullname, telephone, address, email
                );
                users.put(username, profile);
                return true;
            }
        }
        return false;
    }
    public boolean deleteUser(String username){
        //purge user's profile from users HashMap
        users.remove(username);
        //purge user's reports from reportList HashMap
        reportList.remove(username);
        return true;
    }
    
    public boolean addReport(Report report){
        String username = report.getOwner();
        ArrayList<Report> userReports = reportList.get(username);
        //Iterate through user's past reports, if the report exists, do not add it
        if (userReports != null){
            for(Iterator<Report> iterator = userReports.iterator(); iterator.hasNext();){
                String reportCode = iterator.next().getReportID();
                //if identical report is found
                if (reportCode.equals(report.getReportID())){
                    return false;
                }
            }
            userReports.add(report);
            reportList.put(username,userReports);
            return true;
        }else if(userReports == null){
            userReports = new ArrayList<Report>();
            userReports.add(report);
            reportList.put(username, userReports);
            return true;
        }else{
            return false;
        }
    }
    public boolean deleteReport(String reportID){
        //iterate through all ArrayLists in the reportList HashMap
        for (ArrayList<Report> list : reportList.values()){
            //iterate through all Reports in each ArrayList
            for(Iterator<Report> iterator = list.iterator(); iterator.hasNext();){
                //get the reportID for each report
                String reportCode = iterator.next().getReportID();
                //if the reportID equals the key reportID
                if(reportCode.equals(reportID)){
                    //delete the report (without causing ConcurrentModuficationException
                    iterator.remove();
                }
            }
        }
        return true;
    }
    
    public UserAccount getUser(String username){
        return users.get(username);
    }
    public ArrayList<Report> getUsersReport(String username){
        return reportList.get(username);
    }
    
    public boolean checkValidName(String str){
        return !(users.keySet().contains(str));
    }
    
    public ArrayList<PriorityStructure> rankReports(){
        //Create an ArrayList of PriorityStructures, this will allow us to call a sort on it
        ArrayList<PriorityStructure> ranklist = new ArrayList<>();
        //Loop through all user reports in the ReportList
        for(ArrayList<Report> reports: reportList.values()){
            //Loop through each individual user's reports
            for(Iterator<Report> iterator = reports.iterator(); iterator.hasNext();){
                Report report = iterator.next();
                //Create a string concatenation of concern + address
                String handle = report.getConcern() + " - "+ report.getAddress();
                String code = report.getReportID();
                int i = 0;
                //Search the ranklist for the current concern
                while (i < ranklist.size()){
                    PriorityStructure content = ranklist.get(i);
                    //If the concern is found
                    if (content.concern.equals(handle)){
                        //Simply increment the counter
                        content.priority ++;
                        break;
                    }
                    i++;
                }
                //If the concern is not found
                if(i == ranklist.size()){
                    //Add a new concern to the list
                    ranklist.add(new PriorityStructure(handle,1,code));
                }
            }
        }
        Collections.sort(ranklist);
        return ranklist;
    }

        public class PriorityStructure implements Comparable<PriorityStructure>{
        public String concern; //The problem type
        public String code;    //The report code of the problem
        public int priority;   //The priority rating of the problem
        
        public PriorityStructure(String con, int pri, String cd){
            this.concern = con;
            this.priority = pri;
            this.code = cd;
        }
        @Override
        public int compareTo(PriorityStructure other){
            return ~Integer.compare(this.priority,other.priority);
        }    
        
    }
}