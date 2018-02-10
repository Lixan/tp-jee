package Api;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import Annotations.PreferredAnnotation;
import Exceptions.MultiplePreferredImplementationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.xml.sax.SAXException;

public class SPI
{
    public Class<?> getImplementationFromXML(String interfaceToImplement) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        File fXmlFile = new File("C:\\Users\\Clement\\IdeaProjects\\tp-jee\\jee_api\\src\\main\\resources\\mapping.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("mapping");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;
                if(eElement.getAttribute("Interface").equals(interfaceToImplement))
                {
                    return Class.forName(eElement.getAttribute("Implementation"));
                }
            }
        }

        return null;
    }

    public Class<?> getImplementationPreferred(String interfaceToImplement) throws MultiplePreferredImplementationException, ClassNotFoundException
    {
        Class<?> implementationClass = Class.forName(interfaceToImplement);
        Reflections reflections = new Reflections(implementationClass.getPackage());

        if(implementationClass.isInterface())
        {
            Set<?> subClasses = reflections.getSubTypesOf(implementationClass);
            int nbPreferred = 0;
            Iterator<?> iterator = subClasses.iterator();
            Class<?> preferredImplementation = null;

            while(iterator.hasNext())
            {
                Class<?> currentImplementation = ((Class<?>)iterator.next());

                if(currentImplementation.isAnnotationPresent(PreferredAnnotation.class))
                {
                    preferredImplementation = currentImplementation;
                    ++nbPreferred;
                }
            }

            if(nbPreferred == 1) {
                return preferredImplementation;
            }
            else if(nbPreferred > 1) {
                throw new MultiplePreferredImplementationException(); // Preferable d'informer l'utilisateur que de prendre le premeir preferred
            }
        }
        return null;
    }
}
