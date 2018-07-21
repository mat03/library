package persistences;

import models.Borrower;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BorrowerRepository implements IBorrowerRepository {
    private static final String JSON_FILEPATH = "./persistence/src/main/resources/database/borrowers.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void add(Borrower borrower) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Borrower> authors = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Borrower>>(){});

        borrower.setId((long) authors.size());
        authors.add(borrower);
        OBJECT_MAPPER.writeValue(jsonFile, authors);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Borrower borrower) {

    }

    @Override
    public Borrower get(Long id) {
        return null;
    }

    @Override
    public List<Borrower> getAll() {
        return null;
    }
}
