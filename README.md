# Electricity-Billing-System---Java
The Electricity Billing System is a Java-based GUI application designed for managing and recording customer meter information for electricity billing purposes. This application provides a user-friendly interface to enter details related to electricity meters and store them in a database.

Features
1. Intuitive User Interface: Simple and accessible GUI built using Java Swing for easy data entry.
2. Meter Information Management: Fields for meter number, location, type, phase code, billing type, and other relevant details.
3. Database Connectivity: Stores meter information directly into a database, enabling efficient data storage and retrieval.
4. Feedback on Actions: Provides success and error messages to guide users through data entry and submission.

Technologies Used
Java: Core language for the application logic.
Swing: GUI toolkit to create the front-end interface.
JDBC: Database integration for handling meter data storage.


Getting Started

Prerequisites
JDK (Java Development Kit) 8 or higher.
A compatible SQL database (such as MySQL or PostgreSQL) to store meter data.

Installation 

1. Clone the repository:

   ```
   git clone https://github.com/22DhruvPatel/ElectricityBillingSystem.git
   cd ElectricityBillingSystem

   ```

2. Database Setup: Set up a database with a meter_info table as follows:

   ```
   CREATE TABLE meter_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    meter_number VARCHAR(50) NOT NULL,
    meter_location VARCHAR(50) NOT NULL,
    meter_type VARCHAR(50) NOT NULL,
    phase_code VARCHAR(10) NOT NULL,
    bill_type VARCHAR(20) NOT NULL,
    days INT NOT NULL
   );

   ```

