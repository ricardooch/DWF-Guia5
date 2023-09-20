package sv.edu.udb.www.model;
public class LiteraryGenre {
    int id;
    String name;
    public LiteraryGenre() {
    }
    public LiteraryGenre(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
