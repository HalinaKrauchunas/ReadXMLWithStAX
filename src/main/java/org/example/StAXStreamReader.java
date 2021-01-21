package org.example;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class StAXStreamReader {

    @SuppressWarnings("unused")
    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public List<Customer> getDataFromXML(String filename) throws FileNotFoundException, XMLStreamException,
        ParseException {

        List<Customer> data = new ArrayList<>();

        Customer customer = null;

        InputStream in = new FileInputStream(new File(filename));
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(in);

        int eventType;
        while (reader.hasNext()) {
            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {
                String elementName = reader.getName().toString();

                switch (elementName) {
                    case "customer":
                        customer = new Customer();
                        data.add(customer);
                        customer.setId(Integer.parseInt(reader.getAttributeValue("", Customer.ID)));
                        break;
                    case Customer.NAME:
                        customer.setName(reader.getElementText());
                        break;
                    case Customer.JOINED:
                        DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
                        customer.setJoined(dateFormat.parse(reader.getElementText()));
                        break;
                }
            }
        }
        return data;
    }
}
