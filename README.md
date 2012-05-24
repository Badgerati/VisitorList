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

So here, we create a new key of `vertex1` with an initial link to `vertex2`, and we state that `vertex2` has
not yet been visited to by `vertex1` with a boolean value of `false`.

In most cases, it will be vital to get the first non-visited link from some key. Therefore, assuming we have
the VisitorList created just above, we can use:

	int nextVertex = visitorlist.getNext(vertex1);

This will return `vertex2`, because `vertex2` has not yet been visited to by `vertex1`. If we wish to change
the visitation details of a specific link from non-visited to visited, we can use:

	visitorlist.visited(vertex1, vertex2, true);
	
Which will set the visitation details of `vertex2` to have now been visited to by `vertex1`.

Adding a new link to a key is also simple, we can just:

	int vertex3 = 3;
	visitorlist.add(vertex1, vertex3, false);

Now, we still have one key within the `visitorlist`: `vertex1`. But now, this key has two links: `vertex2` and
`vertex3`. Except, `vertex2` has already been visited to by `vertex1`, yet `vertex3` has not. Therefore, if we
were to call,

	int nextVertex = visitorlist.getNext(vertex1);

again on `vertex1`, this time around we would yield the link of `vertex3`. Now let us say that `vertex3` has
already been visited to. If we call `getNext(vertex1)` again then a value of `null` is returned as all links
for `vertex1` have been visited to by it.

Obviously here, we have a problem will `int = null`, therefore, if this is ever the case, you could use either:

	if (visitorlist.getNext(vertex1) != null) {
		nextVertex = visitorlist.getNext(vertex1);
	}

or

	if (!visitorlist.allVisited(vertex1)) {
		nextVertex = visitorlist.getNext(vertex1);
	}

These are only two possible workarounds, but of course, there are others. One final one could be:

	Object obj = visitorlist.getNext(vertex1);
	if (obj != null) {
		nextVertex = (Integer)obj;
	}

It should also be clear as to what it is `allVisited(vertex1)` is doing. This method returns `true` if every link
for `vertex1` has been visited to by it, false otherwise.


Improvements
------------