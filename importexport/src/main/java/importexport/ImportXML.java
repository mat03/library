package importexport;

import models.Book;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

public class ImportXML {
    private static final String FILE_NAME = "./importexport/src/main/resources/import/books_sample.xlsx";

    public ImportXML() {
    }

    public List<Book> getBookList() {
        List<Book> books = new ArrayList<Book>();
        try {
            Workbook workbook = WorkbookFactory.create(new File(FILE_NAME));
            int sheetNumber = workbook.getNumberOfSheets();


            for(int i = 0; i < sheetNumber; ++i) {
                Sheet sheet = workbook.getSheetAt(i);

                int lastRowNumber = sheet.getLastRowNum();

                for(int j = 1; j <= lastRowNumber; ++j) {
                    Book book = new Book();

                    Row row = sheet.getRow(j);

                    String title = row.getCell(0).getStringCellValue();
                    book.setTitle(title);

                    Date date = row.getCell(1).getDateCellValue();
                    book.setPublishDate(date);

                    books.add(book);
                }
            }
            return books;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return books;
    }
}
