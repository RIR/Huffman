package huffman.logic;

/**
 * Luokka Luettavan tiedoston merkkien lukumäärien tallentamista varten.
 *
 * @author Raine Rantanen
 */
public class FrequencyCounter {

    private final int[] frequencies;

    /**
     * Luokan konstruktori, joka alustaa toistumiskertataulukon, taulukon kokona
     * laajennetun ASCII-merkistön koko eli 256 merkkiä.
     */
    public FrequencyCounter() {
        frequencies = new int[256];
    }

    /**
     * Metodi palauttaa toistumiskertataulukon
     *
     * @param chars Parametrina annettu merkkitaulukko, josta merkkien
     * toistuminen lasketaan.
     * @return Toistumiskertataulukko.
     */
    public int[] getFrequencies(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            frequencies[chars[i]]++;
        }

        return frequencies;
    }

}
