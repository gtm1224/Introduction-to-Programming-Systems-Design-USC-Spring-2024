// Name: Tianming Guo
// USC NetID: guotianm
// CSCI 455 PA5
// Spring 2024



//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>
  
// Define the Node struct
struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.


// addFront will add a node with key and value at the first position of a linkedlist implemented with Node struct
// it will return true if the insertion is successful i.e. the key has not been find in the linked list. Otherwise,
// return false.
// pre: theKey is a non empty string and theValue is an integer.
bool addFront(ListType &root,const std::string theKey, int theValue);

// lookup for the key, return true if the kind is present else false.
// pre: theKey is non empty string.
bool findKey(ListType root, const std::string theKey);

// it will print out the values of the linkedlist i.e. all the integer values if the root is
// NULL then print nothing.
void printList(ListType root);

// it will update the value to newValue if the key exist
// it will do nothing is the key does not exist
void updateValue(ListType &root,const std::string theKey,int newValue);

// it will return a int pointer to the value of the key.It will return NULL if the key does not exist.
int * getKeyValue(ListType root,const std::string theKey);

// remove the node from the linkedlist if success return true, if the key does not exist or list is empty
// return False
bool removeKey(ListType &root,std::string theKey);

// return the number of nodes in the linkedlist
int chainLength(ListType root);

// print the key value pairs using format "key value" for the entire list separated in newline
// Sample output:
//   zhou 283
//   sam 84
//   babs 99
void printPairs(ListType root);

// keep the following line at the end of the file
#endif
