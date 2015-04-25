package grapher;

import javax.swing.event.*;

/**
 * @author Gregory Prosper
 * Class that creates Data Object for Graph
 */
public class Data {

	private int data1;
	private int data2;
	private ChangeListener listener;

	/**
	 * Creates Data Object with graph 1 and 2 starting at 0
	 */
	public Data() {
		this.data1 = 0;
		this.data2 = 0;
	}

	/**
	 * Changes data for graph 1
	 * @param i number for 1st bar on graph
	 */
	public void changeData1(int i) {
		this.data1 = i;
		ChangeEvent event = new ChangeEvent(this);
		this.listener.stateChanged(event);
	}

	/**
	 * Gets number for first bar
	 * @return number for first bar
	 */
	public int getData1() {
		return this.data1;
	}

	/**
	 * Changes data for bar 2
	 * @param i number for 2st bar on graph
	 */
	public void changeData2(int i) {
		this.data2 = i;
		ChangeEvent event = new ChangeEvent(this);
		this.listener.stateChanged(event);
	}

	/**
	 * Gets number for second bar
	 * @return number for second bar
	 */
	public int getData2() {
		return this.data2;
	}

	/**
	 * Adds ChangeListener to Data Object
	 * @param listener ChangeListner to be added to Data Object
	 */
	public void addListener(ChangeListener listener) {
		this.listener = listener;
	}
}
