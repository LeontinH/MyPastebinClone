package MyPastebinClone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table(name = "pasteLibrary")
public class PasteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pasteLibrary_generator")
    @SequenceGenerator(name = "paste_generator", allocationSize = 1)
    @Column(name = "paste_id")
    private Long paste_id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;
    @Lob
    @Column(name = "text")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Long getId() {return paste_id;}

    public void setId(Long paste_id) {this.paste_id = paste_id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public PasteModel() {}

    public PasteModel(Long paste_id, String title, String text, String author) {
        this.paste_id = paste_id;
        this.title = title;
        this.author = author;
        this.text = text;
    }
}
