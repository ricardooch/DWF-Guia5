package sv.edu.udb.www.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class LiteraryGenreModel extends Connection{
    public LiteraryGenreModel() {
        super();
    }
    public List<LiteraryGenre> getLiteraryGenre() throws SQLException{
        this.connect();
        PreparedStatement ps = con.prepareStatement("select id, name from literarygenre ");;
                ResultSet result = ps.executeQuery();
        List<LiteraryGenre> list = new ArrayList<LiteraryGenre>();
        while(result.next()){
            LiteraryGenre literaryGenre = new LiteraryGenre();
            literaryGenre.setId(result.getInt("id"));
            literaryGenre.setName(result.getString("name"));
            list.add(literaryGenre);
        }
        this.close();
        return list;
    }
}