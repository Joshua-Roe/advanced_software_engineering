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
- Run bash script under CheckInTerminal/bin/flightCheckin.bash
- Output report generated on exit will be found under CheckInTerminal/bin/output.txt
#### TODOs:

- [x] At the start of the application, all `passengers` have bought a `ticket`. A `text file` should be provided which gives the `list of all bookings`, containing, for each passenger, details of the unique `booking reference code`, the name of the `passenger`, their `flight code`, and whether they have `checked in or not`.
- [x] Another `text file` should be provided that shows the details of each `flight`, including the `destination airport`, the `carrier`, and the `capacity of the flight` (giving the `maximum number of passengers`, the `maximum baggage weight`, and the `maximum baggage volume`).
- [x] These text files are read at the start of the application, and you can assume that they are correctly formatted e.g. the right number of commas in a csv file. You should check that the booking reference code is correct according to your rules.
- [x] Passengers will need to check in when they reach the airport. To provide a facility for this, after the list of bookings has been loaded, your application should display a simple GUI representing an electronic check-in kiosk.
- [x] In addition to allowing the passenger to enter their last name and booking reference, this GUI should ask for the dimensions and weight of the passenger’s baggage and indicate any excess baggage fee that needs to be paid. Your group should come up with their own rules for this. You can assume that each customer has only a single piece of baggage.
- [x] The last name and booking reference which are entered into the GUI should be checked against the entries on the flight booking list. If the details do not correspond, the passenger cannot be checked in, and will have to re-enter their details.
- [x] When the application exits, it should generate a report. This should indicate, for each flight, the number of passengers checked-in, the total weight of their baggage, the total volume of their baggage, and the total excess baggage fees collected. It should also indicate whether the capacity of the flight is exceeded in any way.
---

![Class Diagram 1][class_diagram]

[class_diagram]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/class_diagram.PNG "Class Diagram 1"

#### Tasks Distribution:

* Marek:
    - [x] README.MD creation and maintenance
    - [x] Implementation of Class Flight
* Sean:
    - [x] Implementation of Class AllFlights
* Josh:
    - [x] Implementation of GUI
* Randy:
    - [x] Implementation of Class AllBookings
* Leo:
    - [x] Implementation of Class Booking

Test
