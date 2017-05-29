# Stable-Priority-Queue

-Implemented Min priority queue. 

-The queue supports all the operations i.e. Insertion, Extraction of Element (Minimum), Find the Element (Minimum). 

-The stability of the priority queue is ensured using the counter variable and a priority queue array which stores the insertion order of the elements and while extracting an element, both of these instance members are used to implement the FIFO (First In First Out) property
of the queue i.e. if two elements have the same value, then the element inserted first is extracted first from the queue. 

-The Emma report shows the 100% code coverage. 

-Since the initial implementation of the project, no bugs were found in the analysis of the code using Find Bugs analysis tool.

-To run the Junit test cases, following two jars must be added in the classpath:
 junit.jar
 hamcrest-core.jar
