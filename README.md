VisitorList Data Structure
==========================

A data structure for storing a set of keys, each with a set of links; keeping in memory whether those
links have been visited to by their parent key.


Description
-----------
I designed this data-structure initially when I needed something to help me deal with the triangulation
of polygons.


Usage
-----
To create and initialise a new VisitorList, we use:

	VisitorList<Integer, Integer> visitorlist = new VisitorList<Integer, Integer>();

as we would with any List, Map or Set within Java. Once the VisitorList has been created, we can
give the `visitorlist` a new key to store links by:

	int vertex1 = 1;
	visitorlist.add(vertex1);
	
and this will add `vertex1` to the list of keys contained within the `visitorlist`. It will also initialise
an empty List of Links and visitation information. If we wish to add a new key, with an initial link and
set its visitation details, we use:

	int vertex1 = 1;
	int vertex2 = 2;
	visitorlist.add(vertex1, vertex2, false);

So here, we create a new key of `vertex1` with and initial link to `vertex2`, and we state that `vertex2` has
not yet been visited to by `vertex1` with `false`.

In most cases, it will be vital to get the first non-visited link from some key. Therefore, assuming we have
the VisitorList created just above, we can use:

	int nextVertex = visitorlist.getNext(vertex1);

This will return `vertex2`, because `vertex2` has not yet been visited to by `vertex1`. If we wish to change
the visitation details of a specific link from non-visited to visited, we can use:

	visitorlist.visited(vertex1, vertex2, true);
	
Which will set the visitation details of `vertex2` to have now been visited to by `vertex1`.


Improvements
------------