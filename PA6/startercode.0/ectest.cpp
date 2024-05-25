/*  Name: Tianming Guo
 *  USC NetID: guotianm
 *  CS 455 Spring 2024
 *  Extra credit assignment
 *
 *  ectest.cpp
 *
 *  a non-interactive test program to test the functions described in ecListFuncs.h
 *
 *    to run it use the command:   ectest
 *
 *  Note: this uses separate compilation.  You put your list code ecListFuncs.cpp
 *  Code in this file should call those functions.
 */


#include <iostream>
#include <string>

// for istringstream used in buildList (defined below)
#include <sstream>

#include "ecListFuncs.h"

using namespace std;


// You may use the following two utility functions that will make it easier to test
// your list functions on hard-coded list data and compare it to expected output:
// (function definitions for them appear at the bottom of this file)


/*
 * listToString
 *
 * PRE: list is a well-formed list.
 *
 * converts the list to a string form that has the following format shown by example.
 * the list is unchanged by the function.
 *
 *   string format:
 *
 *   "()"        an empty list
 *   "(3)        a list with one element, 3
 *   "(3 4 5)"   a list with multiple elements: 3 followed by 4 followed by 5
 *
 */
string listToString(ListType list);


/*
 * buildList
 * 
 * PRE: listString only contains numbers (valid integer format) and spaces
 *
 * creates and returns a linked list from a string of space separated numbers
 * 
 *
 * Examples:
 *  listString         return value of buildList(listString)
 *
 *    ""               ()
 *    "-32"            (-32)
 *    "     -32   "    (-32)
 *    "1 3 2"          (1 3 2)
 *    "  1 3 2"        (1 3 2)
 *
 */
ListType buildList(const string & listString);

/*
 * testMonotonicIncr
 * This function will test isMonotonicIncr.
 * PRE: toBuild only contains numbers (valid integer format) and spaces
 *
 * The function will create a linkedlist by using buildList function from toBuild. Then, it will apply isMonotonicIncr function to the linkedlist.
 * It will print out the original list, the expected result, and the actual result. If the actual result matches the
 * expected results. It will print out "the result matches expected!" message else it will print out "Error: the result does not match expected"
 *
 *
 *
 */
void testMonotonicIncr(string toBuild, bool expected);


/*
 * testRemoveMax
 * This function will test removeMax.
 * PRE: toBuild and expected only contains numbers (valid integer format) and spaces
 *
 * The function will create a linkedlist by using buildList function from toBuild. Then, it will apply removeMax function to the linkedlist.
 * It will print out the original list, the expected result, and the actual result. If the actual result matches the
 * expected results. It will print out "the result matches expected!" message else it will print out "Error: the result does not match expected"
 *
 *
 *
 */
void testRemoveMax(string toBuild, string expected);


/*
 * testfillGaps
 * This function will test fillGaps.
 * PRE: toBuild and expected only contains numbers (valid integer format) and spaces
 *
 * The function will create a linkedlist by using buildList function from toBuild. Then, it will apply fillGaps function to the linkedlist.
 * It will print out the original list, the expected result, and the actual result. If the actual result matches the
 * expected results. It will print out "the result matches expected!" message else it will print out "Error: the result does not match expected".
 *
 *
 *
 */
void testfillGaps(string toBuild, string expected);
void testemptyMiddle(string toBuild, string expected);
int main ()
{


   // test isMonotonicIncr function:

   cout<<"***********************************************"<<endl;
   cout<<" test isMonotonicIncr function: "<<endl<<endl;
   testMonotonicIncr("1 3 5",true);
   testMonotonicIncr("1 3 3 5",false);
   testMonotonicIncr("10 3 2",false);
   testMonotonicIncr("3 3 3",false);
   testMonotonicIncr("",true);
   testMonotonicIncr("5",true);
   testMonotonicIncr("1 3 5 10 2", false);
   testMonotonicIncr("-1 -3 -5 -10", false);
   testMonotonicIncr("-10 -5 -3 -1", true);
   testMonotonicIncr("-10 -5 -3 -1 -2", false);

   // test removeMax function:

   cout<<"***********************************************"<<endl;
   cout<<" test removeMax function: "<<endl<<endl;
   testRemoveMax("1 3 2","(1 2)");
   testRemoveMax("3","()");
   testRemoveMax("","()");
   testRemoveMax("1 10 12 3 12 10","(1 10 3 12 10)");
   testRemoveMax("3 3 3","(3 3)");
   testRemoveMax("-3 -43 -5","(-43 -5)"); //test with negative integers

   // test fillGaps function:

   cout<<"***********************************************"<<endl;
   cout<<" test fillGaps function: "<<endl<<endl;
   testfillGaps("1 5 6 8 11 12 15","(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15)");
   testfillGaps("5","(5)");
   testfillGaps("","()");
   testfillGaps("1 4 9 11","(1 2 3 4 5 6 7 8 9 10 11)");
   testfillGaps("-1 11","(-1 0 1 2 3 4 5 6 7 8 9 10 11)");


   testemptyMiddle("1 3 5","(1 5)");
   testemptyMiddle("1 3 4 5 6 8 10","(1 10)");

   return 0;

}





/*********************************************************
 * Utility function definitions
 *
 */
string listToString(ListType list) {

   string listString = "(";

   if (list == NULL) {
      listString += ")";
      return listString;
   }

   Node *p = list;
   while (p->next != NULL) {
      listString += to_string(p->data) + " ";
      p = p->next;
   }

   // print last one with no trailing space
   listString += to_string(p->data) + ")";

   return listString;

}   


ListType buildList(const string & listString) {

   ListType nums = NULL;

   istringstream istr(listString);  // similar to a Java Scanner over a String

   int num;

   if (istr >> num) { // is there one value there?
      nums = new Node(num);
   }
   else {
      return NULL;
   }

   Node *last = nums;

   while (istr >> num) { 
      last->next = new Node(num);
      last = last->next;
   }

   return nums;
}
/*********************************************************
 * Define functions here to test three functions
 *
 */

void testMonotonicIncr(string toBuild, bool expected){
   ListType testLinkedList = buildList(toBuild);
   cout<<"***************test start*********************"<<endl;
   cout<<"original list: "<<listToString(testLinkedList)<<endl;
   string expectedResult;
   if(expected==false){
      expectedResult = "false";
   }else{
      expectedResult = "true";
   }
   cout<<"expected result: "<<expectedResult<<endl;
   bool result = isMonotonicIncr(testLinkedList);
   string actualResult;
   if(result == false){
      actualResult = "false";
   }else{
      actualResult = "true";
   }
   cout<<"actual result: "<<actualResult<<endl;
   if(result == expected){
      cout<<"the result matches expected!"<<endl;
   }else{
      cout<<"Error: the result does not match expected"<<endl;
   }
   cout<<"***************test ends*********************"<<endl<<endl;
}


void testRemoveMax(string toBuild, string expected){
   ListType testLinkedList = buildList(toBuild);
   cout<<"***************test start*********************"<<endl;
   cout<<"original list: "<<listToString(testLinkedList)<<endl;
   cout<<"expected result: "<<expected<<endl;
   removeMax(testLinkedList);
   string result = listToString(testLinkedList);
   cout<<"actual result: "<<result<<endl;
   if(result == expected){
      cout<<"the result matches expected!"<<endl;
   }else{
      cout<<"Error: the result does not match expected"<<endl;
   }
   cout<<"***************test ends*********************"<<endl<<endl;
}

void testfillGaps(string toBuild, string expected){
   ListType testLinkedList = buildList(toBuild);
   cout<<"***************test start*********************"<<endl;
   cout<<"original list: "<<listToString(testLinkedList)<<endl;
   cout<<"expected result: "<<expected<<endl;
   fillGaps(testLinkedList);
   string result = listToString(testLinkedList);
   cout<<"actual result: "<<result<<endl;
   if(result == expected){
      cout<<"the result matches expected!"<<endl;
   }else{
      cout<<"Error: the result does not match expected"<<endl;
   }
   cout<<"***************test ends*********************"<<endl<<endl;
}

void testemptyMiddle(string toBuild, string expected){
   ListType testLinkedList = buildList(toBuild);
   cout<<"***************test start*********************"<<endl;
   cout<<"original list: "<<listToString(testLinkedList)<<endl;
   cout<<"expected result: "<<expected<<endl;
   emptyMiddle(testLinkedList);
   string result = listToString(testLinkedList);
   cout<<"actual result: "<<result<<endl;
   if(result == expected){
      cout<<"the result matches expected!"<<endl;
   }else{
      cout<<"Error: the result does not match expected"<<endl;
   }
   cout<<"***************test ends*********************"<<endl<<endl;
}
