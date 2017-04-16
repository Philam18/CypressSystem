package CypressSys;
/**
 * A Report is created and submitted to the CYPRESS system by users (citizens)
 * and contains information regarding location and nature of the complaint
 */
public class Report {

	private String owner;
	private String address;
	private String concern;
	private String reportCode;
	
	public Report(String concern, String address, String owner){
            setConcern(concern);
            setAddress(address);
            setOwner(owner);
            reportCode = createReportCode();
	}
	
	private String createReportCode(){
            String productConcern = Integer.toString(this.concern.hashCode());
            String productAddress = Integer.toString(this.address.hashCode());
            return productConcern + "_" + productAddress;
	}
        public String getReportID(){
            return reportCode;
        }
        
	public void setOwner(String owner){
            this.owner = owner;
	}
	public String getOwner(){
            return owner;
	}
	
	public void setAddress(String address){
            this.address = address;
	}
        public String getAddress(){
            return address;
	}
	
	public void setConcern(String concern){
            this.concern = concern;
	}
	public String getConcern(){
            return concern;
	}
        
	public void editReport(String concern, String address){
            if(!concern.equals("")){
                this.concern = concern;
            }
            if(!address.equals("")){
                this.address = address;
            }
            this.reportCode = createReportCode();
	}
}