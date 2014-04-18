package action;

import java.io.File;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
public class CreateXMLFileInDB {
    
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/";
    
    /**
     * collection Should be the name of the collection to access
     * filename Should be the name of the file to read and store in the collection
     * @return 
     */
   void Add_xml(String collection,String filename) throws Exception {
       
       
        final String driver = "org.exist.xmldb.DatabaseImpl";
        
        
       
        
        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = null;
        XMLResource res = null;
        try { 
            col = getOrCreateCollection(collection);
            
            // create new XMLResource; an id will be assigned to the new resource
            res = (XMLResource)col.createResource(null, "XMLResource");
            
            
            //validation part
            publishervalidator v1 = new publishervalidator();
            boolean validation_status=v1.validateXMLSchema("Publisher_validator.xsd", filename);
            if(validation_status==true) System.out.println("file Sucessfully validated\nadding to repository now....");
            else System.out.println("Sorry Validation failed\n file can't be added to the given collenction.");
            
            File f = new File(filename);
            if(!f.canRead()) {
                System.out.println("cannot read file " + filename);
                return;
            }
            
            res.setContent(f);
            System.out.print("storing document " + res.getId() + "...");
            col.storeResource(res);
            System.out.println("ok.");
        } finally {
            //dont forget to cleanup
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources();
                	} 
                catch(XMLDBException xe) {xe.printStackTrace();}
                catch(Exception e){ }
            }
            
            if(col != null) {
                try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }
        }
    }
    
    private static Collection getOrCreateCollection(String collectionUri) throws 
XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(String collectionUri, int 
pathSegmentOffset) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(URI + collectionUri);
        if(col == null) {
            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            String pathSegments[] = collectionUri.split("/");
            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }
                Collection start = DatabaseManager.getCollection(URI + path);
                if(start == null) {
                    //collection does not exist, so create
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parent = DatabaseManager.getCollection(URI + parentPath);
                    CollectionManagementService mgt = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    col.close();
                    parent.close();
                } else {
                    start.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }
}