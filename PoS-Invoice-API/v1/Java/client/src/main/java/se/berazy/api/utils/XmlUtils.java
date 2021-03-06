package se.berazy.api.utils;

/**
 * Berazy Bookkeeping API Client
 *
 * @author    <a href="mailto:johan@berazy.se">Johan Sall Larsson</a>
 * @version   1.0.0
 *
 * MIT LICENSE
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * XML helper.
 *
 * @author <a href="mailto:johan@berazy.se">Johan Sall Larsson</a>
 * @since  1.0.0
 */
@SuppressWarnings("unchecked")
public class XmlUtils {

    public static <T> String serialize(JAXBElement<T> object) 
            throws JAXBException, IOException 
    {
        Class<?> clazz = object.getValue().getClass();
        JAXBContext context = JAXBContext.newInstance(clazz.getPackage().getName());
        Marshaller marshaller = context.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);
        sw.close();
        return sw.toString();
    }
    
    public static <TResponse> TResponse deserialize(InputStream stream, Class<TResponse> clazz) 
            throws JAXBException 
    {
        JAXBContext context = JAXBContext.newInstance(clazz.getPackage().getName());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<TResponse> result = (JAXBElement<TResponse>) unmarshaller.unmarshal(stream);
        TResponse response = (TResponse) result.getValue();
        return response;
    }
    
}
