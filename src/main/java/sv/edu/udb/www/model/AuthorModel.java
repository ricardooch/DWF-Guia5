package sv.edu.udb.www.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorModel extends Connection{
    public AuthorModel(){
        super();
    }
    public List<Author> getAuthors() throws SQLException{
        this.connect();
        PreparedStatement ps
                = con.prepareStatement("select a.id as idAuthor, a.name, a.birth, a.number, l.name as literarygenre "
                        + "from authors a "
                        + "join literarygenre l "
                        + "on l.id = a.literarygenreid ");
        ResultSet result = ps.executeQuery();
        List<Author> list = new ArrayList<Author>();
        while(result.next()){
            Author author = new Author();
            author.setAuthorId(result.getInt("idAuthor"));
            author.setAuthorName(result.getString("name"));
            author.setAuthorBirth(result.getDate("birth"));
            author.setAuthorNumber(result.getString("number"));
            author.setLiteraryGenre(result.getString("literarygenre"));
            list.add(author);
        }
        this.close();
        return list;
    }
    public Map<String, Object> getAuthorById(int id) throws SQLException{
        Map<String, Object> authorData = new HashMap<>();
        this.connect();
        PreparedStatement ps
                = con.prepareStatement("select a.id as idAuthor, a.name, a.birth, a.number, l.name as literarygenre "
                + "from authors a "
                + "join literarygenre l "
                + "on l.id = a.literarygenreid "
                + "where a.id = ? ");
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            authorData.put("idAuthor", result.getInt("idAuthor"));
            authorData.put("authorName", result.getString("name"));
            authorData.put("authorBirth", result.getDate("birth"));
            authorData.put("authorNumber", result.getString("number"));
            authorData.put("literaryGenre", result.getString("literarygenre"));
        }

        this.close();
        return authorData;
    }
    public int findSameNameAuhor(String name) throws SQLException{
        this.connect();
        PreparedStatement ps
                = con.prepareStatement("select count(*) as total "
                + "from authors a "
                + " where name like ? ");
        ps.setString(1, name);
        ResultSet result = ps.executeQuery();
        int count=0;
        while(result.next()){
            count= result.getInt("total");
        }
        this.close();
        return count;
    }
    public void addAuthor(Author author) throws SQLException{
        this.connect();
        if(dataSource == null)
            throw new SQLException("Can't get data source");
        if(con == null)
            throw new SQLException("Can't get database connection");
        PreparedStatement ps
                = con.prepareStatement("insert into authors (name,birth,number,literarygenreid) "
                + " values (?,?,?,?)");
        ps.setString(1, author.getAuthorName());
        ps.setDate(2, new java.sql.Date(author.getAuthorBirth().getTime()));//convirtiendo fecha
        ps.setString(3, author.getAuthorNumber());
        ps.setString(4, author.getLiteraryGenre());
        ps.execute();
        this.close();
    }
    public void modifyAuthor(Author author) throws SQLException {
        this.connect();
        if (dataSource == null)
            throw new SQLException("Can't get data source");
        if (con == null)
            throw new SQLException("Can't get database connection");

        // Utiliza un UPDATE en lugar de un INSERT
        PreparedStatement ps = con.prepareStatement("UPDATE authors SET name=?, birth=?, number=?, literarygenreid=? WHERE id=?");
        ps.setString(1, author.getAuthorName());
        ps.setDate(2, new java.sql.Date(author.getAuthorBirth().getTime()));
        ps.setString(3, author.getAuthorNumber());
        ps.setString(4, author.getLiteraryGenre());
        ps.setInt(5, author.getAuthorId()); // ID del autor que deseas modificar

        ps.executeUpdate(); // Utiliza executeUpdate para realizar la actualización

        this.close();
    }
    public void delete(Author author) throws SQLException{
        this.connect();
        if(dataSource == null)
            throw new SQLException("Can't get data source");
        if(con == null)
            throw new SQLException("Can't get database connection");
        PreparedStatement ps
                = con.prepareStatement("delete from authors where id = ?");
        ps.setInt(1, author.getAuthorId());
        ps.execute();
        this.close();
    }
    //Usted debe implementar esta funcionalidad
    public boolean update(Author author){
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
