package saaj_jaxb;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.*;
import javax.xml.soap.*;

public class Demo {

    public static void main(String[] args) throws Exception {
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message = mf.createMessage();
        SOAPBody body = message.getSOAPBody();

        Foo foo = new Foo();
        foo.setBar("Hello World");

        JAXBContext jc = JAXBContext.newInstance(Foo.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_ENCODING, "UTF-8" );
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                true);
        marshaller.marshal(foo, body);

        message.saveChanges();
        //OutputStream outputStream = new FileOutputStream( "item.xml" );
        message.writeTo(System.out);
    }

}