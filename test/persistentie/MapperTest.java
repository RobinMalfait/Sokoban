/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Spel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robin
 */
public class MapperTest
{
    
    private MapperImpl mapper;

    @Before
    public void setUp() throws SQLException
    {
        this.mapper = new MapperImpl();
        
        this.mapper.updateQuery("CREATE TABLE `test` (id INT, name varchar(255))");
        
        this.mapper.insertQuery("INSERT INTO `test` VALUES(?, ?);", "1", "Robin");
        this.mapper.insertQuery("INSERT INTO `test` VALUES(?, ?);", "2", "Bob");
    }
    
    @After
    public void tearDown() throws SQLException
    {
        this.mapper.deleteQuery("DROP TABLE `test`;");
    }

    @Test
    public void it_should_select_everything_without_params() throws Exception
    {
        ResultSet RS = this.mapper.selectQuery("SELECT * FROM `test`");
        
        List<String[]> results = this.mapper.getData(RS);
        
        assertEquals(2, results.size());
    }
    
    @Test
    public void it_should_select_with_where_clause() throws Exception
    {
        assertEquals("Bob", this.getName(2));
    }
    
    @Test
    public void it_should_insert_a_row() throws Exception
    {
        this.mapper.insertQuery("INSERT INTO `test` VALUES(4, \"Bram\");");
        
        assertEquals("Bram", this.getName(4));
    }
    
    @Test
    public void it_should_insert_a_row_using_parameters() throws Exception
    {
        this.mapper.insertQuery("INSERT INTO `test` VALUES(?, ?);", "3", "Demian");
        
        assertEquals("Demian", this.getName(3));
    }
    
    @Test
    public void it_should_update_a_row() throws Exception
    {
        this.mapper.updateQuery("UPDATE `test` SET name = \"Robin Malfait\" where id = 1");
        
        assertEquals("Robin Malfait", this.getName(1));
    }
    
    @Test
    public void it_should_update_a_row_using_parameters() throws Exception
    {
        this.mapper.updateQuery("UPDATE `test` SET name = ? where id = ?", "Bob Naert", 2);
        
        assertEquals("Bob Naert", this.getName(2));
    }
    
    @Test
    public void it_should_delete_all_rows() throws Exception
    {
        this.mapper.deleteQuery("DELETE FROM `test`");
        
        ResultSet RS = this.mapper.selectQuery("SELECT * FROM `test`");
        
        assertEquals(0, this.mapper.getData(RS).size());        
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void it_should_delete_a_specific_rows() throws Exception
    {
        this.mapper.deleteQuery("DELETE FROM `test` WHERE id = ?", 1);
        
        this.getName(1);
    }
    
    private String getName(int id) throws SQLException
    {
        ResultSet RS = this.mapper.selectQuery("SELECT * FROM `test` WHERE id = ?", id);
        
        List<String[]> results = this.mapper.getData(RS);
        
        return results.get(0)[1];
    }

    public class MapperImpl extends Mapper
    {
        public List<String[]> getData(ResultSet rs) throws SQLException
        {
            List<String[]> data = new ArrayList<>();

            while (rs.next())
            {
                String result[] = new String[2];
                
                result[0] = rs.getString("id");
                result[1] = rs.getString("name");

                data.add(result);
            }

            return data;
        }
    }
    
}
