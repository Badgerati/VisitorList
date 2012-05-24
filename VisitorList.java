package sky.engine.util;

import java.util.ArrayList;

/**
 * A data structure for storing a set of keys, each with a set of links; keeping in memory whether
 * those links have been visited to by their parent key.
 * 
 * @author Matthew Kelly (Badgerati).
 * @version 1.0.0.
 * @since 24 May 2012.
 *
 * @param <K> - Class type for the keys.
 * @param <L> - Class type for the links of the keys.
 */
public class VisitorList<K, L>
{
	/**
	 * Keys for this Visitor List.
	 */
	private ArrayList<K> Keys = null;
	
	
	/**
	 * The links this Visitor List's keys points to.
	 */
	private ArrayList<ArrayList<L>> Links = null;
	
	
	/**
	 * Have those links been visited already?
	 */
	private ArrayList<ArrayList<Boolean>> Visited = null;
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Create new instance of a Visitor List.
	 */
	public VisitorList()
	{
		Keys = new ArrayList<K>();
		Links = new ArrayList<ArrayList<L>>();
		Visited = new ArrayList<ArrayList<Boolean>>();
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns whether the given key contains the given link in its Links.
	 * 
	 * @param key - Key to find the link in.
	 * @param link - Link to attempt to locate.
	 * @return True if found, false otherwise.
	 */
	public boolean contains(K key, L link)
	{
		int index = Keys.indexOf(key);
		return Links.get(index).contains(link);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Add a new key. Returning whether it was successful.
	 * 
	 * @param key - Key to add.
	 * @return True if successful, false otherwise.
	 */
	public boolean add(K key)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			Keys.add(key);
			
			ArrayList<L> templinks = new ArrayList<L>();
			Links.add(templinks);
			
			ArrayList<Boolean> tempvisited = new ArrayList<Boolean>();
			Visited.add(tempvisited);
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Adds a new link to or with a given key. If the key does not exist, this will
	 * create the key with the given link and visited state. Returns whether it was
	 * successful.
	 * 
	 * @param key - Key to add.
	 * @param link - Link to add.
	 * @param visited - Current visited state of the link.
	 * @return True if successful, false otherwise.
	 */
	public boolean add(K key, L link, boolean visited)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			Keys.add(key);
			
			ArrayList<L> templinks = new ArrayList<L>();
			templinks.add(link);
			Links.add(templinks);
			
			ArrayList<Boolean> tempvisited = new ArrayList<Boolean>();
			tempvisited.add(visited);
			Visited.add(tempvisited);
			return true;
		}
		else
		{
			int indexLink = Links.get(indexKey).indexOf(link);
			
			if (indexLink == -1)
			{
				Links.get(indexKey).add(link);
				Visited.get(indexKey).add(visited);
				return true;
			}
			else
			{
				Visited.get(indexKey).set(indexLink, visited);
				return true;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Removes the given link from the given key's links, returning whether it was successful.
	 * 
	 * @param key - Key to find the link in.
	 * @param link - Link to remove.
	 * @return True if successful, false otherwise.
	 */
	public boolean remove(K key, L link)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return false;
		}
		else
		{
			int indexLink = Links.get(indexKey).indexOf(link);
			
			if (indexLink == -1)
			{
				return false;
			}
			else
			{
				Links.get(indexKey).remove(indexLink);
				Visited.get(indexKey).remove(indexLink);
				return true;
			}
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Returns whether the given link has been visited by the given key, if this Visitor List does
	 * not contain the given link/key then false is returned instead of null.
	 * 
	 * @param key - Key to find the link in.
	 * @param link - Link to find visited state for.
	 * @return True if link visited, false otherwise.
	 */
	public boolean isVisited(K key, L link)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return false;
		}
		else
		{
			int indexLink = Links.get(indexKey).indexOf(link);
			
			if (indexLink == -1)
			{
				return false;
			}
			else
			{
				return Visited.get(indexKey).get(indexLink);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Set the given link in the given key to the given visited state, returning
	 * whether it was successful.
	 * 
	 * @param key - Key to find the link in.
	 * @param link - Link to set to the given visited state.
	 * @param visited - Visited state to set link to.
	 * @return True if successful, false otherwise.
	 */
	public boolean setVisited(K key, L link, boolean visited)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return false;
		}
		else
		{
			int indexLink = Links.get(indexKey).indexOf(link);
			
			if (indexLink == -1)
			{
				return false;
			}
			else
			{
				Visited.get(indexKey).set(indexLink, visited);
				return true;
			}
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Returns the number of keys this Visitor List has.
	 * 
	 * @return Total number of keys.
	 */
	public int size()
	{
		return Keys.size();
	}
	
	
	
	
	
	
	
	
	/**
	 * Returns the number of links the given key has, -1 if the key does not exist.
	 * 
	 * @param key - Key to get links from.
	 * @return Total number of links key contains.
	 */
	public int size(K key)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
			return -1;
		else
			return Links.get(indexKey).size();
	}
	
	
	
	
	
	
	
	
	/**
	 * Clear the keys and links of this Visitor List. Returning whether is was successful.
	 * 
	 * @return True if successful, false otherwise.
	 */
	public boolean clear()
	{
		Keys.clear();
		Links.clear();
		Visited.clear();
		return true;
	}
	
	
	
	
	
	
	
	
	/**
	 * Clear the links of the given key. Returning whether is was successful.
	 * 
	 * @param key - Key to clear links from.
	 * @return True if successful, false otherwise.
	 */
	public boolean clear(K key)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey != -1)
		{
			Links.get(indexKey).clear();
			Visited.get(indexKey).clear();
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the next non-visited link of the given key, returns null if there is not one.
	 * 
	 * @param key - Key to get next non-visited link from.
	 * @return First non-visited link, or null otherwise.
	 */
	public L getNext(K key)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return null;
		}
		else
		{
			for (int i = 0; i < Links.get(indexKey).size(); i++)
			{
				if (!Visited.get(indexKey).get(i))
					return Links.get(indexKey).get(i);
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the link at the given index of the given key, returns null if out-of-bounds.
	 * 
	 * @param key - Key to get the link from.
	 * @param index - Index to find link at.
	 * @return Link at given index, null otherwise.
	 */
	public L getLink(K key, int index)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return null;
		}
		else
		{
			if (index >= Links.get(indexKey).size() || index < 0)
				return null;
			
			return Links.get(indexKey).get(index);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the index of the given link of the given key, returning -1 if it does not exist.
	 * 
	 * @param key - Key to get index of link from.
	 * @param link - Link to get index of.
	 * @return Index of the link, -1 if it does not exist.
	 */
	public int indexOf(K key, L link)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return -1;
		}
		else
		{
			return Links.get(indexKey).indexOf(link);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the index of the given key, returning -1 if it does not exist.
	 * 
	 * @param key - Key to get index of.
	 * @return Index of the key, -1 if it does not exist.
	 */
	public int indexOf(K key)
	{
		return Keys.indexOf(key);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the Links of the given key.
	 * 
	 * @param key - Key to get links from.
	 * @return List of links, null otherwise.
	 */
	public ArrayList<L> getLinks(K key)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return null;
		}
		else
		{
			return new ArrayList<L>(Links.get(indexKey));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns whether all links have been visited by the given key, returning false
	 * if the key does not exist.
	 * 
	 * @param key - Key to test all links of.
	 * @return True if all are visited, false otherwise.
	 */
	public boolean allVisited(K key)
	{
		int indexKey = Keys.indexOf(key);
		
		if (indexKey == -1)
		{
			return false;
		}
		else
		{
			for (int i = 0; i < Links.get(indexKey).size(); i++)
			{
				if (!Visited.get(indexKey).get(i))
					return false;
			}
			
			return true;
		}
	}
	
	

}
