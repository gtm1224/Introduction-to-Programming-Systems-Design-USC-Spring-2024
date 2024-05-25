// ecListFuncs.h
// CSCI 455 Spring 2024, Extra Credit assignment
// You do not need to modify or submit this file.

#ifndef EC_LIST_FUNCS_H
#define EC_LIST_FUNCS_H

#include <iostream>
#include <string>


//*************************************************************************
// Node type used for lists
struct Node {
   int data;
   Node *next;

   Node(int item);
   Node(int item, Node *n);

};


typedef Node * ListType;


//*************************************************************************
// Functions you need to write for this assignment:
//   (implementations go in ecListFuncs.cpp)



/*
 * isMonotonicIncr
 *    Returns true iff the list of elements forms a strictly monotonically
 *    increasing sequence of ints.  I.e, the values are in increasing order 
 *    with no duplicates.  A list with one or zero elements should be considered
 *    monotonically increasing.
 *    Some examples:
 *      list:                    isMonotonicIncr(list):
 *      (1 3 5)                  true
 *      (1 3 3 5)                false
 *      (10 3 2)                 false
 *      (3 3 3)                  false
 *      ()                       true
 *      (5)                      true
 *      (1 3 5 10 2)             false
 * PRE: list a well-formed list
 */
bool isMonotonicIncr(ListType list);


/*
 * removeMax
 *    Removes the maximum valued item in the list.  
 *    If there are multiple elements in the list with the maximum value, 
 *    removes only the first one appearing in the list.  For an empty list, 
 *    nothing is removed.
 *    Some examples:
 *      list before call:         list after removeMax(list):
 *      (1 3 2)                   (1 2)
 *      (3)                       ()
 *      ()                        ()
 *      (1 10 12 3 12 10)         (1 10 3 12 10)
 *      (3 3 3)                   (3 3)
 * PRE: list is a well-formed list
 */
void removeMax(ListType & list);

/*
 * fillGaps
 *    Given a strictly monotonically increasing sequence of ints, fills
 *    in the missing elements of the list so that the resulting list
 *    has every int from the smallest to the largest from the original list in
 *    increasing order.
 *    Examples:
 *      list before call:         list after fillGaps(list):
 *      (1 5 6 8 11 12 15)        (1 2 3 4 5 6 7 8 9 10 11 12 13 14 15)
 *      (5)                       (5)
 *      ()                        ()
 *   REQUIREMENT: You may not rebuild the list from scratch, but must reuse
 *      nodes from the original list such that they keep their original data values
 *      in the updated list.
 *   NOTE: the pointer to the first node is passed by value here since it is never
 *      changed by the function.
 * PRE: list is a well-formed list and isMonotonicIncr(list) is true
 */
void fillGaps(ListType list);

void emptyMiddle(ListType & list);

//*************************************************************************

#endif
