[class_diagram_csvgen]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/CSVGen.png "CSVGen Class Diagram"
[class_diagram_1]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/CheckInTerminal.png "Class Diagram 1"
[class_diagram_2]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/Part1.png "Class Diagram 2"
[class_diagram_3]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/Part2.png "Class Diagram 3"
[managementgui]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/managementgui.png "ManagementGUI"
## F21AS: Advanced Sorftware Engineering

### Coursework 2020 - A simulation of a simple airport check-in system.

---

#### Group Members:

* Leo Kong
* Joshua Roe
* Sean Katagiri
* Randy Adjepong
* Marek Kujawa

---
#### Running the code:

- Download repository as ZIP
- Run .jar executable
- simulation playback can be controlled using the panel at the bottom of the GUI
-additional information about passengers and flights can be viewed by hovering the mouse cursor over the desired element
- Output log is generated on exit will be found in same directory as the executable
---
#### Screenshot of the Program:
![ManagementGUI][managementgui]

---
#### Core Functionality

1. The simulation consists of multiple check-in desks, a single queue of passengers, and a group of
flights waiting to depart. When passengers arrive at the airport, they join the back of the queue.
When they reach the front of the queue, they will be processed by the next available check-in
desk, and then assigned to the appropriate flight.
2. The application will read in details of passengers and flights for the .csv files in the data folder when it starts up.<br>
The application simulates the arrival of passengers by
randomly selecting passengers who have not yet checked in. Each passenger has one piece of baggage, and the dimensions and weight of the baggage are chosen randomly when
the customer joins the queue.
3. After a certain period of time has elapsed, the planes depart. Any
passengers remaining in the queue will not be able to board their flights.
4. The GUI shows the list of passengers waiting in the queue, details of what each check-in
desk is currently doing (e.g. processing a particular passenger, charging for excess baggage),
and the status of each flight (e.g. number of passengers checked-in, weight of baggage etc.)
throughout the simulation.
5. A log is kept which records events as they happen, e.g. passengers joining the queue,
passengers checking in, passengers boarding flights. This log is written to a file when the application exits.
---
![CSVGen Class Diagram][class_diagram_csvgen]<br>
![Class Diagram 1][class_diagram_1]<br>
![Class Diagram 2][class_diagram_2]<br>
![Class Diagram 3][class_diagram_3]

#### Tasks Distribution:

* Marek:
    - [x] Refactor of Class Flight
* Sean:
    - [x] Implementation of Simulation and Timer
* Josh:
    - [x] Implementation of Class ManagementGUI
* Randy:
    - [x] Implementation of Class Log
* Leo:
    - [x] Refactor of Class Booking
