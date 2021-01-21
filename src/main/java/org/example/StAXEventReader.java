package org.example;

import org.xml.sax.*;

import javax.xml.namespace.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class StAXEventReader {

    @SuppressWarnings("unused")
    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public List<Customer> getDataFromXML(String filename) throws FileNotFoundException, XMLStreamException,
        ParseException {

        List<Customer> data = new ArrayList<>();

        Customer customer = null;

        InputStream in = new FileInputStream(new File(filename));
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(in);

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();

                switch (elementName) {
                    case "customer":
                        customer = new Customer();
                        data.add(customer);
                        QName qName = new QName(Customer.ID);
                        String idAsString = startElement.getAttributeByName(qName).getValue();
                        customer.setId(Integer.parseInt(idAsString));
                        break;
                    case Customer.NAME:
                        customer.setName(reader.getElementText());
                        break;
                    case Customer.PHONE:
                        customer.setPhone(reader.getElementText());
                        break;
                    case Customer.AGE:
                        customer.setAge(Integer.parseInt(reader.getElementText()));
                        break;
                    case Customer.ABOUT:
                        customer.setAbout(reader.getElementText());
                        break;
                    case Customer.ACTIVE:
                        customer.setActive(Boolean.parseBoolean(reader.getElementText()));
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
