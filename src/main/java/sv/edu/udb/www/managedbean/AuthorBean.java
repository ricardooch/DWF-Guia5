package sv.edu.udb.www.managedbean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import sv.edu.udb.www.model.Author;
import sv.edu.udb.www.model.AuthorModel;
import sv.edu.udb.www.model.LiteraryGenreModel;
import java.io.Serializable;
import java.sql.SQLException;
@ManagedBean
@SessionScoped
public class AuthorBean implements Serializable {
    private Author author;
    private AuthorModel authorModel = new AuthorModel();
    private LiteraryGenreModel literaryGenreModel = new LiteraryGenreModel();
    public AuthorBean() {
        this.author = new Author();
    }
    public void addAuthor() throws SQLException{
        if(authorModel.findSameNameAuhor(author.getAuthorName())>0)
        {
            FacesContext.getCurrentInstance().addMessage("errorMessage", new
                    FacesMessage(FacesMessage.SEVERITY_INFO, "Ya existe un autor con ese nombre", "Author"));
        }else{
            authorModel.addAuthor(author);
            FacesContext.getCurrentInstance().addMessage("successMessage", new
                    FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado"));
            author = new Author();
        }
    }
    public void modifyAuthor() throws SQLException{
        if(author.getAuthorId() == 0)
        {
            FacesContext.getCurrentInstance().addMessage("errorMessage", new
                    FacesMessage(FacesMessage.SEVERITY_INFO, "No ha seleccionado ningún autor", "Author"));
        }else if (authorModel.findSameNameAuhor(author.getAuthorName())>0)
        {
            FacesContext.getCurrentInstance().addMessage("errorMessage", new
                    FacesMessage(FacesMessage.SEVERITY_INFO, "Ya existe un autor con ese nombre", "Author"));
        }
        else {
        authorModel.modifyAuthor(author);
        FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Exitosamente", "Modificado"));
        }
    }
    public void deleteAuthor(Author author) throws SQLException{
        authorModel.delete(author);
    }
    public void countAuthor(String name) throws SQLException{
        if(authorModel.findSameNameAuhor(name)>0){
            FacesContext.getCurrentInstance().addMessage("errorMessage", new
                    FacesMessage(FacesMessage.SEVERITY_INFO, "Este autor ya existe", "Author"));
        }
    }
    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }
    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
    /**
     * @return the authorModel
     */
    public AuthorModel getAuthorModel() {
        return authorModel;
    }
    /**
     * @param authorModel the authorModel to set
     */
    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }
    /**
     * @return the literaryGenreModel
     */
    public LiteraryGenreModel getLiteraryGenreModel() {
        return literaryGenreModel;
    }
    /**
     * @param literaryGenreModel the literaryGenreModel to set
     */
    public void setLiteraryGenreModel(LiteraryGenreModel literaryGenreModel) {
        this.literaryGenreModel = literaryGenreModel;
    }
}