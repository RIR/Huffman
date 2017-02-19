package huffman.logic;

/**
 * Luokka joka tarjoaa Stringbuilderista tarvittavia toimintoja vastaava
 * toiminnot, luokan ainoa tehtävä on kerätä Input-luokassa luettavasta
 * tiedostosta tavut talteen ja muodostaa näistä merkkijono palautettavaksi.
 *
 * @author Raine Rantanen
 */
public class CharArrayBuilder {

    private char[] chars;
    private int index;
    private int size;

    /**
     * Luokan konstruktori joka alustaa uuden taulukon.
     */
    public CharArrayBuilder() {
        this.index = 0;
        this.size = 16;
        this.chars = new char[size];
    }

    /**
     * Metodi joka lisää taulukkoon uuden merkin.
     *
     * @param c Taulukkoon lisättävä merkki
     */
    public void add(char c) {
        chars[index] = c;
        index++;

        if (index == size) {
            expandArray();
        }
    }

    /* 
    Yksityinen apumetodi joka kasvattaa tarvittaessa taulukon kokoa.
     */
    private void expandArray() {
        size *= 2;

        char[] newChars = new char[size];

        for (int i = 0; i < chars.length; i++) {
            newChars[i] = chars[i];
        }
        chars = newChars;
    }

    /**
     * Metodi palauttaa merkkitaulukon.
     *
     * @return Merkkitaulukko
     */
    public char[] getChars() {
        if (index == 0) {
            return null;
        }
        /* Muunnetaan merkkitaulukko Stringiksi ja takaisin, jolloin myös ääkköset
        toimii. 
         */
        String bString = new String(chars, 0, index);
        return bString.toCharArray();
    }
}
