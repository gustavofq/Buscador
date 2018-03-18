/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscadordepalabras;

import java.awt.Color;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.beans.binding.ListBinding;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author maquinola
 */
public class BuscadorDePalabras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         File dir = new File(args[0]);
         System.out.println("La ruta es: " + args[0] + " \n");
         
        /*
            dir = directorio donde se encuentran todos pdf que se desean buscar.
            es el primer elemento del string args.
        */
        
        String[] archivos = dir.list();
        System.out.println("Numero de archivos: " + archivos.length );
        /*
            crea un array con todos los archivos pdf dentro del directorio indicado
            con "dir" (el cual se paso por parametro.).
        */
        int numero = 1;
        java.util.List<String> nombreArchivo = new LinkedList();
        if(archivos != null){
             for (String nombre : archivos) {
                 System.out.println(numero + " de " + archivos.length);
                 numero++;
                 PDDocument doc = new PDDocument();
                 /*
                 crea un nuvo documento
                  */
                 File existente = new File(dir.getPath() + "\\" + nombre);
                 if(doc != null){
                     doc.close();
                 }    
                 doc = PDDocument.load(existente);//abre un documento pdf existente
                 PDFTextStripper pdfStripper = new PDFTextStripper();
                 String text = pdfStripper.getText(doc);
                 
                 if(text.contains(args[1])){ 
                     nombreArchivo.add(nombre);
                 }   
                 doc.close();
             }
        
    }else{
            System.out.println("No se encontro ningun archivo");
    }
        Iterator it = nombreArchivo.iterator();
        while(it.hasNext()){
            System.out.print(" ----------------------------------------------------------------------" + "\n");
            System.out.print("La palabra " + args[1] + " puede que este en " + it.next().toString() + "\n");
        }
             
    //System.out.print("La palabra " + args[1] + " puede que este en " + nombreArchivo);
}
}
