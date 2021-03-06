package importexport;

import enums.BookGenre;
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

                    String title = row.getCell(CellType.TITLE).getStringCellValue();
                    book.setTitle(title);

                    Date date = row.getCell(CellType.RELEAS).getDateCellValue();
                    book.setPublishDate(date);

                    Double isbn = row.getCell(CellType.ISBN).getNumericCellValue();
                    book.setIsbn(isbn.longValue());

                    String type = row .getCell(CellType.TYPE).getStringCellValue();
                    book.setGenre(BookGenre.valueOf(type));

                    Double pages = row.getCell(CellType.PAGES).getNumericCellValue();
                    book.setNumberOfPages(pages.longValue());

                    String text = row.getCell(CellType.DESCRIPTION).getStringCellValue();
                    book.setDescription(text);

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
