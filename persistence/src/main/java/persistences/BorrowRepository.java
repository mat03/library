package persistences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.BorrowException;
import models.Borrow;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowRepository implements IBorrowRepository{
    private static final String JSON_FILEPATH = "./persistence/src/main/resources/database/borrows.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public BorrowRepository() {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void add(Borrow borrow) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Borrow> borrows = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Borrow>>(){});
        long id = borrows.size();
        borrow.setId(id++);
        borrows.add(borrow);
        OBJECT_MAPPER.writeValue(jsonFile, borrows);
    }

    @Override
    public void remove(Long id) throws IOException {
        List<Borrow> borrows = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Borrow>>(){});

        for (Borrow borrow : borrows) {
            if(borrow.getId().equals(id)) {
                borrow.setRemove(true);
                return;
            }
        }
        throw new BorrowException("Can't remove ID " + id);
    }

    @Override
    public void update(Borrow borrow) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Borrow> borrows = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Borrow>>(){});

        for (Borrow b: borrows) {
            if(b.getId().equals(borrow.getId())) {
                b.setRentalDate(borrow.getRentalDate());
                b.setBookId(borrow.getBookId());
                b.setBorrowerId(borrow.getBorrowerId());
                b.setRemove(borrow.isRemove());

                OBJECT_MAPPER.writeValue(jsonFile, borrows);
                return;
            }
        }
        throw  new BorrowException("Update fails ");
    }

    @Override
    public Borrow get(Long id) throws IOException {
        List<Borrow> borrows = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Borrow>>(){});

        for (Borrow borrow : borrows) {
            if(borrow.getId().equals(id)) {
                return borrow;
            }
        }
        throw new BorrowException("ID not exist " + id);
    }

    @Override
    public List<Borrow> getAll() throws IOException {
        List<Borrow> borrows = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Borrow>>(){});

        return borrows.stream().filter(borrow -> !borrow.isRemove()).collect(Collectors.toList());
    }
}
