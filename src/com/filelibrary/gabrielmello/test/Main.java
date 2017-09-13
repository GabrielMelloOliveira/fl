package com.filelibrary.gabrielmello.test;

import com.filelibrary.gabrielmello.csv.ManageCSV;
import com.filelibrary.gabrielmello.doc.ManageDOC;
import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.TypeFile;
import com.filelibrary.gabrielmello.json.ManageJSON;
import com.filelibrary.gabrielmello.pdf.ManagePDF;
import com.filelibrary.gabrielmello.txt.ManageTXT;
import com.filelibrary.gabrielmello.xls.ManageXLS;
import com.filelibrary.gabrielmello.xlsx.ManageXLSX;
import com.filelibrary.gabrielmello.xml.ManageXML;
import com.filelibrary.gabrielmello.xps.ManageXPS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class Main {

    public static void main(String[] args) {
        
        List<User> users = new ArrayList();

        //                 id           nome                          email              idade
        users.add(new User(1, "Gabriel Mello de Oliveira", "gabrielmello3011@gmail.com", 19));
        users.add(new User(2, "Mello de Oliveira", "m@gmail.com", 17));
        users.add(new User(3, "Carlos", "carlos@gmail.com", 20));
        users.add(new User(4, "Ana", "ana@gmail.com", 29));
        users.add(new User(5, "Paula", "paula@gmail.com", 16));
        users.add(new User(6, "Cristina Mello de Oliveira", "cristina@gmail.com", 25));
        users.add(new User(7, "Michele Mello de Oliveira", "michele@gmail.com", 21));

        List<Product> products = new ArrayList();

        //                        nome     valor       tipo
        products.add(new Product("Xbox",   2000.0, "Eletronico"));
        products.add(new Product("Play 4", 3000.0, "Eletronico"));
        products.add(new Product("Pão",    10.0,   "Alimento"));

        new ManageXLS().toObject(User.class);
        
//        List<User> user = (ArrayList<User>) new ManageCSV().toObject(User.class);
//        
//        for (User user1 : user) {
//            System.out.println(user1);
//        }
        
//        ArrayList<User> object = (ArrayList<User>) new ManageXML().toObject();
//
//        for (User user : object) {
//            System.out.println(user);
//        }
        
    }

}
