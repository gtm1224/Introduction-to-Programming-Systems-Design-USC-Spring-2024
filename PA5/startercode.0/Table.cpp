// Name: Tianming Guo
// USC NetID: guotianm
// CSCI 455 PA5
// Spring 2024

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

// create an empty table, i.e., one where numEntries() is 0
// (Underlying hash table is HASH_SIZE.)
Table::Table() {
   hashSize = HASH_SIZE;
   tableBukets = new ListType[hashSize]();
   numEntry = 0;
}

// create an empty table, i.e., one where numEntries() is 0
// such that the underlying hash table is hSize
Table::Table(unsigned int hSize) {
   hashSize = hSize;
   tableBukets = new ListType[hashSize]();
   numEntry = 0;
}

// returns the address of the value that goes with this key
// or NULL if key is not present.
//   Thus, this method can be used to either lookup the value or
//   update the value that goes with this key.
int * Table::lookup(const string &key) {
   int buket = hashCode(key);
   return getKeyValue(tableBukets[buket],key);
}

// remove a pair given its key
// return false iff key wasn't present
//         (and no change made to table)
bool Table::remove(const string &key) {
   int buket = hashCode(key);
   if(removeKey( tableBukets[buket],key)){
      numEntry--;
      return true;
   }
   return false;
}

// insert a new pair into the table
// return false iff this key was already present
//         (and no change made to table)
bool Table::insert(const string &key, int value) {
   int buket = hashCode(key);
   if(addFront(tableBukets[buket],key,value)){
      numEntry ++;
      return true;
   }
   return false;

}

// return the number of entries in the table
int Table::numEntries() const {
   return numEntry;
}

// print out all the entries in the table, one per line, to cout
// Sample output:
//   zhou 283
//   sam 84
//   babs 99
void Table::printAll() const {
   for(int i=0;i<hashSize;i++){
      printPairs(tableBukets[i]);
   }
}

// hashStats: for diagnostic purposes only
// prints out info to let us know if we're getting a good distribution
// of values in the hash table.
// Sample output from this function
//   number of buckets: 997
//   number of entries: 10
//   number of non-empty buckets: 9
//   longest chain: 2
void Table::hashStats(ostream &out) const {
   out<<"number of buckets: "<<hashSize<<endl;
   out<<"number of entries: "<<numEntry<<endl;
   out<<"number of non-empty buckets: "<<nonEmptyBuckets()<<endl;
   out<<"longest chain: "<<longestChain()<<endl;

}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string &word) const {

   // Note: calls a std library hash function for string (it uses the good hash
   //   algorithm for strings that we discussed in lecture).
   return hash<string>()(word) % hashSize;

}


// add definitions for your private methods here

// return the size of the longest chain
int Table::longestChain() const{
   int longest = 0;
   for(int i=0;i<hashSize;i++){
      int curLength = chainLength(tableBukets[i]);
      if(curLength>longest){
         longest = curLength;
      }
   }
   return longest;
}

// return the number of used buckets
int Table::nonEmptyBuckets() const{
   int numNonEmptyBuckets = 0;
   for(int i=0;i<hashSize;i++){
      if(tableBukets[i]!=NULL){
         numNonEmptyBuckets++;
      }
   }
   return numNonEmptyBuckets;
}