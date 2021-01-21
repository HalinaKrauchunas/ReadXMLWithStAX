package org.example;

import java.io.FileNotFoundException;
import java.text.*;
import java.util.List;

import javax.xml.stream.XMLStreamException;


public class ReadXMLWithStAXStream {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParseException {
		final String DATADIR = "D:\\StAXReader\\src\\main\\resources\\";
		StAXEventReader reader = new StAXEventReader();
		List<Customer> data = reader.getDataFromXML(DATADIR + "customers.xml");
		for (Customer customer : data) {
			System.out.println(customer);
		}
		System.out.println("Data returned: " + data.size() + " customers");
	}

}
