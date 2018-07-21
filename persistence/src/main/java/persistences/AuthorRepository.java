package persistences;

import exceptions.AuthorException;
import models.Author;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements IAuthorRepository {
    private static final String JSON_FILEPATH = new String("./persistence/src/main/resources/database/authors.json");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public void add(Author author) throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        author.setId(new Long((authors.size())));
        authors.add(author);
        OBJECT_MAPPER.writeValue(new File(JSON_FILEPATH),authors);
    }

    public void remove(Long id) throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        for (Author a: authors) {
            if(a.getId().equals(id)){
                a.setRemove(true);
                return;
            }
        }
        throw new AuthorException("Can't remove ID" + id);
    }

    public Author get(Long id) throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        for (int i = 0; i < authors.size(); ++i)
        {
            if(authors.get(i).getId() == id)
            {
                return authors.get(i);
            }
        }

        throw new AuthorException("ID not exist");
    }

    public void update(Author author) throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        for (Author a : authors) {
            if (a.getId().equals(author.getId())) {
                a.setName(author.getName());
                a.setSurname(author.getSurname());
                a.setBirthPlace(author.getBirthPlace());
                a.setRemove(author.isRemove());

                OBJECT_MAPPER.writeValue(new File(JSON_FILEPATH),authors);
                return;
            }
        }
        throw new AuthorException("Update Fails ");
    }

    public List<Author> getAll() throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});
        ArrayList<Author> res;

        res = (ArrayList<Author>) authors.stream().filter(author -> author.isRemove() == false);
        return res;
    }
}
