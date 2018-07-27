package persistences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.BorrowerException;
import models.Borrower;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowerRepository implements IBorrowerRepository {
    private static final String JSON_FILEPATH = "./persistence/src/main/resources/database/borrowers.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public BorrowerRepository() {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void add(Borrower borrower) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Borrower> borrowers = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Borrower>>(){});
        long id = borrowers.size();

        borrower.setId(++id);
        borrowers.add(borrower);
        OBJECT_MAPPER.writeValue(jsonFile,borrowers);
    }

    @Override
    public void remove(Long id) throws IOException {
        List<Borrower> borrowers = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Borrower>>(){});

        for (Borrower b:borrowers) {
            if(b.getId().equals(id)) {
                b.setRemove(true);
                return;
            }
        }
        throw  new BorrowerException("Can't remove ID " + id);
    }

    @Override
    public void update(Borrower borrower) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Borrower> borrowers = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Borrower>>(){});

        for (Borrower b : borrowers) {
            if(b.getId().equals(borrower.getId())) {
                b.setName(borrower.getName());
                b.setSurname(borrower.getSurname());
                b.setAddress(borrower.getAddress());
                b.setPhone(borrower.getPhone());
                b.setEmail(borrower.getEmail());
                b.setRemove(borrower.isRemove());

                OBJECT_MAPPER.writeValue(jsonFile, borrowers);
                return;
            }
        }
        throw new BorrowerException("Update fails");

    }

    @Override
    public Borrower get(Long id) throws IOException {
        List<Borrower> borrowers = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Borrower>>(){});

        for (Borrower b: borrowers) {
            if(b.getId().equals(id)) {
                return b;
            }
        }
        throw  new BorrowerException("ID not exist" + id);
    }

    @Override
    public List<Borrower> getAll() throws IOException {
        List<Borrower> borrowers = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Borrower>>(){});

        return borrowers.stream().filter(b -> !b.isRemove()).collect(Collectors.toList());
    }
}
