// Name: Tianming Guo
// USC NetID: guotianm
// CSCI 455 PA5
// Spring 2024

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;
void printHelp();
int main(int argc, char * argv[]) {


   Table * grades;  // Table is dynamically allocated below, so we can call
                     // different constructors depending on input from the user.
   
   // optionally gets the hash table size from the command line
   if (argc > 1) {
      int hashSize = atoi(argv[1]);  // atoi converts c-string to int
      
      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*

   //define variables to hold necessary input
   string command;
   string name;
   int score;
   cout << "cmd> ";
   while(cin>>command){

      if(command =="quit"){
         break;
      }else if (command=="insert"){
         cin>>name>>score;
         if(!grades->insert(name,score)){
            cout<<"insert failed! "<<endl;
            cout<<"Error: key already exist !"<<endl;
         }
         cout<<"cmd> ";

      }else if(command=="stats"){
         grades->hashStats(cout);
         cout<<"cmd> ";
      }else if(command=="print"){
         grades->printAll();
         cout<<"cmd> ";
      }else if(command=="change"){
         cin>>name>>score;
         int * p = grades->lookup(name);
         if(p!= NULL){
            *p = score;
         }else{
            cout<<"Error: The "<<name<<" does not exist!"<<endl;
         }
         cout<<"cmd> ";

      }else if(command=="lookup"){
         cin>>name;
         int * p = grades->lookup(name);
         if(p!=NULL){
            cout<<*p<<endl;
         }else{
            cout<<"Error: The "<<name<<" does not exist!"<<endl;
         }
         cout<<"cmd> ";
      }else if(command=="remove"){
         cin>>name;
         if(grades->remove(name)){
            cout<<"The "<<name<<" has been removed successfully!"<<endl;
         }else{
            cout<<"Error: The "<<name<<" does not exist!"<<endl;
         }
         cout<<"cmd> ";

      }else if(command=="size"){
         cout<<grades->numEntries()<<endl;
         cout<<"cmd> ";
      }else if(command=="help"){
         printHelp();
         cout<<"cmd> ";
      }else{
         cout<<"ERROR: invalid command"<<endl;
         cout<<"please see help below: "<<endl;
         printHelp();
         cout<<"cmd> ";
      }
   }

   return 0;
}

// print out the help information which gives the command
void printHelp(){

   cout<<"insert name score\n"
         "     Insert this name and score in the grade table. If this name was already present, print a message to that effect, and don't do the insert.\n"
         "change name newscore\n"
         "     Change the score for name. Print an appropriate message if this name isn't present.\n"
         "lookup name\n"
         "     Lookup the name, and print out his or her score, or a message indicating that student is not in the table.\n"
         "remove name\n"
         "     Remove this student. If this student wasn't in the grade table, print a message to that effect.\n"
         "print\n"
         "     Prints out all names and scores in the table.\n"
         "size\n"
         "     Prints out the number of entries in the table.\n"
         "stats\n"
         "     Prints out statistics about the hash table at this point. (Calls hashStats() method)\n"
         "help\n"
         "     Prints out a brief command summary.\n"
         "quit\n"
         "     Exits the program."<<endl;

}