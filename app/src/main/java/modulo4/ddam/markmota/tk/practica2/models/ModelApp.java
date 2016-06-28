package modulo4.ddam.markmota.tk.practica2.models;

/**
 * Created by markmota on 6/27/16.
 */
public class ModelApp {
    public int id=0;
    public String name;
    public String description;
    public String developer;
    public int image;
    public String installed;
    public String edited=null;
    public String updated=null;
    public int status=0;

    public ModelApp(int id, String name, String description, String developer, int image, String installed, String edited, String updated, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.image = image;
        this.installed = installed;
        this.edited = edited;
        this.updated = updated;
        this.status = status;
    }
}
