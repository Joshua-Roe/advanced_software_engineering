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

#### TODOs:

- [ ] At the start of the application, all `passengers` have bought a `ticket`. A `text file` should be provided which gives the `list of all bookings`, containing, for each passenger, details of the unique `booking reference code`, the name of the `passenger`, their `flight code`, and whether they have `checked in or not`.
- [ ] Another `text file` should be provided that shows the details of each `flight`, including the `destination airport`, the `carrier`, and the `capacity of the flight` (giving the `maximum number of passengers`, the `maximum baggage weight`, and the `maximum baggage volume`).
- [ ] These text files are read at the start of the application, and you can assume that they are correctly formatted e.g. the right number of commas in a csv file. You should check that the booking reference code is correct according to your rules.
- [ ] Passengers will need to check in when they reach the airport. To provide a facility for this, after the list of bookings has been loaded, your application should display a simple GUI representing an electronic check-in kiosk.
- [ ] In addition to allowing the passenger to enter their last name and booking reference, this GUI should ask for the dimensions and weight of the passenger’s baggage and indicate any excess baggage fee that needs to be paid. Your group should come up with their own rules for this. You can assume that each customer has only a single piece of baggage.
- [ ] The last name and booking reference which are entered into the GUI should be checked against the entries on the flight booking list. If the details do not correspond, the passenger cannot be checked in, and will have to re-enter their details.
- [ ] When the application exits, it should generate a report. This should indicate, for each flight, the number of passengers checked-in, the total weight of their baggage, the total volume of their baggage, and the total excess baggage fees collected. It should also indicate whether the capacity of the flight is exceeded in any way.
---

![Class Diagram 1][class_diagram]

[class_diagram]: https://github.com/Joshua-Roe/advanced_software_engineering/blob/master/images/class_diagram.PNG "Class Diagram 1"

#### Tasks Distribution:

* Marek:
    - [ ] README.MD creation and maintenance
    - [ ] Implementation of Class Flight
* Sean:
    - [ ] Implementation of Class AllFlights
* Josh:
    - [ ] Implementation of GUI
* Randy:
    - [ ] Implementation of Class AllBookings
* Leo:
    - [ ] Implementation of Class Booking

Test
