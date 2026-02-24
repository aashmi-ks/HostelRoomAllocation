Hostel Room Allocation System

Team Members

- Ashmi K S               24UBC121
- Merin Ellizabeth Thomas 24UBC143

---

Problem Statement

Managing hostel room allocation manually is time-consuming and prone to errors.  
The system should allow administrators to:

- Add student details
- Update or delete records
- Allocate rooms with capacity control
- View room members


Objective

To develop a Java Swing-based GUI application that efficiently manages student records and hostel room allocations with room capacity restriction.


Features

- Add, Update, Delete student records
- Allocate rooms block-wise
- Maximum 3 students per room
- View room members
- Clean and user-friendly GUI
- Error messages for full rooms


Technologies Used

- Java
- Swing (GUI)
- AWT
- JTable & DefaultTableModel
- IntelliJ IDEA

Steps to Run the Program

1. Open IntelliJ IDEA
2. Open the project folder
3. Run `HostelRoomAllocation.java`
4. The GUI window will open
5. Add students and allocate rooms

Sample Input

| Student ID | Name     | Department | Year |
|------------|----------|------------|------|
| 101        | Rahul    | CSE        | 2    |
| 102        | Anjali   | ECE        | 1    |

Room Allocation
Block: A Block  
Room: 101  

Sample Output

- Student successfully added to table.
- Room allocated successfully.
- If room capacity exceeds 3 → "Room Full" message appears.
