// Name: Tianming Guo
// USC NetID: guotianm
// CSCI 455 PA5
// Spring 2024


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;
// Node constructor which takes a key and theValue the next will be set to NULL
// pre: key is a string and theValue is an integer number
Node::Node(const std::string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

// Node constructor which takes a key, theValue and a Node as next
// pre: key is a string and theValue is an integer number
Node::Node(const std::string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below


// addFront will add a node with key and value at the first position of a linkedlist implemented with Node struct
// it will return true if the insertion is successful i.e. the key has not been find in the linked list. Otherwise,
// return false.
// pre: theKey is a non empty string and theValue is an integer.
bool addFront(ListType &root,const std::string theKey, int theValue){
   ListType p = root;
   if(!findKey(p,theKey)){
      root = new Node(theKey,theValue,root);
      return true;
   }

   return false;


}

// lookup for the key, return true if the kind is present else false.
// pre: theKey is non empty string.
bool findKey(ListType root,std::string theKey){

   while(root!= nullptr){
      if (root->key == theKey){
         return true;
      }
      root = root->next;
   }

   return false;

}

// it will print out the values of the linkedlist i.e. all the integer values if the root is
// NULL then print nothing.
void printList(ListType root){

   while(root!= nullptr){
      cout<< root->value << " ";
      root = root->next;
   }
}

// it will update the value to newValue if the key exist
// it will do nothing is the key does not exist
void updateValue(ListType &root,const std::string theKey,int newValue){
   ListType p = root;
   while(p!= NULL && p->key!=theKey){
      p=p->next;
   }
   if(p!= NULL && p->key==theKey){
      p->value = newValue;
   }
}

// it will return a int pointer to the value of the key.It will return NULL if the key does not exist.
int * getKeyValue(ListType root,const std::string theKey){
   while(root!=NULL){
      if(root->key == theKey){
         return &(root->value);
      }
      root = root->next;
   }
   return NULL;
}

// remove the node from the linkedlist if success return true, if the key does not exist or list is empty
// return False
bool removeKey(ListType &root,std::string theKey){
   if(root == NULL){
      return false;
   }
   //if the root happens to be the key then make the root be the next node then delete the root,
   if(root->key == theKey){
      ListType p = root;
      root = root->next;
      delete p;
      return true;
   }
   ListType p = root;
   // move p to the next until the Node before the Node we want to remove
   while(p!= NULL && p->next!= NULL && p->next->key!=theKey){
      p = p->next;
   }
   // if it is already the last one and does not match the key then return false
   if(p == NULL || p->next == NULL){
      return false;
   }

   ListType remove = p->next;
   p->next = remove->next;
   delete remove;
   return true;

}

// return the number of nodes in the linkedlist
int chainLength(ListType root){
   int size = 0;
   while(root!=NULL){
      root=root->next;
      size++;
   }
   return size;
}

// print the key value pairs using format "key value" for the entire list separated in newline
// Sample output:
//   zhou 283
//   sam 84
//   babs 99
void printPairs(ListType root){
   while(root!=NULL){
      cout<<root->key<<" "<<root->value<<endl;
      root = root->next;
   }
}