# CypressSystem
City of Toronto Problem Report and Solution System


CypressGUI.java and CypressGUI.form
The main UI of the system. Includes functionality to Login/Logout, create/modify user accounts, and submit problems.
Also the ability to view the live map. Created using a Netbeans integrated UIBuilder, utilizing UI frameworks such as 
AWT and Swing. 


CypressSystem.java
The driver behind the UI. Because it is unconventional to have the UI host the database and the static data,
the CypressSystem is intended to have the UI query requests to retrieve/send data. In a sense, it is the intermediary between
the UI and the data.


UserAccount.java 
Simple class that represents the user's profile. It holds information such as username, password, full name, address, 
telephone, and email- this email can also be edited. 


Report.java
Simple class that represents a system report. This includes data such as the location of the report and the problem type. 
Information about the owner of the submission is also kept. 


Database.java
Stores all UserAccount's and Report's in HashTable's, with a simple "key-fetch" process. It also utilizes a subclass 
PriorityQueue that ranks the reports from highest to lowest. Simple functionality that Create/Modify/Delete's UserAccounts and
Reports are also included.


LiveMap.java
Uses Google's Static Map API to draw a map of toronto, visualized by the encoded poly-line created from Google's online
Polyline Encoder (in order to be URL parseable). Combines this with Report data from the database the create markers 
of locations that are reported. Markers are colour coded based off of problem types as well. To do make use of this, I created
a URL object inside java, following closely Googles URL formatting. The result is an image that can be painted onto a simple
JFrame. 

To read more about Google Static Maps API: https://developers.google.com/maps/documentation/static-maps/
To encode polylines for URL formatting: https://developers.google.com/maps/documentation/utilities/polylineutility





