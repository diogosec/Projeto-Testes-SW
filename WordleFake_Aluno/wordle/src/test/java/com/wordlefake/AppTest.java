package com.wordlefake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.*;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.nio.file.Files;
import java.io.File;
import java.nio.charset.Charset;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private App wordle;

    public AppTest(){
    }

    @Test
    public void main()
    {
        assertTrue( true );
    }

    @Test
    public void SorteioPalavra()
    {
        File wordFile = new File("TXT//br-utf8.txt");
        assertTrue(wordle.SorteioPalavra(wordFile));
    }

    @Test
    public void SelectFile()
    {
        assertTrue(wordle.SelectFile(new Random().nextInt(20)+2));
    }

    @Test
    public void removeAccents()
    {
        String S1 = "café";
        String S2 = "cafe";
        assertEquals(wordle.removeAccents(S1), S2);
    }

    @Test
    public void WordsEquals()
    {
        byte[] array = new byte[new Random().nextInt(20)+2];
        new Random().nextBytes(array);
        String geradorString = new String(array, Charset.forName("UTF-8"));
        String geradorString2 = geradorString;
        int tamanhoString = geradorString.length();
        assertTrue(wordle.WordsEquals(geradorString,geradorString2,tamanhoString));
        assertTrue( true );
    }

    @Test
    public void TamanhoCerto()
    {
        byte[] array = new byte[new Random().nextInt(20)+2];
        new Random().nextBytes(array);
        String geradorString = new String(array, Charset.forName("UTF-8"));
        int tamanhoString = geradorString.length();
        assertTrue( wordle.TamanhoCerto(tamanhoString, geradorString) );
    }

    @Test
    public void ExistePalavra()
    {
        assertFalse(wordle.ExistePalavra("AIOFHE"));;
    }
}
