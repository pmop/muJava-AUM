/**
 * Copyright (C) 2015  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

 
package openjava.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  
 * @author Wuzhi Xu, Date: Dec 12, 2006
 * 
 * @author Nan Li, updated
 * 
 */
public class Node {

	private Object obj;
	private List<Edge> outGoingEdges;
	
	/**
	 * the constructor can only be accessed in the package. 
	 * if construct an node, please use Graph.createNode. A node should not exist without a graph
	 * @param obj any object. However, the object is better to printable, such as primitive objects, string
	 */
	Node(Object obj)
	{
		this.obj = obj;
		outGoingEdges = new ArrayList<Edge>();
	}

	
	/**
	 * 
	 * @return the object that the node represents
	 */
	public Object getObject()
	{
		return obj;
	}
	
	/**
	 * @return string that the object can output
	 */
	public String toString()
	{
		return obj.toString(); 
	}
	
	/**
	 * override the object equals. It compares the objects of two nodes. 
	 * 
	 * @param n
	 * @return
	 */
	public boolean equals(Node n)
	{
		if(obj.equals(n.getObject()))
			return true;
		else 
			return false;
	}
	
	/**
	 * This method should not be accessed from outside of the package
	 * 
	 * @param e
	 */
	void addOutGoing(Edge e)
	{
		outGoingEdges.add(e);
	}
	
	/**
	 * This method should not be accessed from outside of the package
	 * The out going edge is removed for this node
	 * @param e
	 */
	void removeOutGoing(Edge e)
	{
		outGoingEdges.remove(e);
	}
	
	/**
	 * 
	 * @return the number of outgoing edges
	 */
	public int sizeOfOutEdges()
	{
		return outGoingEdges.size();
	}
	
	/**
	 * The node object should not be mutated from out of this package. So, it return an iterator of 
	 * outgoing edges.
	 * 
	 * @return an iterator that contains outgoing edges
	 */
	public Iterator<Edge> getOutGoingIterator()
	{
		return outGoingEdges.iterator();
	}
	/**
	 * 
	 * @return a list of outgoing edges
	 */
	
	public List<Edge> getOutGoingEdges(){
		return outGoingEdges;
	}
}
