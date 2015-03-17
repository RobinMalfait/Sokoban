/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author Demian
 */
public class AdminApplicatie
{
    public void start(DomeinController dc, Scanner input)
    {
        System.out.printf("%s%n- %s%n- %s%n- %s%n", "Wat wilt u doen?", "Een nieuw spel maken", "Een spelbord toevoegen aan een bestaand spel", "Stoppen");
    }
}
