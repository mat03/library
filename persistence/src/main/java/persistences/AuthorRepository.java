package persistences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.AuthorException;
import models.Author;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepository implements IAuthorRepository {
    private static final String JSON_FILEPATH = "./persistence/src/main/resources/database/authors.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public AuthorRepository() {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void add(Author author) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Author> authors = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Author>>(){});

        author.setId((long) authors.size());
        authors.add(author);
        OBJECT_MAPPER.writeValue(jsonFile, authors);
    }

    @Override
    public void remove(Long id) throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        for (Author a: authors) {
            if(a.getId().equals(id)){
                a.setRemove(true);
                return;
            }
        }
        throw new AuthorException("Can't remove ID " + id);
    }

    @Override
    public Author get(Long id) throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        for (Author author : authors) {
            if(author.getId().equals(id))
            {
                return author;
            }
        }
        throw new AuthorException("ID not exist" + id);
    }

    @Override
    public void update(Author author) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Author> authors = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Author>>(){});

        for (Author a : authors) {
            if (a.getId().equals(author.getId())) {
                a.setName(author.getName());
                a.setSurname(author.getSurname());
                a.setBirthPlace(author.getBirthPlace());
                a.setRemove(author.isRemove());

                OBJECT_MAPPER.writeValue(jsonFile,authors);
                return;
            }
        }
        throw new AuthorException("Update Fails ");
    }

    @Override
    public List<Author> getAll() throws IOException {
        List<Author> authors = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Author>>(){});

        return authors.stream().filter(author -> !author.isRemove()).collect(Collectors.toList());
    }
}
