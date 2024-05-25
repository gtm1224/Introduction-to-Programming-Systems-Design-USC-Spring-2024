// Name:
// USC NetID:
// CS 455 PA5
// Spring 2024

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {

   ListType one = new Node("one",1,NULL);
   cout<<"original list: "<<endl;
   printList(one);
   cout<<endl;
   if(addFront(one,"two",2)){
      printList(one);
      cout<<endl;
   }else{
      cout<<"Error: please check addFront function!"<<endl;
   }

   addFront(one,"three",3);
   addFront(one,"four",4);

   cout<<"current list:"<<endl;
   cout<<"should be 4 3 2 1"<<endl;
   printList(one);
   cout<<endl;

   cout<<"test findKey: "<<endl;
   if(findKey(one,"three") && !findKey(one,"Four")){
      cout<<"findKey works fine"<<endl;
   }

   cout<<"test updateValue: "<<endl;
   updateValue(one,"three",5);
   cout<<"should be 4 5 2 1"<<endl;
   printList(one);

   cout<<"test getKeyValue"<<endl;
   cout<<getKeyValue(one,"three")<<endl;
   cout<<"should be 5!"<<endl;


   addFront(one,"five",5);
   addFront(one,"six",6);

   cout<<"print current list: "<<endl;
   printList(one);
   cout<<"remove Four"<<endl;
   cout<<"should not change anything!!!"<<endl;
   removeKey(one,"Four");
   printList(one);

   cout<<"test remove the first element"<<endl;
   removeKey(one,"six");
   printList(one);

   cout<<"test remove the last element"<<endl;
   removeKey(one,"one");
   printList(one);

   cout<<"test remove any middle element"<<endl;
   removeKey(one,"four");
   printList(one);
   cout<<endl;

   cout<<"test chain length"<<endl;
   cout<<chainLength(one)<<endl;
   cout<<"should be 3"<<endl;

   cout<<"test print pairs"<<endl;
   printPairs(one);
   return 0;
}
