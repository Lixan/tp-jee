package Injection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import Annotations.PreferredAnnotation;
import Annotations.QualifierAnnotation;
import Exceptions.MultiplePreferredImplementationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.xml.sax.SAXException;

public class ClassRetriever
{
    public Class<?> getClassToImplement(Field field) throws MultiplePreferredImplementationException, ClassNotFoundException,
            IOException, SAXException, ParserConfigurationException {
        Class<?> classToInstanciate = null;
        String fieldName= field.getType().getName();

        if(field.getType().isInterface()) {
            classToInstanciate = getClassFromInterfaceField(field, fieldName);
        }
        else {
            classToInstanciate = getClassFromClassField(fieldName);
        }

        return classToInstanciate;
    }

    private Class<?> getClassFromClassField(String fieldName) throws ClassNotFoundException{
        return Class.forName(fieldName);
    }

    private Class<?> getClassFromInterfaceField(Field field, String fieldName) throws MultiplePreferredImplementationException, ClassNotFoundException,
            IOException, SAXException, ParserConfigurationException {
        Class<?> classToInstanciate = null;
        if(field.isAnnotationPresent(QualifierAnnotation.class))
        {
            classToInstanciate = this.getClassImplementationFromQualifier(field);
        }
        else
        {
            //else check if preferred annotation is found
            classToInstanciate = this.getImplementationPreferred(fieldName);
        }
        return classToInstanciate;
    }

    private Class<?> getClassImplementationFromQualifier(Field field) throws ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        Annotation[] annotations = field.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof QualifierAnnotation){
                QualifierAnnotation qualifierAnnotation = (QualifierAnnotation) annotation;
                return getImplementationFromBeansXML(qualifierAnnotation.id());
            }
        }
        return null;
    }

    private Class<?> getImplementationFromBeansXML(String id) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File fXmlFile = new File(classLoader.getResource("beans.xml").getFile());

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("bean");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;
                if(eElement.getAttribute("id").equals(id))
                {
                    return Class.forName(eElement.getAttribute("class"));
                }
            }
        }

        return null;
    }

    private Class<?> getImplementationPreferred(String interfaceToImplement) throws MultiplePreferredImplementationException, ClassNotFoundException
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
