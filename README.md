# About
This is my first java assignment that talk about Object-Oriented Programming. Basically is a program for mobile phone shop to keep track their record.

## Setup
### Database
Using Microsoft Access 2007. Refer to [Handphone.accdb] (https://github.com/jslim89/js-sem4-assignment/blob/master/Handphone.accdb)

Data source name is "Handphone" of the database

## Run
Open Command Line tool _(i.e. cmd)_
```sh
$ javac Main.java # compile
$ java Main # run
```

## Test
Login  
user id = 100000  # Must be number  
password = kiko007  

after this you will go to user Profile

press the "search Mobile phone" then will go to search
eg. type "Nokia" or "N" in d Brand Text Field
it will get the data from database according to criteria

you can press "<<" for previous item and ">>" for next item

if you wish to buy this item, you just press "Add to Bill" button depending on how many item you wish to buy

after your choosing, you just press "Print Invoice" button and display the invoice

then you can close

after that will go back to profile

if you press the "Change Password" Button, you can change you password from database  
after you changed your password  
then you just press "Log out" button to sign out

when you login again, you should using your new password

if you press "Register" button, then you need to fill in all then Yellow field  
because that is neccessary information need by our company

if you input First name or Last Name is digit, it will prompt up an error  
if your ic is not digit, it will prompt up an error message also

if "Password" and "Confirm Password" is not match, it will also prompt up an error message

if email does not contain '@' character, it will prompt up an error message also

press the "Reset" button will clear all the text field

press the "Cancel" button will go back to Login page
