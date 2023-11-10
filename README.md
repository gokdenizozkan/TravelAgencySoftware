# TravelAgencySoftware
A desktop software for your travel agency with GUI and MySQL Database Configuration.

## Database Configuration
To start using the software,
you first need to prepare your database.
The very first step is to create a database and tables in MySQL.
After you create your database and scheme,
preferably named `agency`, you need to create the tables.

**If you change the name of the database or the scheme,
you need to change the database name in the code.
You may find more information about it in the section
[Database Connection](#database-connection).**

[Open the `create-tables.sql` file](src/dev/patika/plus/sql/create_tables.sql).
Run it. It will automatically generate the tables for you.
If you want to create the tables manually, you can use the [SQL code](#sql-table-creation-codes) at the bottom of this file to create the tables.

Before starting to use the software,
you may want to test it. For this reason, 
we have included [sample data SQL file](src/dev/patika/plus/sql/sample_data.sql) 
which you can click
and run to insert all the sample data into your database. 
Again, you can also insert the sample data manually.
Check out the [sample data](#sample-data-sql-codes-) section for more information.

## Database Connection
To connect to your database,
you need to change the database connection information in the code.
You can find the database connection information in the [Config.java](src/dev/patika/plus/essential/Config.java) file.

Change the following lines in the `Config.java` file to connect to your database.
```
public static final String URL = "jdbc:mysql://localhost:3306/agency";
public static final String USERNAME = "";
public static final String PASSWORD = "";
```

If you need assistance, please refer to your IT guy.

## First Run
After you have created your database and tables,
you can run the software.
The first GUI to be loaded is Login.

![Login Screen](img/login-panel.png)

Behind the scenes,
this panel checks your user table in the database
to see if there is any admin user.
If there is no admin user 
(which is generally the case for the first run),
it will ask you to create an admin user.

You will be automatically redirected to the register screen.

![Register Screen](img/register-panel.png)

You can create your admin user here.
After you create your admin user,
you can login with your admin user.

Admin login gives you access to user management panel
where you can add and edit users.

![User Management Screen](img/admin-panel.png)

Either by creating an employee account and logging into that user
or by clicking the `Switch to Employee Panel` button located at the top right corner,
you can access the employee panel.

![Employee Panel](img/management-hub.png)

Employee panel is just a hub for other panels.
There are two key panels in the employee panel:
* Adding and Editing Hotels and related data.
* Reservation Management Panel

## Usage

### Add and Edit Panel

![Add and Edit Panel](img/add-and-edit-panel.png)

This is your main panel for adding and editing hotels and related data.
You can add and edit hotels, rooms, seasons, and board types.
You can also add and edit hotel facilities and room facilities.

#### Adding and Editing Hotels

The main screen is for hotel-related operations.
If you right click on any of the shown hotels,
you will see a context menu with the following options:
* Add
* Edit
* Delete
* Reload Hotels

![Context Menu](img/hotels-table-context-menu.png)

##### Adding a Hotel
Either by using the context menu or by clicking the `Add` button located at the bottom left corner,
you can add a new hotel.

![Add Hotel](img/add-hotel-panel.png)

You can add a new hotel by filling the form.

If you want to add a new hotel facility,
you can click the `Edit` button near the related label.

This will open up a new window where you can add and edit hotel facilities.

![Edit Hotel Facilities](img/property-management-panel-hotel-facilities.png)

Simply click on the property you want to add or remove and then click on the corresponding button.

Same applies for board types.

##### Editing a Hotel

You can edit a hotel by right-clicking on the hotel you want to edit and then clicking on the `Edit` button.

![Edit Hotel](img/edit-hotel-panel.png)

This will open the same panel as the add hotel panel.
However, there will be some data already filled in.
Do as you like and press `Submit` button to save your changes.

##### Deleting a Hotel

You can delete a hotel by right-clicking on the hotel you want to delete and then clicking on the `Delete` button.
However, it is a work in progress feature.
It will be implemented in the future.

##### Reloading Hotels

You can reload hotels by right-clicking on any hotel and then clicking on the `Reload Hotels` button.
This will reload the hotels table.

##### Managing Seasons

To manage seasons of a hotel, first you need to select the hotel on the table.
If you selected it, its name will appear in the top right corner.

![Hotel Name](img/season-management.png)

Then, you can click on the `Manage Seasons` button to open the seasons management panel.

![Season Management Panel](img/season-management-panel.png)

You can add and edit seasons here.
Remember that pressing add button will add a new season even if it is already present.
To update a season, you need to select it from the table and then press the update button.

##### Adding and Editing Rooms

To add a room, you need to select a hotel first.
Then, you can click on the `Manage Rooms` button to open the rooms management panel.

![Room Management Panel](img/room-ane-panel.png)

Currently, room editing is not implemented.
You can only add rooms.

Be sure to fill all the fields and type prices on the table before submitting.

Adding and editing facilities is the same as hotel facilities.
Go to the [Adding and Editing Hotels](#adding-and-editing-hotels) section for more information if necessary.

### Reservation Panel

![Reservation Panel](img/reservation-panel.png)

This is the main panel for reservation management.
It is a hub for other two panels.

#### Add Reservation Panel

![Add Reservation Panel](img/add-reservation-panel.png)

Add reservation panel, or better known as the hotel finder,
lets you find hotels with available rooms for a given date range and guest count.

You can select the start and end dates from the date pickers.
You can also select the adult and child guest count from the spinners.
Please make sure that you filled all the fields before pressing the `Find` button.

![Hotel Finder](img/hotel-finder.png)

After you press the `Find` button,
you will see a list of hotels with available rooms for the given date range and guest count.
To proceed with reservation details, you need to double-click on the hotel row.

![Hotel Finder Result](img/room-selector.png)

This will open up the room selector panel
where you can select the board type and room for reservation.

![Room Selector](img/selected-room.png)

When you select a room, more information about the room will be shown.
Also you can see the total price for the reservation.

Pressing the `Proceed to Reservation` will take you to
the reservation details panel where you can see the summary of the reservation
and add contact information to complete the reservation.

![Reservation Details](img/reservation-summary.png)

You can add contact information by filling the form.
After you fill the form,
if everything looks okay,
you can press the `Reserve` button to complete the reservation.

That's it! You have successfully completed a reservation.

Now you can see the reservation in the reservation listing panel.

### Reservation Listing Panel

![Reservation Listing Panel](img/reservation-listing.png)

Here you can see all the reservations.

![Reservation Listing Panel](img/deleting-a-reservation.png)

By right-clicking on a reservation,
you can delete it by selecting the `Delete` option from the context menu.


## References
### SQL Table Creation Codes

```sql
create table hotel
(
    id           int auto_increment
        primary key,
    name         varchar(255) not null,
    province     varchar(55)  null,
    state        varchar(55)  null,
    address      text         not null,
    email        varchar(255) not null,
    phone_number varchar(100) not null,
    stars        int          null,
    facilities   varchar(255) null,
    board_types  varchar(255) null
);

create table pricing
(
    room_id       int null,
    season_id     int null,
    board_type_id int null,
    price_adult   int null,
    price_child   int null
);

create table property
(
    id      int auto_increment
        primary key,
    name    varchar(255)                                                                          null,
    of_type enum ('hotel_facility', 'room_facility', 'board_type', 'room_type', 'age_classifier') null
);

create table reservation
(
    id                   int auto_increment
        primary key,
    hotel_id             int          not null,
    room_id              int          not null,
    board_type_id        int          not null,
    start_date           varchar(255) not null,
    end_date             varchar(255) not null,
    adult_guest_count    int          not null,
    child_guest_count    int          not null,
    total_price          int          not null,
    contact_name         varchar(255) not null,
    contact_phone_number varchar(255) not null,
    contact_email        varchar(255) not null
);

create table room
(
    id         int auto_increment
        primary key,
    hotel_id   int          null,
    of_type    varchar(255) null,
    beds       int          null,
    stock      int          null,
    size       int          null,
    facilities varchar(255) null
);

create index room_hotel_id_fk
    on room (hotel_id);

create table room_availability
(
    room_id int          not null
        primary key,
    amount  int          null,
    date    varchar(255) null
);

create table season
(
    id       int auto_increment
        primary key,
    hotel_id int          not null,
    start    varchar(255) null,
    end      varchar(255) null,
    name     varchar(255) null
);

create index season_hotel_id_fk
    on season (hotel_id);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(255)               not null,
    password varchar(255)               not null,
    of_type  enum ('admin', 'employee') null
);
```

### Sample Data SQL Codes 
```sql

```