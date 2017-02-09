package huffman.huffman.logic;

import huffman.huffman.domain.FrequencyCounter;
import huffman.huffman.domain.HuffmanTree;
import huffman.huffman.domain.Node;
import huffman.huffman.io.Input;
import huffman.huffman.io.Output;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Raine Rantanen
 * 
 * Luokka tiedoston pakkaamiseen. 
 */
public class Compress {

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Input input;
    private Output output;
    private FrequencyCounter frequencyCounter=new FrequencyCounter();
    private int readBits;
    private int writtenBits;
    private int[] frequencies;

    /**
     * Luokan konstruktori joka saa parametreina tiedostojen nimet pakattavalle ja pakatulle tiedostolle.
     * @param inputFile Pakattava tiedoston nimi
     * @param outputFile Pakatun tiedosto nimi
     * @throws FileNotFoundException Palauttaa virheen jos pakattavaa tiedostoa ei löydy.
     */
    public Compress(File inputFile, File outputFile) throws FileNotFoundException {      
        input=new Input(inputFile);
          
        //  Luetaan merkit taulukkoon         
        char[] chars=input.readChars();
        
        // Lasketaan eri merkkien toistumiset
        this.frequencies=frequencyCounter.getFrequencies(chars); 
        
        // Lasketaan luettujen bittien määrä
        this.readBits=chars.length*8;
        
        // Luodaan Huffmanin puu
        HuffmanTree huffmanTree=new HuffmanTree(frequencies);
        
        Node root = huffmanTree.create();
        
        
        // jonka jälkeen kirjoitetaan tieto pakattavaan tiedostoon.
        
    }

    
    /**
     * Metodi palauttaa luettujen bittien määrän
     * @return Luetut bitit 
     */
    public int getReadBits() {
        return readBits;
    }

    
    /**
     * Metodi palauttaa kirjoitettujen bittien määrän 
     * @return Kirjoitetut bitit
     */
    public int getWrittenBits() {
        return writtenBits;
    }

    
    
    
    
    
}
