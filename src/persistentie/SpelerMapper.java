package persistentie;

import domein.Speler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpelerMapper extends Mapper
{

    /**
     *
     * @param gebruikersnaam
     */
    public Speler geefSpeler(String gebruikersnaam) throws SQLException
    {
        ResultSet rs = selectQuery("SELECT * FROM Speler WHERE gebruikersnaam = '" + gebruikersnaam + "'");
        return maakSpeler(rs);
    }

    public List<Speler> geefSpelers() throws SQLException
    {
        List<Speler> spelers = new ArrayList<>();

        ResultSet rs = this.selectQuery("SELECT * FROM Speler");

        while (rs.next())
        {
            spelers.add(maakSpeler(rs));
        }

        return spelers;

    }


    private Speler maakSpeler(ResultSet rs) throws SQLException
    {
        
        int id = rs.getInt("id");
        String naam = rs.getString("naam");
        String voornaam = rs.getString("voornaam");
        String gebruikersnaam = rs.getString("gebruikersnaam");
        String wachtwoord = rs.getString("wachtwoord");

        return new Speler(id, naam, voornaam, gebruikersnaam, wachtwoord);
    }

}
